package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.system.SysMenu;

import java.util.List;

/**
 * @author weinh
 */
public interface MenuService extends BaseService {
    List<SysMenu> findAll();

    List<SysMenu> findAllFunctionPoint();
}
