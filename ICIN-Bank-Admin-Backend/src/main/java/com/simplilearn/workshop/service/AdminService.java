package com.simplilearn.workshop.service;

import java.util.List;

import com.simplilearn.workshop.model.ChequebookRequest;
import com.simplilearn.workshop.model.Transfer;
import com.simplilearn.workshop.model.User;
import com.simplilearn.workshop.model.UserDisplay;

public interface AdminService {
	
	public List<UserDisplay> getAllUsers();
	public List<Transfer> getAllTransactions(long accountNo);
	public List<ChequebookRequest> getAllChequebookRequests();
	public void enableUser(String username);
	public void disableUser(String username);
	public void authorizeUser(String username);
	public void cancelAuthorization(String username);
	public List<User> getAllUnauthorizedUsers();
	public void setUserFeatures(String username, int featureId);
	public UserDisplay searchUser(String userDetail);
	void acceptChequebookRequest(long accNo);
	
}
