package com.simplilearn.workshop.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.workshop.model.Saccount;
import com.simplilearn.workshop.repository.SaccountRepository;
import com.simplilearn.workshop.repository.UserRepository;

@Service
public class SaccountCreationImpl {
	
	@Autowired
	SaccountRepository sardata;
	
	@Autowired
	UserRepository urdata;
	
	private static String acctPrefix = "5000000000" ;
	
	public long generate_saving(int userId) {
		String accNo = acctPrefix+String.valueOf(userId);
		return Long.parseLong(accNo);
	}

	public Saccount newAccount(String username, int userId) {
		Saccount account =new Saccount();
		account.setUsername(username);
		account.setAccno(generate_saving(userId));
		account.setUser(urdata.findByUsername(username));
		return sardata.save(account);
	}

}
