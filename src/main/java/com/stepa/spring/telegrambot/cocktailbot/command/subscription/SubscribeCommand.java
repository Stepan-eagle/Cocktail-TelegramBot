package com.stepa.spring.telegrambot.cocktailbot.command.subscription;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class SubscribeCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String MESSAGE = "Подписка оформлена";

    public SubscribeCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setSubs(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setSubs(true);
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(getChatId(update), MESSAGE);
    }
}
