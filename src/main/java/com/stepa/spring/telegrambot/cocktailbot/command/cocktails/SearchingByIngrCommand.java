package com.stepa.spring.telegrambot.cocktailbot.command.cocktails;

import com.stepa.spring.telegrambot.cocktailbot.command.Command;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import com.stepa.spring.telegrambot.cocktailbot.service.DBCocktailsService;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

public class SearchingByIngrCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final DBCocktailsService dbCocktailsService;

    public final static String MESSAGE = "Запрос не прочитан. Введите минимум 2  и максимум 6 разных" +
                                         " ингредиентов через запятую.";
    public final static String NULL_MESSAGE = "К сожалению, коктейлей с такими ингредиентами не найдено.";
    public final static String REPEAT_MESSAGE = "Введите ингредиенты. " +
                                                "Для смены условий поиска воспользуйтесь командным списком.";

    public SearchingByIngrCommand(SendBotMessageService sendBotMessageService, DBCocktailsService dbCocktailsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.dbCocktailsService = dbCocktailsService;
    }

    private String[] removeDuplicates(String[] array) {
        HashSet<String> hash = new HashSet<>();
        for (int i = 0 ; i < array.length; i++) {
            array[i] = hash.add(array[i]) ? array[i] : null;
        }
        String arr[] = hash.toArray(new String[hash.size()]);
        return arr;
    }

    @Override
    public void execute(Update update) {
        String[] retval = update.getMessage().getText().split("[^A-Za-zА-Яа-я]+");
        String[] ingredients = removeDuplicates(retval);
        for (int t = 0; t < ingredients.length; t++) {
            ingredients[t] = "%" + ingredients[t].toLowerCase() + "%";
            System.out.println("Ищем  " + ingredients[t]);
        }
        if (ingredients.length > 1 & ingredients.length < 6) {
            List<DBCocktails> retrieveCocktail = null;
            if (ingredients.length == 2) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyTwoIngr(ingredients[0], ingredients[1]);
            }
            if (ingredients.length == 3) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyThreeIngr(ingredients[0], ingredients[1],
                        ingredients[2]);
            }
            if (ingredients.length == 4) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyFourIngr(ingredients[0], ingredients[1],
                        ingredients[2], ingredients[3]);
            }
            if (ingredients.length == 5) {
                retrieveCocktail = dbCocktailsService.retrieveCocktailsbyFiveIngr(ingredients[0], ingredients[1],
                        ingredients[2], ingredients[3], ingredients[4]);
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
