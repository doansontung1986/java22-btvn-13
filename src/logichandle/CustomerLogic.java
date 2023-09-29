package logichandle;

import entity.Customer;
import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public class CustomerLogic {

    private Customer[] customers = new Customer[100];

    public void inputCustomerList() {
        System.out.print("Nhập số lượng khách hàng cần thêm: ");
        int numberOfCustomer;
        do {
            try {
                numberOfCustomer = ScannerUtility.inputIntegerNumber();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);
        for (int i = 0; i < numberOfCustomer; i++) {
            System.out.println("Nhập thông tin khách hàng thứ " + (i + 1));
            Customer customer = new Customer();
            customer.inputInfo();
            saveCustomer(customer);
        }
    }

    private void saveCustomer(Customer customer) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                customers[i] = customer;
                break;
            }
        }
    }

    public void displayCustomerList() {
        if (checkEmptyCustomerList(customers)) {
            System.out.println("-------------------------");
            System.out.println("Danh sách khách hàng rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-12s | %-16s | %-16s | %-12s | %-32s |\n", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Loại khách hàng");
        for (Customer customer : customers) {
            if (customer != null) {
                customer.displayInfo();
            }
        }
    }

    private boolean checkEmptyCustomerList(Customer[] customers) {
        for (Customer customer : customers) {
            if (customer != null) {
                return false;
            }
        }

        return true;
    }

    public Customer searchCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer != null && customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

}
