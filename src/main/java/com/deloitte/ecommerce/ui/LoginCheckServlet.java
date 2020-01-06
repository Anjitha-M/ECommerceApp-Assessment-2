package com.deloitte.ecommerce.ui;

import com.deloitte.ecommerce.dao.CustomerDaoImpl;
import com.deloitte.ecommerce.service.CustomerServiceImpl;
import com.deloitte.ecommerce.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/first")
public class LoginCheckServlet extends HttpServlet {

    private ICustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String phone = req.getParameter("Phonenumber");
        String password = req.getParameter("password");
        boolean correct=customerService.credentialsCorrect(phone,password);

        if (correct) {
            HttpSession session=req.getSession();
            session.setAttribute("Phonenumber",phone);
            resp.sendRedirect("/second");
        }else {
            resp.sendRedirect("html/form.html");
        }
    }


}







