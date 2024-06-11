package Server.Storage;

import java.io.*;

public class StorageSystem implements StrorageInterface{

    public void saveHistoryToFile(String s){
        String filepath = "Database/log.txt";
        try(PrintWriter writer = new PrintWriter(new FileWriter(filepath, true))){
            writer.println(s);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public String loadHistoryFromFile(){
        String filepath = "Database/log.txt";
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e){
            stringBuilder.append("Файл не найден");
        }

        return stringBuilder.toString();
    }
}
