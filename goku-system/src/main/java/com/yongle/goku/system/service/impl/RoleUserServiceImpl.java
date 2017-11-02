package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.model.system.SysUserRole;
import com.yongle.goku.model.system.SysUserRoleExample;
import com.yongle.goku.system.mapper.SysUserRoleMapper;
import com.yongle.goku.system.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weinh
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl implements RoleUserService {

    @Resource
    private SysUserRoleMapper roleUserMapper;

    @Override
    public Set<String> findRoles(Long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SysUserRole> roleUserList = roleUserMapper.selectByExample(example);
        Set<String> roles = new HashSet<>(roleUserList.size());
        for (SysUserRole roleUser : roleUserList) {
            roles.add(roleUser.getRoleId().toString());
        }
        return roles;
    }
}
