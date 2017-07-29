package org.lushen.zhuifeng.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络图片下载工具类（普通url的文件素材）
 *
 * @author hlm
 */
public final class FileDownloadUtil {
	
	/**
	 * 下载资源文件到指定文件
	 */
	public static final void download(String url, File targetFile) {
		try {
			
			HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(120*1000);
			conn.setRequestMethod("GET");
			conn.setReadTimeout(120*1000);
			
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException(conn.getResponseMessage());
			}
			
			InputStream is = conn.getInputStream();
			int size = 0;
			byte[] bytes = new byte[1024];
			FileOutputStream fos = new FileOutputStream(targetFile);
			while ((size = is.read(bytes)) > 0) {
				fos.write(bytes, 0, size);
			}
			fos.flush();
			fos.close();
			is.close();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}

}
