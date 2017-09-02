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

/**
 * XML解析器（Jaxb）
 * 
 * @author hlm
 */
public class JaxbXmlAnnotationParser implements Parser {
	
	private JaxbXmlAnnotationParser() {
		super();
	}

	@Override
	public <F> String parseAsString(F arg) throws Exception {
		StringWriter writer = null;
		try {
			writer = new StringWriter();
			
			Marshaller marshaller = JAXBContext.newInstance(arg.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {  
                public void escape(char[] ch, int start,int length, boolean isAttVal, Writer writer) throws IOException {  
                    writer.write(ch, start, length);  
                }  
            });
			marshaller.marshal(arg, writer);
            
			return writer.toString();
			
		} catch (JAXBException e) {
			throw e;
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <F> F parseAsBean(String arg, Class<F> clazz) throws Exception {
		StringReader reader = null;
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
			reader = new StringReader(arg);
			return (F) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			throw e;
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	/**
	 * 构建单例当前类实例
	 * 
	 * @return Parser
	 */
	public static final Parser build() {
		return JaxbXmlAnnotationParserBuilder.JAXB_XML_ANNOTATION_PARSER;
	}
	
	private enum JaxbXmlAnnotationParserBuilder {
		;
		private static final JaxbXmlAnnotationParser JAXB_XML_ANNOTATION_PARSER = new JaxbXmlAnnotationParser();
		
	}

}
