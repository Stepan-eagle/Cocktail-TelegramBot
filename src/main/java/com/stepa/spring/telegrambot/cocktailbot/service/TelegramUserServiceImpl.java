package com.stepa.spring.telegrambot.cocktailbot.service;

import com.stepa.spring.telegrambot.cocktailbot.repository.TelegramUserRepository;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TelegramUserService}.
 */
@Service
public class TelegramUserServiceImpl implements TelegramUserService{
    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public List<TelegramUser> retrieveAllSubsUsers() {
        return telegramUserRepository.findAllBySubsTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(Long chatId) {
        return telegramUserRepository.findById(chatId);
    }
}
