package ir.isar.isarapp.enm;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author User
 */
public enum GenderEnum  {

    Male, Female;
    protected final ResourceBundle messages;
    protected String localName;

    private GenderEnum() {
        this.messages = ResourceBundle.getBundle(getClass().getName());
        this.localName = messages.getString("isar.GenderEnum." + super.name());
    }

    public String getLocalName() {
        return localName;
    }

    @Override
    public String toString() {
        return localName;
    }
    
    public static GenderEnum getByCode(int code) {
        return values()[code];
    }

    public static GenderEnum getByLocalName(String localName) {
        if (localName == null || localName.isEmpty()) {
            return null;
        }
        for (GenderEnum enm : GenderEnum.values()) {
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
        for (GenderEnum enm : GenderEnum.values()) {
            allLocalNames.add(enm.getLocalName());
        }
        return allLocalNames;
    }
}
