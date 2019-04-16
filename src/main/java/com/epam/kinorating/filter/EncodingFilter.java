package com.epam.kinorating.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class EncodingFilter implements Filter {
    private static final String ENCODING_PARAM = "encoding";
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING_PARAM);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestEncoding = servletRequest.getCharacterEncoding();

        if(encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
