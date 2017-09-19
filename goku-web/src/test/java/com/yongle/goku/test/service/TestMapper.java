package com.yongle.goku.test.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongle.goku.demo.mapper.DemoMapper;
import com.yongle.goku.demo.service.DemoService;
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

    @Resource
    private DemoService demoService;

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

    @Test
    public void testTransactional() {
        demoService.a(true);
        demoService.b();
        demoService.b();
//        int a = 4;//100
//        int a = 4;//转为2进制是32位
//        for (int i = 0; i < 100; i++) {
//            //循环左移7位
//            a = a << 1 | a >>> (3 - 1);//这里注意右移用的是无符号右移
//            System.out.println(Integer.toBinaryString(a));//正确答案是0x242F87EB
//        }
    }
}
