package com.example.player.controllers;

import com.example.player.constants.MessageConstants;
import com.example.player.controllers.PlayerController;
import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.models.PlayerModel;
import com.example.player.service.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @InjectMocks
    @Spy
    private PlayerController playerController;

    @Mock
    protected PlayerService playerService;

    @Before
    public void setup(){

    }

    String mockPlayerValue = "PlayerModel(id=PLYR001, firstName=James, lastName=Walker)";

    PlayerModel mockPlayerModel = new PlayerModel("PLYR001","James","Walker");

    @Test
    public void getPlayerByIdTest(){
        when(playerService.getPlayerById("PLR939")).thenThrow(new PlayerNotFoundException("PLR939"));
        Assert.assertEquals(playerController.getPlayerById("PLR939"),"Player with id PLR939 is not available!");
    }

    @Test
    public void getPlayerByIdTestPositive(){
        when(playerService.getPlayerById("PLYR001")).thenReturn(mockPlayerModel);
        Assert.assertEquals(playerController.getPlayerById("PLYR001").toString(),mockPlayerValue);
    }

    @Test
    public void getAllPlayersTest(){
        when(playerService.getAllPlayers()).thenThrow(new NoPlayersFoundException());
        Assert.assertEquals(playerController.getAllPlayers(), MessageConstants.NO_PLAYERS_FOUND);
    }

    @Test
    public void getAllPlayersTestPositive(){
        List<PlayerModel> playerModelList = new ArrayList<>();
        playerModelList.add(mockPlayerModel);
        when(playerService.getAllPlayers()).thenReturn(playerModelList);
        Assert.assertEquals(playerController.getAllPlayers().toString(),"["+mockPlayerValue+"]");
    }
}
