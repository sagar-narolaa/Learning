package com.sagar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sagar.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

}
