package info.stuetz.covtrack.boundary;

import info.stuetz.covtrack.entity.ListEntry;
import info.stuetz.covtrack.repository.ListEntryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/api/listentry")
public class ListEntryResource {
    @Inject
    ListEntryRepository repo;

    // Get all

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ListEntry> getAll() {
        return this.repo.listAll();
    }

    /**
     * http :8080/api/listentry Accept:text/plain
     *
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCsv() {

        String header = "id,lastname,firstname,phone,email,timestamp" + System.lineSeparator();

        StringBuilder csvData = new StringBuilder();
        csvData.append(header);

        for (ListEntry listEntry : this.repo.listAll()) {
            String record =
                    listEntry.getId() + "," +
                    listEntry.getLastName() + "," +
                    listEntry.getFirstName() + "," +
                    listEntry.getTelephoneNo() + "," +
                    listEntry.getEmail() + "," +
                    LocalDateTime
                            .ofInstant(listEntry.getTimestamp(), ZoneId.of("Europe/Vienna"))
                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm")) +
                            System.lineSeparator()
            ;
            csvData.append(record);
        }
        return csvData.toString();
    }


    // Insert new
    @POST
    @Transactional
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
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
    @Produces({MediaType.TEXT_HTML})
    public String seeLogs() {
        List<ListEntry> entries = this.repo.listAll();
        StringBuilder response = new StringBuilder();
        response.append("<head><link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic\">");
        response.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css\">");
        response.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/milligram/1.4.1/milligram.css\"></head><body>");
        for (ListEntry entry : entries) {
            response.append(entry.toString()).append("<br>");
        }
        response.append("</body>");
        return response.toString();
    }
}