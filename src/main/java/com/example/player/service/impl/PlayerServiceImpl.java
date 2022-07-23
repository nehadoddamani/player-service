package com.example.player.service.impl;

import com.example.player.csv.CsvService;
import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.models.PlayerModel;
import com.example.player.service.PlayerService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    CsvService csvService;

    @Override
    public List<PlayerModel> getAllPlayers() {
        List<PlayerModel> playerModelList = null;
        try {
            CSVReader reader = csvService.getCsvReader();
            playerModelList = getPlayersList(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(playerModelList.size()>0)
            return playerModelList;
        else
            throw new NoPlayersFoundException();
    }

    @Override
    public PlayerModel getPlayerById(String playerId) {
        PlayerModel playerModel = null;
        try{
            CSVReader reader = csvService.getCsvReader();
            playerModel = searchPlayerModel(reader, playerId);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(playerModel!=null)
            return playerModel;
        else
            throw new PlayerNotFoundException(playerId);
    }

    List<PlayerModel> getPlayersList(CSVReader csvReader){
        List<PlayerModel> playerModelList = new ArrayList<>();
        String[] record;
        try {
            while ((record = csvReader.readNext()) != null) {
                playerModelList.add(new PlayerModel(record[0], record[1], record[2]));
            }
        }catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        return playerModelList;
    }

    PlayerModel searchPlayerModel(CSVReader reader, String playerId){
        PlayerModel playerModel = null;
        String[] record;

        try {
            while ((record = reader.readNext()) != null) {
                if (record[0].equals(playerId)) {
                    playerModel = new PlayerModel(record[0], record[1], record[2]);
                    break;
                }
            }
        }catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        return playerModel;
    }

}
