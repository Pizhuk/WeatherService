package telegrambot;

import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.EmailService;
import services.FileService;

import java.io.File;

import static constants.WeatherConstants.CITY_NAME_STRING;

public class TelegramBotUpdater extends TelegramLongPollingBot {
    private static final String CHAT_ID = "640399186";
    @Override
    public String getBotUsername() {
        return "@WeatherServiceByPizhukBot";
    }

    @Override
    public String getBotToken() {
        return "5225091469:AAF-y7I48c4hp-RpiuYLvjsCd1c4pNl6Co4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.getReplyToMessage() != null){
            String reply = message.getReplyToMessage().getText();
            if (reply != null){
                if (reply.contains("{") && reply.contains("}") && reply.contains("\"status\"")
                        && reply.contains("\"name\"") && reply.contains("\"email\"") && reply.contains("\"subj\"")
                        && reply.contains("\"mes_text\"")){
                    JSONObject jsonMes = new JSONObject(reply);
                    System.out.println(jsonMes);
                    JSONObject text = jsonMes.getJSONObject("text");

                    String name = text.getString("name");
                    String email = text.getString("email");

                    EmailService emailService = new EmailService();
                    emailService.sendEmail(email, name, message.getText());

                    try {
                        execute(SendMessage.builder().chatId(CHAT_ID).text("We've sent letter to "+email).build());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        execute(SendMessage.builder().chatId(CHAT_ID).text("No such a command!(Maybe you've replied on wrong mes)").build());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                String caption = message.getReplyToMessage().getCaption();
                JSONObject object = new JSONObject(caption);
                String dir = object.getString("text");
                switch (message.getText()){
                    case "yes":
                        try {
                            FileService fileService = new FileService();
                            fileService.moveToAnotherDir(new File(dir));
                            execute(SendMessage.builder().chatId(CHAT_ID).text(String.format("Photo was added to gallery(%s)", dir)).build());
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "no":
                        try {
                            FileService fileService = new FileService();
                            fileService.deleteFile(new File(dir));
                            execute(SendMessage.builder().chatId(CHAT_ID).text(String.format("Photo was deleted(%s)", dir)).build());
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        try {
                            execute(SendMessage.builder().chatId(CHAT_ID).text("No such a command").build());
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
        else {
            try {
                execute(SendMessage.builder().chatId(CHAT_ID).text("No such a command. Remember: you should reply on photo or text").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
