package logger;

import console.ConsoleColors;
import java.text.SimpleDateFormat;

public class MiniLogger implements IMinLogger {

  private String datePattern = "yyyy-mm-dd hh:mm:ss";
  private SimpleDateFormat date = new SimpleDateFormat(datePattern);
  private String clazzName;


  public void log(Status status, String message, String clazz) {
    if (Status.ERROR == status) {
      System.err
          .printf("%s %s [%s] %s", date.format(System.currentTimeMillis()), status.getStatus(),
              clazz,
              message);
    } else {
      System.out
          .printf("%s %s [%s] %s", date.format(System.currentTimeMillis()), status.getStatus(),
              clazz,
              message);
    }

  }

  public void info(String message) {
    log(Status.INFO, message, this.clazzName);
  }

  public void err(String message) {
    log(Status.ERROR, message, this.clazzName);
  }

  public void debug(String message) {
    log(Status.DEBUG, message, this.clazzName);
  }

  public void warn(String message) {
    log(Status.WARNING, message, this.clazzName);
  }

  public static MiniLogger getMinLogger(Class clazz) {
    MiniLogger logger = new MiniLogger();
    logger.clazzName = clazz.getSimpleName();
    return logger;
  }

  public static void getMinLogger(Class clazz, String message) {
    SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    System.out
        .printf("%s %s [%s] %s\n",
            ConsoleColors.CYAN,
            date.format(System.currentTimeMillis()),
            clazz,
            message);
  }

  public enum Status {
    WARNING("WAWNING :"),
    ERROR("ERROR :"),
    INFO("INFO :"),
    DEBUG("DEBUG :");
    private String status;

    Status(String status) {
      this.status = status;
    }

    public String getStatus() {
      return status;
    }
  }


}
