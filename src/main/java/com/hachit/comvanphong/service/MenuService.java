package com.hachit.comvanphong.service;

import com.hachit.comvanphong.dto.MenuDTO;

import java.util.List;

public interface MenuService {
    MenuDTO createMenu(MenuDTO menuDTO);
    MenuDTO getMenuById(Long id);
    List<MenuDTO> getAllMenus();
    MenuDTO updateMenu(Long id, MenuDTO menuDTO);
    void deleteMenu(Long id);
}
