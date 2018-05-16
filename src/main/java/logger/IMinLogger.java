package logger;

import logger.MiniLogger.Status;

public interface IMinLogger {

  public void log(Status status, String message, String clazz);

  void info(String message);

  void err(String message);

  void debug(String message);

  void warn(String message);

}
