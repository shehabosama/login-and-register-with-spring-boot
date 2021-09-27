package com.shehab.authdemo.authdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shehab.authdemo.authdemo.entity.User;




public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String email);
}
