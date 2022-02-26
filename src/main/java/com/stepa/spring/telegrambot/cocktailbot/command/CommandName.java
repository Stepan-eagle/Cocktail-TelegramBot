package com.stepa.spring.telegrambot.cocktailbot.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nocommand"),
    STAT("/stat"),
    INGR("/ingr"),
    NAME("/name"),
    SEARCHBYNAME("/searchbyname"),
    SEARCHBYINGR("/searchbyingr"),
    SUBSCRIBE("/subscribe"),
    UNSUBSCRIBE("/unsubscribe");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
