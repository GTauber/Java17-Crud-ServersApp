package com.gtauber.serverapp.model;

import com.gtauber.serverapp.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "IP Address is required")
    private String ipAddress;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

}
