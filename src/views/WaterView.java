package views;

import java.util.Scanner;

public class WaterView {
  private Scanner scanner = new Scanner(System.in);

  public int getUserConsumerNumber() {
    System.out.printf("Eisagete arithmo katanaloti:");
    return scanner.nextInt();
  }

  public int getUserWaterConsumption() {
    System.out.printf("Eisagete katanalosi nerou se kybika metra:");
    return scanner.nextInt();
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }
}