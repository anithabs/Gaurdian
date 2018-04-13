package capstone.uwm.com.gaurdian.Objects;

/**
 * Created by Anitha on 3/5/2018.
 */

public class BrowsingHistroy {

    String browseLog;
    String browseTitle;
    String browseDate;

    public String getBrowseDate() {
        return browseDate;
    }

    public String getBrowseTitle(){
        return browseTitle;
    }

    public String getBrowseLog() {
        return browseLog;
    }

    public void setBrowseTime(String browseTitle){
        this.browseTitle = browseTitle;
    }

    public void setBrowseDate(String browseDate) {
        this.browseDate = browseDate;
    }

    public void setBrowseLog(String browseLog) {
        this.browseLog = browseLog;
    }
}
