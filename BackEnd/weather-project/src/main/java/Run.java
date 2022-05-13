import configuration.ServerConfiguration;
import org.eclipse.jetty.server.Server;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegrambot.TelegramBotUpdater;
import threads.CheckDirWithImg;

public class Run {
    public static void main(String[] args) throws TelegramApiException {
        ServerConfiguration serverConfiguration = new ServerConfiguration();
        Server server = serverConfiguration.buildServer();
        CheckDirWithImg checkDirWithImg = new CheckDirWithImg();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBotUpdater telegramBotUpdater = new TelegramBotUpdater();
        telegramBotsApi.registerBot(telegramBotUpdater);
        try {
            server.start();
            new Thread(checkDirWithImg).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
