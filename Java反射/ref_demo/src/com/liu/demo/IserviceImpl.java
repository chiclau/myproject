package com.liu.demo;

import java.util.HashMap;
import java.util.Map;

public class IserviceImpl implements IService {

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 1;
	}
  StringBuffer sb;
  StringBuilder sbd;
	@Override
	public Map<String, Object> getMap() {
		// TODO Auto-generated method stub
		Map<String, Object> has=new HashMap<String, Object>();
		has.put("key", 12);
		return has;
	}

}
