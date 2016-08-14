package ir.isar.isarapp.util;

import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.entity.CodeValue;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class ConversionUtils {

    public static final String NULL_STRING = new String(new byte[]{0});

    public String parseAsString(String str) {
        if (str == null || str.isEmpty() || str.equals(NULL_STRING)) {
            return null;
        }
        return str;
    }

    public Long parseAsLong(String str) {
        if (str == null || str.isEmpty() || str.equals(NULL_STRING)) {
            return null;
        }
        return Long.parseLong(str);
    }

    public Integer parseAsInteger(String str) {
        if (str == null || str.isEmpty() || str.equals(NULL_STRING)) {
            return null;
        }
        return Integer.parseInt(str);
    }

    public Double parseAsDouble(String str) {
        if (str == null || str.isEmpty() || str.equals(NULL_STRING)) {
            return null;
        }
        return Double.parseDouble(str);
    }

    public Long parseAsLongNoZero(String str) {
        Long value = parseAsLong(str);
        if (value == null || value.equals(0L)) {
            return null;
        }
        return value;
    }

    public Integer parseAsIntegerNoZero(String str) {
        Integer value = parseAsInteger(str);
        if (value == null || value.equals(0)) {
            return null;
        }
        return value;
    }

    public Double parseAsDoubleNoZero(String str) {
        Double value = parseAsDouble(str);
        if (value == null || value.equals(0.0)) {
            return null;
        }
        return value;
    }

    public Integer parseAsIntegerFromDouble(String str) {
        if (str == null || str.isEmpty() || str.equals(NULL_STRING)) {
            return null;
        }
        double d = Double.parseDouble(str);
        return (int) d;
    }

    //////////////////////////////////////////////////////
    public String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (isPrimitive(obj)) {
            return obj.toString();
        }
        if (obj instanceof CodeValue) {
            return ((CodeValue) obj).getValue();
        }
        if (obj instanceof GenderEnum) {
            return ((GenderEnum) obj).getLocalName();
        }
        if (obj instanceof MarriageEnum) {
            return ((MarriageEnum) obj).getLocalName();
        }

        throw new IllegalArgumentException("Unknown field type: " + obj.getClass().getSimpleName());

    }

    public boolean isPrimitive(Object obj) {
        Class[] primitiveTypes = {Integer.class, Long.class, Double.class, String.class};
        for (Class c : primitiveTypes) {
            if (c.equals(obj.getClass())) {
                return true;
            }
        }
        return false;
    }

    public String nationalCode(Long code) {
        if (code == null) {
            return "";
        }
        String str = code.toString();
        if (str.length() > 6) {
            while (str.length() < 10) {
                str = "0" + str;
            }
        }
        return str;
    }
}
