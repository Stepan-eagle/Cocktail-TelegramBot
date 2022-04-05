package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.common.StopCommand;
import org.junit.jupiter.api.DisplayName;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.STOP;
import static com.stepa.spring.telegrambot.cocktailbot.command.common.StopCommand.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}