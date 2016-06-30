package com.train.bookshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.train.bookshop.dto.ManagerEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/application-context.xml" })
public class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Test
    public void saveManagerTest() {
        String loginName = "guest";
        String name = "guest";
        String password = "guest123";

        managerService.saveManager(loginName, name, password);
    }

    @Test
    public void getManagerTest() {
        System.out.println(managerService.getManagerByLoginName("guest"));
    }

    @Test
    public void verifyPasswordTest() {
        ManagerEntity managerEntity = managerService.getManagerByLoginName("guest");
        managerService.verifyPassword(managerEntity, "guest123");
    }
}
