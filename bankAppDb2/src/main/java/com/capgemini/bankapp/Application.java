package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.bankapp.configpackage.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.entities.BankAccount;

public class Application {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
		BankAccountController bankAccountController = 	context.getBean("bankAccountController", BankAccountController.class);
	System.out.println(bankAccountController.getBalance(12343));
		
//		try {
//			System.out.println(bankAccountController.withdraw(12343,4000 ));
//			System.out.println(bankAccountController.deposit(12346,2000 ));
//			System.out.println(bankAccountController.getBalance(12346));
//			System.out.println(bankAccountController.fundTransfer(12344,12343,2000));
//			System.out.println("\n"+bankAccountController.getBalance(12344));
//		System.out.println("\n"+bankAccountController.getBalance(12343));
//			
		//	System.out.println("\n"+bankAccountController.updateBankAccount());
//			
//		} catch (LowBalanceException e) {
//			e.printStackTrace();
//		}
		BankAccount account = new BankAccount(12345,"SAVINGS","Sam",50000);
//		System.out.println(bankAccountController.deleteBankAccount(12546));
//		System.out.println(bankAccountController.addBankAccount(account));
	System.out.println(bankAccountController.updateBankAccount(account));
	}

}
