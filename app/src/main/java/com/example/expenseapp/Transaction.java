package com.example.expenseapp;

public class Transaction {
    String description;
    Double amount;
    String currentDateTime;

    public Transaction(String disc, Double amt, String curDateTime) {
        this.description = disc;
        this.amount = amt;
        this.currentDateTime = curDateTime;
    }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getDate() { return currentDateTime; }
}