package com.example.player.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CsvService {

    public CSVReader getCsvReader(String fileName) {
        CSVReader csvReader = null;
        try {
            InputStream in = new ClassPathResource(fileName).getInputStream();
            csvReader = new CSVReaderBuilder(new InputStreamReader(in, "UTF-8")).withSkipLines(1).build();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return csvReader;
    }

}
