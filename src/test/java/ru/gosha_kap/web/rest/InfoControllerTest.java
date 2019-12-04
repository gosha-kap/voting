package ru.gosha_kap.web.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntVoteHistory;
import ru.gosha_kap.util.JpaUtil;
import ru.gosha_kap.util.MenuUtil;

import javax.annotation.PostConstruct;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.gosha_kap.TestData.*;


@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
class InfoControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
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
        mockMvc.perform(get("/info/today"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(List.of(MENU_13, MENU_14, MENU_15)), RestrntFullInfo.class));

    }

    @Test
    void getHistoryForOne() throws Exception {
        mockMvc.perform(get("/info/history/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAllForOne(List.of(MENU_15, MENU_11, MENU_7, MENU_3)), RestrntFullInfo.class));
    }

    @Test
    void getHistoryVoting() throws Exception {
        mockMvc.perform(get("/info/history"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getVoteHistory(MENUS_HISTORY), RestrntVoteHistory.class));
    }

    @Test
    void getPopularsMenus() throws Exception {
        mockMvc.perform(get("/info/top10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(
                        List.of(MENU_1, MENU_2, MENU_5, MENU_6, MENU_9, MENU_10, MENU_11, MENU_12))
                        , RestrntFullInfo.class));
    }

    @Test
    void getPopularsMenuForOne() throws Exception {
        mockMvc.perform(get("/info/top10/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MenuUtil.getAll(
                        List.of(MENU_2, MENU_6, MENU_10)), RestrntFullInfo.class));
    }
}
