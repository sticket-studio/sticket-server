package com.ec.sticket.controllers;

import com.ec.sticket.controllers.normal.AssetController;
import com.ec.sticket.services.AssetService;
import com.ec.sticket.util.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// https://github.com/HeechanYang/spring5-recipe-app/blob/upload-images/src/test/java/guru/springframework/controllers/ImageControllerTest.java를 참고하자


@Slf4j
public class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @Mock
    JwtParser jwtParser;

    @Mock
    private AssetController assetController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        assetController = new AssetController(assetService, jwtParser);

        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();
    }

    @Test
    public void getUserTest() throws Exception {
//        User user = assetController.getUser();
//
//        if(user != null){
//            log.info("user.name : " + user.getName());
//            log.info("user.email : " + user.getEmail());
//        }else{
//            log.info("user is null");
//        }
// 다음과 같이 given - when - verify 형식으로 짜자
        //given
//        verify(assetController.findAllAssets().size());

//        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
//        mockMvc.perform(get("/recipe/1/image"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("recipe"));
//
//        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @After
    public void tearDown() throws Exception {
        log.info("MainControllerTest::tearDown()");
    }
}