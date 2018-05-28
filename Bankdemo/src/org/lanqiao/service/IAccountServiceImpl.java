package org.lanqiao.service;

import java.sql.SQLException;

import org.lanqiao.dao.AccountDaoImpl;
import org.lanqiao.dao.IAccountDao;
import org.lanqiao.dbutil.Dbutils;
import org.lanqiao.entity.Account;

public class IAccountServiceImpl  implements IAccountService{

	@Override
	public void transfer(String fromName, String toName, double transferMoney){
		try {
			//开启事务
		Dbutils.beginTransaction();
		IAccountDao dao=new AccountDaoImpl();
		//收款方
			Account toAccount = dao.queryAccountByName(toName);
			//付款方
			Account fromAccount=dao.queryAccountByName(fromName);
			//转账
			if(transferMoney<fromAccount.getBalance()) {
				//付款方余额减少
				double fromBalance=fromAccount.getBalance()-transferMoney;
				fromAccount.setBalance(fromBalance);
				//收款方余额增加
				double toBalance=toAccount.getBalance()+transferMoney;
				toAccount.setBalance(toBalance);
				//更新账户
				dao.updateAccount(fromAccount);
				dao.updateAccount(toAccount);
				System.out.println("转账成功");
				//提交事务
				Dbutils.commitTransaction();
				System.out.println("提交成功");
				
				
			}else {
				System.out.println("余额不足，转账失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("提交失败，回滚...");
			Dbutils.rollbackTransaction();
			e.printStackTrace();
		}finally {
			Dbutils.close();
		}
	
		
		
	}
	

}
