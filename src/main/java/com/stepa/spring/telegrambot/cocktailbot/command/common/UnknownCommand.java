package com.stepa.spring.telegrambot.cocktailbot.command.common;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class UnknownCommand implements Command {
    public static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), UNKNOWN_MESSAGE);
    }
}
