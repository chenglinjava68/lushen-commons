package org.lushen.zhuifeng.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON解析器（Jackson）
 * 
 * @author hlm
 */
public class JacksonJsonParser implements Parser {

	private final ObjectMapper mapper = new ObjectMapper();

	private JacksonJsonParser() {
		super();
	};

	@Override
	public <F> String parseAsString(F arg) throws Exception {
		return mapper.writeValueAsString(arg);
	}

	@Override
	public <F> F parseAsBean(String arg, Class<F> clazz) throws Exception {
		return mapper.readValue(arg, clazz);
	}

	/**
	 * 构建单例当前类实例
	 * 
	 * @return Parser
	 */
	public static final Parser build() {
		return JacksonJsonParserBuilder.JACKSON_JSON_PARSER;
	}

	private enum JacksonJsonParserBuilder {
		;
		private static final JacksonJsonParser JACKSON_JSON_PARSER = new JacksonJsonParser();

	}

}
