package entity;

public class ServiceDetail {
    private Service service;
    private double charge;

    public ServiceDetail(Service service, double charge) {
        this.service = service;
        this.charge = charge;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
