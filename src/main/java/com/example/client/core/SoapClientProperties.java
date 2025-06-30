package com.example.client.core;

import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import java.util.List;

public class SoapClientProperties {

    private String contextPath;
    private String defaultUri;
    private List<ClientInterceptor> interceptors;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getDefaultUri() {
        return defaultUri;
    }

    public void setDefaultUri(String defaultUri) {
        this.defaultUri = defaultUri;
    }

    public List<ClientInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<ClientInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
