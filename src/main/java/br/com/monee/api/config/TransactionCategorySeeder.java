package br.com.monee.api.config;

import br.com.monee.api.entity.TransactionCategoryEntity;
import br.com.monee.api.repository.TransactionCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionCategorySeeder {

    @Bean
    CommandLineRunner initTransactionsCategorySeeder(TransactionCategoryRepository transactionCategoryRepository){
        return args -> {
            if(transactionCategoryRepository.count() == 0){

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                   "Transporte",
                   "Rceitas ou despesas relacionadas a transporte",
                   null,
                   null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Alimentação",
                        "Gastos com mercado, restaurantes, delivery ou receitas da área de alimentação",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Moradia",
                        "Despesas de aluguel, condomínio, luz, água, internet ou receitas de aluguel de imóveis",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Lazer",
                        "Gastos com viagens, cinema, festas, hobbies ou receitas de eventos/passeios",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Saúde",
                        "Despesas médicas, odontológicas, farmácia ou receitas relacionadas à saúde",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Educação",
                        "Gastos com cursos, mensalidades, livros ou receitas de aulas/treinamentos",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Investimentos",
                        "Aplicações financeiras, compra de ativos ou receitas de rendimentos",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Salário",
                        "Receitas relacionadas a salários, bônus e benefícios",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Impostos e Taxas",
                        "Despesas com impostos, tributos e taxas diversas",
                        null,
                        null
                ));

                transactionCategoryRepository.save(new TransactionCategoryEntity(
                        "Outros",
                        "Receitas ou despesas diversas não categorizadas",
                        null,
                        null
                ));

            }
        };
    }

}
