package capstone.uwm.com.gaurdian.Objects;

/**
 * Created by Anitha on 3/18/2018.
 */

public class AccountDetails {
    String parentName;
    String childName;
    int childAge;
    boolean trackDetails;

    public boolean isTrackDetails() {
        return trackDetails;
    }

    public String getChildName() {
        return childName;
    }

    public int getChildAge() {
        return childAge;
    }

    public String getParentName() {
        return parentName;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setTrackDetails(boolean trackDetails) {
        this.trackDetails = trackDetails;
    }
}
