package com.currantino.rfidtelegramclient.bot;

import com.currantino.rfidtelegramclient.config.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RfidBot extends TelegramBot {


    private final BotConfig botConfig;

    @Autowired
    public RfidBot(BotConfig botConfig) {
        super(botConfig.getToken());
        System.out.println(botConfig.getToken());
        this.botConfig = botConfig;
    }

    @PostConstruct
    public void start() {
        setUpdatesListener(
                updates -> {
                    updates.stream()
                            .filter(update -> update.message() != null)
                            .forEach(update -> {
                                Message message = update.message();
                                log.info("Processing message \"{}\" from \"{}\"", message.text(), message.chat().username());
                                SendMessage response = new SendMessage(update.message().chat().id(),
                                        """
                                                        Hello, %s!
                                                        You just said \"%s\""""
                                                .formatted(message.chat().username(), message.text()));
                                execute(response);
                            });
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                },
                exception -> {
                    sendError();
                });

    }

    private void sendError() {
    }

}
