package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.helper.PaymentHelper;
import com.sliit.ds.FastFoodOnlineIT16032798.model.Payment;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.PaymentServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/payment")
@CrossOrigin(origins = "http://localhost:8080")
public class PaymentController {


    @Autowired
    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Autowired
    private SessionServiceImpl sessionService = new SessionServiceImpl();

    @Autowired
    private FoodServiceImpl foodService = new FoodServiceImpl();

    private PaymentHelper paymentHelper = new PaymentHelper();


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

    // GET loyalty points count of a given user.
    @RequestMapping(value = "/{uid}/loyalty", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getTotalLoyaltyPoints(@RequestHeader("Authentication") long authKey,@PathVariable("uid") long uid) {
        Map<String, String> response = new HashMap<>();

        if (sessionService.authenticate(authKey)) {
            List<Payment> paymentsByUser = paymentService.findByUid(uid);
            double tot = 0;

            for(Payment payment: paymentsByUser) { tot += payment.getLoyaltyPoints(); }

            response.put("success", "true");
            response.put("loyalty", Double.toString(tot));
        }
        else {
            response.put("success", "false");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ADD new entry for payment.
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> addPayment(@RequestHeader("Authentication") long authKey, @RequestBody Payment payment) {
        Map<String, String> response = new HashMap<>();

        if (sessionService.authenticate(authKey)) {
            payment.setPid((payment.getUid() + payment.getPaymentDate().toString() + new Date().toString()).hashCode());

            // the client is not supposed to send the amount to be charged since it can be easily altered.
            // instead the fId and count of each is sent.
            double tot = 0;
            Map<String, Integer> items = payment.getItemsAndCounts();
            // calculate
            for (String fId: items.keySet()) { tot += (foodService.getPriceOf(fId) * items.get(fId)); }
            payment.setAmount(tot);

            // give loyalty points for the amount.
            payment.setLoyaltyPoints(tot * (0.01/100));

            // for credit cards we only keep the last 4 digits of the card number for,
            // security concerns.
            if (payment.getPaymentType().equals("card")) {
                Map<String, String> paymentDetails = payment.getPaymentDetails();
                String cardNumber = paymentDetails.get("number");
                String censoredCardNumber = "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length()-4, cardNumber.length());
                paymentDetails.put("number", censoredCardNumber);
            }

            // we aren't technically contacting any payment gateways :(
            // but we'll direct if the payment is successful.
            paymentService.savePayment(payment);
            response.put("success", "true");
            response.put("redirect", "bill.html");
            response.put("pid", Long.toString(payment.getPid()));
        }
        else {
            response.put("success", "false");
            response.put("redirect", "buy.html");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
