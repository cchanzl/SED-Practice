package Q3;

public class Show {

  private final String title;
  private final double ticketPrice;

  public Show(String title, double unitPrice) {
    this.title = title;
    this.ticketPrice = unitPrice;
  }

  public static void checkAvailable(Show show, int i) {
  }

  public String title() {
    return title;
  }

  public double price() {
    return ticketPrice;
  }
}
