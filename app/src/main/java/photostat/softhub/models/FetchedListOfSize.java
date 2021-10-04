package photostat.softhub.models;

public class FetchedListOfSize {


    private String id;
    private String size;
    private String price;

    public FetchedListOfSize(String id, String size, String price) {
        this.id = id;
        this.size = size;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
