package org.example.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class BotButtons {
    static void setButtons(SendMessage sendMessage){
        //button
        InlineKeyboardButton topButton = new InlineKeyboardButton();
        topButton.setText("            Топ 10            ");
        topButton.setCallbackData("/top10");
        InlineKeyboardButton kyrButton = new InlineKeyboardButton();
        kyrButton.setText("       Кыргызстан        ");
        kyrButton.setCallbackData("/kyrgyzstan");
        InlineKeyboardButton russButton = new InlineKeyboardButton();
        russButton.setText("  Россия  ");
        russButton.setCallbackData("/russia");
        InlineKeyboardButton kazButton = new InlineKeyboardButton();
        kazButton.setText("  Казахстан  ");
        kazButton.setCallbackData("/kazakhstan");
        InlineKeyboardButton uzbButton = new InlineKeyboardButton();
        uzbButton.setText("  Узбекистан  ");
        uzbButton.setCallbackData("/uzbekistan");
//        InlineKeyboardButton chiButton = new InlineKeyboardButton();
//        chiButton.setText("  Китай  ");
//        chiButton.setCallbackData("/china");
//        InlineKeyboardButton turButton = new InlineKeyboardButton();
//        turButton.setText("  Турция  ");
//        turButton.setCallbackData("/turkey");
        //
        //row
        List<InlineKeyboardButton> row = new LinkedList<>();
        row.add(topButton);
        List<InlineKeyboardButton> row1 = new LinkedList<>();
        row1.add(kyrButton);
        row1.add(russButton);
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        row2.add(kazButton);
        row2.add(uzbButton);
//        List<InlineKeyboardButton> row3 = new LinkedList<>();
//        row3.add(chiButton);
//        row3.add(turButton);
        //ROW COLLECTION
        List<List<InlineKeyboardButton>> rowCollection = new LinkedList<>();
        rowCollection.add(row);
        rowCollection.add(row1);
        rowCollection.add(row2);
//        rowCollection.add(row3);
        //keyboard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowCollection);
        //
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }
}
