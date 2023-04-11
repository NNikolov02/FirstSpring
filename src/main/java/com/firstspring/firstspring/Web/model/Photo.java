package com.firstspring.firstspring.Web.model;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

  @Id
  @GeneratedValue
  @Setter(AccessLevel.NONE)
  private UUID id;

  private String originalFilename;
  private String contentType;

  @ManyToMany(mappedBy = "photos")
  private Set<Person> persons;

  @Lob
  @Column(length = 20971520)
  private byte[] content;


}