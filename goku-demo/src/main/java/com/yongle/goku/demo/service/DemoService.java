package com.yongle.goku.demo.service;

import com.yongle.goku.model.demo.Demo;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;

/**
 * 类 名 称：DemoService.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月19日
 */
public interface DemoService {
    void a(boolean isException);

    void b();

    ResultVO<Page<Demo>> getDemos();
}
