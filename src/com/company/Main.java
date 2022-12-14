package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;

    public Main(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax:  <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server server = new Server(port);

        Thread th = new Thread(server);
        th.start();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            server.sendMessage(str);


        }

    }


}
