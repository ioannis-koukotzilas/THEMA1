import controllers.WaterController;

public class WaterApp {
  public static void main(String[] args) {
    WaterController waterController = new WaterController();
    waterController.calculateBilling();
  }
}