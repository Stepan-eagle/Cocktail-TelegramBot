package com.stepa.spring.telegrambot.cocktailbot.service;

public interface SendBotMessageService {

    void sendMessage(Long chatId, String message);
}
