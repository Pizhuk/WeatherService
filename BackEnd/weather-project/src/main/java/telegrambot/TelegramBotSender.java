package telegrambot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.File;
import static constants.MesConstants.*;

public class TelegramBotSender extends DefaultAbsSender {
    private static final String CHAT_ID = "640399186";

    public TelegramBotSender(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return "5225091469:AAF-y7I48c4hp-RpiuYLvjsCd1c4pNl6Co4";
    }

    public void sendPhoto(File file){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(CHAT_ID);
        sendPhoto.setCaption(String.format(PHOTO_MES, file.getAbsolutePath()));
        sendPhoto.setPhoto(new InputFile(file));
        TelegramBotSender senderTG = new TelegramBotSender(new DefaultBotOptions());
        try {
            senderTG.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMesFromContactPage(String mes){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(String.format(CONTACT_MES, mes));
        TelegramBotSender senderTG = new TelegramBotSender(new DefaultBotOptions());
        try {
            senderTG.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
