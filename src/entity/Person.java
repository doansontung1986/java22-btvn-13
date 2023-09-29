package entity;

import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public abstract class Person implements Inputable, Displayable {
    protected String name;
    protected String address;
    protected String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public abstract void displayInfo();

    @Override
    public void inputInfo() {
        System.out.println("Nhập họ tên: ");

        do {
            try {
                this.name = ScannerUtility.inputString();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        System.out.println("Nhập địa chỉ: ");

        do {
            try {
                this.address = ScannerUtility.inputString();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        System.out.println("Nhập số điện thoại: ");

        do {
            try {
                this.phoneNumber = ScannerUtility.inputString();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);
    }
}
