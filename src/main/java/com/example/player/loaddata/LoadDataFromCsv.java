package com.example.player.loaddata;

import com.example.player.csv.CsvService;
import com.example.player.entities.PlayerEntity;
import com.example.player.repos.PlayerRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class LoadDataFromCsv implements ApplicationRunner {

    @Autowired
    private Map<String, PlayerEntity> playersMap;

    @Value("${app.csv.path}")
    private String fileName;

    public static final int idIndex = 0;
    public static final int firstNameIndex = 1;
    public static final int lastNameIndex = 2;

    @Autowired
    CsvService csvService;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CSVReader csvReader = csvService.getCsvReader(fileName);
        try {
            List<String[]> allData = csvReader.readAll();
            for(String[] record: allData){
                if(record[idIndex].length()>1) {
                    playersMap.put(record[idIndex], playerRepository.fetch(record[idIndex], record[firstNameIndex], record[lastNameIndex]));
                }
            }
        }catch (IOException | CsvException e){
            e.printStackTrace();
        }
    }
}
