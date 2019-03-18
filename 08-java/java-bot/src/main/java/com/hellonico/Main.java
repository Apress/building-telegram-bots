package com.hellonico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

    public static String streamFile_Buffer(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static void main(String[] args) throws Exception {
        TelegramBot bot = new TelegramBot(streamFile_Buffer("resources/token"));
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                System.out.println(updates.toString());
                int id = updates.get(0).message().chat().id().intValue();

                SendMessage requestText = new SendMessage(id, "*hello from java*").parseMode(ParseMode.Markdown);
                bot.execute(requestText, new Callback<SendMessage, SendResponse>() {
                    @Override
                    public void onResponse(SendMessage request, SendResponse response) {

                    }

                    @Override
                    public void onFailure(SendMessage request, IOException e) {

                    }
                });

                SendPhoto requestPhoto = new SendPhoto(id, new java.io.File("resources/cat.jpg"));

                try {
                    SendResponse response = bot.execute(requestPhoto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // bot.execute(requestPhoto, new Callback<SendPhoto, SendResponse>() {
                // @Override
                // public void onResponse(SendPhoto request, SendResponse response) {

                // }

                // @Override
                // public void onFailure(SendPhoto request, IOException e) {

                // }
                // });

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }

}