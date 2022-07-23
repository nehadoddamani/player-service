package com.example.player.csv;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CsvService {

    public CSVReader getCsvReader() {
        CSVReader csvReader = null;
        try {
            InputStream in = new ClassPathResource("players.csv").getInputStream();
            csvReader = new CSVReader(new InputStreamReader(in, "UTF-8"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return csvReader;
    }

}
