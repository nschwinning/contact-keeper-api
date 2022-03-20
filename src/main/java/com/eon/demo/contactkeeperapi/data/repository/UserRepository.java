package com.eon.demo.contactkeeperapi.data.repository;

import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    UserEntity getByEmail(String email);

}
