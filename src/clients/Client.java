package src.clients;

import java.io.*;
import java.net.*;
import src.Products;
import src.Sockets;

public class Client {
  private String[] products;
  private Boolean[] productsCont;
  private Boolean[] requests;

  public Client(String p1, String p2) {
    Boolean[] def1 = {false, false};
    Boolean[] def2 = {false, false};
    String[] products = {p1, p2};
    this.products = products;
    this.productsCont = def1;
    this.requests = def2;
    System.out.println("I will search " + p1 + " and " + p2);
  }

  public void Run(Integer smokeTime, Integer requestTime) {
    try {
      Boolean resp;
      Integer x = 0;
      while (true) {
        if (this.productsCont[x] == false) {
          System.out.println("I will search " + this.products[x]);
          if (this.Search(this.products[x])) {
            System.out.println("I found " + this.products[x]);
            this.productsCont[x] = true;
          } else {
            System.out.println("I dont' found " + this.products[x]);
            if (this.requests[x]) {
              this.requests[x] = false;
              System.out.println("I'm calling seller ");
              this.CallSeller();
            } else {
              this.requests[x] = true;
            }
          }
        }
        if (this.productsCont[0] == true && this.productsCont[1] == true) {
          System.out.println("I'm smoking!");
          this.productsCont[0] = false;
          this.productsCont[1] = false;
          this.pause(smokeTime);
        } else {
          this.pause(requestTime);
        }
        x = (x + 1) % 2;
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public Boolean Search(String item) {
    switch (item) {
    case Products.product1:
      return CallServer(Sockets.server1);
    case Products.product2:
      return CallServer(Sockets.server2);
    case Products.product3:
      return CallServer(Sockets.server3);
    }
    return false;
  }

  private void CallSeller() {
    try {
      Socket socket = new Socket("localhost", Sockets.seller);
      socket.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  private Boolean CallServer(Integer server) {
    String resp = "";
    try {
      Socket socket = new Socket("localhost", server);
      DataInputStream dataIn = new DataInputStream(socket.getInputStream());
      DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
      dataOut.writeUTF("I need!");
      dataOut.flush();
      resp = dataIn.readUTF();
      dataOut.close();
      socket.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return resp.equals("Take one!");
  }

  private void pause(Integer ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }
}
