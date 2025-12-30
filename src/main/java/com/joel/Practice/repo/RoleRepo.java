package com.joel.Practice.repo;

import com.joel.Practice.model.entity.Role;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    @NullMarked
    Optional<Role> findById(Long id);
    Optional<Role> findByRole(String name);
}
