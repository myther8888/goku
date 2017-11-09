package com.yongle.goku.system.shiro;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.model.system.SysMenu;
import com.yongle.goku.system.service.MenuService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weinh
 */
public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MenuService menuService;

    /**
     * 记录配置中的过滤链
     */
    private static String definition = "";

    /**
     * 初始化设置过滤链
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {
        //记录配置的静态过滤链
        definition = definitions;
        List<SysMenu> menus = menuService.findAllFunctionPoint();
        Map<String, String> otherChains = new HashMap<>(menus.size());
        menus.forEach(menu -> otherChains.put(menu.getUrl(), "tokenAuthFilter,permissionAuthFilter[" + menu.getUrl() + "]"));
        otherChains.put("/**", "tokenAuthFilter");
        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //加上数据库中过滤链
        section.putAll(otherChains);
        logger.info("权限控制过滤的配置：{}", JSONObject.toJSONString(section));
        setFilterChainDefinitionMap(section);
    }

    public synchronized void reloadFilterChains() {
        //强制同步，控制线程安全
        try {
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) this.getObject();
            PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            // 过滤管理器
            DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
            // 清除权限配置
            manager.getFilterChains().clear();
            // 重新设置权限
            this.getFilterChainDefinitionMap().clear();
            //传入配置中的filterchains
            this.setFilterChainDefinitions(ShiroPermissionFactory.definition);
            Map<String, String> chains = this.getFilterChainDefinitionMap();
            //重新生成过滤链
            if (!CollectionUtils.isEmpty(chains)) {
                chains.forEach((url, definitionChains) -> {
                    manager.createChain(url, definitionChains.trim().replace(" ", ""));
                });
            }
            logger.info("重新加载拦截器链：{}", JSONObject.toJSONString(manager.getFilterChains()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
