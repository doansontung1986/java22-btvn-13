package logichandle;

import entity.Bill;
import entity.Customer;
import entity.Service;
import entity.ServiceDetail;
import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BillLogic {
    private final CustomerLogic customerLogic;
    private final ServiceLogic serviceLogic;
    private final Bill[] bills = new Bill[100];

    public BillLogic(CustomerLogic customerLogic, ServiceLogic serviceLogic) {
        this.customerLogic = customerLogic;
        this.serviceLogic = serviceLogic;
    }

    public Bill[] getBills() {
        return bills;
    }

    public void inputBillList() {
        System.out.print("Nhập số lượng hóa đơn cho khách hàng đang có: ");
        int numberOfBill;
        do {
            try {
                numberOfBill = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        for (int i = 0; i < numberOfBill; i++) {
            Customer customer = inputCustomerInfo();
            ServiceDetail[] serviceDetails = inputSaleDetails();

            if (!checkExistCustomerInBills(customer, serviceDetails)) {
                Bill bill = new Bill(customer, serviceDetails);
                saveBill(bill);
            }
        }
    }

    private boolean checkExistCustomerInBills(Customer customer, ServiceDetail[] serviceDetails) {
        for (Bill bill : bills) {
            if (bill != null && bill.getCustomer().getCustomerId() == customer.getCustomerId()) {
                bill.addServiceDetails(serviceDetails);
                return true;
            }
        }
        return false;
    }

    public void displayBillLists() {
        if (checkEmptyBillList(bills)) {
            System.out.println("-------------------------");
            System.out.println("Danh sách hóa đơn rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-16s | %-16s | %-16s | %-16s |\n", "Tên khách hàng", "Tên dịch vụ", "Đơn vị tính", "Số tiền cần thanh toán");
        for (Bill bill : bills) {
            if (bill != null) {
                bill.displayInfo();
            }
        }
    }

    private void saveBill(Bill bill) {
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == null) {
                bills[i] = bill;
                break;
            }
        }
    }

    private ServiceDetail[] inputSaleDetails() {
        System.out.print("Nhập số lượng dịch vụ: ");
        int numberOfService;
        do {
            try {
                numberOfService = ScannerUtility.inputIntegerNumber();

                if (numberOfService > 0 && numberOfService < 6) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }

            System.out.println("Mỗi khách hàng chỉ được phép sử dụng không quá 5 loại dịch vụ khác nhau");
        } while (true);

        ServiceDetail[] serviceDetails = new ServiceDetail[numberOfService];
        int count = 0;

        for (int i = 0; i < numberOfService; i++) {
            System.out.println("Thông tin dịch vụ thứ " + (i + 1));
            int serviceId = -1;
            Service service;
            do {
                try {
                    System.out.print("Nhập mã dịch vụ: ");
                    serviceId = ScannerUtility.inputIntegerNumber();
                } catch (InputMismatchException e) {
                    System.out.println("Định dạng service ID sai");
                }

                service = serviceLogic.searchServiceById(serviceId);

                if (service != null) {
                    break;
                }

                System.out.print("Không tồn tại hàng hóa mang mã " + serviceId + ", vui lòng nhập lại: ");

            } while (true);

            System.out.print("Nhập số lượng hàng hóa: ");
            int quantity;
            do {
                try {
                    quantity = ScannerUtility.inputIntegerNumber();

                    if (quantity > 0) {
                        service.setUnit(quantity);
                        break;
                    }
                    System.out.println("Số lượng phải lớn hơn 0");
                } catch (InputMismatchException e) {
                    PrintMessageUtility.printErrorMessageForString();
                }
            } while (true);

            double totalAmount = service.getPrice() * service.getUnit();

            ServiceDetail serviceDetail = new ServiceDetail(service, totalAmount);
            serviceDetails[count] = serviceDetail;
            count++;
        }

        return serviceDetails;
    }

    private Customer inputCustomerInfo() {
        int customerId = -1;
        Customer customer;

        do {
            try {
                System.out.print("Nhập mã nhân viên: ");
                customerId = ScannerUtility.inputIntegerNumber();
            } catch (InputMismatchException e) {
                System.out.println("Định dạng customer ID sai");
            }
            customer = customerLogic.searchCustomerById(customerId);

            if (customer != null) {
                break;
            }

            System.out.println("Không tồn tại nhân viên mang mã " + customerId + ", vui lòng nhập lại.");

        } while (true);

        return customer;
    }

    private boolean checkEmptyBillList(Bill[] bills) {
        for (Bill bill : bills) {
            if (bill != null) {
                return false;
            }
        }

        return true;
    }

    private Bill[] copyBillList(Bill[] bills) throws NullPointerException {
        if (checkEmptyBillList(bills)) {
            return null;
        }

        Bill[] copyBillList = new Bill[bills.length];
        for (int i = 0; i < bills.length; i++) {
            if (copyBillList[i] == null) {
                copyBillList[i] = bills[i];
            }
        }
        return copyBillList;
    }

    public void sortBillListByCustomerName() {
        Bill[] copyBillList;

        try {
            copyBillList = copyBillList(bills);
        } catch (NullPointerException e) {
            System.out.println("Danh sách hóa đơn rỗng.");
            return;
        }

        if (copyBillList != null) {
            for (int i = 0; i < copyBillList.length - 1; i++) {
                boolean isSwap = false;
                for (int j = i + 1; j < copyBillList.length; j++) {
                    if (copyBillList[i] != null && copyBillList[j] != null && copyBillList[i].getCustomer().getName().compareTo(copyBillList[j].getCustomer().getName()) > 0) {
                        swap(copyBillList, i, j);
                        isSwap = true;
                    }
                }

                if (!isSwap) {
                    break;
                }
            }
        }
    }

    public void sortBillListByQuantity() {
        Bill[] copyBillList;

        try {
            copyBillList = copyBillList(bills);
        } catch (NullPointerException e) {
            System.out.println("Danh sách hóa đơn rỗng.");
            return;
        }

        if (copyBillList != null) {
            for (int i = 0; i < copyBillList.length - 1; i++) {
                boolean isSwap = false;
                for (int j = i + 1; j < copyBillList.length; j++) {
                    if (copyBillList[i] != null && copyBillList[j] != null && copyBillList[i].getCustomer().getName().compareTo(copyBillList[j].getCustomer().getName()) < 0) {
                        swap(copyBillList, i, j);
                        isSwap = true;
                    }
                }

                if (!isSwap) {
                    break;
                }
            }
        }
    }

    private void swap(Bill[] bills, int i, int j) {
        Bill tempBillList = bills[i];
        bills[i] = bills[j];
        bills[j] = tempBillList;
    }

    public void displayBillChargeForEachCustomer() {
        if (checkEmptyBillList(bills)) {
            System.out.println("-------------------------");
            System.out.println("Danh sách hóa đơn rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-16s | %-32s |\n", "Tên Khách hàng", "Số tiền cần thanh toán");
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] != null) {
                for (int j = 0; j < bills[i].getServiceDetails().length; j++) {
                    ServiceDetail[] serviceDetails = bills[i].getServiceDetails();
                    if (serviceDetails[j] != null) {
                        double totalCharge = bills[i].getServiceDetails()[j].getCharge();
                        System.out.printf("%-16s | %-16s |\n", bills[i].getCustomer().getName(), totalCharge);
                    }
                }
            }
        }
    }
}
