package com.mfarial.practicebaseapp.repositories;

import com.mfarial.practicebaseapp.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
