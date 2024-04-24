package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileUtils {

    public static void write(String path,String fileName, String txt){
        BufferedWriter writer;
        String filePath = path + File.separator + fileName;
        try {
            createDir(path);
            createFile(filePath);
            writer = new BufferedWriter(new FileWriter(filePath,true));
            writer.write(txt);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean createDir(String path){
        File file = new File(path);
        if(file.exists()){
            return false;
        }
        return file.mkdirs();

    };
    public static boolean createFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
           return file.createNewFile();
        }
        return false;
    }


}
