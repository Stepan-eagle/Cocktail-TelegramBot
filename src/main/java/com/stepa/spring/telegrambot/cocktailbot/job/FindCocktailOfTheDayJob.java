package com.stepa.spring.telegrambot.cocktailbot.job;

import com.stepa.spring.telegrambot.cocktailbot.service.FindCocktailOfTheDayService;
import com.stepa.spring.telegrambot.cocktailbot.service.SendBotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandUtils.getChatId;

@Slf4j
@Component
public class FindCocktailOfTheDayJob {

    private final FindCocktailOfTheDayService findCocktailOfTheDayService;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public FindCocktailOfTheDayJob(FindCocktailOfTheDayService findCocktailOfTheDayService, SendBotMessageService sendBotMessageService) {
        this.findCocktailOfTheDayService = findCocktailOfTheDayService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Scheduled(cron = "${bot.recountNewCocktail}")
    public void findNewCocktail() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Find new cocktail job started.");

        findCocktailOfTheDayService.findCocktail();


        LocalDateTime end = LocalDateTime.now();
        log.info("Find new articles job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));

    }
}
