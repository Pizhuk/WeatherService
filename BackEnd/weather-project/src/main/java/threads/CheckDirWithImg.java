package threads;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import telegrambot.TelegramBotSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static constants.UploadImagesConstants.DIR_STRING;

public class CheckDirWithImg implements Runnable{
    @Override
    public void run() {
        TelegramBotSender telegramBotSender = new TelegramBotSender(new DefaultBotOptions());
        while (true){
            List<File> list = getListOfImages();
            if (list.size() >= 3){
                for (int i = 0; i < 3; i++){
                    telegramBotSender.sendPhoto(list.get(i));
                }
            }
            else if(list.size() < 3 && list.size() >= 1) {
                telegramBotSender.sendPhoto(list.get(0));
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List getListOfImages(){
        File dir = new File(DIR_STRING);
        List<File> lst = new ArrayList<>();
        for (File file : dir.listFiles() ){
            if (file.isFile())
                lst.add(file);
        }
        return lst;
    }
}
