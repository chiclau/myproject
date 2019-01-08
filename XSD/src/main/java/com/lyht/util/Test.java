package com.lyht.util;


import java.util.List;
import java.util.Map;

/*import com.tools.jdbc.context.PreglacialApplication;
import com.tools.jdbc.dao.Dao;
import com.tools.jdbc.dao.DaoFactory;*/

public class Test {
	public static void main(String[] args) {/*
		String path = System.getProperty("user.dir");
		PreglacialApplication.initContext(path+"/src/main/resources/applicationContext.xml");
		try {
			Dao dao = DaoFactory.getPreglacial("linshi.dataSource");
			dao.beginTransaction();
//			System.out.println(dao.getJdbcTemplate().getDataSource().getConnection().getCatalog());
			List list  = dao.getJdbcTemplate().queryForList("select * from SYS_STAFF");
			for(int i = 0 ; i < list.size(); i++){
				Map map = (Map)list.get(i);
				System.out.println("-------------------------------"+map.get("STAFF_NAME")+"-------------------------------"+map.get("STAFF_DEPT")  );
			}
			
			dao.commit();
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/}
}
