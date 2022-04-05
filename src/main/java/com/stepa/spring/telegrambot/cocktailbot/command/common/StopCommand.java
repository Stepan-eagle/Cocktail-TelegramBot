package com.stepa.spring.telegrambot.cocktailbot.command.common;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class StopCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Пока \uD83D\uDE1F"; // = Пока 😟

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), STOP_MESSAGE);
        // при использовании команды /stop пользователь в бд помечается, как неактивный и отменяется подписка
        telegramUserService.findByChatId(getChatId(update))
                .ifPresent(it -> {
                    it.setActive(false);
                    it.setSubs(false);
                    telegramUserService.save(it);
                });
    }
}
