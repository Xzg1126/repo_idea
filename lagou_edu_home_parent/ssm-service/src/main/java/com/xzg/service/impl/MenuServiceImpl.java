package com.xzg.service.impl;

import com.xzg.dao.MenuMapper;
import com.xzg.domain.Menu;
import com.xzg.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    //查询所有菜单
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public List<Menu> findSubMenuListBypid(int pid) {
        List<Menu> subMenuListBypid = menuMapper.findSubMenuListBypid(pid);
        return subMenuListBypid;

    }
    //根据角色ID查询关联菜单ID
    @Override
    public List<Integer> findMenuByRoleId(int rid) {
        List<Integer> menuByRoleId = menuMapper.findMenuByRoleId(rid);
        return menuByRoleId;
    }

    @Override
    public Menu findMenuById(int id) {
        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }
    //添加菜单
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.saveMenu(menu);
    }
    //修改菜单
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }
}
