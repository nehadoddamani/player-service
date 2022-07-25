package com.example.player.entities;

import com.example.player.models.PlayerModel;
import org.modelmapper.ModelMapper;

public class EntityModelMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static PlayerModel mapPlayerModel(PlayerEntity playerEntity) {
        PlayerModel playerModel = modelMapper.map(playerEntity, PlayerModel.class);
        StringBuffer fullName = new StringBuffer(playerEntity.firstName).append(" ").append(playerEntity.lastName);
        playerModel.setFullName(fullName.toString());
        return playerModel;
    }
}
