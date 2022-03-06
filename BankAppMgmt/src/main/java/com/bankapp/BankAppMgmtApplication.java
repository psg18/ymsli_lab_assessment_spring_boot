package com.bankapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.UserService;

@SpringBootApplication
public class BankAppMgmtApplication implements CommandLineRunner{
	

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BankAppMgmtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
//		userService.addUser(new User("ParasMgr", "paras", "ROLE_MGR", "psgmgr@gmail.com"));
//		userService.addUser(new User("ParasClerk", "paras", "ROLE_CLERK", "psgclerk@gmail.com"));
//		
//		accountService.addAccount(new Account( "ParasMgr", 3000.00, "987654321", 
//				"Derawal Nagar Delhi", "psgmgr@gmail.com"));
//		
//		accountService.addAccount(new Account( "ParasClerk", 8000.00, "976452382", 
//				"Derawal Nagar Delhi", "psgclerk@gmail.com"));

		
	}
}
