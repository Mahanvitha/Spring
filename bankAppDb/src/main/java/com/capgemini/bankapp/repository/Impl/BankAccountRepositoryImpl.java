package com.capgemini.bankapp.repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
private HashSet<BankAccount> accounts;
//
//	public BankAccountRepositoryImpl() {
//	super();
//	accounts=new HashSet<>();
//	accounts.add(new BankAccount(1234,"John","Savings",34000));
//	accounts.add(new BankAccount(123,"Tom","Savings",40000));
//	accounts.add(new BankAccount(12,"Sam","Current",50000));
//}

@Override
public double getBalance(long accountId) {
	String query = "select accountBalance FROM bankAccounts where accountId=?";
	try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
		statement.setInt(1, (int) accountId);
		ResultSet result = statement.executeQuery();
		if(result.next())
		{
			return result.getDouble(1);
		}
		}
catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
}

@Override
public boolean updateBalance(long accountId, double newBalance) {
	
	String query = "UPDATE bankAccounts SET accountBalance=? where accountId=?";
	try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
		statement.setInt(2, (int) accountId);
		statement.setDouble(1, newBalance);
		if (statement.executeUpdate() == 1)
			return true;
	return false;
} catch (SQLException e) {
	e.printStackTrace();
}
	return false;
}
}