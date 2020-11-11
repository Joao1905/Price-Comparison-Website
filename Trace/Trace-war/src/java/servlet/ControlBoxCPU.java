package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Whitelist;

@WebServlet(name = "ControlBoxCPU", urlPatterns = {"/ControlBoxCPU"})
public class ControlBoxCPU extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String amd = request.getParameter("amd");
        String intel = request.getParameter("intel");
        String str_price = request.getParameter("price");
        
        
        //imput validation
        if (amd != null && !amd.isEmpty()) {
            Whitelist only_asci = new Whitelist();
            only_asci.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            if (!only_asci.Apply(amd)){
                String x = "Caractere invalido";
                request.setAttribute("error", x);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                return;
          }
        }
        
        if (intel != null && !intel.isEmpty()) {
            Whitelist only_asci = new Whitelist();
            only_asci.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            if (!only_asci.Apply(intel)){
                String x = "Caractere invalido";
                request.setAttribute("error", x);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                return;
          }
        }
        
        if (str_price != null && !str_price.isEmpty()) {
            Whitelist only_numbers = new Whitelist();
            only_numbers.setWhitelist("0123456789.,");
            if (!only_numbers.Apply(str_price)){
                String x = "Caractere invalido";
                request.setAttribute("error", x);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                return;
          }
        }
        
        request.setAttribute("amd", amd);
        request.setAttribute("intel", intel);
        request.setAttribute("price", str_price);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cpu.jsp");
        rd.forward(request, response);
 
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
