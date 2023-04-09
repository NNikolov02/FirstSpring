package com.firstspring.firstspring.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Builder
public class Address {
    @GeneratedValue
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String street;
    private int number;
    @OneToOne(mappedBy = "address")
    private Person person;

    public Address() {
    }

}