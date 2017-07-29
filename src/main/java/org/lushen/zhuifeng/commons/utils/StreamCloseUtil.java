package org.lushen.zhuifeng.commons.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 关闭IO流 工具类
 *
 * @author hlm
 */
public final class StreamCloseUtil {
	
	private static Log log = LogFactory.getLog(StreamCloseUtil.class);

	public static final void close(Reader... readers){
		try {
			for(Reader reader : readers) {
				if(reader!=null) {
					reader.close();
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static final void close(Writer... writers){
		try {
			for(Writer writer : writers) {
				if(writer!=null) {
					writer.close();
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static final void close(InputStream... inputStreams){
		try {
			for(InputStream is : inputStreams) {
				if(is!=null) {
					is.close();
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static final void close(OutputStream... outputStreams){
		try {
			for(OutputStream os : outputStreams) {
				if(os!=null) {
					os.close();
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

}
