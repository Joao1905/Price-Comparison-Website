package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
import modelo.User;
import facade.UserFacade;
import tools.Whitelist;



@WebServlet(name = "ControlLogin", urlPatterns = {"/controlLogin"})
public class ControlLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Catch form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        //imput validation
        Whitelist only_asci = new Whitelist();
        Whitelist passwd_list = new Whitelist();
        only_asci.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-");
        passwd_list.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-@_.âêôáéíóúãõ !#$?");
        if (!only_asci.Apply(username) || !passwd_list.Apply(password)){
            String x = "Caractere invalido";
            request.setAttribute("error", x);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
        //Validate if there's session in place
       HttpSession session_check = request.getSession(false);
       User user_check = (session_check != null) ? (User) session_check.getAttribute("session_user") : null;   // statement ? true result : false result;
       if (user_check != null){
            String x = "Voce ja esta logado";
            request.setAttribute("error", x);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (username == null || password == null || username.isEmpty() || password.isEmpty()){
            //A field was not filled
            String x = "Um campo nao foi preenchido";
            request.setAttribute("error", x);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
        User user = UserFacade.selectUserByLogin(username);
        if (user == null) {
            //User doesn't exist or field was not filled
            String x = "Usuario inexistente ou senha incorreta";
            request.setAttribute("error", x);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (username.equals(user.getLogin())){
            //Check if Passwords match
            if (password.equals(user.getPasswd())){

                UserFacade.updateUserColumnByLogin(user.getLogin(), user, "last_login");
                
                HttpSession session = request.getSession();
                session.setAttribute("session_user", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else {
                //Password doesn't match
                String x = "Usuario inexistente ou senha incorreta";
                request.setAttribute("error", x);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }
        } else {
            //IMPORTANT: username different than user object (MYSQL Select is case insensitive, .equals() is case sensitive)
            String x = "Usuario inexistente ou senha incorreta";
            request.setAttribute("error", x);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
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
