package capstone.uwm.com.gaurdian.Objects;

import java.util.Date;

/**
 * Created by Anitha on 3/5/2018.
 */

public class MessageHistroy {
    String fromContent;
    String fromNumber;
    String toContent;
    String toNumber;
    Date date;

    public MessageHistroy( String _fromContent, String _fromNumber, String _toContent, String _toNumber ,Date _date){
        setFromContent(_fromContent);
        setFromNumber(_fromNumber);
        setToContent(_toContent);
        setToNumber(_toNumber);
        setDate(_date);
    }
    public String getFromContent(){
        return fromContent;
    }

    public Date getDate() {
        return date;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getToContent() {
        return toContent;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setFromContent(String fromContent) {
        this.fromContent = fromContent;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
