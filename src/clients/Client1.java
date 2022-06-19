package src.clients;

import java.io.*;
import java.net.*;
import src.Products;
import src.Sockets;

public class Client1 {
  public static void main(String[] args) {
    Client client = new Client(Products.product2, Products.product3);
    client.Run(5000, 3000);
  }
}
