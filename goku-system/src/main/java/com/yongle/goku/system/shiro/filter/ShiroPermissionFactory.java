package com.yongle.goku.system.shiro.filter;

import com.yongle.goku.system.service.MenuService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 类 名 称：ShiroPermissionFactory.java
 * 功能说明：
 * @author weinh
 * 开发时间：2017年10月16日
 */
public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

    private MenuService menuService;

    /**
     * 记录配置中的过滤链
     */
    public static String definition = "";

    /**
     * 初始化设置过滤链
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {
        //记录配置的静态过滤链
        definition = definitions;
//        List<SysMenu> permissions = menuService.findAll();
        Map<String, String> otherChains = new HashMap<>(1);
//        permissions.forEach(menu -> {
        //perms.add(e)
//        otherChains.put("/users", "roles[1]");

        otherChains.put("/users/user/*",
                "tokenAuthFilter,roleAuthFilter[1],permissionAuthFilter[user]");
        otherChains.put("/**",
                "tokenAuthFilter");
//        });
        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //加上数据库中过滤链
        section.putAll(otherChains);
        setFilterChainDefinitionMap(section);
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
