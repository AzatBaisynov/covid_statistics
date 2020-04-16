package org.example.controller;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



import static org.example.controller.BotProMinUpdate.runUpdateProMin;

public class BotProMin extends TelegramLongPollingBot {
    public DeleteMessage deleteMsg(Message message){
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        return deleteMessage;
    }

    public void sendMsg(Message message, String text){
        String parsedSmile = EmojiParser.parseToUnicode(":red_circle:");

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyToMessageId(message.getMessageId());

        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.enableMarkdown(true);
        sendMessage1.setChatId(message.getChatId().toString());
        sendMessage1.setText(parsedSmile + " Rollin " + parsedSmile);
        sendMessage1.setReplyToMessageId(message.getMessageId());

        try {
            Message sentOutMessage = execute(sendMessage1);
            Thread.sleep(1500);
            execute(deleteMsg(sentOutMessage));
            execute(sendMessage);

        } catch (TelegramApiException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        runUpdateProMin(update);
    }

    @Override
    public String getBotUsername() {
        return "RollBot";
    }

    @Override
    public String getBotToken() {
        return "1103452033:AAGv_WK5FvwY-rTaykKMlN57X3ySHocnZOU";
    }
}
