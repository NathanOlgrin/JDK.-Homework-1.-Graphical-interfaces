package Client;

import Server.Server.ServerController;

public class ClientController implements ClientInterface{
    private boolean isConnect;
    String name;
    private ClientGraphics clientGraphics;
    private ServerController serverController;

    public String getName(){
        return clientGraphics.getName();
    }

    public void setServerController(ServerController serverController){
        this.serverController = serverController;
    }

    public void setClientGraphics(ClientGraphics clientGraphics){
        this.clientGraphics = clientGraphics;
    }

    public void connected(){
        if(!isConnect && serverController.isConnected()){
            isConnect = true;
            serverController.addClient(this);
        } else if (isConnect && !serverController.isConnected()){
            clientGraphics.showMessage(clientGraphics.getName() + " уже подключен");
            serverController.showOnWindow(clientGraphics.getName() + " уже подключен");
        } else {
            clientGraphics.showMessage("Сервер не в сети");
        }
    }

    public void disconnect(){
        if(isConnect){
            isConnect = false;
            clientGraphics.showMessage("Сервер отключен");
            serverController.stopServer();
        }
    }

    public void showMessage(String message){
        clientGraphics.showMessage(message);
    }

    public void sendMessage(String message){
        if(isConnect && serverController.isConnected()){
            String toSend = clientGraphics.getName() + " : " + message;
            clientGraphics.showMessage(toSend);
            serverController.sendMessageToUser(toSend, this);
        } else if(!isConnect){
            clientGraphics.showMessage("Вы не подключены к серверу");
        } else {
            clientGraphics.showMessage("Сервер выключен");
        }
    }
}
