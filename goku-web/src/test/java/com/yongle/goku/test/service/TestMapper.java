package com.yongle.goku.test.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongle.goku.demo.mapper.DemoMapper;
import com.yongle.goku.model.Demo;
import com.yongle.goku.model.DemoExample;
import com.yongle.goku.user.mapper.UserInfoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weinh on 2016/5/7.
 */
public class TestMapper extends JUnitServiceBase {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private DemoMapper demoMapper;

    @Test
    public void testMapper() {
        PageHelper.startPage(1, 10);
        DemoExample example = new DemoExample();
        example.setOrderByClause("id desc");
        List<Demo> demos = demoMapper.selectByExample(example);
        log.info(JSONObject.toJSONString(demos));
        PageInfo<Demo> pageInfo = new PageInfo<>(demos);
        log.info(pageInfo.getTotal()+"");
    }
}
