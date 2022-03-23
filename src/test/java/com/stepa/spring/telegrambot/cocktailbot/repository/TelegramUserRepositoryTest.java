package com.stepa.spring.telegrambot.cocktailbot.repository;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

 //* Integration-level testing for {@link TelegramUserRepository}.


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelegramUserRepositoryTest {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Test
    @Sql(scripts = {"/sql/clear_tguser.sql", "/sql/telegram_users.sql"})
    public void shouldProperlyFindAllActiveUsers() {
        //when
        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();
        System.out.println("Пользователи "+users.size());
        //then
        Assertions.assertEquals(5, users.size());

        //when
        List<TelegramUser> subs = telegramUserRepository.findAllBySubsTrue();
        //then
        System.out.println("Подписчики "+subs.size());
        Assertions.assertEquals(3, subs.size());
    }

    @Test
    @Sql(scripts = {"/sql/clear_tguser.sql"})
    public void ShouldProperlySaveTelegramUser() {
        //given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(12089898L);
        telegramUser.setActive(false);
        telegramUserRepository.save(telegramUser);

        //when
        Optional<TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChatId());

        //then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }
}
