package controllers;

import services.WaterService;

public class WaterController {
  public WaterController() {}

  public void calculateBilling() {
    WaterService waterService = new WaterService();
    waterService.calculateBilling();
  }
}