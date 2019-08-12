import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class UrlUtils {
	
	
	public static String getResult(String urlStr, String content) throws IOException  {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(100000);
            connection.setReadTimeout(300000);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(content.getBytes("utf-8"));
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
                throw new IOException(e);
        } finally {
            if (connection != null) {
            	connection.disconnect();
            }
        }
    }
	
	public static String getResult2(String urlStr,String m) throws IOException  {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(100000);
            connection.setReadTimeout(300000);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
               if(line.contains(m)){
            	   System.out.println(line);
            	   break;
               }
            }
            reader.close();
            return line;
        } catch (IOException e) {
                throw new IOException(e);
        } finally {
            if (connection != null) {
            	connection.disconnect();
            }
        }
    }
	
	public static String getResult3(String urlStr, String content) throws IOException  {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", " application/json");
            connection.setConnectTimeout(100000);
            connection.setReadTimeout(300000);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(content.getBytes("utf-8"));
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
                throw new IOException(e);
        } finally {
            if (connection != null) {
            	connection.disconnect();
            }
        }
    }
	
	
	public static String getResultHttps(String urlStr,String m) throws IOException  {
		
		String resultStr=null;
        String https = "https://fin-dataservice.shuyun.com/finance/1.0/query?ids=17801001173&start_date=2016-01-01%2000:00:00&end_date=2018-07-31%2000:00:00";
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            HttpsURLConnection conn = (HttpsURLConnection) new URL(https).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);}
          resultStr=sb.toString();
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
	
	public static String getSplitName(String mobile){
		mobile = mobile+"00000000000";
		return mobile.substring(3, 10);
	}
	
	public static String getPathOfName(String basePath,String mobile){
		String name = getSplitName(mobile);
		return String.format("%s/%s/%s", basePath,name.substring(0, 3),name.substring(3, 5));
	}
	public static String getFileOfName(String basePath,String mobile){
		String name = getSplitName(mobile);
		return String.format("%s/%s/%s/%s.dat", basePath,name.substring(0, 3),name.substring(3, 5),name.substring(5, 7));
	}
	static String getFileName(String m){
		return getFileOfName("http://127.0.0.1:8999/df", m);
	}
	
	
	
}
