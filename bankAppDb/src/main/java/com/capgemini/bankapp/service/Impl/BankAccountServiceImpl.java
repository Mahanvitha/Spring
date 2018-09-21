package com.capgemini.bankapp.service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	//public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
	//	this.bankAccountRepository = bankAccountRepository;
//	}
	@Autowired
DbUtil dbUtil;
	@Override
	public double getBalance(long accountId) {
		return bankAccountRepository.getBalance(accountId);
	}

	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			} 
			else
				throw new LowBalanceException("You don't have sufficent fund.");
		}
		return balance;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {

			String query = "select accountId from customers where accountId =?";
			try (Connection connection = dbUtil.getConnection();
					PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, (int) toAccount);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					if (withdraw(fromAccount, amount) >= 0) {
						deposit(toAccount, amount);
						return true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
}
	
	
