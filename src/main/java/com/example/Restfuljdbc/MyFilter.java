//package com.example.Restfuljdbc;
//import org.apache.catalina.filters.ExpiresFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Enumeration;
//
//@Component
//public class MyFilter implements Filter{
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Filter here...");
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        Enumeration<String> headerNames = httpRequest.getHeaderNames();
//        boolean access = false;
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String value = httpRequest.getHeader(headerNames.nextElement());
//                System.out.println("Header: " + value);
//                if (value.equals("123")){
//                    access = true;
//                }
//            }
//        }
//        if (!access){
//            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
//
//            return;
//        }
//
//        System.out.println("Access granted");
//        chain.doFilter(request,response);
//    }
//}
