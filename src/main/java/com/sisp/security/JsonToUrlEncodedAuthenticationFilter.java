package com.sisp.security;


import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonToUrlEncodedAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

    static class RequestWrapper extends HttpServletRequestWrapper {
        private final Map<String, Object> requestBody;

        public RequestWrapper(HttpServletRequest request, Map<String, Object> requestBody) {
            super(request);
            this.requestBody = requestBody;
        }

        @Override
        public String getParameter(String name) {
            Object value = requestBody.get(name);
            return value != null ? value.toString() : super.getParameter(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> parameterMap = super.getParameterMap();
            for (Map.Entry<String, Object> entry : requestBody.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof String) {
                        parameterMap.put(key, new String[] { (String) value });
                    } else if (value instanceof List) {
                        List<?> list = (List<?>) value;
                        String[] array = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            array[i] = String.valueOf(list.get(i));
                        }
                        parameterMap.put(key, array);
                    } else {
                        parameterMap.put(key, new String[] { String.valueOf(value) });
                    }
                }
            }
            return parameterMap;
        }
    }
}
