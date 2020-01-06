package com.deloitte.ecommerce.ui;

import com.deloitte.ecommerce.dao.CustomerDaoImpl;
import com.deloitte.ecommerce.entities.CustomerWallet;
import com.deloitte.ecommerce.service.CustomerServiceImpl;
import com.deloitte.ecommerce.service.ICustomerService;

import java.util.Collection;
import java.util.Set;

public class CustomerWalletUi {
    private ICustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    public static void main(String[] args) {
        CustomerWalletUi cui = new CustomerWalletUi();
        cui.execute();
    }

    public void execute() {
        try {
//            CustomerWallet c1 = new CustomerWallet("9446012026", "Ram", 1000, "1234");
//            CustomerWallet c2 = new CustomerWallet("9495574686", "Raj", 300, "5678");
//            customerService.addCustomer(c1);
//            customerService.addCustomer(c2);
//
//            CustomerWallet found = customerService.findByPhone("9446012026");
//            System.out.println("The customer found with given ph. no: " + found.getName());
//
//            System.out.println("-------transferring amount -------------");
//            customerService.transferAmount(c1, c2, 100);
//
            System.out.println(" -----------------Printing all customers-----------------------");
            Set<CustomerWallet> customers = customerService.allCustomers();
            print(customers);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }


    }

    public void print(Collection<CustomerWallet> cust) {
        for (CustomerWallet c : cust) {
            System.out.println("Customer [" + c.getName() +
                    ", Phone Number: " + c.getPhoneNo() + ", Wallet Balance: " + c.getBalance() + "]");
        }
    }
}
