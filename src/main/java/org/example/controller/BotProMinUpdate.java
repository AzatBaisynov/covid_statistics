package org.example.controller;

import org.example.repo.Article;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotProMinUpdate {
    public static void runUpdateProMin(Update update) {
        Random rd = new Random();
        BotProMin bot = new BotProMin();
        Message message = update.getMessage();


        if (message != null && message.hasText()) {
            if (message.getText().equals("!roll")) {
                bot.sendMsg(message, rd.nextInt(100 - 1) + 1 + "");
            } else if (message.getText().contains("!roll")) {
                LinkedList<Integer> list = new LinkedList<>();
                Pattern pattern = Pattern.compile("\\d+");
                String word = message.getText();
                Matcher matcher = pattern.matcher(word);
                int start = 0;
                while (matcher.find(start)) {
                    String value = word.substring(matcher.start(), matcher.end());
                    int result = Integer.parseInt(value);
                    list.add(result);
                    start = matcher.end();
                }
                if (list.size() == 1) {
                    bot.sendMsg(message, rd.nextInt(list.get(0) - 1) + 1 + "");
                } else if (list.size() == 2 && list.get(0) != list.get(1)) {
                    bot.sendMsg(message, rd.nextInt(list.get(1) - list.get(0)) + list.get(0) + "");
                } else if (list.size() == 2 && list.get(0) == list.get(1)) {
                    bot.sendMsg(message, "" + list.get(0));
                }
            }
        }
    }
}
