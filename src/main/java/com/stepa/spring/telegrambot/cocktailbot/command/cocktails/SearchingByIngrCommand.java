package com.stepa.spring.telegrambot.cocktailbot.command.cocktails;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import com.stepa.spring.telegrambot.cocktailbot.service.DBCocktailsService;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Stream;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class SearchingByIngrCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final DBCocktailsService dbCocktailsService;
    public final static String MESSAGE = "Запрос не прочитан. Введите минимум 2  и максимум 6 ингредиентов через запятую.";
    public final static String NULL_MESSAGE = "К сожалению, коктейлей с такими ингредиентами не найдено.";
    public final static String REPEAT_MESSAGE = "Введите ингредиенты. " +
            "Для смены условий поиска воспользуйтесь командным списком.";

    public SearchingByIngrCommand(SendBotMessageService sendBotMessageService, DBCocktailsService dbCocktailsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.dbCocktailsService = dbCocktailsService;
    }

    @Override
    public void execute(Update update) {
        String[] retval = update.getMessage().getText().split("[^A-Za-zА-Яа-я]+");

        for (int t = 0; t < retval.length; t++) {
            retval[t] = "%" + retval[t].toLowerCase() + "%";
            System.out.println("Ищем  " + retval[t]);
        }
        if (retval.length > 1 & retval.length < 6) {
            List<DBCocktails> retrieveCocktail = null;
            if (retval.length == 2) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyTwoIngr(retval[0], retval[1]);
            }
            if (retval.length == 3) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyThreeIngr(retval[0], retval[1], retval[2]);
            }
            if (retval.length == 4) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyFourIngr(retval[0], retval[1], retval[2], retval[3]);
            }
            if (retval.length == 5) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyFiveIngr(retval[0], retval[1], retval[2], retval[3], retval[4]);
            }
            if (retrieveCocktail.size()==0) {
                sendBotMessageService.sendMessage(getChatId(update), NULL_MESSAGE);
                sendBotMessageService.sendMessage(getChatId(update), REPEAT_MESSAGE);
            } else {
                String[] fullRecipe = new String[retrieveCocktail.size()];
                for (int i = 0; i < retrieveCocktail.size(); i++) {
                    fullRecipe[i] = retrieveCocktail.get(i).getName()
                            + "\nИнгредиенты: " + retrieveCocktail.get(i).getIngr()
                            + ".\n" + retrieveCocktail.get(i).getPick()
                            + ".\nТехника приготовления: " + retrieveCocktail.get(i).getTechnique();
                    sendBotMessageService.sendMessage(getChatId(update), fullRecipe[i]);
                }
                sendBotMessageService.sendMessage(getChatId(update), REPEAT_MESSAGE);
            }
        } else sendBotMessageService.sendMessage(getChatId(update), MESSAGE);
    }
}
