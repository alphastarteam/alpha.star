package com.example.demouser.dataaccess.repository;

import com.example.demouser.dataaccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    @Query(value = "update User set password = ?2 where id = ?1")
    void modifyPassword(long id, String newPwd);
}
