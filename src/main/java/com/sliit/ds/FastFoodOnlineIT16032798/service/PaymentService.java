package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {

    Payment findByPid(long pid);

    List<Payment> findByUid(long uid);

    List<Payment> findByPaymentDate(Date date);

    List<Payment> getAllPayments();

    void savePayment(Payment payment);

    void updatePayment(long pid, Payment paymentUpdate);

    void deletePayment(Payment payment);

    boolean isPaymentExist(Payment payment);
}
