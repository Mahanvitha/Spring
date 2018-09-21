package com.capgemini.bankapp.repository.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
//private HashSet<BankAccount> accounts;
//
//	public BankAccountRepositoryImpl() {
//	super();
//	accounts=new HashSet<>();
//	accounts.add(new BankAccount(1234,"John","Savings",34000));
//	accounts.add(new BankAccount(123,"Tom","Savings",40000));
//	accounts.add(new BankAccount(12,"Sam","Current",50000));
//}
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
		double balance = jdbcTemplate.queryForObject("SELECT accountbalance FROM bankAccounts WHERE accountId=?",
				new Object[] { accountId }, Double.class);
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("UPDATE bankaccounts SET accountbalance = ? WHERE accountId =?",
				new Object[] { accountId, newBalance });
		return count != 0;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("INSERT INTO BankAccounts VALUES (?,?,?,?)",
				new Object[] { account.getAccountId(), account.getAccountType(), account.getAccountHolderName(),
						account.getAccountBalance() });
		return count != 0;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		return jdbcTemplate.queryForObject("SELECT * FROM bankaccounts WHERE accountId = ?", new Object[] { accountId },
				new BankAccountRowMapper());

	}

	@Override
	public List<BankAccount> findBankAccounts() {
		jdbcTemplate.query("SELECT * FROM banaccounts", new Object[] {}, new BankAccountRowMapper());
		return null;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("UPDATE bankaccounts SET accountHolderName = ?, accountType =? WHERE accountId = ?",
				new Object[] { account.getAccountHolderName(), account.getAccountType(),account.getAccountId() });
		return count != 0 ? account : findBankAccountById(account.getAccountId());
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		int count = jdbcTemplate.update("DELETE FROM bankaccounts WHERE accountId = ?", new Object[] { accountId });
		return count != 0;
	}

	private class BankAccountRowMapper implements RowMapper<BankAccount> {
		@Override

		public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getLong(1));
			account.setAccountHolderName(rs.getString(2));
			account.setAccountType(rs.getString(3));
			account.setAccountBalance(rs.getDouble(4));
			return account;
		}

	}
}