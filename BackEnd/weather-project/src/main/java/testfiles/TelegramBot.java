package testfiles;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {
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
        System.out.println(message.getReplyToMessage().getCaption());
    }


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBot telegramBot = new TelegramBot();
        telegramBotsApi.registerBot(telegramBot);
    }
//    private static final String CHAT_ID = "640399186";
//
//    public static void main(String[] args) {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(new T);
//        try {
//            telegramBotsApi.registerBot(new TelegramBot());
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sendPhoto(Message message){
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(message.getChatId());
////        sendPhoto.setCaption("photo");
//        sendPhoto.setNewPhoto(new File("C:\\Users\\1\\Desktop\\~myphotos\\photo_2021-11-05_17-08-52.jpg"));
//        try {
//            sendPhoto(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sendMsg(Message message, String text){
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(message.getChatId());
////        sendPhoto.setCaption("photo");
//        sendPhoto.setNewPhoto(new File("C:\\Users\\1\\Desktop\\~myphotos\\photo_2021-11-05_17-08-52.jpg"));
//
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
//        sendMessage.setText(text);
//        try {
//            sendPhoto(sendPhoto);
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//        }
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        if (message != null && message.hasText()){
//            switch (message.getText()){
//                case "/help":
//                    sendMsg(message, "How can I help you?");
////                    sendPhoto(message);
//                    break;
//                default:
//                    sendMsg(message, "No such a command!");
//                    break;
//            }
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "@WeatherServiceByPizhuk";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "5225091469:AAF-y7I48c4hp-RpiuYLvjsCd1c4pNl6Co4";
//    }
}
