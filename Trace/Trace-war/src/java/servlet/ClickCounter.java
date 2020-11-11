package servlet;

import facade.PriceHistoryFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PriceHistory;
import tools.Whitelist;

@WebServlet(name = "ClickCounter", urlPatterns = {"/ClickCounter"})
public class ClickCounter extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String string_id = request.getParameter("id");

         if (string_id == null || string_id.isEmpty()) {
            response.sendRedirect("error.jsp?error=ID nao encontrado");
            return;
         }
         
         //Imput validation ID
        Whitelist only_numbers = new Whitelist();
        only_numbers.setWhitelist("0123456789");
        if (!only_numbers.Apply(string_id)){
            response.sendRedirect("error.jsp?error=Caractere invalido");
            return;
        }
         
        int id = Integer.parseInt(string_id);
        
        try {
        HttpSession session = request.getSession(false);
        List<PriceHistory> click_products = new ArrayList<>();
        click_products = (List<PriceHistory>) session.getAttribute("click_products");
        
        response.sendRedirect(click_products.get(id).getProduct_url());
        
        click_products.get(id).setPage_clicks(click_products.get(id).getPage_clicks()+1);
        PriceHistoryFacade.UpdatePCColumnByID(click_products.get(id).getPrice_entry_id(), click_products.get(id), "page_clicks" );
        } catch (Exception e) {
            response.sendRedirect("error.jsp?error=Ocorreu um erro");
            return;
        }

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
