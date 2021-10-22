package com.simplilearn.workshop.controller;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.workshop.model.ChequebookRequest;
import com.simplilearn.workshop.model.User;
import com.simplilearn.workshop.model.UserDisplay;
import com.simplilearn.workshop.service.impl.AdminServiceImpl;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminServiceImpl admservice;
	
	
	@GetMapping("user/{username}/features/{featureId}")
	public void setUserFeatures(@PathVariable("username") String username, @PathVariable("featureId") int featureId) {
		admservice.setUserFeatures(username, featureId);
	}
	
	@GetMapping("user/{username}/authorize")
	public void authorizeUser(@PathVariable("username") String username) {
		try {
			admservice.authorizeUser(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("user/{username}/authorize/cancel")
	public void  cancelAuthorization(@PathVariable("username") String username) {
		admservice.cancelAuthorization(username);
	}
	
	@GetMapping("user/unauthorized/all")
	public List<User> getAllUnauthorizedUsers()
	{
		return admservice.getAllUnauthorizedUsers();
	}
	
	@GetMapping("/user/all")
	public List<UserDisplay> getAllUsers()
	{
		return admservice.getAllUsers();
	}
	
	
	@GetMapping("/chequebook/request/all")
	public List<ChequebookRequest> getAllChequeBookRequests()
	{
		return admservice.getAllChequebookRequests();
	}
	
	@GetMapping("/user/{accNo}/chequebook/request/confirm")
	public void confirmChequeBookRequest(@PathVariable("accNo") long accNo)
	{
		admservice.acceptChequebookRequest(accNo);
	}
	
	@GetMapping("/user/{username}/enable")
	public void enableUser(@PathVariable("username") String username)
	{
		admservice.enableUser(username);
	}
	
	@GetMapping("/user/{username}/disable")
	public void disableUser(@PathVariable("username") String username)
	{
		admservice.disableUser(username);
	}
	
	@GetMapping("search/user/{userDetail}")
	public UserDisplay searchUser(@PathVariable("userDetail") String userDetail) {
		return admservice.searchUser(userDetail);
	}
	
}
