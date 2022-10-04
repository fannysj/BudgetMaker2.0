package com.example.budgetmaker2_0;

import Model.Transaction;

import java.util.ArrayList;

public interface transactionObserver {
    void update(ArrayList<Transaction> transactions);
}
