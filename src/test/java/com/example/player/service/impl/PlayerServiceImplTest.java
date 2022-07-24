package com.example.player.service.impl;

import com.example.player.constants.MessageConstants;
import com.example.player.csv.CsvService;
import com.example.player.exceptions.PlayerNotFoundException;
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

import java.io.IOException;
import java.io.InputStreamReader;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @InjectMocks
    @Spy
    private PlayerServiceImpl playerService;

    @Mock
    private CsvService csvService;

    String playersFileName = "players.csv";
    String blankFileName = "blank.csv";

    public PlayerServiceImplTest() {
    }


    @Test
    public void getPlayersList() throws IOException {
        when(csvService.getCsvReader(playersFileName)).thenReturn(new CSVReader(new InputStreamReader(new ClassPathResource(playersFileName).getInputStream(), "UTF-8")));
        Assert.assertTrue(playerService.getPlayersList(csvService.getCsvReader(playersFileName)).size()>0);
    }

    @Test
    public void getPlayersListBlankFile() throws IOException {
        when(csvService.getCsvReader(blankFileName)).thenReturn(new CSVReader(new InputStreamReader(new ClassPathResource(blankFileName).getInputStream(), "UTF-8")));
        Assert.assertTrue(playerService.getPlayersList(csvService.getCsvReader(blankFileName)).size()==0);
    }
}
