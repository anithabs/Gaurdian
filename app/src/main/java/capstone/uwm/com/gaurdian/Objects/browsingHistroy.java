package capstone.uwm.com.gaurdian.Objects;

import java.util.Date;

/**
 * Created by Anitha on 3/5/2018.
 */

public class browsingHistroy {
    String browseLog;
    Date browseDate;

    public Date getBrowseDate() {
        return browseDate;
    }

    public String getBrowseLog() {
        return browseLog;
    }

    public void setBrowseDate(Date browseDate) {
        this.browseDate = browseDate;
    }

    public void setBrowseLog(String browseLog) {
        this.browseLog = browseLog;
    }
}
