package Client;

import Server.Server.ServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGraphics extends JFrame implements ClientInterface{
    public static final int HEIGHT = 600;
    public static final int WEIGHT = 500;

    private boolean connectClient;
    private JTextField ip;
    private JTextField port;

    private JTextField name;
    private JPasswordField password;
    private JButton login, send;
    private JTextArea userArea;
    private JTextArea history = new JTextArea();

    private ClientController clientController;

    public ClientGraphics() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WEIGHT, HEIGHT);
        setTitle("Client");
        ip = new JTextField("127.0.0.1");
        ip.setSize(25,25);
        port = new JTextField("8080");
        name = new JTextField("Name");
        password = new JPasswordField("Password");
        login = new JButton("Login");
        userArea = new JTextArea();
        send = new JButton("Send message");
        JPanel clientPanel = new JPanel(new GridLayout(2,6));
        clientPanel.add(ip);
        clientPanel.add(port);
        clientPanel.add(name);
        clientPanel.add(password);
        clientPanel.add(login);
        add(clientPanel, BorderLayout.NORTH);

        JPanel clientChat = new JPanel(new GridLayout(1,2));
        clientChat.add(userArea, BorderLayout.CENTER);
        clientChat.add(send, BorderLayout.EAST);
        add(clientChat, BorderLayout.SOUTH);
        JScrollPane chat = new JScrollPane(history);
        add(chat, BorderLayout.CENTER);

        setVisible(true);
        userArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    clientController.sendMessage(userArea.getText());
                    userArea.setText("");
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.connected();
            }
        });

        send.addActionListener(e -> {
            clientController.sendMessage(userArea.getText());
            userArea.setText("");
        });
    }

    public String getName(){
        return name.getText();
    }

    public void clientDisconnect(){
        connectClient = false;
    }

    public void getMessage(String string){
        history.append(string);
    }

    public void showMessage(String message){
        history.append(message + "\n");
    }

    public void setClientController(ClientController clientController){
        this.clientController = clientController;
    }
}
