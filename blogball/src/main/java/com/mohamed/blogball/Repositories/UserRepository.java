package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.User;
import java.util.Optional;

import com.mohamed.blogball.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  User findByRole(RoleName roleName);
}
