package br.com.monee.api.config;

import br.com.monee.api.entity.LevelEntity;
import br.com.monee.api.repository.LevelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LevelSeeder {

    @Bean
    CommandLineRunner initLevels (LevelRepository levelRepository){
        return args -> {
          if(levelRepository.count() == 0){
              levelRepository.save(new LevelEntity(
                      "Iniciante",
                      "Descrição iniciante",
                      0, 999));
              levelRepository.save(new LevelEntity(
                      "Intermediário",
                      "Descrição intermediária",
                      1000, 4999));
              levelRepository.save(new LevelEntity(
                      "Expert",
                      "Descrição expert",
                      5000, 10000));

          }
        };
    }
}
