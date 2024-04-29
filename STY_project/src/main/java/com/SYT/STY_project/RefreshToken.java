package com.SYT.STY_project;


import com.SYT.STY_project.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
public class RefreshToken {
    @Id
    private String id;

    @DocumentReference
    private User owner;

}
