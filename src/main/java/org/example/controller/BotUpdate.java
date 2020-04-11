package org.example.controller;

import org.example.bl.FileWriterLogs;
import org.example.dao.HtmlReader;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.example.repo.Article;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class BotUpdate {
    public static void runUpdate(Update update) throws FileNotFoundException {
        Bot bot = new Bot();
        if(update.hasCallbackQuery()){

            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            User user = callbackQuery.getFrom();
            FileWriterLogs.writeToLog(user.getId()+ " "+ data + " " + LocalDateTime.now().toString() + "\n" + "  ");

            Message message1 = callbackQuery.getMessage();
            switch (data) {
                case "/top10" :
                    bot.sendMsgNoReplyNoButton(message1, "Вывожу статистику:");
                    List<Article> list = HtmlReader.getOfficialTable();
                    for(int i = 0; i < 10; i++) {
                        bot.sendMsgNoReplyNoButtonTop10(message1,list.get(i).toString());
                    }
                    bot.sendMsgNoReply(message1, "\u2063                                Меню                 \u2063");
                    break;
                case "/kyrgyzstan" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по Кыргызстану:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Kyrgyzstan", "Asia").toString());
                    break;
                case "/russia" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по России:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Russia", "Europe").toString());
                    break;
                case "/kazakhstan" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по Казахстану:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Kazakhstan", "Asia").toString());
                    break;
                case "/uzbekistan" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по Узбекистану:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Uzbekistan", "Asia").toString());
                    break;
                case "/china" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по Китаю:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("China", "Asia").toString());
                    break;
                case "/turkey" :
                    bot.sendMsgNoReplyNoButton(message1, "Статистика по Турции:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Turkey", "Asia").toString());
                    break;
            }
        } else {
            if (update.getMessage().getText().contains("Меню") ||
                    update.getMessage().getText().contains("меню") ||
                    update.getMessage().getText().contains("Menu") ||
                    update.getMessage().getText().contains("menu") ||
                    update.getMessage().getText().contains("/start")){
                bot.sendMsgNoReply(update.getMessage(),"\u2063                                Меню                 \u2063");
            }
        }
    }
}
