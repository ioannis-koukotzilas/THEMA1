package services;

import views.WaterView;

public class WaterService {
  private static final int FIXED_FEE = 8;

  private WaterView waterView;

  public WaterService() {
    this.waterView = new WaterView();
  }

  public void calculateBilling() {
    int userConsumerNumber = waterView.getUserConsumerNumber();
    int userWaterConsumption = waterView.getUserWaterConsumption();

    double tieredWaterCharge = calculateTieredWaterCharge(userWaterConsumption);
    double tieredSewerageFee = calculateSewerageFee(tieredWaterCharge);
    double tieredSpecialWaterFee = calculateSpecialWaterFee(userWaterConsumption);
    double tieredWaterVAT = calculateWaterVAT(tieredWaterCharge);
    double tieredFeesVAT = calculateFeesVAT(FIXED_FEE, tieredSewerageFee, tieredSpecialWaterFee);
    double tieredTotal = calculateTotal(FIXED_FEE, tieredWaterCharge, tieredSewerageFee, tieredSpecialWaterFee,
        tieredWaterVAT, tieredFeesVAT);

    double fixedWaterCharge = calculateFixedWaterCharge(userWaterConsumption);
    double fixedSewerageFee = calculateSewerageFee(fixedWaterCharge);
    double fixedSpecialWaterFee = calculateSpecialWaterFee(userWaterConsumption);
    double fixedWaterVAT = calculateWaterVAT(fixedWaterCharge);
    double fixedFeesVAT = calculateFeesVAT(null, fixedSewerageFee, fixedSpecialWaterFee);
    double fixedTotal = calculateTotal(null, fixedWaterCharge, fixedSewerageFee, fixedSpecialWaterFee, fixedWaterVAT,
        fixedFeesVAT);

    double tieredVAT = tieredWaterVAT + tieredFeesVAT;
    double fixedVAT = fixedWaterVAT + fixedFeesVAT;

    displayBill(userConsumerNumber, userWaterConsumption, tieredWaterCharge, tieredSewerageFee, tieredSpecialWaterFee,
        tieredVAT, tieredTotal, fixedVAT, fixedTotal);
  }

  private double calculateTieredWaterCharge(int userWaterConsumption) {
    double waterCharge = 0;

    if (userWaterConsumption > 160) {
      waterCharge += (userWaterConsumption - 160) * 4.670;
      userWaterConsumption = 160;
    }

    if (userWaterConsumption > 120) {
      waterCharge += (userWaterConsumption - 120) * 3.820;
      userWaterConsumption = 120;
    }

    if (userWaterConsumption > 60) {
      waterCharge += (userWaterConsumption - 60) * 1.273;
      userWaterConsumption = 60;
    }

    if (userWaterConsumption > 40) {
      waterCharge += (userWaterConsumption - 40) * 0.743;
      userWaterConsumption = 40;
    }

    if (userWaterConsumption > 10) {
      waterCharge += (userWaterConsumption - 10) * 0.636;
      userWaterConsumption = 10;
    }

    if (userWaterConsumption > 0) {
      waterCharge += userWaterConsumption * 0.420;
    }

    return waterCharge;
  }

  private double calculateFixedWaterCharge(int userWaterConsumption) {
    double waterCharge = 0;

    if (userWaterConsumption > 0) {
      waterCharge = userWaterConsumption * 0.5;
    }

    return waterCharge;
  }

  private double calculateSewerageFee(double waterCharge) {
    return waterCharge * 0.8;
  }

  private double calculateSpecialWaterFee(int userWaterConsumption) {
    return userWaterConsumption * 0.07;
  }

  private double calculateWaterVAT(double waterCharge) {
    return waterCharge * 0.13;
  }

  private double calculateFeesVAT(Integer fixedFee, double sewerageFee, double specialWaterFee) {
    if (fixedFee != null) {
      return (fixedFee + sewerageFee + specialWaterFee) * 0.24;
    } else {
      return (sewerageFee + specialWaterFee) * 0.24;
    }
  }

  private double calculateTotal(Integer fixedFee, double waterCharge, double sewerageFee, double specialWaterFee,
      double waterVAT, double feesVAT) {
    if (fixedFee != null) {
      return fixedFee + waterCharge + sewerageFee + specialWaterFee + waterVAT + feesVAT;
    } else {
      return waterCharge + sewerageFee + specialWaterFee + waterVAT + feesVAT;
    }
  }

  private void displayBill(int consumerNumber, int waterConsumption, double waterCharge, double sewerageFee,
      double specialWaterFee, double tieredVAT, double tieredTotal, double fixedVAT, double fixedTotal) {
    waterView.displayMessage("Arithmos katanaloti: " + consumerNumber);
    waterView.displayMessage("Ogkos nerou: " + waterConsumption);
    waterView.displayMessage("Xrewsi nerou: " + formatDouble(waterCharge));
    waterView.displayMessage("Xrewsi apoxeteusis: " + formatDouble(sewerageFee));
    waterView.displayMessage("Eidiko telos kyklou nerou: " + formatDouble(specialWaterFee));
    waterView.displayMessage("FPA klimakwtis xrewsis: " + formatDouble(tieredVAT));
    waterView.displayMessage("SYNOLIKO POSO KLIMAKWTIS XREWSIS: " + formatDouble(tieredTotal));
    waterView.displayMessage("FPA statheris xrewsis: " + formatDouble(fixedVAT));
    waterView.displayMessage("SYNOLIKO POSO STATHERIS XREWSIS: " + formatDouble(fixedTotal));
  }

  private static String formatDouble(double input) {
    return String.format("%.2f", input);
  }
}