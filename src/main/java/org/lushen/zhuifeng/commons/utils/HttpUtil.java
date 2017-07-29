package org.lushen.zhuifeng.commons.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.lushen.zhuifeng.commons.enums.HttpMethod;

/**
 * JDK自带HttpURLConnection发起HTTP
 *
 * @author hlm
 */
public final class HttpUtil {
	
	/**
	 * HTTP GET 请求 （key-value参数）
	 * 
	 * @param url
	 * @param params
	 */
	public static final String getMethodRequest(String url, Map<String, String> params) {
		try {
			
			URL console = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(30*1000);
			conn.setRequestProperty("Accept-Charset", "utf-8");  
			conn.setRequestProperty("contentType", "utf-8");  
			if(params!=null){
				for(String name : params.keySet()){
					conn.setRequestProperty(name, params.get(name));
				}
			}
			
			InputStream is = conn.getInputStream();
			if(is!=null){
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				String result = new String(outStream.toByteArray());
				is.close();
				outStream.close();
				return result;
			}
			
			return null;
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}
	
	/**
	 * Http请求
	 * 
	 * @param url
	 * @param method
	 * @param outputStr
	 * 
	 * @return
	 */
	public static final String httpRequest(String url, HttpMethod method, String outputStr) {
		
		OutputStream os = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod(method.name());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			os = conn.getOutputStream();
			os.write(outputStr.getBytes());
			
			is = conn.getInputStream();
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line=br.readLine())!=null) {
				sb.append(line);
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			StreamCloseUtil.close(os);
			StreamCloseUtil.close(is);
			StreamCloseUtil.close(isr, br);
		}
		
	}

}
