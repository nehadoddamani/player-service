package com.example.player.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    private String playerId;

    public PlayerNotFoundException(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String getMessage() {
        return "Player with id " + playerId + " is not available!";
    }
}
