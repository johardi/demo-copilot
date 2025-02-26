package com.example.api;

import com.example.service.OrcidValidator;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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
            return Response.ok(Map.of(
                "orcid", orcid,
                "valid", isValid
            )).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", "Invalid ORCID format"))
                .build();
        }
    }
}
