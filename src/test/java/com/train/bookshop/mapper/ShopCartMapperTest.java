package com.train.bookshop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.train.bookshop.dao.mapper.ShopCartMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/application-context.xml" })
public class ShopCartMapperTest {

    @Autowired
    private ShopCartMapper shopCartMapper;

    @Test
    public void queryByCartTest() {
        System.out.println(shopCartMapper.selectByCartId("DBDD22F2B629169A23A16F9619B75E62"));
    }

    @Test
    public void queryByConditionsTest() {
        System.out.println(shopCartMapper.selectByConditions("DBDD22F2B629169A23A16F9619B75E62", 1213l));
    }

    @Test
    public void deleteByConditionsTest() {
        System.out.println(shopCartMapper.deleteByConditions("DBDD22F2B629169A23A16F9619B75E62", 1213l));
    }
}
