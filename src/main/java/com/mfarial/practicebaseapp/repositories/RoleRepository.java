package com.mfarial.practicebaseapp.repositories;

import com.mfarial.practicebaseapp.entities.Role;
import com.mfarial.practicebaseapp.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(EnumRole name);
}
