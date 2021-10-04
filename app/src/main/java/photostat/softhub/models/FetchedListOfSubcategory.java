package photostat.softhub.models;

public class FetchedListOfSubcategory {

    private String subcategory_id;
    private String subcategory_name;
    private String subcategory_description;
    private String icon;

    public FetchedListOfSubcategory(String subcategory_id, String subcategory_name, String subcategory_description,String icon) {
        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
        this.subcategory_description = subcategory_description;
        this.icon = icon;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getSubcategory_description() {
        return subcategory_description;
    }

    public void setSubcategory_description(String subcategory_description) {
        this.subcategory_description = subcategory_description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
