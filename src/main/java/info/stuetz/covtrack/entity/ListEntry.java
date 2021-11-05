package info.stuetz.covtrack.entity;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
public class ListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String telephoneNo;

//    @JsonbTransient
    @JsonbDateFormat("dd.MM.yyyy HH:mm")
    private Instant timestamp;

//    /**
//     * CET ... central european time)
//     */
//    @JsonbProperty("timestamp")
//    public String timestampCET() {
//        return LocalDateTime
//                .ofInstant(timestamp, ZoneId.of("Europe/Vienna"))
//                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }

    public ListEntry() {
        this.timestamp = Instant.now();
    }

    public ListEntry(String firstName, String lastName, String email, String telephoneNo) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNo = telephoneNo;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public Instant getTimestamp() {
        //return timestamp.atZone(ZoneId.of("Europe/Vienna")).toInstant();
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        //return this.getTimestamp() + " - " + this.getLastName() + ", " + this.getFirstName() + " - Tel.: " + this.getTelephoneNo();
        return this.getTimestamp().atZone(ZoneId.of("Europe/Vienna")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                + " " + this.getFirstName()
                + " " + this.getLastName().substring(0,1) + "."
                //+ ", " + this.getTelephoneNo()
                //+ " (" + this.getEmail() + ")"
                ;
    }
}
