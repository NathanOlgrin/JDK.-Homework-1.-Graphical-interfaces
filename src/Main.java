import Client.ClientController;
import Client.ClientGraphics;
import Server.Server.ServerController;
import Server.Server.ServerGraphics;
import Server.Server.ServerStorage;
import Server.Storage.StorageSystem;

public class Main {
    public static void main(String[] args) {
        ServerGraphics serverGraphics = new ServerGraphics();
        ServerStorage serverStorage = new ServerStorage();
        StorageSystem storageSystem = new StorageSystem();
        ServerController serverController = new ServerController(serverGraphics, serverStorage, storageSystem);
        serverGraphics.setServerController(serverController);

        ClientGraphics clientGraphics_One = new ClientGraphics();
        ClientController clientController_One = new ClientController();
        clientController_One.setClientGraphics(clientGraphics_One);
        clientController_One.setServerController(serverController);
        clientGraphics_One.setClientController(clientController_One);

        ClientGraphics clientGraphics_Two = new ClientGraphics();
        ClientController clientController_Two = new ClientController();
        clientController_Two.setClientGraphics(clientGraphics_Two);
        clientController_Two.setServerController(serverController);
        clientGraphics_Two.setClientController(clientController_Two);
    }
}