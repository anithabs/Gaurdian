package capstone.uwm.com.gaurdian.Objects;

/**
 * Created by Anitha on 3/5/2018.
 */

public class MessageHistroy {
    private String fromContent;
    private String fromNumber;
    private String toContent;
    private String toNumber;
    private String date;
    private String key;

    public MessageHistroy( String _fromContent, String _fromNumber, String _toContent, String _toNumber , String _date ,String _key){
        setFromContent(_fromContent);
        setFromNumber(_fromNumber);
        setToContent(_toContent);
        setToNumber(_toNumber);
        setDate(_date);
        setKey(_key);
    }

    public MessageHistroy( String _fromContent, String _fromNumber, String _toContent, String _toNumber , String _date ){
        setFromContent(_fromContent);
        setFromNumber(_fromNumber);
        setToContent(_toContent);
        setToNumber(_toNumber);
        setDate(_date);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getFromContent(){
        return fromContent;
    }

    public String getDate() {
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

    public void setDate(String date){
        this.date = date;
    }
}
