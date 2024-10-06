package com.hachit.comvanphong.service.impl;

import com.hachit.comvanphong.dto.MenuDTO;
import com.hachit.comvanphong.entity.Menu;
import com.hachit.comvanphong.exception.ResourceNotFoundException;
import com.hachit.comvanphong.repository.MenuRepository;
import com.hachit.comvanphong.service.MenuService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuServiceImpl(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = modelMapper.map(menuDTO, Menu.class);
        Menu savedMenu = menuRepository.save(menu);
        return modelMapper.map(savedMenu, MenuDTO.class);
    }

    @Override
    public MenuDTO getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy menu với id: " + id));
        return modelMapper.map(menu, MenuDTO.class);
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy menu với id: " + id));
        menu.setNameMenu(menuDTO.getNameMenu());
        menu.setDetail(menuDTO.getDetail());
        menu.setPrice(menuDTO.getPrice());
        menu.setDelYn(menuDTO.getDelYn());
        Menu updatedMenu = menuRepository.save(menu);
        return modelMapper.map(updatedMenu, MenuDTO.class);
    }

    @Override
    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy menu với id: " + id));
        menuRepository.delete(menu);
    }
}
