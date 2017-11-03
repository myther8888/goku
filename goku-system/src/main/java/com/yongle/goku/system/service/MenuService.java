package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.system.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @author weinh
 */
public interface MenuService extends BaseService {
    /**
     * 查询所有的功能点
     *
     * @return 所有的功能点
     */
    List<SysMenu> findAllFunctionPoint();

    /**
     * 查询有权限的菜单
     *
     * @param userId 用户标识
     * @return 有权限的菜单
     */
    List<SysMenu> findByUserId(Long userId);

    /**
     * 获取有权限的功能点
     *
     * @param userId 用户标识
     * @return 有权限的功能点
     */
    Set<String> getPermission(Long userId);
}
