package com.yongle.goku.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysMenu;
import com.yongle.goku.model.system.SysMenuExample;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.MenuVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysMenuMapper;
import com.yongle.goku.system.mapper.SysMenuMapperExt;
import com.yongle.goku.system.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author weinh
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuVO> implements MenuService {
    @Resource
    private SysMenuMapperExt menuMapperExt;

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findAllFunctionPoint() {
        return menuMapperExt.findAllFunctionPoint();
    }

    @Override
    public Set<String> getPermission(Long userId) {
        List<SysMenu> allFunctionPoint = menuMapperExt.findAllFunctionPoint();
        Map<String, Map<String, Boolean>> allPermission = new HashMap<>(allFunctionPoint.size());
        allFunctionPoint.forEach(menu -> {
            //将不需要权限的设置为true，将需要权限的设置为false
            Map<String, Boolean> actionMap = allPermission.get(menu.getUrl());
            if (actionMap == null) {
                actionMap = new HashMap<>(4);
                actionMap.put("create", true);
                actionMap.put("read", true);
                actionMap.put("update", true);
                actionMap.put("delete", true);
            }
            if (actionMap.containsKey(menu.getAction())) {
                actionMap.put(menu.getAction(), false);
            }
            allPermission.put(menu.getUrl(), actionMap);
        });

        List<SysMenu> functionPoints = menuMapperExt.findFunctionPointByUserId(userId);
        functionPoints.forEach(menu -> {
            //将具有权限的设置为true
            Map<String, Boolean> actionMap = allPermission.get(menu.getUrl());
            actionMap.put(menu.getAction(), true);
            allPermission.put(menu.getUrl(), actionMap);
        });

        Set<String> permissions = new HashSet<>();
        allPermission.forEach((url, actionMap) ->
                //将权限以url:action的形式组装下
                actionMap.forEach((action, operationFlag) -> {
                    if (operationFlag) {
                        permissions.add(url + ":" + action);
                    }
                }));
        logger.info("用户：{}的所有权限：{}", userId, JSONObject.toJSONString(permissions));
        return permissions;
    }

    @Override
    public ResultVO save(MenuVO menuVO, UserVO currentUser) {
        return new ResultVO(ErrorEnum.SUCCESS);
    }

    @Override
    public ResultVO update(Long id, MenuVO menuVO, UserVO currentUser) {
        return new ResultVO(ErrorEnum.SUCCESS);
    }

    @Override
    public ResultVO disabled(Long id, UserVO currentUser) {
        return new ResultVO(ErrorEnum.SUCCESS);
    }

    @Override
    public ResultVO enabled(Long id, UserVO currentUser) {
        return new ResultVO(ErrorEnum.SUCCESS);
    }

    @Override
    public ResultVO<Page<MenuVO>> findByPage(MenuVO menuVO, Page page, UserVO currentUser) {
        return new ResultVO<>();
    }
}
