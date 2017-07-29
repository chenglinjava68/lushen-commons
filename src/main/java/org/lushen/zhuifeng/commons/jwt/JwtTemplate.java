package org.lushen.zhuifeng.commons.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import net.sf.json.JSONObject;

/**
 * jwt授权认证服务
 * 
 * @author hlm
 */
public final class JwtTemplate {
	
	private Log log = LogFactory.getLog(getClass());

	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
	
	private Key secretKey;

	private String jwtheader;
	
	private String jwtcookie;
	
	private String jwtsecret;
	
	private int expiredSecond;

	public JwtTemplate(String jwtheader, String jwtcookie, String jwtsecret, int expiredSecond) {
		super();
		this.jwtheader = jwtheader;
		this.jwtcookie = jwtcookie;
		this.jwtsecret = jwtsecret;
		this.expiredSecond = expiredSecond;
		this.secretKey = new SecretKeySpec(this.jwtsecret.getBytes(), this.signatureAlgorithm.getJcaName());
	}

	/**
	 * 获取jwt授权token
	 * 
	 * @param jwtAuthorization	授权加密信息对象
	 * 
	 * @return String jwt授权token
	 */
	@SuppressWarnings("unchecked")
	public String generateToken(JwtAuthorizedUser authorizedUser) {
		return Jwts.builder()
					.setClaims(JSONObject.fromObject(authorizedUser))
					.setSubject(authorizedUser.getUniqueID())
					.setExpiration(new Date(System.currentTimeMillis() + this.expiredSecond*1000L))
					.signWith(this.signatureAlgorithm, this.secretKey)
					.compact();
	}
	
	/**
	 * 解析授权token为加密前的对象
	 * 
	 * @param token	jwt授权token
	 * @param clazz 
	 * 
	 * @return <T extends JwtAuthorization> 授权加密信息对象
	 * 
	 * @exception RuntimeException 解析失败抛出异常
	 */
	public <T extends JwtAuthorizedUser> T resolveToken(String token, Class<T> clazz) {
		try {
			
			Claims claims = Jwts.parser()
	                .setSigningKey(this.secretKey)
	                .parseClaimsJws(token)
	                .getBody();
			
			T authorization = clazz.newInstance();
			BeanUtils.copyProperties(authorization, claims);
			
			if(!StringUtils.equals(claims.getSubject(), authorization.getUniqueID())) {
				throw new SignatureException("subjectID error, subject=" + claims.getSubject() + ", uniqueId=" + authorization.getUniqueID());
			}
			
			return authorization;
			
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从HTTP请求Header中读取token
	 * 
	 * @param request
	 * 
	 * @return String	jwt授权token
	 */
	public String readTokenFromHeader(HttpServletRequest request) {
		return request.getHeader(this.jwtheader);
	}
	
	/**
	 * 从HTTP请求Cookies中读取token
	 * 
	 * @param request
	 * 
	 * @return String	jwt授权token
	 */
	public String readTokenFromCookie(HttpServletRequest request) {
		for(Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals(this.jwtcookie)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 将token写出到cookie中
	 * 
	 * @param response
	 * @param token
	 */
	public void writeTokenIntoCookie(HttpServletResponse response, String token) {
		Cookie cookie = new Cookie(this.jwtcookie, token);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(this.expiredSecond);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
