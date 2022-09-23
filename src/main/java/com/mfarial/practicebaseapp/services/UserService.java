package com.mfarial.practicebaseapp.services;

import com.mfarial.practicebaseapp.dto.request.CreateUserRequest;
import com.mfarial.practicebaseapp.dto.request.UpdateUserRequest;
import com.mfarial.practicebaseapp.entities.Role;
import com.mfarial.practicebaseapp.entities.User;
import com.mfarial.practicebaseapp.enums.EnumRole;
import com.mfarial.practicebaseapp.repositories.RoleRepository;
import com.mfarial.practicebaseapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  public int enableUser(String email) {
    return userRepository.enableUser(email);
  }
  public void update(Long id, UpdateUserRequest request){
    User user = userRepository.findById(id).orElseThrow();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    userRepository.save(user);
  }

  public void create( CreateUserRequest request){
    Set<Role> role = new HashSet<>();
    role.add(roleRepository.findByName(EnumRole.ROLE_STAFF).orElseThrow());

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    User user = new User();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(encoder.encode(request.getPassword()));
    user.setRoles(role);
    userRepository.save(user);
    enableUser(user.getEmail());
  }

  public void delete(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    userRepository.delete(user);
  }

}
