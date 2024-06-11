package Server.Storage;

public interface StrorageInterface {
    void saveHistoryToFile(String history);
    String loadHistoryFromFile();
}
