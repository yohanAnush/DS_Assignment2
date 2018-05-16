package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Payment;
import com.sliit.ds.FastFoodOnlineIT16032798.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {


    @Autowired
    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    // GET all payment entries in the database.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPaymentEntries() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    // ADD new entry for payment.
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        // TODO keep only the last 4 digits of the card number visible.
        payment.setPid((payment.getUid()+payment.getPaymentDate().toString()+new Date().toString()).hashCode());

        paymentService.savePayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
