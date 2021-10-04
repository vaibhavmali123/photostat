package photostat.softhub.models;

public class FetchedListOfPackages {

    private String packageId;
    private String packageName;
    private String packageDetails;

    public FetchedListOfPackages(String packageId, String packageName, String packageDetails) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageDetails = packageDetails;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(String packageDetails) {
        this.packageDetails = packageDetails;
    }
}
