package com.pengrad.telegrambot.sample.spark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
// import 
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

class MyMain {

    public static String streamFile_Buffer(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static void main(String[] args) throws Exception {
        TelegramBot bot = new TelegramBot(streamFile_Buffer("token"));
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                System.out.println(updates.toString());

                SendMessage requestText = new SendMessage(121843071, "*hello from java*").parseMode(ParseMode.Markdown);
                bot.execute(requestText, new Callback<SendMessage, SendResponse>() {
                    @Override
                    public void onResponse(SendMessage request, SendResponse response) {

                    }

                    @Override
                    public void onFailure(SendMessage request, IOException e) {

                    }
                });
                ;

                SendPhoto requestPhoto = new SendPhoto(121843071, new java.io.File("cat.jpg"));
                bot.execute(requestPhoto, new Callback<SendPhoto, SendResponse>() {
                    @Override
                    public void onResponse(SendPhoto request, SendResponse response) {

                    }

                    @Override
                    public void onFailure(SendPhoto request, IOException e) {

                    }
                });
                ;

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }

}