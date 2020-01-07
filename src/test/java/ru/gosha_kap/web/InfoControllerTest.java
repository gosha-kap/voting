package ru.gosha_kap.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntVoteHistory;
import ru.gosha_kap.util.JpaUtil;
import ru.gosha_kap.util.MenuUtil;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.gosha_kap.TestData.*;

@Transactional
class InfoControllerTest extends AbstractTestController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    public InfoControllerTest() {
        super("/");
    }

    @BeforeEach
    void setUp() {
        cacheManager.getCache("history").clear();
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }

    @Test
    void getAllToday() throws Exception {


            perform(get("/rest/info/today"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(List.of(MENU_15, MENU_13, MENU_14)), RestrntFullInfo.class));

    }

    @Test
    void getHistoryForOne() throws Exception {
        perform(get("/rest/info/history/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAllForOne(List.of(MENU_15, MENU_11, MENU_7, MENU_3)), RestrntFullInfo.class));
    }

    @Test
    void getHistoryVoting() throws Exception {
        perform(get("/rest/info/history"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getVoteHistory(MENUS_HISTORY), RestrntVoteHistory.class));
    }

    @Test
    void getPopularsMenus() throws Exception {
        perform(get("/rest/info/top10"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPopularsMenuForOne() throws Exception {
        perform(get("/rest/info/top10/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(
                        List.of(MENU_2, MENU_6, MENU_10)), RestrntFullInfo.class));
    }
}
