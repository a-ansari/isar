package ir.isar.isarapp.entity;

import javax.persistence.Entity;

/**
 *
 * @author User
 */
@Entity
public class TermStatus extends CodeValue {

    public static TermStatus Unknown;
    public static TermStatus Normal;
    public static TermStatus Conditional;
    public static TermStatus RemovedWithCounting;
    public static TermStatus RemovedWithoutCounting;

}
