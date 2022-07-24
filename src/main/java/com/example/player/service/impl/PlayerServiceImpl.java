package com.example.player.service.impl;

import com.example.player.csv.CsvService;
import com.example.player.exceptions.NoPlayersFoundException;
import com.example.player.exceptions.PlayerNotFoundException;
import com.example.player.models.PlayerModel;
import com.example.player.service.PlayerService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    public static final int idIndex = 0;
    public static final int firstNameIndex = 1;
    public static final int lastNameIndex = 2;

    public static final String fileName = "players.csv";

    @Autowired
    CsvService csvService;

    @Override
    public List<PlayerModel> getAllPlayers() {
        List<PlayerModel> playerModelList = null;
        try {
            CSVReader reader = csvService.getCsvReader(fileName);
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
            CSVReader reader = csvService.getCsvReader(fileName);
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
        try {
            List<String[]> allData = csvReader.readAll();
            for(String[] record: allData){
                if(record[idIndex].length()>1)
                    playerModelList.add(new PlayerModel(record[idIndex], record[firstNameIndex], record[lastNameIndex]));
            }
        }catch (IOException | CsvException e){
            e.printStackTrace();
        }
        return playerModelList;
    }

    PlayerModel searchPlayerModel(CSVReader reader, String playerId){
        PlayerModel playerModel = null;
        try {
            List<String[]> allData = reader.readAll();
            Optional<String[]> matchedPlayer = allData.stream().filter(playerData -> playerData[idIndex].equals(playerId)).findFirst();
            playerModel = matchedPlayer.isPresent()? new PlayerModel(matchedPlayer.get()[idIndex],matchedPlayer.get()[firstNameIndex],matchedPlayer.get()[lastNameIndex]):null;
        }catch (IOException | CsvException e){
            e.printStackTrace();
        }
        return playerModel;
    }

}
