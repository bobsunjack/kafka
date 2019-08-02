package com.example.security2.filter;

import org.springframework.web.filter.GenericFilterBean;
import sun.misc.BASE64Encoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BeforeLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String auth = httpRequest.getHeader("Authorization");
        System.out.println(base64Encode("test:test".getBytes()));
        System.out.println( httpRequest.getRequestURL());
        if (auth == null) {
            httpRequest.getRequestURL();
        }
        // 继续调用 Filter 链
        filterChain.doFilter(servletRequest, servletResponse);
    }


    public static String base64Encode(byte[] bstr) {
        String strEncode = new BASE64Encoder().encode(bstr);
        return strEncode;
    }
}
