package com.zoho.hierarchy.services;

import com.zoho.hierarchy.JacksonObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class HierarchyWebService extends ResourceConfig {
    public HierarchyWebService() {
        // register custom Jackson mapper
        register(JacksonObjectMapperProvider.class);

        // register Jackson as default JSON library
        register(JacksonFeature.withoutExceptionMappers());
    }
}
