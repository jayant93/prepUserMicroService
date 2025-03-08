package com.interview.prep.repositories;

import com.interview.prep.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //find by name
    Optional<User> findByName(String name);

    //check if user exists by provide name
    boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);

}
