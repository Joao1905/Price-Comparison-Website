package servlet;

import modelo.PriceHistory;
import facade.PriceHistoryFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Whitelist;

@WebServlet(name = "GetGPUAttributes", urlPatterns = {"/GetGPUAttributes"})
public class GetGPUAttributes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String unique_name = request.getParameter("unique_name");
        
        //Imput Validation
        Whitelist only_asci = new Whitelist();
        only_asci.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-");
        if (!only_asci.Apply(unique_name)){
            request.setAttribute("gpu_products", "");
            return;
        }
        
        List<PriceHistory> gpu_products = new ArrayList<>();
        gpu_products = PriceHistoryFacade.SelectCheapestByShop(2, unique_name);
        
        //Se modelo não existir
        if (gpu_products == null || gpu_products.isEmpty()) {
            request.setAttribute("gpu_products", "");
            return;
        }
        
        List<List<PriceHistory>> historical_price = new  ArrayList<>();
        for (PriceHistory pc: gpu_products) {
            pc.setPage_visits(pc.getPage_visits()+1);
            PriceHistoryFacade.UpdatePCColumnByID(pc.getPrice_entry_id(), pc, "page_visits" );
            
            List<PriceHistory> price_list = new ArrayList<>();
            price_list = PriceHistoryFacade.SelectHistoricalPrice (pc.getProduct_id(), pc.getModel_id(), pc.getShop_id(), 180);
            historical_price.add(price_list);
        }
        
        request.setAttribute("historical_price", historical_price);
        request.setAttribute("gpu_products", gpu_products);
        
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
