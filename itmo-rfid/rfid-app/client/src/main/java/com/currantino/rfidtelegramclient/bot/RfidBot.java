package com.currantino.rfidtelegramclient.bot;

import com.currantino.rfidtelegramclient.client.RfidClient;
import com.currantino.rfidtelegramclient.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RfidBot extends TelegramLongPollingBot {
    public static final String BLOCK_USER = "BLOCK_USER";
    public static final String ADD_USER = "ADD_USER";
    private int global = 0;


    private final BotConfig botConfig;
    private final RfidClient rfidClient;
    private InlineKeyboardMarkup markup;

    @Autowired
    public RfidBot(BotConfig botConfig, RfidClient rfidClient) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.rfidClient = rfidClient;
    }

    @PostConstruct
    public void init() {
        InlineKeyboardButton addUserButton = InlineKeyboardButton.builder()
                .text("Add user")
                .callbackData(ADD_USER)
                .build();
        InlineKeyboardButton blockUserButton = InlineKeyboardButton.builder()
                .text("Block user")
                .callbackData(BLOCK_USER)
                .build();
        this.markup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(addUserButton, blockUserButton))
                .build();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String senderUsername = update.getMessage().getChat().getUserName();
            SendMessage msg = SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(String.valueOf(global))
                    .replyMarkup(markup)
                    .build();
            try {
                execute(msg);
            } catch (TelegramApiException e) {
                throw new RuntimeException("AAAAAAAAAAAAAAA", e);
            }
        } else if (update.hasCallbackQuery()) {
            try {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                switch (callbackQuery.getData()) {
                    case BLOCK_USER -> processBlockUserCommand(callbackQuery);
                }
                EditMessageText msg = EditMessageText.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .replyMarkup(markup)
                        .text(String.valueOf(global))
                        .build();
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void processBlockUserCommand(CallbackQuery callbackQuery) throws TelegramApiException {
        rfidClient.blockUser();
        execute(SendMessage.builder()
                .chatId(callbackQuery.getMessage().getChatId())
                .text("Пользователь успешно заблокирован.")
                .build());
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

}
