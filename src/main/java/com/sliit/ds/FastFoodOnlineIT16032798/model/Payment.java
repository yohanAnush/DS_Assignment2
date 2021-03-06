package com.sliit.ds.FastFoodOnlineIT16032798.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "payment")
public class Payment {

    @Id
    private long pid;
    private long uid;
    private double amount;
    private double loyaltyPoints;
    private Map<String, Integer> itemsAndCounts;
    private String paymentType;
    private Date paymentDate;
    private Map<String, String> paymentDetails;

    // there are two payment types:- credit card and dialog post paid.
    // we will assign a constructor for each type of payment.
    public Payment() { }

    public long getPid() {
        return pid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Map<String, Integer> getItemsAndCounts() {
        return itemsAndCounts;
    }

    public void setItemsAndCounts(Map<String, Integer> itemsAndCounts) {
        this.itemsAndCounts = itemsAndCounts;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Map<String, String> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Map<String, String> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
