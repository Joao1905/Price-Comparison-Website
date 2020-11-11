package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.User;
import facade.UserFacade;
import javax.servlet.RequestDispatcher;
import tools.Whitelist;

@WebServlet(name = "ChangePasswd", urlPatterns = {"/ChangePasswd"})
public class ChangePasswd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String changepass1 = request.getParameter("changepass1");
        String changepass2 = request.getParameter("changepass2");
        String changepass3 = request.getParameter("changepass3");
        
        //Imput validation
        Whitelist passwd_list = new Whitelist();
        passwd_list.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-@_.âêôáéíóúãõ !#$?");
        if (!passwd_list.Apply(changepass1) || !passwd_list.Apply(changepass2) || !passwd_list.Apply(changepass3)){
            response.sendRedirect("error.jsp?error=Caractere invalido");
            return;
        }
        
        //Validate if there's session in place
        HttpSession session_check = request.getSession(false);
        User user_check = (session_check != null) ? (User) session_check.getAttribute("session_user") : null;   // statement ? true result : false result;
        if (user_check == null){
            response.sendRedirect("error.jsp?error=Voce precisa estar logado para mudar sua senha");
            return;
        }
        
        if (changepass1 == null || changepass2 == null || changepass3 == null || changepass1.isEmpty() || changepass2.isEmpty() || changepass3.isEmpty()){
                    //A field was not filled
                    response.sendRedirect("error.jsp?error=Um campo nao foi preenchido");
                    return;
        }
        
        if (changepass2.length() < 10 || changepass2.length() > 48) {
                    //Short or Long password
                    response.sendRedirect("error.jsp?error=Sua senha precisa conter ao menos 10 digitos");
                    return;
        }
        
        if (!changepass2.equals(changepass3)){
                    response.sendRedirect("error.jsp?error=Novas senhas sao diferentes");
                    return;
        }
        
        if (user_check.getPasswd().equals(changepass1)){
                    //Success - Insert into database
                    user_check.setPasswd(changepass2);
                    UserFacade.updateUserColumnByLogin(user_check.getLogin(), user_check, "passwd");
                    
                    //Update session
                    User new_user = UserFacade.selectUserByLogin(user_check.getLogin());
                    session_check.setAttribute("session_user", new_user);
                    
                    response.sendRedirect("home.jsp");
                } else {
                    response.sendRedirect("error.jsp?error=Senha incorreta");
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
