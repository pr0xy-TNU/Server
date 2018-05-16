package utils;

import static utils.ConnectionHelper.PROPERTIES_FILE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import logger.MiniLogger;

public class Config {

  public static Integer PORT;
  private static String HOST;
  private static Integer TIMEOUT;
  private static Properties prop;
  private static Map<String, String> properties = new HashMap<>();

  static {
    try {
      InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(PROPERTIES_FILE);
      prop = new Properties();
      prop.load(in);
      PORT = Integer.parseInt(prop.getProperty("PORT"));
      HOST = String
          .valueOf(prop.getProperty(ConnectionHelper.HOST));
      TIMEOUT = Integer.parseInt(prop.getProperty(ConnectionHelper.TIMEOUT));
      fillProp();
    } catch (FileNotFoundException e) {
      MiniLogger.getMinLogger(Config.class, "File not found...");
    } catch (IOException e) {
      MiniLogger.getMinLogger(Config.class, "IOException...");
    }
  }

  public static void fillProp() {
    properties.put(ConnectionHelper.PORT, String.valueOf(PORT));
    properties.put(ConnectionHelper.HOST, String.valueOf(HOST));
    properties.put(ConnectionHelper.TIMEOUT, String.valueOf(TIMEOUT));

    if (properties != null && !properties.entrySet().isEmpty()) {
      MiniLogger
          .getMinLogger(Config.class, String.format("Configuration of server was setup..."));
    } else {
      MiniLogger.getMinLogger(Config.class, String.format("Can`t read properties file..."));
    }
  }

  public static Map<String, String> getProperties() {
    return properties;
  }
}