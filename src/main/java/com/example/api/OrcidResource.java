package com.example.api;

import com.example.service.OrcidValidator;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Path("/orcid")
@Produces(MediaType.APPLICATION_JSON)
public class OrcidResource {
    private final OrcidValidator validator;

    @Inject
    public OrcidResource(OrcidValidator validator) {
        this.validator = validator;
    }

    @GET
    @Path("/validate/{orcid}")
    public Response validateOrcid(@PathParam("orcid") String orcid) {
        try {
            boolean isValid = validator.isValid(orcid);
            Map<String, Object> response = new HashMap<>();
            response.put("orcid", orcid);
            response.put("valid", isValid);
            
            if (isValid) {
                Optional<String> fullName = validator.getFullName(orcid);
                fullName.ifPresent(name -> response.put("fullName", name));
            }

            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", "Invalid ORCID format"))
                .build();
        }
    }
}
