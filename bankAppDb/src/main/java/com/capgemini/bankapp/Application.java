package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.bankapp.configpackage.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.exception.LowBalanceException;

public class Application {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
		BankAccountController bankAccountController = 	context.getBean("bankAccountController", BankAccountController.class);
		System.out.println(bankAccountController.getBalance(12343));
		
		DbUtil dbutil = context.getBean(DbUtil.class);
		dbutil.getConnection();
		try {
			System.out.println(bankAccountController.withdraw(12343,4000 ));
			System.out.println(bankAccountController.deposit(12343,2000 ));
			System.out.println(bankAccountController.getBalance(12343));
			System.out.println(bankAccountController.fundTransfer(12344,12343,2000));
			System.out.println("\n"+bankAccountController.getBalance(12344));
			System.out.println("\n"+bankAccountController.getBalance(12343));
		} catch (LowBalanceException e) {
			e.printStackTrace();
		}
		
	}

}
