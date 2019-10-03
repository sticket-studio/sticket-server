package com.ec.sticket.controllers.normal;

import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.StickService;
import com.ec.sticket.services.TitleService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.services.mapping.UserQuestService;
import com.ec.sticket.util.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

@Slf4j
public class UserControllerTest {

    @Mock
    private UserService userService;
    private StickService stickService;
    private QuestService questService;
    private UserQuestService userQuestService;
    private TitleService titleService;
    private JwtParser jwtParser;

    private UserController userController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userController = new UserController(userService
                , stickService
                , questService
                , userQuestService
                , titleService, jwtParser);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findAllUsers() throws Exception {
        assertEquals(2, userController.findAllUsers().size(), 0);
    }

    @Test
    public void findUserById() throws Exception {
    }

    @Test
    public void saveUser() throws Exception {
    }
}