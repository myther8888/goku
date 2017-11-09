package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

import java.util.List;

/**
 * @author weinh
 */
public interface RoleService extends BaseService {
    /**
     * 分配操作权限
     *
     * @param id          角色id
     * @param menuIds     菜单id列表
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO assignOperationAuthority(Long id, List<Integer> menuIds, UserVO currentUser);
}
