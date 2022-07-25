package com.example.player.repos;

import com.example.player.entities.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayerRepository {

    @Autowired
    private Map<String, PlayerEntity> playersMap;

    public PlayerEntity fetch(String id, String firstName, String lastName){
        return new PlayerEntity(id, firstName, lastName);
    }

    public PlayerEntity getPlayer(String id){
        return playersMap.get(id);
    }

    public List<PlayerEntity> getPlayers(){
        return playersMap.values().stream().collect(Collectors.toList());
    }
}
