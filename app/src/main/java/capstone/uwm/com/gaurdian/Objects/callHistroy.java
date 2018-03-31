package capstone.uwm.com.gaurdian.Objects;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Anitha on 3/5/2018.
 */

public class CallHistroy {

    String phoneNumber;
    String mode;
    Time duration;
    Date date;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getMode() {
        return mode;
    }

    public Time getDuration() {
        return duration;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
