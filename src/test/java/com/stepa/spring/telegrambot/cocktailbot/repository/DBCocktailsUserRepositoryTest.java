package com.stepa.spring.telegrambot.cocktailbot.repository;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import com.stepa.spring.telegrambot.cocktailbot.service.DBCocktailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DBCocktailsUserRepositoryTest {

    @Autowired
    private DBCocktailsUserRepository dbCocktailsUserRepository;

    @Test
    @Sql(scripts = {"/sql/clear_cocktails.sql", "/sql/cocktails.sql"})
    public void shouldProperlyFindCocktails() {
        //when
        List<DBCocktails> a52 = dbCocktailsUserRepository.findByName("%aaa-52%");
        System.out.println("Коктйля А-52 =  "+a52.size());
        //then
        Assertions.assertEquals(1, a52.size());

        //when
        List<DBCocktails> cocktails = dbCocktailsUserRepository.findByIngrByTwo("%absent%", "%cream%");
        System.out.println("Коктейлей %52 =  "+cocktails.size());
        //then
        Assertions.assertEquals(3, cocktails.size());

        //when
        DBCocktails byId = dbCocktailsUserRepository.findById(5000);
        System.out.println("Коктейль с id = 1  "+byId.getName());
        //then
        Assertions.assertEquals("AAA-52", byId.getName());
    }
}
