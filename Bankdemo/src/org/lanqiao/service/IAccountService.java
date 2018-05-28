package org.lanqiao.service;

import java.sql.SQLException;

public interface IAccountService {
	public abstract void transfer(String fromName,String toName,double transferMoney ) ;

}
