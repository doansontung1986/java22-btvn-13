package entity;

import statics.CustomerType;
import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public class Customer extends Person {
    private static int AUTO_ID = 10000;
    private int customerId;
    private CustomerType customerType;

    public Customer() {
        this.customerId = AUTO_ID;
        AUTO_ID++;
    }

    public int getCustomerId() {
        return customerId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-16s | %-16s | %-12s | %-32s |\n", this.customerId, this.name, this.address, this.phoneNumber, this.customerType.type);
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        System.out.println("Nhập loại khách hàng: ");
        int type;

        do {
            try {
                System.out.println("Chọn 1 trong 3 loại khách hàng.");
                System.out.println("1. Cá nhân");
                System.out.println("2. Đại diện đơn vị hành chính");
                System.out.println("3. Đại diện đơn vị kinh doanh");
                type = ScannerUtility.inputIntegerNumber();
                if (type < 1 || type > 3) {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        switch (type) {
            case 1 -> this.customerType = CustomerType.PERSONAL;
            case 2 -> this.customerType = CustomerType.REPRESENTATIVE_ADMINISTRATIVE_UNIT;
            case 3 -> this.customerType = CustomerType.BUSINESS_UNIT_REPRESENTATIVE;
            default -> System.out.println("Loại khách hàng đã chọn không hợp lệ");
        }
    }
}
