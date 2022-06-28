package src.servers;

import java.io.*;
import java.net.*;
import src.Sockets;

public class Server3 {
  public static void main(String[] args) {
    Server server = new Server(Sockets.server3port);
    server.Run(5000);
  }
}
