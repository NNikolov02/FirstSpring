package com.firstspring.firstspring.model;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Builder
public class Photo {


  public Photo(){}

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String originalFilename;
    private String contentType;

    @ManyToMany(mappedBy = "photos")

    private Set <Person> persons;
    @Lob
    @Column(length = 20971520)
    private byte[] content;

}
