package com.xiajr.masterblog.shiro;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author xiajr
 */
public class AuthenticatedFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

    }
}
