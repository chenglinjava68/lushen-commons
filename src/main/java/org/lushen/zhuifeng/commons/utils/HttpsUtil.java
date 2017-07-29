package org.lushen.zhuifeng.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.lushen.zhuifeng.commons.enums.HttpMethod;

/**
 * HTTPS请求工具类
 */
public final class HttpsUtil {

	/**
	 * 发起https请求
	 * 
	 * @param requestUrl	请求地址
	 * @param method		请求方式
	 * @param outputStr		提交的数据
	 * 
	 * @return JSONObject 失败返回null
	 */
	public static final String httpRequest(String requestUrl, HttpMethod method, String outputStr) {
		
		try {
			
			//SSL
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//Connection
			HttpsURLConnection conn = (HttpsURLConnection) new URL(requestUrl).openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method.name());

			if (HttpMethod.GET.name().equals(method.name())) {
				conn.connect();
			}
			
			//写出数据
			if (outputStr != null) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("UTF-8"));
				os.flush();
				os.close();
			}

			//接收响应数据
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			isr.close();
			is.close();
			conn.disconnect();
			
			return sb.toString();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}
	
	private static class MyX509TrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

}