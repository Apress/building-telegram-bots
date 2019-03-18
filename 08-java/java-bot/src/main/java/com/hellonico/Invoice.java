package com.hellonico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import 
import java.util.List;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.PreCheckoutQuery;
import com.pengrad.telegrambot.model.ShippingQuery;
import com.pengrad.telegrambot.model.SuccessfulPayment;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.LabeledPrice;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ShippingOption;
import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery;
import com.pengrad.telegrambot.request.AnswerShippingQuery;
import com.pengrad.telegrambot.request.SendInvoice;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Invoice {

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

                try {

                    ShippingQuery sihpping = updates.get(0).shippingQuery();
                    if (sihpping != null) {
                        ShippingOption option = new ShippingOption("fedex", "FedEx", new LabeledPrice("JOY", 2000));
                        AnswerShippingQuery query = new AnswerShippingQuery(sihpping.id(), option);
                        bot.execute(query);

                        return UpdatesListener.CONFIRMED_UPDATES_ALL;
                    }
                    PreCheckoutQuery query = updates.get(0).preCheckoutQuery();
                    if (query != null) {
                        System.out.println("query");
                        AnswerPreCheckoutQuery apcq = new AnswerPreCheckoutQuery(query.id());
                        bot.execute(apcq);
                        return UpdatesListener.CONFIRMED_UPDATES_ALL;
                    }

                    int id = updates.get(0).message().chat().id().intValue();
                    SuccessfulPayment payment = updates.get(0).message().successfulPayment();
                    if (payment != null) {
                        SendMessage thankyou = new SendMessage(id, "*thank you*").parseMode(ParseMode.Markdown);
                        bot.execute(thankyou);
                        return UpdatesListener.CONFIRMED_UPDATES_ALL;
                    }

                    SendInvoice sendInvoice = new SendInvoice(id, "Lemon", "lemons", "prod_DePEWfK0vcbnct",
                            "284685063:TEST:NDBlMjliMGM2YmQ0", "my_start_param", "JPY", new LabeledPrice("label", 2000))
                                    .needPhoneNumber(false).needShippingAddress(false).isFlexible(true)
                                    .replyMarkup(new InlineKeyboardMarkup(
                                            new InlineKeyboardButton[] { new InlineKeyboardButton("just pay").pay(),
                                                    new InlineKeyboardButton("google it").url("www.google.com") }));
                    SendResponse response = bot.execute(sendInvoice);
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                } catch (Exception e) {
                    e.printStackTrace();
                    return UpdatesListener.CONFIRMED_UPDATES_NONE;
                }

            }
        });
    }

}