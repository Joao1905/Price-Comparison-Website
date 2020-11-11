package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.User;
import facade.UserFacade;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import tools.Whitelist;

@WebServlet(name = "ControlRegister", urlPatterns = {"/ControlRegister"})
public class ControlRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String password2 = request.getParameter("password2");
                
                //TODO Imput validation
                Whitelist only_asci = new Whitelist();
                Whitelist email_list = new Whitelist();
                Whitelist passwd_list = new Whitelist();
                only_asci.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-");
                email_list.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-@_.âêôáéíóúãõ");
                passwd_list.setWhitelist("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-@_.âêôáéíóúãõ !#$?");
                if (!only_asci.Apply(username) || !email_list.Apply(email) || !passwd_list.Apply(password) || !passwd_list.Apply(password2)){
                    response.sendRedirect("error.jsp?error=Caractere invalido");
                    return;
                }
                
                //Validate if there's session in place
                HttpSession session_check = request.getSession(false);
                User user_check = (session_check != null) ? (User) session_check.getAttribute("session_user") : null;   // statement ? true result : false result;
                if (user_check != null){
                    response.sendRedirect("error.jsp?error=Voce ja esta logado");
                    return;
                }
                
                if (username == null || email == null || password == null || password2 == null || username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()){
                    //A field was not filled
                    response.sendRedirect("error.jsp?error=Um campo nao foi preenchido");
                    return;
                }
                
                if (username.length() > 16) {
                    //Long Username
                    response.sendRedirect("error.jsp?error=Nome de usuario muito grande");
                    return;
                }
                
                if (email.length() > 36) {
                    //Long email
                    response.sendRedirect("error.jsp?error=Email muito grande");
                    return;
                }
                
                if (password.length() < 10 || password.length() > 48) {
                    //Short or Long password
                    response.sendRedirect("error.jsp?error=Sua senha precisa conter ao menos 10 digitos");
                    return;
                }
                
                User user_provided = UserFacade.selectUserByLogin(username);
                
                if (user_provided != null){
                    //User already exists
                    response.sendRedirect("error.jsp?error=Usuario ja existente");
                    return;
                }
                
                User user_provided2 = UserFacade.selectUserByEmail(email);
                
                if (user_provided2 != null){
                    //Email already exists
                    response.sendRedirect("error.jsp?error=Este email ja foi utilizado");
                    return;
                }
                
                if (password.equals(password2)){
                    //Success - Insert into database
                    User user_insert = new User();
                    user_insert.setLogin(username);
                    user_insert.setEmail(email);
                    user_insert.setPasswd(password);
                    UserFacade.insertUser(user_insert);
                    
                    //Create session
                    HttpSession session = request.getSession();
                    session.setAttribute("session_user", user_insert);
                    
                    response.sendRedirect("home.jsp");
                } else {
                    response.sendRedirect("error.jsp?error=Senhas nao coincidem");
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
