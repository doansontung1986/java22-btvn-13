package entity;

import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public class Service implements Inputable, Displayable {
    private static int AUTO_ID = 100;
    private int serviceId;
    private String serviceName;
    private double price;
    private int unit;

    public Service() {
        this.serviceId = AUTO_ID;
        AUTO_ID++;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-16s | %-16s |\n", this.serviceId, this.serviceName, this.price);
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập tên dịch vụ: ");

        do {
            try {
                this.serviceName = ScannerUtility.inputString();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        System.out.println("Nhập giá cước: ");

        do {
            try {
                this.price = ScannerUtility.inputDoubleNumber();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);
    }
}
