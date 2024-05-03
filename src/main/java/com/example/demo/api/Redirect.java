package com.example.demo.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/index.xhtml")
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (!(session != null && session.getAttribute("user") != null))
        {
        resp.sendRedirect(req.getContextPath() + "/auth/login.xhtml");}
        else {
            resp.sendRedirect(req.getContextPath() + "/events/index.xhtml");
        }
    }
}
