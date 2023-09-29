package logichandle;

import entity.Bill;
import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public class MenuManagement {
    private final CustomerLogic customerLogic = new CustomerLogic();
    private final ServiceLogic serviceLogic = new ServiceLogic();
    private final BillLogic billLogic = new BillLogic(customerLogic, serviceLogic);

    public void run() {
        while (true) {
            printMenu();
            int functionChoice = chooseFunction();
            switch (functionChoice) {
                case 1:
                    customerLogic.inputCustomerList();
                    break;
                case 2:
                    customerLogic.displayCustomerList();
                    break;
                case 3:
                    serviceLogic.inputServiceList();
                    break;
                case 4:
                    serviceLogic.displayServiceList();
                    break;
                case 5:
                    billLogic.inputBillList();
                    break;
                case 6:
                    billLogic.displayBillLists(billLogic.getBills());
                    break;
                case 7:
                    printSubmenu();
                    int subMenuChoice = chooseSubmenuChoice();
                    switch (subMenuChoice) {
                        case 1:
                            Bill[] sortedByName = billLogic.sortBillListByCustomerName();
                            billLogic.displayBillLists(sortedByName);
                            break;
                        case 2:
                            Bill[] sortedByQuantity = billLogic.sortBillListByQuantity();
                            billLogic.displayBillLists(sortedByQuantity);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ");
                            break;
                    }
                    break;
                case 8:
                    billLogic.displayBillChargeForEachCustomer();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }

        }
    }

    private int chooseFunction() {
        System.out.print("Xin mời lựa chọn chức năng (1-9): ");
        int functionChoice;
        do {
            try {
                functionChoice = ScannerUtility.inputIntegerNumber();
                if (functionChoice > 0 && functionChoice < 10) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return functionChoice;
    }

    private int chooseSubmenuChoice() {
        int choice;

        do {
            System.out.print("Xin mời lựa chọn chức năng (1 hoặc 2): ");

            try {
                choice = ScannerUtility.inputIntegerNumber();

                if (choice > 0 && choice < 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return choice;
    }

    private void printMenu() {
        System.out.println("------PHẦN MỀM QUẢN LÝ HÀNG HÓA");
        System.out.println("1. Nhập danh sách khách hàng mới");
        System.out.println("2. In ra danh sách khách hàng đã có");
        System.out.println("3. Nhập danh sách dịch vụ mới");
        System.out.println("4. In ra danh sách dịch vụ đã có");
        System.out.println("5. Nhập hóa đơn cho mỗi khách hàng");
        System.out.println("6. In danh sách hóa đơn");
        System.out.println("7. Sắp xếp danh sách hóa đơn");
        System.out.println("8. Lập bảng kê số tiền phải trả cho mỗi khách hàng.");
        System.out.println("9. Thoát");
    }

    private void printSubmenu() {
        System.out.println("1. Theo tên nhân viên");
        System.out.println("2. Theo số lượng sử dụng (giảm dần)");
    }
}
