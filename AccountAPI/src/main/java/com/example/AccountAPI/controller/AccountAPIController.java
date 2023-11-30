/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AccountAPI.controller;

import com.example.AccountAPI.exception.AccoutNotFoundException;
import com.example.AccountAPI.model.Account;
import com.example.AccountAPI.model.Amount;
import com.example.AccountAPI.service.AccountService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author huutuan
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountAPIController {
    private AccountService service;
    private AccountAssember assember;

    public AccountAPIController(AccountService service, AccountAssember assember) {
        this.service = service;
        this.assember = assember;
    }
    
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Account>>> listAll(){
        List<Account> listAccounts = service.listAll();
        
        if(listAccounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        List<EntityModel<Account>> accounts = listAccounts.stream().map(assember::toModel).collect(Collectors.toList());
        
        CollectionModel<EntityModel<Account>> model = CollectionModel.of(accounts);
        model.add(linkTo(methodOn(AccountAPIController.class).listAll()).withSelfRel());
        
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Account>> getAccountByID(@PathVariable("id") Integer id){
        try{
            Account account = service.getAccount(id);
            
            EntityModel<Account> entityModel = assember.toModel(account);
            
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<EntityModel<Account>> add(@RequestBody Account account){
        Account savedAccount = service.saveAccount(account);
        
        EntityModel<Account> entityModel = assember.toModel(savedAccount);
        
        return ResponseEntity.created(linkTo(methodOn(AccountAPIController.class)
                .getAccountByID(savedAccount.getId())).toUri()).body(entityModel);
        
    }
    
    @PutMapping
    public ResponseEntity<EntityModel<Account>> replace(@RequestBody Account account){
        Account updatedAccount = service.saveAccount(account);
        
        EntityModel<Account> entityModel = assember.toModel(updatedAccount);
        
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/deposit")
    public ResponseEntity<EntityModel<Account>> deposit(@RequestBody Amount amount,
            @PathVariable("id") Integer id){
        Account updatedAccount = service.depositAccount(amount.getAmount(), id);
        
        EntityModel<Account> entityModel = assember.toModel(updatedAccount);
        
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<EntityModel<Account>> withdraw(@RequestBody Amount amount,
            @PathVariable("id") Integer id){
        Account updatedAccount = service.withdraw(amount.getAmount(), id);
        
        EntityModel<Account> entityModel = assember.toModel(updatedAccount);
        
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        try{
            service.deleteAccount(id);
            
            return ResponseEntity.noContent().build();
        }catch(AccoutNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
    }
}
