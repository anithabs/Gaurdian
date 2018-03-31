package capstone.uwm.com.gaurdian.Objects;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Anitha on 3/5/2018.
 */

public class BrowsingHistroy {

    String browseLog;
    Date browseDate;
    Time browseTime;

    public Date getBrowseDate() {
        return browseDate;
    }

    public Time getBrowseTime(){
        return browseTime;
    }

    public String getBrowseLog() {
        return browseLog;
    }

    public void setBrowseTime(Time browseTime){
        this.browseTime = browseTime;
    }

    public void setBrowseDate(Date browseDate) {
        this.browseDate = browseDate;
    }

    public void setBrowseLog(String browseLog) {
        this.browseLog = browseLog;
    }
}
