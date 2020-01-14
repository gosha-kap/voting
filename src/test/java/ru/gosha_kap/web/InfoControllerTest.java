package ru.gosha_kap.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntVoteHistory;
import ru.gosha_kap.util.JpaUtil;
import ru.gosha_kap.util.MenuUtil;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.gosha_kap.TestData.*;
import static ru.gosha_kap.TestUtil.readFromJson;

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


            perform(get("/rest/info"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(List.of(MENU_15, MENU_13, MENU_14)), RestrntFullInfo.class));

    }

    @Test
    void getHistoryForOne() throws Exception {
        perform(get("/rest/info/votehist/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getVoteHistory(List.of(MENU_15, MENU_11, MENU_7, MENU_3)), RestrntVoteHistory.class));
    }

    @Test
    void getHistoryVoting() throws Exception {
        perform(get("/rest/info/votehist"))
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

    @Test
    void getOne() throws  Exception{
       ResultActions actions = perform(get("/rest/info/menu/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
       RestrntFullInfo menuInfo = readFromJson(actions,RestrntFullInfo.class);
       assertEquals(menuInfo.getMenu_id(),10);

    }
}
