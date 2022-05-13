package services;

import java.io.File;

import static constants.DirConstants.*;

public class DirService {
    public String getFilesList(){
        String str = "";
        File dir = new File(DIR_OF_FILES_TO_SENT_CLIENT);
        for (int i = 0; i < dir.listFiles().length; i++){
            if (i == dir.listFiles().length-1){
                str+=String.format("%s", dir.listFiles()[i]);
            }
            else {
                str+=String.format("%s@", dir.listFiles()[i]);
            }
        }
        return str;
    }
}
