package org.lanqiao.Test;

import org.lanqiao.service.IAccountService;
import org.lanqiao.service.IAccountServiceImpl;

public class TestAccountTransfer {
   public static void main(String[] args) {
	
	   IAccountService service=new IAccountServiceImpl();
	   
	   service.transfer("张三", "李四", 1000.0);
	   
}
}
