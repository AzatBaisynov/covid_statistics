package org.example.controller;

import org.example.repo.Article;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Random;

public class BotProMinUpdate {
    public static void runUpdateProMin(Update update) {
        Random rd = new Random();
        BotProMin bot = new BotProMin();
        Message message = update.getMessage();


        if(message != null && message.hasText()) {
            switch (message.getText()) {
                case "/roll":
                    bot.sendMsg(message, rd.nextInt(100-1)+1 + "");
                    break;
            }
        }
    }
}
