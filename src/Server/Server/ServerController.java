package Server.Server;

import Client.ClientController;
import Server.Storage.StorageSystem;

public class ServerController {
    private boolean connected;
    private ServerGraphics serverGraphics;
    private final ServerStorage serverStorage;
    private final StorageSystem storageSystem;

    public ServerController(ServerGraphics serverGraphics, ServerStorage serverStorage, StorageSystem storageSystem) {
        this.serverGraphics = serverGraphics;
        this.serverStorage = serverStorage;
        this.storageSystem = storageSystem;
    }

    public boolean isConnected(){
        return connected;
    }

    public void addClient(ClientController client){
        serverGraphics.showOnWindow(client.getName() + " подключился!");
        client.showMessage(client.getName()  + " подключился!");
        serverStorage.addClient(client);
    }

    public void showOnWindow(String s){
        serverGraphics.showOnWindow(s);
    }

    public void startServer(){
        if(!connected){
            connected = true;
            storageSystem.loadHistoryFromFile();
            serverGraphics.showOnWindow("Сервер запущен");
            storageSystem.saveHistoryToFile("Сервер запущен");
        } else {
            serverGraphics.showOnWindow("Сервер уже запущен");
            storageSystem.saveHistoryToFile("Сервер уже запущен");
        }
    }

    public void stopServer(){
        if(connected){
            serverGraphics.showOnWindow("Сервер выключен");
            storageSystem.saveHistoryToFile("Сервер выключен");
            connected = false;
            for(ClientController client: serverStorage.getServerClients()){
                client.disconnect();
            }
        }
    }

    public void sendMessageToUser(String message, ClientController sender){
        serverGraphics.showOnWindow("message");
        for(ClientController client : serverStorage.getServerClients()){
            if(client != sender){
                client.showMessage(message);
            }
        }
        storageSystem.saveHistoryToFile(message);
    }
}
