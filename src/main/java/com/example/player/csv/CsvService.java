package com.example.player.csv;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvService {

    public CSVReader getCsvReader(String fileName) {
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return csvReader;
    }

}
