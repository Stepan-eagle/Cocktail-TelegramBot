package com.stepa.spring.telegrambot.cocktailbot.repository;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TelegramUserRepository extends CrudRepository<TelegramUser, Long> {
    List<TelegramUser> findAllByActiveTrue();
    List<TelegramUser> findAllBySubsTrue();

}