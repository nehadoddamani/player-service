package com.example.player.exceptions;

import com.example.player.constants.MessageConstants;

public class NoPlayersFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return MessageConstants.NO_PLAYERS_FOUND;
    }
}
