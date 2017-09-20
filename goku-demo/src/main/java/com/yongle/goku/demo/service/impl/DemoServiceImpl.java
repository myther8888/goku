package com.yongle.goku.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongle.goku.demo.mapper.DemoMapper;
import com.yongle.goku.demo.service.DemoService;
import com.yongle.goku.model.demo.Demo;
import com.yongle.goku.model.demo.DemoExample;
import com.yongle.goku.model.vo.Paging;
import com.yongle.goku.model.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ResultVO<List<Demo>> getDemos() {
        DemoExample example = new DemoExample();
        PageHelper.startPage(1, 10);
        List<Demo> demos = demoMapper.selectByExample(example);
        PageInfo<Demo> pageInfo = new PageInfo<>(demos);

        Paging paging = new Paging();
        paging.setLimit(10);
        paging.setStart(1);
        paging.setTotal(pageInfo.getTotal());

        ResultVO<List<Demo>> vo = new ResultVO<>();
        vo.setData(demos);
        vo.setPaging(paging);
        return vo;
    }

}
