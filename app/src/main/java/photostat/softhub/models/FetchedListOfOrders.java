package photostat.softhub.models;

public class FetchedListOfOrders {

    private String orderId;
    private String mobileNumber;
    private String packageName;
    private String firstName;
    private String lastName;
    private String cityName;
    private String eventName;
    private String eventAddress;
    private String orderDate;

    public FetchedListOfOrders(String orderId, String mobileNumber, String packageName, String firstName, String lastName, String cityName, String eventName, String eventAddress, String orderDate) {
        this.orderId = orderId;
        this.mobileNumber = mobileNumber;
        this.packageName = packageName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
