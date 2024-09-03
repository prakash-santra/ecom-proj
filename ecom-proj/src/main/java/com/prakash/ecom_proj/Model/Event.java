package com.prakash.ecom_proj.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Component
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;//Event Name
    private String brand;//Event Venue
    private String description;//Store Location Here
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;//Event Date
    private int price;//Ticket price
    private String category;//Event Category
    private int stockQuantity;//Total Available Seat
    private Boolean productAvailable;//Ticket Availability

    private String imageName;
    private String imageType;
    @Lob
    private byte[]imageData;
}
