package com.example.demouser.dataaccess.repository;

import com.example.demouser.dataaccess.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByUserId(Long userId);

    UserLogin findByToken(String token);

    @Query(value = "update UserLogin set logout = true,updateTime=now() where token = ?1")
    void logout(String token);

}
