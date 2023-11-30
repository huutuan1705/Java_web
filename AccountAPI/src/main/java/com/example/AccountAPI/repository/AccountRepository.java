/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AccountAPI.repository;

import com.example.AccountAPI.model.Account;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author huutuan
 */
public interface AccountRepository extends JpaRepository<Account, Integer>{
   
    @Modifying
    @Transactional
    @Query(value="UPDATE accounts a SET a.balance = :amount WHERE a.id = :id", nativeQuery=true)
    public void updateBalance(@Param("amount") float amount, @Param("id") Integer id);
}
