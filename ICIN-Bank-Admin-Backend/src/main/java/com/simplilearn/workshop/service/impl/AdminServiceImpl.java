package com.simplilearn.workshop.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.workshop.model.ChequebookRequest;
import com.simplilearn.workshop.model.Transfer;
import com.simplilearn.workshop.model.User;
import com.simplilearn.workshop.model.UserDisplay;
import com.simplilearn.workshop.repository.AccountRepository;
import com.simplilearn.workshop.repository.ChequeBookRequestsRepository;
import com.simplilearn.workshop.repository.SaccountRepository;
import com.simplilearn.workshop.repository.TransferRepository;
import com.simplilearn.workshop.repository.UserDisplayRepository;
import com.simplilearn.workshop.repository.UserRepository;
import com.simplilearn.workshop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private TransferRepository trdata;
	
	@Autowired
	private ChequeBookRequestsRepository cbdata;
	
	@Autowired
	private UserRepository urdata;
	
	@Autowired
	private UserDisplayRepository udrdata;
	
	@Autowired
	private AccountRepository ardata;
	
	@Autowired
	private SaccountRepository sardata;
	
	@Autowired
	private AccountsCreationImpl accService;
	
	@Autowired
	private SaccountCreationImpl sAccService;
	
	@Override
	public void authorizeUser(String username) {
		urdata.authorizeUser(username);
		System.out.println("error here top");
		User currUser = urdata.findByUsername(username);
		int userId = currUser.getId();
		System.out.println("error here 1");
		accService.newAccount(username,userId);
		System.out.println("error here 2");
		sAccService.newAccount(username,userId);
	}
	
	@Override
	public void cancelAuthorization(String username) {
		urdata.cancelAuthorization(username);
	}

	@Override
	public void acceptChequebookRequest(long accNo) {
		String username = "";
		cbdata.setChequebookInfoByAccount(accNo);
		if(Long.toString(accNo).length() == 7) {
			username = ardata.findByAccno(accNo).getUsername();
		}
		else {
			username = sardata.findByAccno(accNo).getUsername();
		}
	}

	@Override
	public void enableUser(String username) {
		urdata.enableUser(username);
		
	}

	@Override
	public void disableUser(String username) {
		urdata.disableUser(username);
		
	}

	@Override
	public List<UserDisplay> getAllUsers() {
		return udrdata.getAllUsers();
	}

	@Override
	public List<Transfer> getAllTransactions(long accountNo) {
		List<Transfer> sender=trdata.findBySaccount(accountNo);
		List<Transfer> receiver=trdata.findByRaccount(accountNo);
		List<Transfer> merged=new ArrayList<>();
		merged.addAll(sender);
		merged.addAll(receiver);
		Collections.sort(merged);
		return merged;
	}

	@Override
	public List<ChequebookRequest> getAllChequebookRequests() {
		return cbdata.findAllChequebookRequests();
	}

	@Override
	public List<User> getAllUnauthorizedUsers() {
		return urdata.findAllUnauthorizedAccounts();
	}

	@Override
	public void setUserFeatures(String username, int featureId) {
		urdata.setUserFeatureStatus(username,featureId);
		
	}
	
	static boolean isNumber(String s) 
    { 
        for (int i = 0; i < s.length(); i++) 
        if (Character.isDigit(s.charAt(i))  
            == false) 
            return false; 
  
        return true; 
    } 

	@Override
	public UserDisplay searchUser(String userDetail) {
		if(isNumber(userDetail)) {
			return udrdata.getUserDetailsByAccountNo(Long.parseLong(userDetail));
		}
		else {
			return udrdata.getUserDetailsByUsername(userDetail);
		}
		
	}

}
