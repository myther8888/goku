package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysMenuRole;
import com.yongle.goku.model.system.SysMenuRoleExample;
import com.yongle.goku.model.system.SysUserRole;
import com.yongle.goku.model.system.SysUserRoleExample;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysMenuRoleMapper;
import com.yongle.goku.system.mapper.SysUserRoleMapper;
import com.yongle.goku.system.service.RoleService;
import com.yongle.goku.utils.redis.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private SysUserRoleMapper userRoleMapper;

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
        logger.info("重新分配角色：{}权限", id);
        menuRoles.forEach(menuRole -> menuRoleMapper.insert(menuRole));

        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andRoleIdEqualTo(id);
        List<SysUserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);

        List<String> userAuthorizationKeys = new ArrayList<>(userRoles.size());
        userRoles.forEach(userRole -> userAuthorizationKeys.add(
                RedisUtils.RedisKey.getUserAuthorizationKey(userRole.getUserId())));
        logger.info("清除角色：{}缓存权限", id);
        redisUtils.del(userAuthorizationKeys);
        return new ResultVO(ErrorEnum.SUCCESS);
    }
}
