package com.eon.demo.contactkeeperapi.data.repository;

import com.eon.demo.contactkeeperapi.data.model.ContactEntity;
import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> getByUser(UserEntity user);

}
