package capstone.uwm.com.gaurdian.Objects;

import java.util.Date;

/**
 * Created by Anitha on 3/5/2018.
 */

public class appHistroy {
    String appName;
    Date installedDate;

    public Date getInstalledDate() {
        return installedDate;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }
}
