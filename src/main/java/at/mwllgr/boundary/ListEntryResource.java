package at.mwllgr.boundary;

import at.mwllgr.entity.ListEntry;
import at.mwllgr.repository.ListEntryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/listentry")
public class ListEntryResource {
    @Inject
    ListEntryRepository repo;

    // Get all
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<ListEntry> getAll() {
        return this.repo.listAll();
    }

    // Insert new
    @POST
    @Transactional
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    public Response addClient(
            @FormParam("first_name") String firstName,
            @FormParam("last_name") String lastName,
            @FormParam("email") String email,
            @FormParam("telephoneNo") String telephoneNo
    ) {
        ListEntry entry = new ListEntry(
                firstName, lastName, email, telephoneNo
        );
        repo.persist(entry);

        //return entry;
        return Response.status(302).header("Location", "/").build();
    }

    @GET
    @Path("html")
    @Produces({ MediaType.TEXT_HTML })
    public String seeLogs() {
        List<ListEntry> entries = this.repo.listAll();
        StringBuilder response = new StringBuilder();
        for (ListEntry entry : entries) {
            response.append(entry.toString()).append("<br>");
        }

        return response.toString();
    }
}