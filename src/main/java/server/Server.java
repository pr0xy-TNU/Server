package server;

import console.ConsoleColors;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import logger.MiniLogger;
import utils.Config;

public class Server extends Thread {

  private ServerSocket socket;
  private Socket client;
  boolean isActive;
  private long upWork;

  public Server() {
    isActive = Boolean.TRUE;
    try {
      this.socket = new ServerSocket(Config.PORT);
      this.upWork = System.currentTimeMillis();
    } catch (IOException e) {
      MiniLogger.getMinLogger(getClass(), "Can`t deploy server...");
    }
    MiniLogger.getMinLogger(getClass(), "Server was started...");
  }

  public void shutdown() {
    try {
      this.socket.close();

    } catch (IOException e) {
      MiniLogger.getMinLogger(getClass(), "Can`t close server socket...");
    } finally {
      Long endTime = System.currentTimeMillis();
      endTime -= upWork;
      MiniLogger
          .getMinLogger(getClass(), String.format("%sServer work time : %s",
              ConsoleColors.RED_BOLD,
              new SimpleDateFormat("mm:ss\n").format(endTime)));
    }

  }

  public void getServerInfo() {
    Config.getProperties().forEach((k, v) -> {
      MiniLogger.getMinLogger(getClass(), String.format("%s%s %.20s",ConsoleColors.GREEN_UNDERLINED, k, v));
    });
  }

  @Override
  public void run() {
    while (isActive) {
      try {
        sleep(3000);
        isActive = false;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    shutdown();
  }
}
