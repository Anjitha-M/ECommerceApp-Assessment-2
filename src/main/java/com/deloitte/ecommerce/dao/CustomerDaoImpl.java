package com.deloitte.ecommerce.dao;

import com.deloitte.ecommerce.entities.CustomerWallet;
import exceptions.CustomerNotFoundException;

import java.util.*;


public class CustomerDaoImpl implements ICustomerDao{
    private Map<String, CustomerWallet> storage = new HashMap<>();

    public CustomerDaoImpl() {
        CustomerWallet c1 = new CustomerWallet("9446012026", "Ram", 1000, "1234");
        CustomerWallet c2 = new CustomerWallet("9495574686", "Raj", 300, "5678");
        storage.put("9446012026", c1);
        storage.put("9495574686", c2);
    }

    @Override
    public void addCustomer(CustomerWallet c) {
        storage.put(c.getPhoneNo(), c);
    }

    @Override
    public CustomerWallet findByPhone(String ph) {
        CustomerWallet c = storage.get(ph);
        if (c == null) {
            throw new CustomerNotFoundException("No customer found with phone number " + ph);
        }
        return c;
    }

    @Override
    public Set<CustomerWallet> allCustomers() {
        Collection<CustomerWallet> cust = storage.values();
        Set<CustomerWallet> customerSet = new HashSet<>(cust);
        return customerSet;
    }

    @Override
    public void transferAmount(CustomerWallet c1, CustomerWallet c2, double amount) {
       double bal = c1.getBalance();

        if (amount < bal) {
            bal = bal - amount;
            c1.setBalance(bal);
            double bal1 = c2.getBalance();
            bal1 = bal1 + amount;
            c2.setBalance(bal1);
            System.out.println("Amount " + amount + " transferred from " + c1.getName() + " to " + c2.getName());
        }else {
            System.out.println("Insufficient balance to transfer");
        }

    }

    @Override
    public boolean credentialsCorrect(String phone, String password) {
        CustomerWallet customer = storage.get(phone);
        if(customer == null){
            return false;
        }

        return customer.getPassword().equals(password);
    }
}
