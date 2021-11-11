package com.simplilearn.workshop.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.workshop.model.Account;
import com.simplilearn.workshop.repository.AccountRepository;
import com.simplilearn.workshop.repository.UserRepository;

@Service
public class AccountsCreationImpl {
	
	@Autowired
	AccountRepository ardata;
	
	@Autowired
	private UserRepository urdata;
	
	private static String acctPrefix = "1000000000";
    
	public Account newAccount(String username,int userId) {
		Account account=new Account();
		account.setUsername(username);
		account.setAccno(generate_saving(userId));
		account.setUser(urdata.findByUsername(username));
		return ardata.save(account);

	}

	public long generate_saving(int userId) {
		String accNo = acctPrefix+String.valueOf(userId);
		return Long.parseLong(accNo);
	}

}
