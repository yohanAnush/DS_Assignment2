package com.sliit.ds.FastFoodOnlineIT16032798.repository;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Payment findByPid(long pid);

    List<Payment> findByPaymentDate(Date date);
    List<Payment> findByUid(long uid);

    void deleteByPid(long pid);
}
