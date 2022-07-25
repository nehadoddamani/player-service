package com.example.player.service.impl;

import com.example.player.constants.MessageConstants;
import com.example.player.csv.CsvService;
import com.example.player.entities.PlayerEntity;
import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.models.PlayerModel;
import com.example.player.repos.PlayerRepository;
import com.opencsv.CSVReader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @InjectMocks
    @Spy
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void getAllPlayersTest() {
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        playerEntityList.add(new PlayerEntity("PLYR001","James","Walker"));
        when(playerRepository.getPlayers()).thenReturn(playerEntityList);
        Assert.assertTrue(playerService.getAllPlayers().size()>0);
    }

    @Test
    public void getPlayerByIdTest() {
        when(playerRepository.getPlayer("PLYR001")).thenReturn(new PlayerEntity("PLYR001","James","Walker"));
        Assert.assertEquals(playerService.getPlayerById("PLYR001").toString(),"PlayerModel(id=PLYR001, firstName=James, lastName=Walker, fullName=James Walker)");
    }
}
