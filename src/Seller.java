package src;

import java.io.*;
import java.net.*;

public class Seller {
  public static void main(String[] args) {
    try {
      ServerSocket server = new ServerSocket(Sockets.seller);
      System.out.println("Listening in " + Integer.toString(Sockets.seller));
      while (true) {
        Socket socket = server.accept();
        if ((int)(Math.random() * (10)) % 2 ==
            (int)(Math.random() * (10)) % 2) {
          CallServer(Sockets.server1);
          System.out.println("1");
          if ((int)(Math.random() * (10)) % 2 ==
              (int)(Math.random() * (10)) % 2) {
            CallServer(Sockets.server2);
            System.out.println("2");
          } else {
            CallServer(Sockets.server3);
            System.out.println("3");
          }
        } else {
          CallServer(Sockets.server2);
          CallServer(Sockets.server3);
          System.out.println("2 y 3");
        }
        socket.close();
        pause(5000);
      }
    } catch (IOException e) {
      System.out.println("Exception detected: " + e);
    }
  }

  private static void CallServer(Integer server) {
    try {
      Socket socket = new Socket("localhost", server);
      DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
      dataOut.writeUTF("Take one!");
      dataOut.flush();
      dataOut.close();
      socket.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void pause(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }
}
