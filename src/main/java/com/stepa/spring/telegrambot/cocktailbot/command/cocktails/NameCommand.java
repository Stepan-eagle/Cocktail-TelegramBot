package com.stepa.spring.telegrambot.cocktailbot.command.cocktails;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class NameCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public final static String MESSAGE = "Введите название коктейля";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), MESSAGE);
    }

    public NameCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
}
