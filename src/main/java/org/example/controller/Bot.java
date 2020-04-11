package org.example.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.example.controller.BotButtons.setButtons;
import static org.example.controller.BotUpdate.runUpdate;

public class Bot extends TelegramLongPollingBot {

    public void sendMsgNoReplyNoButton(Message message, String text) throws FileNotFoundException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        SendPhoto msg = new SendPhoto().setPhoto("место для Вашей рекламы!", new FileInputStream(new File("classes/reklama.jpg")));
        msg.setChatId(message.getChatId().toString());
        try {
            execute(msg);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgNoReplyNoButtonTop10(Message message, String text) throws FileNotFoundException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgNoReply(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        try {
            runUpdate(update);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void onUpdatesReceived(List<Update> updates) {
//
//    }

    public String getBotUsername() {
        return "Covid_statistics";
    }

    public String getBotToken() {
        return "1160635009:AAEGZxCC1n_kpbfsP199AAJs609y6U4Q2wc";
    }
}
