package com.yongle.goku.system.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.system.service.RoleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weinh
 */
@RestController
@RequestMapping("/system/roles")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @PostMapping("/role/{id}/menus")
    public ResultVO assignOperationAuthority(@PathVariable("id") Long id,
                                             @RequestBody List<Integer> menuIds,
                                             HttpServletRequest request) {
        return roleService.assignOperationAuthority(id, menuIds, getCurrentUser(request));
    }
}
