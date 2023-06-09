package com.currantino.rfidtelegramclient.bot;

import com.currantino.rfidtelegramclient.client.RfidClient;
import com.currantino.rfidtelegramclient.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RfidBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final RfidClient rfidClient;

    @Autowired
    public RfidBot(BotConfig botConfig, RfidClient rfidClient) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.rfidClient = rfidClient;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String senderUsername = update.getMessage().getChat().getUserName();
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(
                    """
                            Hello, %s!
                            You've just said \"%s\"
                            """.formatted(senderUsername, rfidClient.hello()));

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

}
