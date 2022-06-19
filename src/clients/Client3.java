package src.clients;

import java.io.*;
import java.net.*;
import src.Products;
import src.Sockets;

public class Client3 {
  public static void main(String[] args) {
    Client client = new Client(Products.product1, Products.product2);
    client.Run(5000, 3000);
  }
}
