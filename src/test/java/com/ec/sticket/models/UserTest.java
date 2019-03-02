package com.ec.sticket.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getId(){
        Integer idValue = 4;

        user.setId(idValue);

        assertEquals(idValue,user.getId());
    }

    @After
    public void tearDown() throws Exception {
    }
}