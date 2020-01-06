package com.deloitte.ecommerce.ui;

import com.deloitte.ecommerce.dao.CustomerDaoImpl;
import com.deloitte.ecommerce.service.CustomerServiceImpl;
import com.deloitte.ecommerce.service.ICustomerService;
import com.deloitte.ecommerce.entities.CustomerWallet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/second")
public class HomeServlet extends HttpServlet {

    private ICustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        String signedOutVal = req.getParameter("signout");
        boolean sessionDestroyed = false;
        if (signedOutVal != null && signedOutVal.equals("true")) {
            session.invalidate();
            sessionDestroyed = true;
        }
        Object phoneNumberObj = null;
        if (!sessionDestroyed) {
            phoneNumberObj = session.getAttribute("Phonenumber");
        }

        if (phoneNumberObj == null || phoneNumberObj.toString().isEmpty()) {

            resp.getWriter().println("you are not signed in yet");
            String signInLink = "<a href='/html/form.html'> Sign In </a> ";
            writer.println(signInLink);
            return;
        }
        String phoneNumber = phoneNumberObj.toString();
        CustomerWallet customer = customerService.findByPhone(phoneNumber);

        String name = customer.getName();
        double bal = customer.getBalance();
        writer.println("Welcome " + name+". Your balance = " + bal);
        String signoutLink = "<a href='/second?signout=true'> Sign out </a> ";
        writer.println(signoutLink);

    }


}







