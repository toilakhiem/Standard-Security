package com.example.standard.Security.Filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        filterChain.doFilter(contentCachingRequestWrapper,contentCachingResponseWrapper);
        String RequestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(),request.getCharacterEncoding());
        String ReponseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(),response.getCharacterEncoding());
        InetAddress address = InetAddress.getLocalHost();
        String ipAddress = address.getHostAddress();
        LOGGER.info("Filter Logs: IP = {}, Method = {}, RequestURI = {}, ResponseBody : {}, ResponseCode : {}, RequestBody : {}",
                address ,request.getMethod(), request.getRequestURI(),  ReponseBody, response.getStatus(), request.getHeader("Authorization")
        );
        contentCachingResponseWrapper.copyBodyToResponse();
    }
    public String getStringValue(byte[] contentAsByteArray, String characterEncoding){
        try {
            return new String(contentAsByteArray,0,contentAsByteArray.length,characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
