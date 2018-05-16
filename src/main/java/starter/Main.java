package starter;

import java.util.stream.IntStream;
import server.Server;
import utils.Config;


public class Main {

  public static void main(String[] args) {
    Server server = new Server();
    server.start();
    server.getServerInfo();

  }

}