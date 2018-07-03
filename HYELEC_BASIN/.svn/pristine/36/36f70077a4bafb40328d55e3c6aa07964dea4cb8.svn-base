package com.lyht.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ZtreeUtil {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static JSONArray getZtreeJSONArray(List treeList,String pidProperty,String idProperty,String nameProperty,String rootIcon,String leafIcon,String rootPidValue,boolean isOpen){
		JSONArray jsonarr = JSONArray.fromObject(treeList);
		JSONArray rejsonarr = JSONArray.fromObject(new ArrayList());
		JSONObject pjson = null;
		//找到根节点
		List<JSONObject> rootJsonList = new ArrayList<JSONObject>();
		for(Iterator it = jsonarr.iterator();it.hasNext();){
			JSONObject json = (JSONObject)it.next();
			if(json !=null && json.get(pidProperty)!=null && json.get(pidProperty).toString().equals(rootPidValue)){
				rootJsonList.add(json);
			}
		}
		//遍历组织树形jsonarr
		getSubJSONObject(rootJsonList,jsonarr,pidProperty,idProperty,nameProperty,rootIcon,leafIcon,rejsonarr,isOpen);
		return rejsonarr;
	}
	
	
	private static void getSubJSONObject(List<JSONObject> rootList,JSONArray jsonarr,String pidProperty,String idProperty,String nameProperty,String rootIcon,String leafIcon,JSONArray rejsonarr,boolean isOpen){
		for(int i=0;i<rootList.size();i++){
			JSONObject rootJson = rootList.get(i);
			List<JSONObject> sublist = findSubJSONObject(jsonarr,rootJson,pidProperty,idProperty);
			if(sublist.size()<1 && CommonUtil.getLength(leafIcon)>0){
				rootJson.put("icon", leafIcon);
			}else if(CommonUtil.getLength(rootIcon)>0){
				rootJson.put("icon", rootIcon);
			}
			rootJson.put("id", rootJson.get(idProperty));
			rootJson.put("pId", rootJson.get(pidProperty));
			rootJson.put("name", rootJson.get(nameProperty));
			if(isOpen)
				rootJson.put("open", "true");
			rejsonarr.add(rootJson);
			getSubJSONObject(sublist,jsonarr,pidProperty,idProperty,nameProperty,rootIcon,leafIcon,rejsonarr,isOpen);
		}
	}
	@SuppressWarnings("rawtypes")
	private static List<JSONObject> findSubJSONObject(JSONArray jsonarr,JSONObject pjson,String pidProperty,String idProperty){
		List<JSONObject> sublist = new ArrayList<JSONObject>();
		for(Iterator it = jsonarr.iterator();it.hasNext();){
			JSONObject ljson = (JSONObject)it.next();
			if(pjson.get(idProperty)!=null && ljson.get(pidProperty)!=null && pjson.get(idProperty).toString().equals(ljson.get(pidProperty).toString())){
				sublist.add(ljson);
			}
		}
		return sublist;
	}
}
