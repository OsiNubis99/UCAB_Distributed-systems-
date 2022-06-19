package src.servers;

import java.io.*;
import java.net.*;
import src.Sockets;

public class Server1 {
  public static void main(String[] args) {
    Server server = new Server(Sockets.server1);
    server.Run(5000);
  }
}
