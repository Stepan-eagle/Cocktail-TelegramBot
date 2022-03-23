package com.stepa.spring.telegrambot.cocktailbot.command.cocktails;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import com.stepa.spring.telegrambot.cocktailbot.service.DBCocktailsService;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class SearchingByNameCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final DBCocktailsService dbCocktailsService;
    public final static String MESSAGE = "Коктейля с таким названием не найдено.";

    @Override
    public void execute(Update update) {
        String request = "%" + update.getMessage().getText().toLowerCase() + "%";
        List<DBCocktails> retrieveCocktail = dbCocktailsService.retrieveCocktailsByName(request);
        if(retrieveCocktail.size()==0){
            sendBotMessageService.sendMessage(getChatId(update), MESSAGE);
        }
        String[] fullRecipe = new String[retrieveCocktail.size()];
        for(int i=0;i<retrieveCocktail.size();i++){
            fullRecipe[i] = retrieveCocktail.get(i).getName()
                    + "\nИнгредиенты: " + retrieveCocktail.get(i).getIngr()
                    + ".\n" + retrieveCocktail.get(i).getPick()
                    + ".\nТехника приготовления: " + retrieveCocktail.get(i).getTechnique();
            sendBotMessageService.sendMessage(getChatId(update), fullRecipe[i]);
        }
        sendBotMessageService.sendMessage(getChatId(update), "Введите новое название коктейля. " +
                "Для смены условий поиска воспользуйтесь командным списком.");
    }

    public SearchingByNameCommand(SendBotMessageService sendBotMessageService, DBCocktailsService dbCocktailsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.dbCocktailsService = dbCocktailsService;
    }
}
