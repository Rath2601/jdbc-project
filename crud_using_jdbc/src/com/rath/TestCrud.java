package com.rath;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class TestCrud {
	private static final Logger logger = Logger.getLogger(TestCrud.class.getName());
public static void main(String[] args) {
	CrudOperations co = new CrudOperations();
	
//	co.createEmp("Rathna", "arr@email.com", "India", 12000);
//	co.createEmp("Nishanth", "nish@email.com", "USA", 1000000);
//	
	List<String> ls = co.selectEmp();
	
	 for(String s : ls) {
		 logger.info(s);
	 }
	
	
//	logger.info(co.selectEmp(3));
	
//	co.updateEmp(2, "Nishanth", "nish@email.com", "Japan", 2000000);
//	
//	co.deleteEmp(1);
}
}
