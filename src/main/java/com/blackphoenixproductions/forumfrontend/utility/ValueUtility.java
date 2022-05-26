package com.blackphoenixproductions.forumfrontend.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueUtility {

    private final String buildVersion;
    private final Boolean isSecureCookie;
    private final String domain;
    private final String sseBackend;
    private final String backendPath;

    @Autowired
    public ValueUtility(@Value("${build.version}") String buildVersion, @Value("#{new Boolean('${secure.cookie}')}") Boolean isSecureCookie,
                        @Value("${domain}") String domain, @Value("${sse.backend}") String sseBackend, @Value("${backend.path}") String backendPath) {
        this.buildVersion = buildVersion;
        this.isSecureCookie = isSecureCookie;
        this.domain = domain;
        this.sseBackend = sseBackend;
        this.backendPath = backendPath;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public Boolean isSecureCookie() {
        return isSecureCookie;
    }

    public String getDomain() {
        return domain;
    }

    public String getSseBackend() {
        return sseBackend;
    }

    public String getBackendPath() {
        return backendPath;
    }
}
