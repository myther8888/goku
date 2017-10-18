package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.model.system.SysMenu;
import com.yongle.goku.model.system.SysMenuExample;
import com.yongle.goku.system.mapper.SysMenuMapper;
import com.yongle.goku.system.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weinh
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

    @Resource
    SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findAll() {
        return menuMapper.selectByExample(new SysMenuExample());
    }
}
