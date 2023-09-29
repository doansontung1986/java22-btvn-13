package statics;

public enum CustomerType {
    PERSONAL("Cá nhân"),
    REPRESENTATIVE_ADMINISTRATIVE_UNIT("Đại diện đơn vị hành chính"),
    BUSINESS_UNIT_REPRESENTATIVE("Đại diện đơn vị kinh doanh");

    public String type;

    CustomerType(String type) {
        this.type = type;
    }
}
