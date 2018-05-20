package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Payment;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.SessionRepository;
import com.sliit.ds.FastFoodOnlineIT16032798.service.PaymentServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/payment")
public class PaymentController {


    @Autowired
    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Autowired
    private SessionServiceImpl sessionService = new SessionServiceImpl();


    // GET all payment entries in the database.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPaymentEntries() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    // GET payment by its pid.
    @RequestMapping(value = "/pid/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getPayment(@PathVariable("pid") long pid) {
        return new ResponseEntity<>(paymentService.findByPid(pid), HttpStatus.OK);
    }

    // GET payment entries by the uid.
    @RequestMapping(value = "/uid/{uid}", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPaymentEntriesByUser(@PathVariable("uid") long uid) {
        return new ResponseEntity<>(paymentService.findByUid(uid), HttpStatus.OK);
    }

    // ADD new entry for payment.
    @RequestMapping(value = "{authKey}/", method = RequestMethod.POST)
    public ResponseEntity<String> addPayment(@PathVariable("authKey") long authKey, @RequestBody Payment payment) {
        if (sessionService.authenticate(authKey)) {
            payment.setPid((payment.getUid() + payment.getPaymentDate().toString() + new Date().toString()).hashCode());

            // for credit cards we only keep the last 4 digits of the card number for,
            // security concerns.
            if (payment.getPaymentType().equals("card")) {
                Map<String, String> paymentDetails = payment.getPaymentDetails();
                String cardNumber = paymentDetails.get("number");
                String censoredCardNumber = "xxxx-xxxx-xxxx-" + cardNumber.substring(12, 16);
                paymentDetails.put("number", censoredCardNumber);
            }

            paymentService.savePayment(payment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return new ResponseEntity<>("Authentication key required in API call.", HttpStatus.BAD_REQUEST);
        }
    }


}
