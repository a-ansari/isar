package ir.isar.isarapp.enm;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author User
 */
public enum MarriageEnum {

    Unmarried, Married;

    protected final ResourceBundle messages;
    protected String localName;

    private MarriageEnum() {
        this.messages = ResourceBundle.getBundle(getClass().getName());
        this.localName = messages.getString("isar.MarriageEnum." + super.name());
    }

    public String getLocalName() {
        return localName;
    }

    @Override
    public String toString() {
        return localName;
    }
    
    public static MarriageEnum getByCode(int code) {
        return values()[code];
    }

    public static MarriageEnum getByLocalName(String localName) {
        if (localName == null || localName.isEmpty()) {
            return null;
        }
        for (MarriageEnum enm : MarriageEnum.values()) {
            if (enm.getLocalName().equals(localName)) {
                return enm;
            }
        }
        return null;
    }

    protected static List<String> allLocalNames;

    public static List<String> getAllLocalNames() {
        if (allLocalNames != null) {
            return allLocalNames;
        }
        allLocalNames = new ArrayList<>();
        for (MarriageEnum enm : MarriageEnum.values()) {
            allLocalNames.add(enm.getLocalName());
        }
        return allLocalNames;
    }
}
