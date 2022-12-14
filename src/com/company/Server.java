package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable {
    private DatagramSocket socket;
    DatagramPacket dp;
    private String userHostname;
    private int port;
    private InetAddress address;
    String name;

    public Server(int port) {
        name = "User";
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            byte[] buf = new byte[1024];
            dp = new DatagramPacket(buf, 1024);
            try {
                socket.receive(dp);
                address = dp.getAddress();
                String str = new String(dp.getData(), 0, dp.getLength());

                if(str.startsWith("@name")){
                    name = str.substring(6);
                }
                if(str.startsWith("@quit")){
                    System.exit(1);
                }

                System.out.println(name+ ":" + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessage(String msg){
        DatagramPacket answer = new DatagramPacket(msg.getBytes(), msg.length(), address, dp.getPort());
        try {
            socket.send(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
