package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Field(write = Field.Write.NON_NULL)
    private String password;
    private LocalDateTime dateCreate;
    private LocalDateTime lastUpdate;
    private boolean active;

    public void prePersist() {
        dateCreate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
