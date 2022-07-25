package com.example.player.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PlayerModel {
    String id;

    String firstName;

    String lastName;

    String fullName;

    public PlayerModel(String id, String firstName){
        setId(id);
        setFirstName(firstName);
    }

}

