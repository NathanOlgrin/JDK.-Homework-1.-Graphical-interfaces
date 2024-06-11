package Server.Server;

import Client.ClientController;
import Client.ClientGraphics;

import java.util.ArrayList;

public class ServerStorage implements ServerStorageInterface{
    ArrayList<ClientController> serverClients = new ArrayList<>();

    public void addClient(ClientController client){
        this.serverClients.add(client);
    }

    public ArrayList<ClientController> getServerClients(){
        return serverClients;
    }
}
