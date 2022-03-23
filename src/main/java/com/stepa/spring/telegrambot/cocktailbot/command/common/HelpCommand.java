package com.stepa.spring.telegrambot.cocktailbot.command.common;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.command.subscription.SubscribeCommand;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.*;
import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Дотупные команды</b>✨\n\n"

                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - найти рецепт коктейля по названию\n"
                    + "%s - найти рецепт коктейля по ингредиентам\n"
                    + "%s - подписаться на рассылку\n"
                    + "%s - отписаться от рассылки\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(),
            NAME.getCommandName(), INGR.getCommandName(), SUBSCRIBE.getCommandName(), UNSUBSCRIBE.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), HELP_MESSAGE);
    }
}
