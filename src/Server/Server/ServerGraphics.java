package Server.Server;

import Client.ClientGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerGraphics extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WEIGHT = 600;
    private static final int POS_X = 200;
    private static final int POS_Y = 200;

    private JButton btnStart, btnExit;
    private JTextArea history = new JTextArea();
    private boolean statusServer;
    private ServerController serverController;

    public ServerGraphics(){
        statusServer = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WEIGHT, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Server");
        setResizable(false);
        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");

        JScrollPane scrollHistory = new JScrollPane(history);
        add(scrollHistory, BorderLayout.CENTER);

        JPanel serverPanel = new JPanel((new GridLayout(1,2)));
        add(serverPanel, BorderLayout.SOUTH);
        serverPanel.add(btnStart);
        serverPanel.add(btnExit);

        setVisible(true);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.startServer();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stopServer();
            }
        });
    }

    public void saveHistory(String message){
        String path = "src/log.txt";
        try(PrintWriter fileWriter = new PrintWriter(new FileWriter(path, true))){
            fileWriter.write(message);
        } catch (IOException e){
            System.out.println("Ошибка записи файла");
        }
    }

    public boolean isStatusServer() {
        return statusServer;
    }

    public void showMessageToUser(String message){
        history.append(message + "\n");
    }

    public void setServerController(ServerController serverController){
        this.serverController = serverController;
    }

    public ServerController getServerController() {
        return serverController;
    }

    public void showOnWindow(String s){
        history.append(s + '\n');
    }

}
