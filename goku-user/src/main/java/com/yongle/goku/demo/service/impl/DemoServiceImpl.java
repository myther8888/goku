package com.yongle.goku.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.demo.mapper.DemoMapper;
import com.yongle.goku.demo.service.DemoService;
import com.yongle.goku.model.Demo;
import com.yongle.goku.model.DemoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 类 名 称：DemoServiceImpl.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月19日
 */
@Service
public class DemoServiceImpl implements DemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    DemoMapper demoMapper;

    //    @Transactional
    @Override
    public void a(boolean isException) {
        Demo demo1 = new Demo();
        demo1.setName("1");
        demoMapper.insert(demo1);
//        DemoService demoService = (DemoService) AopContext.currentProxy();
//        demoService.b();
//        b();
        if (isException) {
//            throw new Error("a出错了");
        }
    }

//    @Transactional
    @Override
    public void b() {
//        Demo demo1 = new Demo();
//        demo1.setName("2");
        DemoExample example = new DemoExample();
        logger.info(JSONObject.toJSONString(demoMapper.selectByExample(example)));
    }
}
