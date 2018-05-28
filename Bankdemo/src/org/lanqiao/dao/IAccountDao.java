package org.lanqiao.dao;

import java.sql.SQLException;

import org.lanqiao.entity.Account;

public interface IAccountDao {
	//根据姓名查询用户
	public abstract Account queryAccountByName(String name)throws SQLException;
	//修改账户，增加余额减少余额
	public abstract void updateAccount(Account account)throws SQLException;

}
