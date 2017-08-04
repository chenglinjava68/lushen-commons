package org.lushen.zhuifeng.commons.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

@SuppressWarnings("restriction")
public class JaxbUtils {

	/**
	 * xml转换bean
	 * 
	 * @param xml
	 * @param clazz
	 * @return T
	 * @throws JAXBException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T resolveXml2Bean(String xml, Class<T> clazz) throws JAXBException, IOException {
		StringReader reader = null;
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
			reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			throw e;
		} finally {
			StreamCloseUtil.close(reader);
		}
	}
	
	/**
	 * bean转xml
	 * 
	 * @param object
	 * @param clazz
	 * @return String
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static final <T> String resolveBean2Xml(T object, Class<T> clazz) throws JAXBException, IOException {
		StringWriter writer = null;
		try {
			writer = new StringWriter();
			
			Marshaller marshaller = JAXBContext.newInstance(clazz).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {  
                public void escape(char[] ch, int start,int length, boolean isAttVal, Writer writer) throws IOException {  
                    writer.write(ch, start, length);  
                }  
            });
            
			marshaller.marshal(object, writer);
            
			return writer.toString();
			
		} catch (JAXBException e) {
			throw e;
		} finally {
			StreamCloseUtil.close(writer);
		}
	}

}
