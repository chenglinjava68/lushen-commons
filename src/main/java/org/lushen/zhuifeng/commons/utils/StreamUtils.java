package org.lushen.zhuifeng.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 流 工具类
 * 
 * @author hlm
 */
public class StreamUtils {

	/**
	 * 读取输入流为字符串
	 */
	public static final String readString(InputStream inputStream, String charset) throws IOException {
			
		StringBuffer sb = new StringBuffer();
		
		byte[] bytes = new byte[1024];
		int size = 0;
		while ((size = inputStream.read(bytes)) > 0) {
			sb.append(new String(bytes, 0, size, charset));
		}
		
		closeInputStream(inputStream);
		
		return sb.toString();
	}
	
	/**
	 * 读取输入流为byte[]
	 */
	public static final byte[] readBytes(InputStream inputStream) throws IOException {
			
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int ch = 0;
        while ((ch = inputStream.read(buffer)) != -1) {
        	outputStream.write(buffer, 0, ch);
        }  
        outputStream.flush();
        
        byte[] bytes = outputStream.toByteArray();
		
		closeInputStream(inputStream);
		closeOutputStream(outputStream);
		
		return bytes;
	}
	
	/**
	 * 读取文件为byte[]
	 */
	public static final byte[] readBytes(File file) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int ch = 0;
        while ((ch = inputStream.read(buffer)) != -1) {
        	outputStream.write(buffer, 0, ch);
        }  
        outputStream.flush();
        
        byte[] bytes = outputStream.toByteArray();
		
		closeInputStream(inputStream);
		closeOutputStream(outputStream);
		
		return bytes;
	}
	
	/**
	 * 写出文件byte[]
	 * 
	 * @param file
	 * @param bytes
	 * @throws Exception
	 */
	public static final void writeFile(File file, byte[] bytes) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(bytes);
		fileOutputStream.flush();
		closeOutputStream(fileOutputStream);
	}
	
	/**
	 * 写出文件byte[]
	 * 
	 * @param file
	 * @param bytes
	 * @throws Exception
	 */
	public static final void writeFile(String file, byte[] bytes) throws IOException {
		writeFile(new File(file), bytes);
	}
	
	/**
	 * 关闭输入字节流
	 */
	public static final void closeInputStream(InputStream inputStream) throws IOException {
		if(inputStream != null) {
			inputStream.close();
		}
	}
	
	/**
	 * 关闭输出字节流
	 */
	public static final void closeOutputStream(OutputStream outputStream) throws IOException {
		if(outputStream != null) {
			outputStream.close();
		}
	}

	/**
	 * 关闭输入字符流
	 */
	public static final void closeReader(Reader reader) throws IOException {
		if(reader != null) {
			reader.close();
		}
	}
	
	/**
	 * 关闭输出字符流
	 */
	public static final void closeWriter(Writer writer) throws IOException {
		if(writer != null) {
			writer.close();
		}
	}

}
