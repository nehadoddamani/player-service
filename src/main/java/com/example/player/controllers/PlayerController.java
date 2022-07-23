package com.example.player.controllers;

import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/players")
public class PlayerController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("")
    public Object getAllPlayers(){
        try {
            return playerService.getAllPlayers();
        }catch (NoPlayersFoundException e){
            LOGGER.error("No players found");
            return e.getMessage();
        }
    }

    @GetMapping("/{playerId}")
    public Object getPlayerById(@PathVariable("playerId") String playerId) {
        try {
            return playerService.getPlayerById(playerId);
        }catch (PlayerNotFoundException e){
            LOGGER.error("Player with id "+playerId+" not found ");
            return e.getMessage();
        }
    }

}
