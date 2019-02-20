package com.ec.sticket.controllers;

import com.ec.sticket.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://github.com/HeechanYang/spring5-recipe-app/blob/upload-images/src/test/java/guru/springframework/controllers/ImageControllerTest.java를 참고하자


@Slf4j
public class MainControllerTest {

    private MainController mainController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mainController = new MainController();

        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void getUserTest() throws Exception {
        User user = mainController.getUser();

        if(user != null){
            log.info("user.name : " + user.getName());
            log.info("user.email : " + user.getEmail());
        }else{
            log.info("user is null");
        }
// 다음과 같이 given - when - verify 형식으로 짜자
//        //given
//        RecipeCommand command = new RecipeCommand();
//        command.setId(1L);
//
//        when(recipeService.findCommandById(anyLong())).thenReturn(command);
//
//        //when
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