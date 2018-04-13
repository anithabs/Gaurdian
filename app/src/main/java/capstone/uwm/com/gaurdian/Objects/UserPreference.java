package capstone.uwm.com.gaurdian.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Anitha on 3/26/2018.
 */

public class UserPreference {

    private static String parentName;
    private static String parEmailID;
    private static String childName;
    private static String childBirthday;
    private static String parentRelation;
    private static String device;
    private static final String DEVICE_KEY = "deviceKey";
    private static final String PARENT_KEY = "parentKey";
    private static final String EMAIL_KEY ="emailKey";
    private static final String CHILD_KEY = "childType";
    private static final String CBIRTHDAY = "cbirthdayType";
    private static final String CRELATION = "childRelationType";

    public static String getDevice() {
        return device;
    }
    public static void setDeviceKey(String deviceKey) {
        UserPreference.device = deviceKey;
    }

    public void setPreference(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(PARENT_KEY, parentName);
        editor.putString(DEVICE_KEY,device);
        editor.putString(EMAIL_KEY,parEmailID);
        editor.putString(CHILD_KEY,childName);
        editor.putString(CBIRTHDAY,childBirthday);
        editor.putString(CRELATION,parentRelation);
        editor.commit();
    }
    public UserPreference(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setParentName(pref.getString(PARENT_KEY, "parentName"));
        setParEmailID(pref.getString(EMAIL_KEY,"emailID"));
        setChildName(pref.getString(CHILD_KEY,"childName"));
        setChildBirthday(pref.getString(CBIRTHDAY,"08-12-2014"));
        setParentRelation(pref.getString(CRELATION,"mother"));
        setDeviceKey(pref.getString(DEVICE_KEY,"children"));
    }

    public static void setParentName(String name) {
        UserPreference.parentName = name;
    }
    public static void setParEmailID(String email) {
        UserPreference.parEmailID = email;
    }
    public static void setChildName(String _childName) {
        UserPreference.childName = _childName;
    }
    public static void setChildBirthday(String childbday){
        UserPreference.childBirthday = childbday;
    }
    public static void setParentRelation(String relation){
        UserPreference.parentRelation = relation;
    }
    public static String getParentName() {
        return parentName;
    }
    public static String getChildBirthday() {
        return childBirthday;
    }
    public static String getChildName() {
        return childName;
    }
    public static String getParentRelation() {
        return parentRelation;
    }
    public static String getParEmailID() {
        return parEmailID;
    }
}
