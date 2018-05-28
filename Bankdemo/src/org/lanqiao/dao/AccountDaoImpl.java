package org.lanqiao.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.dbutil.Dbutils;
import org.lanqiao.entity.Account;

public class AccountDaoImpl implements IAccountDao {

	@Override
	public Account queryAccountByName(String name) throws SQLException {
		QueryRunner runner =new QueryRunner();
		String sql="select * from account where name=?";
		Connection conn=Dbutils.getConnection();
		Object[] os= {name};
		Account account=runner.query(conn, sql,new BeanHandler<Account>(Account.class) , os);
		return account;
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		QueryRunner runner =new QueryRunner(Dbutils.getDataSource());
		Connection conn=Dbutils.getConnection();
		String sql="update account set balance=? where name=?";
		Object[] os= {account.getBalance(),account.getName()};
		runner.update(conn,sql,os);
	
	}
	

}
