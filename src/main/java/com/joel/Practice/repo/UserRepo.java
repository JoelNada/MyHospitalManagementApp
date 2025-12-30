package com.joel.Practice.repo;

import com.joel.Practice.model.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<@NonNull User,@NonNull Integer> {
    User findByUserName(String username);
}
