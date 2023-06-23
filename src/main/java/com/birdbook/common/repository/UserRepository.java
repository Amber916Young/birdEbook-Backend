package com.birdbook.common.repository;

import com.birdbook.common.entity.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ForumUser, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, long id);

    Optional<ForumUser> findByEmail(String email);

    Optional<ForumUser> findByResetKey(String resetKey);

}
