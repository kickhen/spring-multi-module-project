package com.example.config;

import com.atomikos.remoting.twopc.AtomikosRestPort;
import com.atomikos.remoting.twopc.ParticipantsProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

//    @Bean
//    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
//        ServletContainer servletContainer = new ServletContainer(new JerseyConfig());
//        ServletRegistrationBean<ServletContainer> registration = new ServletRegistrationBean<>(servletContainer);  // 여기 중요!!!
//        registration.setName("jerseyServlet");
//        return registration;
//    }

    public JerseyConfig() {
        register(JacksonJsonProvider.class);
        register(ParticipantsProvider.class);
        register(AtomikosRestPort.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
//        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/app/.*");
    }
}
