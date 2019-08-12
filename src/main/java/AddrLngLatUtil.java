import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class AddrLngLatUtil {
	
	public static  JSONObject transAddToLocation(String address) {
	
		JSONObject locationJson =new JSONObject();
		try {
			String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
			Pattern p = Pattern.compile(regEx); 
			Matcher m = p.matcher(address);
		    address=m.replaceAll("").trim();
			address=address.replaceAll(" ", "");
			int addrLen=address.getBytes("gbk").length;
			if (addrLen>84) {
				address=address.substring(0,42);
			 }	
			String baiduAPI = AddrLngLatConfig.baidu_API;
			String baiduAK = AddrLngLatConfig.baidu_AK;
			String param = AddrLngLatConfig.baidu_param;
			
			String res=UrlUtils.getResult(baiduAPI, String.format(param, address,baiduAK));
		
			System.out.println(res);
		
			JSONObject jsonObject=JSON.parseObject(res);
			JSONObject resultJson=(JSONObject)jsonObject.get("result");
		    locationJson=(JSONObject)resultJson.get("location");
		    Object lngObj = locationJson.get("lng");
		    Object latObj = locationJson.get("lat");
		   /* System.out.println(locationJson.toString());
		    
		    JSONObject locationObject=JSON.parseObject(locationJson.toString());
		    JSONObject lngObj = (JSONObject)locationObject.get("lng");
		    JSONObject latObj = (JSONObject)locationObject.get("lat");*/
//		    System.out.println(lngObj);
//		    System.out.println(latObj);
		    System.out.println(lngObj+"_"+latObj);
		    
    	} catch (Exception e) {
    		locationJson.put("error", "0");
    	    e.printStackTrace();
		}
		return locationJson;
	}
	
	
	public static  String transAddToLngLat(String address) {
		
		JSONObject locationJson =new JSONObject();
		try {
			String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
			Pattern p = Pattern.compile(regEx); 
			Matcher m = p.matcher(address);
		    address=m.replaceAll("").trim();
			address=address.replaceAll(" ", "");
			int addrLen=address.getBytes("gbk").length;
			if (addrLen>84) {
				address=address.substring(0,42);
			 }	
			String baiduAPI = AddrLngLatConfig.baidu_API;
			String baiduAK = AddrLngLatConfig.baidu_AK;
			String param = AddrLngLatConfig.baidu_param;
			
			String res=UrlUtils.getResult(baiduAPI, String.format(param, address,baiduAK));
		
			// System.out.println(res);
		
			JSONObject jsonObject=JSON.parseObject(res);
			JSONObject resultJson=(JSONObject)jsonObject.get("result");
		    locationJson=(JSONObject)resultJson.get("location");
		    /*Object lngObj = locationJson.get("lng");
		    Object latObj = locationJson.get("lat");
		    System.out.println(lngObj+"_"+latObj);*/
		    
    	} catch (Exception e) {
    		return "NON_NON";
		}
		Object lngObj = locationJson.get("lng");
	    Object latObj = locationJson.get("lat");
	    String xx = lngObj+"_"+latObj;
		return xx ;
	}

	
	

	     
}
