package com.example.demouser.dataaccess.repository;

import com.example.demouser.dataaccess.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByUserId(Long userId);

    UserLogin findByUserIdAndIsLogout(Long userId,boolean isLogout);

    UserLogin findByToken(String token);

    @Modifying
    @Query(value = "update UserLogin set isLogout = true,updateTime=now() where token = ?1")
    @Transactional
    void logout(String token);

}
