package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.PaymentType;

import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class PaymentTypeDao extends CodeValueDao<PaymentType> {

    @Override
    public Class getEntityClass() {
        return PaymentType.class;
    }

}
