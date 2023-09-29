package logichandle;

import entity.Service;
import utilities.PrintMessageUtility;
import utilities.ScannerUtility;

import java.util.InputMismatchException;

public class ServiceLogic {

    private Service[] services = new Service[100];

    public void inputServiceList() {
        System.out.print("Nhập số lượng dịch vụ cần thêm: ");
        int numberOfService;
        do {
            try {
                numberOfService = ScannerUtility.inputIntegerNumber();
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForString();
            }
        } while (true);

        for (int i = 0; i < numberOfService; i++) {
            System.out.println("Nhập thông tin dịch vụ thứ " + (i + 1));
            Service service = new Service();
            service.inputInfo();
            saveService(service);
        }
    }

    private void saveService(Service service) {
        for (int i = 0; i < services.length; i++) {
            if (services[i] == null) {
                services[i] = service;
                break;
            }
        }
    }

    public void displayServiceList() {
        if (checkEmptyServiceList(services)) {
            System.out.println("-------------------------");
            System.out.println("Danh sách dịch vụ rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-12s | %-16s | %-16s |\n", "Mã dịch vụ", "Tên dịch vụ", "Đơn giá");
        for (Service service : services) {
            if (service != null) {
                service.displayInfo();
            }
        }
    }

    private boolean checkEmptyServiceList(Service[] services) {
        for (Service service : services) {
            if (service != null) {
                return false;
            }
        }

        return true;
    }

    public Service searchServiceById(int serviceId) {
        for (Service service : services) {
            if (service != null && service.getServiceId() == serviceId) {
                return service;
            }
        }
        return null;
    }
}
