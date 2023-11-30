/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AccountAPI.service;

import com.example.AccountAPI.exception.AccoutNotFoundException;
import com.example.AccountAPI.model.Account;
import com.example.AccountAPI.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author huutuan
 */
@Service
public class AccountService {
    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }
    
    public List<Account> listAll(){
        return repository.findAll();
    }
    
    public Account getAccount(Integer id){
        return repository.findById(id).get();
    }
    
    public Account saveAccount(Account account){
        return repository.save(account);
    }
    
    public Account depositAccount(float amount, Integer id){
        repository.updateBalance(amount, id);
        return repository.findById(id).get();
    }
    
    public Account withdraw(float amount, Integer id){
        repository.updateBalance(-amount, id);
        return repository.findById(id).get();
    }
    
    public void deleteAccount(Integer id) throws AccoutNotFoundException{
        if(!repository.existsById(id)){
            throw new AccoutNotFoundException();
        }
        repository.deleteById(id);
    }
}
