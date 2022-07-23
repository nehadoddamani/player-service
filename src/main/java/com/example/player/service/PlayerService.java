package com.example.player.service;

import com.example.player.models.PlayerModel;

import java.util.List;

public interface PlayerService {

    List<PlayerModel> getAllPlayers();

    PlayerModel getPlayerById(String playerId);
}
