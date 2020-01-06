package com.deloitte.ecommerce.service;

import com.deloitte.ecommerce.dao.ICustomerDao;

import com.deloitte.ecommerce.entities.CustomerWallet;
import exceptions.InvalidPhoneNumberException;

import java.util.Set;


public class CustomerServiceImpl implements ICustomerService {
    private ICustomerDao dao;

    public CustomerServiceImpl(ICustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public void transferAmount(CustomerWallet c1, CustomerWallet c2, double amount) {
        dao.transferAmount(c1, c2, amount);
    }

    @Override
    public void addCustomer(CustomerWallet c) {
        dao.addCustomer(c);
    }

    @Override
    public CustomerWallet findByPhone(String ph) {
        if (ph == null || (ph.length() != 10)) {
            throw new InvalidPhoneNumberException("Invalid Phone Number!!!");
        }

        return dao.findByPhone(ph);
    }

    @Override
    public Set<CustomerWallet> allCustomers() {
        Set<CustomerWallet> customers = dao.allCustomers();
        return customers;
    }

    @Override
    public boolean credentialsCorrect(String phone, String password) {
        boolean correct = dao.credentialsCorrect(phone, password);
        return correct;
    }
}
