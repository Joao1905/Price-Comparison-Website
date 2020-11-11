package servlet;

import facade.ModelFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Model;

@WebServlet(name = "CreateBoxCPU", urlPatterns = {"/CreateBoxCPU"})
public class CreateBoxCPU extends HttpServlet {
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String amd = (String) request.getAttribute("amd");
        String intel = (String) request.getAttribute("intel");
        String str_price = (String) request.getAttribute("price");
        int price = 0;
        
        if (amd == null && intel ==null && str_price == null){
            amd = "amd";
            intel = "intel";
            price = 15000;
        } else if (str_price != null){
            price = Integer.parseInt(str_price);
        }
        
        if (amd == null){
            amd = "";
        }
        
        if (intel == null){
            intel = "";
        }

        
        List<Model> model = new ArrayList<>();
        model = ModelFacade.selectCheapestTable(1, amd, intel, price);
        int count = 0;
        
        try (PrintWriter out = response.getWriter()) {
            
            
            
            out.println("<div class=\"container\" style=\"width: 90%; max-width:initial;\">");
            
            for (Model m : model) {
                count++;
                
                if (count%4 == 1) {
                    out.println("<div class=\"row\">");
                }
                
                if (count<5){
                   
                    out.println("<div class=\"col s3\" style=\"margin-top: 13vh !important;\">");
                        out.println("<div class=\"card-panel\">");
                            out.println("<div class=\"row\">");
                                out.println("<div class=\"col s10 offset-s1\">");
                                
                                    out.println("<a href=\"cpu_product.jsp?unique_name="+m.getUnique_name()+"\" style=\"color: #404040 !important\">");
                                    out.println("<img src="+m.getImage()+" onerror=\"this.onerror=null;this.src='resources/img/cpu/error.jpg';\" width=\"100%\" height=\"100%\" style=\"\">");
                                    
                                    out.println("<h6>"+m.getName()+"</h6>");
                                    out.println("<h6><b>R$ "+m.getCheapest_price()+"</b></h6>");
                                    out.println("</a>");
                                    
                                out.println("</div>");
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                
                } else {
                    
                    out.println("<div class=\"col s3\">");
                        out.println("<div class=\"card-panel\">");
                            out.println("<div class=\"row\">");
                                out.println("<div class=\"col s10 offset-s1\">");
                                    
                                    out.println("<a href=\"cpu_product.jsp?unique_name="+m.getUnique_name()+"\" style=\"color: #404040 !important\">");
                                    out.println("<img src="+m.getImage()+" onerror=\"this.onerror=null;this.src='resources/img/cpu/error.jpg';\" width=\"100%\" height=\"100%\" style=\"\">");
                                
                                    out.println("<h6>"+m.getName()+"</h6>");
                                    out.println("<h6><b>R$ "+m.getCheapest_price()+"</b></h6>");
                                    out.println("</a>");
                                    
                                out.println("</div>");
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    
                }
                
                if (count%4 == 0 || model.size() - count == 0) {
                    out.println("</div>");
                }
            
            }
            
            out.println("</div>");
        
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
