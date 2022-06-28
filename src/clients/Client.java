package src.clients;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
      DateTimeFormatter dtf =
          DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      while (true) {
        if (this.productsCont[x] == false) {
          System.out.println(dtf.format(now) + ": I will search " +
                             this.products[x]);
          if (this.Search(this.products[x])) {
            System.out.println(dtf.format(now) + ": I found " +
                               this.products[x]);
            this.productsCont[x] = true;
          } else {
            System.out.println(dtf.format(now) + ": I dont' found " +
                               this.products[x]);
            if (this.requests[x]) {
              this.requests[x] = false;
              System.out.println(dtf.format(now) + ": I'm calling seller ");
              this.CallSeller();
            } else {
              this.requests[x] = true;
            }
          }
        }
        if (this.productsCont[0] == true && this.productsCont[1] == true) {
          System.out.println(dtf.format(now) + ": I'm smoking!");
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
      return CallServer(Sockets.server1ip, Sockets.server1port);
    case Products.product2:
      return CallServer(Sockets.server2ip, Sockets.server2port);
    case Products.product3:
      return CallServer(Sockets.server3ip, Sockets.server3port);
    }
    return false;
  }

  private void CallSeller() {
    try (Socket socket = new Socket(Sockets.sellerIp, Sockets.sellerPort)) {
      socket.close();
    } catch (UnknownHostException ex) {
      System.out.println("Server not found: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("I/O error: " + ex.getMessage());
    }
  }

  private Boolean CallServer(String serverIp, Integer serverPort) {
    String resp = "";
    try (Socket socket = new Socket(serverIp, serverPort)) {
      DataInputStream dataIn = new DataInputStream(socket.getInputStream());
      DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
      dataOut.writeUTF("I need!");
      dataOut.flush();
      resp = dataIn.readUTF();
      dataOut.close();
      socket.close();
    } catch (UnknownHostException ex) {
      System.out.println("Server not found: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("I/O error: " + ex.getMessage());
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
