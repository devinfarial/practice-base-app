package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.dto.request.CreateUserRequest;
import com.mfarial.practicebaseapp.services.UserService;
import com.mfarial.practicebaseapp.dto.request.UpdateUserRequest;
import com.mfarial.practicebaseapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<?>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateUserRequest request){
        userService.create(request);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        userService.update(id, request);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
