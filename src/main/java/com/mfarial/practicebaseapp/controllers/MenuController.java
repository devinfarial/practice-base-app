package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.dto.request.CreateMenuRequest;
import com.mfarial.practicebaseapp.dto.request.CreateUserRequest;
import com.mfarial.practicebaseapp.repositories.MenuRepository;
import com.mfarial.practicebaseapp.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuService menuService;

    @GetMapping
    public ResponseEntity<List<?>> getAll(){
        return ResponseEntity.ok(menuService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateMenuRequest request){
        menuService.create(request);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CreateMenuRequest request ){
        menuService.update(id, request);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        menuService.delete(id);
        return ResponseEntity.ok("deleted");
    }

}
