package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.common.StartCommand;
import org.junit.jupiter.api.DisplayName;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.START;
import static com.stepa.spring.telegrambot.cocktailbot.command.common.StartCommand.START_MESSAGE;

@DisplayName("Unit-level testing for StartCommand")
class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}