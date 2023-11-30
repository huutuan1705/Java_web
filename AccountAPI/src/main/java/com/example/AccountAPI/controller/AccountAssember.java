/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AccountAPI.controller;

import com.example.AccountAPI.model.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author huutuan
 */
@Configuration
public class AccountAssember implements RepresentationModelAssembler<Account, EntityModel<Account>>{

    @Override
    public EntityModel<Account> toModel(Account account) {
        EntityModel<Account> accountModel = EntityModel.of(account);
        
        accountModel.add(linkTo(methodOn(AccountAPIController.class).getAccountByID(account.getId())).withSelfRel());
        accountModel.add(linkTo(methodOn(AccountAPIController.class).deposit(null, account.getId())).withRel("deposit"));
        accountModel.add(linkTo(methodOn(AccountAPIController.class).withdraw(null, account.getId())).withRel("withdraw"));
        accountModel.add(linkTo(methodOn(AccountAPIController.class).listAll()).withRel("home"));
        
        return accountModel;
    }
    
}
