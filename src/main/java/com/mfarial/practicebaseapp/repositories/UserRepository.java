package com.mfarial.practicebaseapp.repositories;

import com.mfarial.practicebaseapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsernameAndEnabledTrue(String username);

  Boolean existsByEmail(String email);

  boolean existsByEmailAndEnabledTrue(String email);

  List<User> findByEnabledTrue();

  @Transactional
  @Modifying
  @Query("UPDATE User a " +
          "SET a.enabled = TRUE WHERE a.email = ?1")
  int enableUser(String email);
}
