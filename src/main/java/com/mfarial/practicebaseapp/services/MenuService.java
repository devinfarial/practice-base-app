package com.mfarial.practicebaseapp.services;

import com.mfarial.practicebaseapp.dto.request.CreateMenuRequest;
import com.mfarial.practicebaseapp.dto.response.MenuResponse;
import com.mfarial.practicebaseapp.entities.Menu;
import com.mfarial.practicebaseapp.repositories.MenuRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MapperFacade mapperFacade;

    public List<MenuResponse> getAll(){

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Menu.class, MenuResponse.class)
                .field("menuName", "label")
                .field("id", "id")
                .register();
        mapperFacade = mapperFactory.getMapperFacade();
        List<Menu> listMenu = menuRepository.findAll();
        return mapperFacade.mapAsList(listMenu, MenuResponse.class);

    }

    public void create(CreateMenuRequest request){
        Menu menu = new Menu();
        menu.setMenuName(request.getMenuName());
        menuRepository.save(menu);
    }

    public void update(Long id, CreateMenuRequest request){
        Menu menu = menuRepository.findById(id).orElseThrow();
        menu.setMenuName(request.getMenuName());
        menuRepository.save(menu);
    }

    public void delete(Long id){
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);
    }
}
