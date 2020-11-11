<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resources/css/background2.css"/>
        <link rel="stylesheet" href="resources/css/forms.css"/>
        <link rel="stylesheet" href="resources/css/all.css"/>
        <title>Register</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
    </head>
    <body>
        
        <c:set var="login" value="${session_user.login}" />
        
        <%@include  file="/tools/navbar.jsp" %>
        
        <c:if test="${empty login}">
        <div class="container">
            <div class="row">
                <div class="col s4 offset-s4" style="margin-top: 13vh !important;">
                    <div class="card-panel">
                        <div class="row">
                         <div class="col s10 offset-s1">
                             
                             <h5 style="margin-left: 32%">Register</h5>
                             
                            <form action="ControlRegister" method="POST" autocomplete="off">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">person</i>
                                <input type="text" id="login_register" class="autocomplete" name="username" value="" required>
                                <label for="login_register">Login</label>
                              </div>
                              <div class="input-field col s12">
                                <i class="material-icons prefix">vpn_key</i>
                                <input type="email" id="email_register" class="autocomplete" name="email" value="" required>
                                <label for="email_register">Email</label>
                              </div>
                              <div class="input-field col s12">
                                <i class="material-icons prefix">person</i>
                                <input type="password" id="password_register" class="autocomplete" name="password" value="" required>
                                <label for="password_register">Password</label>
                              </div>
                              <div class="input-field col s12">
                                <i class="material-icons prefix">person</i>
                                <input type="password" id="password2_register" class="autocomplete" name="password2" value="" required>
                                <label for="password2_register">Repeat your password</label>
                              </div>
                                <div class="col s6 offset-s3">
                                     <br>
                                    <button class="btn waves-effect waves-light" type="submit" name="action" value="Ok">Submit
                                      <i class="material-icons right">send</i>
                                    </button>
                                </div>
                            </form>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="/resources/svg/logo.jsp" %>
        </c:if>
            
        <c:if test="${!empty login}">
            <jsp:forward page="error.jsp">
                <jsp:param name="error" value="Você já está logado!" />
            </jsp:forward>
        </c:if>
            
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
