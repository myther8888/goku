package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;

import java.util.Set;

/**
 * 类 名 称：RoleUserService.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月16日
 */
public interface RoleUserService extends BaseService {
    Set<String> findRoles(Long userId);
}
