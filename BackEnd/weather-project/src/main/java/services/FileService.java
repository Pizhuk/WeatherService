package services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    private static final String FILE_NAME = "names.txt";

    public FileService() {
        File file = new File(FILE_NAME);
        if (!file.exists()){
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(FILE_NAME, false);
                String num = "1";
                writer.write(num);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getFileName(){
        String helpString = "";
        try(FileReader reader = new FileReader(FILE_NAME))
        {
            int c;
            while((c=reader.read())!=-1){
                helpString += (char)c;
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        int returnNum = Integer.parseInt(helpString);

        String newNum = String.format("%s", returnNum + 1);
        try {
            FileWriter writer = new FileWriter(FILE_NAME, false);
            writer.write(newNum);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnNum;
    }

    public void moveToAnotherDir(File file){
        File destFolder = new File("/Users/User/Desktop/WebstormProjects/weatherservice-front/photos_for_slider"); // это папка, в которую будем перемещать
        System.out.println(file.renameTo(new File(destFolder, file.getName())));
    }

    public void deleteFile(File file){
        if (file.exists()){
            file.delete();
        }
    }
}
