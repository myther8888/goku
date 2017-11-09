package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.system.SysMenu;
import com.yongle.goku.model.vo.system.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * @author weinh
 */
public interface MenuService extends BaseService<MenuVO> {
    /**
     * 查询所有的功能点
     *
     * @return 所有的功能点
     */
    List<SysMenu> findAllFunctionPoint();

    /**
     * 获取有权限的功能点
     *
     * @param userId 用户标识
     * @return 有权限的功能点
     */
    Set<String> getPermission(Long userId);
}
