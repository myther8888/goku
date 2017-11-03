package com.yongle.goku.system.mapper;

import com.yongle.goku.model.system.SysMenu;

import java.util.List;

/**
 * @author weinh
 */
public interface SysMenuMapperExt {

    /**
     * 查询所有的功能点
     *
     * @return 所有功能点
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
     * 查询有权限的功能点
     *
     * @param userId 用户标识
     * @return 有权限的功能点
     */
    List<SysMenu> findFunctionPointByUserId(Long userId);

}