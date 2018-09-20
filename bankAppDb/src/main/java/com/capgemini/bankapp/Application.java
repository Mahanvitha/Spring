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
		System.out.println(bankAccountController.getBalance(1234));
		try {
			System.out.println(bankAccountController.withdraw(1234,4000 ));
			System.out.println(bankAccountController.deposit(123,2000 ));
			System.out.println(bankAccountController.getBalance(123));
			System.out.println(bankAccountController.fundTransfer(1234,12,1000));
			System.out.println("\n"+bankAccountController.getBalance(1234));
			System.out.println("\n"+bankAccountController.getBalance(12));
		} catch (LowBalanceException e) {
			e.printStackTrace();
		}
		DbUtil dbutil = context.getBean(DbUtil.class);
		dbutil.getConnection();
	}

}
