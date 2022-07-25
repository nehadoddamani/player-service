package com.example.player.loaddata;

import com.example.player.entities.PlayerEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class LoadDataConfiguration {

    @Bean
    public Map<String, PlayerEntity> playersMap(){
        Map<String, PlayerEntity> map = new LinkedHashMap<>();
        return map;
    }

}
