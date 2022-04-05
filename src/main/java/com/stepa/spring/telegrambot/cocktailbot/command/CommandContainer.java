package com.stepa.spring.telegrambot.cocktailbot.command;

import com.google.common.collect.ImmutableMap;
import com.stepa.spring.telegrambot.cocktailbot.command.annotation.AdminCommand;
import com.stepa.spring.telegrambot.cocktailbot.command.cocktails.*;
import com.stepa.spring.telegrambot.cocktailbot.command.common.*;
import com.stepa.spring.telegrambot.cocktailbot.command.subscription.*;
import com.stepa.spring.telegrambot.cocktailbot.service.*;

import java.util.List;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.*;
import static java.util.Objects.nonNull;

public class CommandContainer {
    private ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            DBCocktailsService dbCocktailsService,
                            List<String> admins) {

        this.admins = admins;
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(INGR.getCommandName(), new IngrCommand(sendBotMessageService))
                .put(NAME.getCommandName(), new NameCommand(sendBotMessageService))
                .put(SEARCHBYNAME.getCommandName(), new SearchingByNameCommand(sendBotMessageService, dbCocktailsService))
                .put(SEARCHBYINGR.getCommandName(), new SearchingByIngrCommand(sendBotMessageService, dbCocktailsService))
                .put(SUBSCRIBE.getCommandName(), new SubscribeCommand(sendBotMessageService, telegramUserService))
                .put(UNSUBSCRIBE.getCommandName(), new UnsubscribeCommand(sendBotMessageService, telegramUserService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
