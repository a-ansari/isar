package ir.isar.isarapp.oldapp;

import ir.isar.isarapp.model.OldStudentModel;
import ir.isar.isarapp.entity.Student;
import javax.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class SharifOnlyConverter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void convert(OldStudentModel model, Student entity) {
        guessAccountNumber(entity);
        guessNationalNumber(entity);
        guessMobileNumber(entity);
    }

    private void guessAccountNumber(Student entity) {
        // diplomaType -> accountNumber
        String diplomaType = entity.getDiplomaType();
        if (diplomaType == null) {
            return;
        }
        diplomaType = diplomaType.replaceAll("/", "");
        if (StringUtils.isNumeric(diplomaType)) {
            entity.setAccountNumber(entity.getDiplomaType());
            entity.setDiplomaType(null);
        } else {
            logger.info("unknown diploma type: {}", diplomaType);
        }
    }

    private void guessNationalNumber(Student entity) {
        // diplomaPlace -> nationalNumber
        String diplomaPlace = entity.getDiplomaPlace();
        if (diplomaPlace == null) {
            return;
        }
        if (StringUtils.isNumeric(diplomaPlace)) {
            entity.setNationalNumber(Long.parseLong(diplomaPlace));
            entity.setDiplomaPlace(null);
        } else {
            logger.info("unknown diploma place: {}", entity.getDiplomaPlace());
        }
    }

    private void guessMobileNumber(Student entity) {
        // phoneNumber -> mobileNumber
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber == null) {
            return;
        }
        boolean error = false;
        entity.setPhoneNumber(null);
        for (String phone: phoneNumber.split("-")){
            if (phone.startsWith("09") || phone.startsWith("9")){
                if (entity.getMobileNumber() == null) {
                    entity.setMobileNumber(phone);
                } else {
                    error = true;
                    break;
                }
            } else {
                if (entity.getPhoneNumber() == null) {
                    entity.setPhoneNumber(phone);
                } else {
                    error = true;
                    break;
                }
            }
        }
        if (error) {
            entity.setPhoneNumber(phoneNumber);
        }
    }
}
