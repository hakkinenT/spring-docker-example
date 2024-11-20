package com.hakkinent.spring_docker.repositories;

import com.hakkinent.spring_docker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
