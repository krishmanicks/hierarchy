package com.zoho.hierarchy;

import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/api") //No I18N
public class RESTAPIController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> listZohoServices() {
        List<String> services = new ArrayList<>();
        services.add("Zoho Stratus"); //No I18N
        services.add("Zoho Nimbus"); //No I18N
        services.add("Zoho Pagir"); //No I18N

        return services;
    }
}