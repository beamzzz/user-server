package com.mx.repository;

import com.mx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserCode(String userCode);

    public User findByTelephone(String telephone);
}
