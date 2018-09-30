/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BeatModel;

/**
 *
 * @author matheus
 */
@WebServlet(name = "DJController", urlPatterns =
{
    "/DJController"
})
public class DJController extends HttpServlet
{

    @Override
    public void init() throws ServletException
    {
        BeatModel beatModel = new BeatModel();
        beatModel.initialize();
        getServletContext().setAttribute("beatModel", beatModel);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        BeatModel beatModel = (BeatModel) getServletContext().getAttribute("beatModel");

        String bpm = request.getParameter("bpm");
        if (bpm == null)
        {
            bpm = Integer.toString(beatModel.getBPM());
        }

        String set = request.getParameter("set");
        if (set != null)
        {
            int bpmNumber = Integer.parseInt(bpm);
            beatModel.setBPM(bpmNumber);
        }

        String decrease = request.getParameter("decrease");
        if (decrease != null)
        {
            beatModel.setBPM(beatModel.getBPM() - 10);
        }
        String increase = request.getParameter("increase");
        if (increase != null)
        {
            beatModel.setBPM(beatModel.getBPM() + 10);
        }
        String on = request.getParameter("on");
        if (on != null)
        {
            beatModel.on();
        }

        String off = request.getParameter("off");
        if (off != null)
        {
            beatModel.off();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
