package com.stepa.spring.telegrambot.cocktailbot.code;

import com.stepa.spring.telegrambot.cocktailbot.command.CommandContainer;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageServiceImpl;
import com.stepa.spring.telegrambot.cocktailbot.service.TelegramUserService;
import com.stepa.spring.telegrambot.cocktailbot.service.DBCocktailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private boolean checkOfIngr=false;
    private boolean checkOfName=false;
    private final CommandContainer commandContainer;
    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("onUpdateReceived");
        System.out.println("Чек нэйм " + checkOfName);
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String user = update.getMessage().getFrom().getUserName();
            System.out.println(user);
            if (message.startsWith(COMMAND_PREFIX)) {
                checkOfName = false;
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                System.out.println(commandIdentifier);
                if(commandIdentifier.equals(INGR.getCommandName())){
                    checkOfIngr=true;
                    commandContainer.retrieveCommand(INGR.getCommandName(),user).execute(update);
                }
                else if (commandIdentifier.equals(NAME.getCommandName())) {
                    checkOfName = true;
                    commandContainer.retrieveCommand(NAME.getCommandName(),user).execute(update);
                } else {
                    commandContainer.retrieveCommand(commandIdentifier,user).execute(update);
                }
            }
            //передача запроса на поиск коктейля
            else if(checkOfIngr){//код по поиску ингредиентов
                commandContainer.retrieveCommand(SEARCHBYINGR.getCommandName(),user).execute(update);
            }
            else if(checkOfName){//код по поиску названия
                commandContainer.retrieveCommand(SEARCHBYNAME.getCommandName(),user).execute(update);
            }
            else {
                commandContainer.retrieveCommand(NO.getCommandName(),user).execute(update);
            }
        }
    }

    public TelegramBot(TelegramUserService telegramUserService, DBCocktailsService dbCocktailsService,@Value("#{'${bot.admins}'.split(',')}") List<String> admins) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, dbCocktailsService, admins);
    }
}
