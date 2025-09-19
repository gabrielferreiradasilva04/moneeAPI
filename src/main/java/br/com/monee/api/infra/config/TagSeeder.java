package br.com.monee.api.infra.config;

import br.com.monee.api.domain.transaction.tag.TagEntity;
import br.com.monee.api.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagSeeder {
    @Bean
    CommandLineRunner initTags(TagRepository tagRepository){
        return args -> {
            if(tagRepository.count() == 0){
                tagRepository.save(new TagEntity("Gasto supérfluo", null));
                tagRepository.save(new TagEntity("Essencial", null));
                tagRepository.save(new TagEntity("Parcelado", null));
                tagRepository.save(new TagEntity("À vista", null));
                tagRepository.save(new TagEntity("Investimento", null));
                tagRepository.save(new TagEntity("Receita extra", null));
                tagRepository.save(new TagEntity("Reembolso", null));
                tagRepository.save(new TagEntity("Assinatura", null));
                tagRepository.save(new TagEntity("Urgente", null));
                tagRepository.save(new TagEntity("Planejado", null));
                tagRepository.save(new TagEntity("Viagem", null));
                tagRepository.save(new TagEntity("Negócios", null));
                tagRepository.save(new TagEntity("Saúde e bem-estar", null));
                tagRepository.save(new TagEntity("Entretenimento", null));
                tagRepository.save(new TagEntity("Alimentação fora de casa", null));
            }
        };
    }

}
