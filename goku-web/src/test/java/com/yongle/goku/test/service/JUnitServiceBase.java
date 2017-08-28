package com.yongle.goku.test.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by weinh on 2016/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-service.xml",
        "classpath:/spring/applicationContext-nosql.xml"})
public class JUnitServiceBase {
    final Logger log = LoggerFactory.getLogger(getClass());
}
