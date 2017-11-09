package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysMenuRole;
import com.yongle.goku.model.system.SysMenuRoleExample;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysMenuRoleMapper;
import com.yongle.goku.system.service.RoleService;
import com.yongle.goku.system.shiro.ShiroPermissionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weinh
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    @Resource
    private SysMenuRoleMapper menuRoleMapper;

    @Resource
    private ShiroPermissionFactory shiroPermissionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO assignOperationAuthority(Long id, List<Integer> menuIds, UserVO currentUser) {
        SysMenuRoleExample menuRoleExample = new SysMenuRoleExample();
        menuRoleExample.createCriteria().andRoleIdEqualTo(id);
        menuRoleMapper.deleteByExample(menuRoleExample);
        Set<SysMenuRole> menuRoles = new HashSet<>();
        menuIds.forEach(menuId -> {
            SysMenuRole menuRole = new SysMenuRole();
            menuRole.setMenuId((long) menuId);
            menuRole.setRoleId(id);
            menuRoles.add(menuRole);
        });
        menuRoles.forEach(menuRole -> menuRoleMapper.insert(menuRole));
        shiroPermissionFactory.reloadFilterChains();
        return new ResultVO(ErrorEnum.SUCCESS);
    }
}
