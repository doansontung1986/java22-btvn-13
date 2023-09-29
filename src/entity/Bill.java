package entity;

public class Bill implements Displayable {
    private Customer customer;
    private ServiceDetail[] serviceDetails;

    public Bill(Customer customer, ServiceDetail[] serviceDetails) {
        this.customer = customer;
        this.serviceDetails = serviceDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ServiceDetail[] getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ServiceDetail[] serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public void addServiceDetails(ServiceDetail[] serviceDetails) {
        ServiceDetail[] newServiceDetails = new ServiceDetail[this.serviceDetails.length + serviceDetails.length];

        for (int i = 0; i < this.serviceDetails.length; i++) {
            if (newServiceDetails[i] == null) {
                newServiceDetails[i] = this.serviceDetails[i];
            }
        }

        for (int i = 0; i < serviceDetails.length; i++) {
            if (newServiceDetails[i] == null) {
                newServiceDetails[i] = serviceDetails[i];
            }
        }

        this.serviceDetails = newServiceDetails;
    }

    @Override
    public void displayInfo() {
        for (ServiceDetail serviceDetail : serviceDetails) {
            if (serviceDetail != null) {
                System.out.printf("%-16s | %-16s | %-16s | %-16s |\n", customer.getName(), serviceDetail.getService().getServiceName(), serviceDetail.getService().getUnit(), serviceDetail.getCharge());
            }
        }
    }

}
