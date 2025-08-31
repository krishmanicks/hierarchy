package com.zoho.hierarchy.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(APIServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
        try{
            this.doOperation(request, response, "GET");
        }
        catch (Exception e){
            System.out.println("Exception "+ e);
        }
    }

    private void doOperation(HttpServletRequest request, HttpServletResponse response, String operation) throws Exception{
        logger.log(Level.INFO, "Enter DoOperation: " + operation);
        System.out.println("Request: " +request);
        System.out.println("Response: " +response);
    }

}
