package com.deloitte.ecommerce.service;

import com.deloitte.ecommerce.entities.CustomerWallet;

import java.util.Set;

public interface ICustomerService {
    void addCustomer(CustomerWallet c);

    CustomerWallet findByPhone(String ph);

    Set<CustomerWallet> allCustomers();
    void transferAmount(CustomerWallet c1, CustomerWallet c2, double amount);
    public boolean credentialsCorrect(String phone, String password);
}
