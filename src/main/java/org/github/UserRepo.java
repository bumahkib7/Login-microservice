package org.github;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {



    User findByUsername(String username);



    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(UUID id);
}
