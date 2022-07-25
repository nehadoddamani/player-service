package com.example.player.service.impl;

import com.example.player.csv.CsvService;
import com.example.player.entities.EntityModelMapper;
import com.example.player.entities.PlayerEntity;
import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.models.PlayerModel;
import com.example.player.repos.PlayerRepository;
import com.example.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    CsvService csvService;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<PlayerModel> getAllPlayers() {
        List<PlayerEntity> playerEntityList = playerRepository.getPlayers().stream().collect(Collectors.toList());
        List<PlayerModel> playerModelList = new ArrayList<>();
        if(playerEntityList.size()>0) {
            for(PlayerEntity playerEntity: playerEntityList){
                playerModelList.add(new PlayerModel(playerEntity.getId(), playerEntity.getFirstName()));
            }
            return playerModelList;
        }
        else
            throw new NoPlayersFoundException();
    }

    @Override
    public PlayerModel getPlayerById(String playerId) {
        PlayerEntity playerEntity = playerRepository.getPlayer(playerId);
        if(playerEntity!=null)
            return EntityModelMapper.mapPlayerModel(playerEntity);
        else
            throw new PlayerNotFoundException(playerId);
    }

}
