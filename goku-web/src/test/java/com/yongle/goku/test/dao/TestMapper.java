package com.yongle.goku.test.dao;

import com.yongle.goku.model.UserInfo;
import com.yongle.goku.model.UserInfoExample;
import com.yongle.goku.user.mapper.UserInfoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by weinh on 2016/5/7.
 */
public class TestMapper extends JUnitMapperBase {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Test
    public void testMapper() {
        List<UserInfo> list = userInfoMapper.selectByExample(new UserInfoExample());
        System.out.println(list);
    }
}
