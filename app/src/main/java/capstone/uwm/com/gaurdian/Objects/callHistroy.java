package capstone.uwm.com.gaurdian.Objects;

import android.support.annotation.NonNull;

/**
 * Created by Anitha on 3/5/2018.
 */

public class CallHistroy implements Comparable<CallHistroy> {

    String phoneNumber;
    String mode;
    String duration;
    String date;
    String key;
    String incomingDuration;
    String outgoingDuration;
    int totalIncoming;
    int totalOutgoing;

    public CallHistroy(String _phontNumber, String _incomingDuration, String _outgoingDuration ){
        setPhoneNumber( _phontNumber );
        setIncomingDuration( _incomingDuration );
        setOutgoingDuration( _outgoingDuration );
    }

    public CallHistroy(String _date, int _totalIncoming, int _totalOutgoing ){
        setDate(_date);
        setTotalIncoming( _totalIncoming );
        setTotalOutgoing( _totalOutgoing );
    }

    public void updateCall(int _totalIncoming, int _totalOutgoing){
        setTotalIncoming( _totalIncoming );
        setTotalOutgoing( _totalOutgoing );
    }

    public void updateDuration(String _incomingDuration, String _outgoingDuration){
        setOutgoingDuration( _incomingDuration );
        setIncomingDuration( _outgoingDuration );
    }

    public CallHistroy(String _phoneNumber, String _mode, String _duration, String _date ){
        setPhoneNumber( _phoneNumber );
        setDuration( _duration );
        setDate( _date );
        setMode( _mode );
    }

    public CallHistroy(String _phoneNumber, String _mode, String _duration, String _date, String _key ){
        setPhoneNumber( _phoneNumber );
        setDuration( _duration );
        setDate( _date );
        setMode( _mode );
        setKey( _key );
    }

    public String getIncomingDuration() {
        return incomingDuration;
    }

    public String getOutgoingDuration() {
        return outgoingDuration;
    }

    public int getTotalIncoming() {
        return totalIncoming;
    }

    public int getTotalOutgoing() {
        return totalOutgoing;
    }

    public void setIncomingDuration(String incomingDuration) {
        this.incomingDuration = incomingDuration;
    }

    public void setOutgoingDuration(String outgoingDuration) {
        this.outgoingDuration = outgoingDuration;
    }

    public void setTotalIncoming(int totalIncoming) {
        this.totalIncoming = totalIncoming;
    }

    public void setTotalOutgoing(int totalOutgoing) {
        this.totalOutgoing = totalOutgoing;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public String getMode() {
        return mode;
    }

    public String getDuration() {
        return duration;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public int compareTo(@NonNull CallHistroy callHistroyObject) {

        String[] part = callHistroyObject.date.split("/");
        String[] part1 = date.split("/");
        int date = Integer.parseInt(part[0]);
        int month = Integer.parseInt(part[1]);
        String _year = part[2];
        int year = Integer.parseInt(_year.substring(0,4));

        int date1 = Integer.parseInt(part1[0]);
        int month1 = Integer.parseInt(part1[1]);
        String _year1 = part1[2];
        int year1 = Integer.parseInt(_year1.substring(0,4));
        if(year > year1)
            return  -1;
        else if(month > month1)
            return  -1;
        else if(date >date1)
            return -1;
        else
            return 0;
    }
}