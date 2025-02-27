package com.example.api;

import com.example.service.OrcidFormatValidator;
import com.example.service.OrcidApiService;

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
    private final OrcidFormatValidator formatValidator;
    private final OrcidApiService apiService;

    @Inject
    public OrcidResource(OrcidFormatValidator formatValidator, OrcidApiService apiService) {
        this.formatValidator = formatValidator;
        this.apiService = apiService;
    }

    @GET
    @Path("/validate/{orcid}")
    public Response validateOrcid(@PathParam("orcid") String orcid) {
        try {
            boolean isValid = formatValidator.isValid(orcid);
            Map<String, Object> response = new HashMap<>();
            response.put("orcid", orcid);
            response.put("valid", isValid);
            
            if (isValid) {
                Optional<String> fullName = apiService.getFullName(orcid);
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
