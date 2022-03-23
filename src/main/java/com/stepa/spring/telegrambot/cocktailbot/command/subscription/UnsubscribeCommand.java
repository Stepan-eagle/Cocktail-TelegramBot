package com.stepa.spring.telegrambot.cocktailbot.command.subscription;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class UnsubscribeCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String MESSAGE = "Подписка отменена.";

    public UnsubscribeCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), MESSAGE);
        telegramUserService.findByChatId(getChatId(update))
                .ifPresent(it -> {
                    it.setSubs(false);
                    telegramUserService.save(it);
                });
    }
}
