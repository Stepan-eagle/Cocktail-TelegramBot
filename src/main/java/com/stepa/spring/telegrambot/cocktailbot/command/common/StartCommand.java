package com.stepa.spring.telegrambot.cocktailbot.command.common;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет. Я помогу тебе найти коктейль по ингредиентам " +
                                                "или по названию. Я еще маленький и только учусь. " +
                                                "Воспользуйся командой /help, чтобы узнать, какие команды я понимаю.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(getChatId(update), START_MESSAGE);
    }
}
