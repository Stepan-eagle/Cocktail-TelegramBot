package com.stepa.spring.telegrambot.cocktailbot.command.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

// аннотация для команд админа

@Retention(RUNTIME)
public @interface AdminCommand {
}