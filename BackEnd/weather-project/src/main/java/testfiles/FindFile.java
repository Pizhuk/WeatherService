package testfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFile {
        public static void main(String[] args) {
                File dir = new File("C:\\Users\\1\\Desktop\\java"); //path указывает на директорию
                List<File> lst = new ArrayList<>();
                for ( File file : dir.listFiles() ){
                        if (file.isFile())
                                lst.add(file);
                }
                System.out.println(lst);
        }
}
