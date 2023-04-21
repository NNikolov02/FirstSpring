package com.firstspring.firstspring.Web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CoolHeaderPrinter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getHeader("CoolHeader") != null) {
            System.out.println(httpRequest.getHeader("CoolHeader"));
        }

        chain.doFilter(request, response);
    }

}
