package Server.Server;

import Client.ClientController;

import java.util.ArrayList;

public interface ServerStorageInterface {
    public void addClient(ClientController client);

    public ArrayList<ClientController> getServerClients();
}
