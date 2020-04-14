package org.example;


import org.example.controller.Bot;
import org.example.controller.BotProMin;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
            telegramBotsApi.registerBot(new BotProMin());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
