package capstone.uwm.com.gaurdian.Objects;

import java.util.Date;

/**
 * Created by Anitha on 3/23/2018.
 */

public class AppUsage {

    String appName;
    int durationInMinutes;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
