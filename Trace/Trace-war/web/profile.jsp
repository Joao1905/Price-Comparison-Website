<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/all.css"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resources/css/background.css"/>
        <title>Profile</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        
        <c:set var="login" value="${session_user.login}" />
        <c:set var="email" value="${session_user.email}" />
        
        <%@include  file="/tools/navbar.jsp" %>
        
        <c:if test="${!empty login}">
        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2" style="margin-top: 10vh !important;">
                    <div class="card-panel">
                         <div class="col s6">
                             
                            <div class="section">
                                <h5 class="grey-text text-darken-3">Username</h5>
                                <p class="grey-text text-darken-3"><c:out value="${login}" /></p>
                            </div>
                             <div class="divider"></div>
                          </div>
                        
                          <div class="col s6">
                                <div class="section">
                                  <h5 class="grey-text text-darken-3">Email</h5>
                                  <p class="grey-text text-darken-3"><c:out value="${email}" /></p>
                                </div>
                              <div class="divider"></div>
                          </div>

                            <div style="margin-top: 115px !important; margin-left: 1.3vh !important;" class="section">
                              <h5 class="grey-text text-darken-3">Change Password</h5>
                              <a href="changepass.jsp" style="color: #00bfa5 ;">Click here to change password</a>
                            </div>

                    </div>
                </div>
            </div>
        </div>
        </c:if>
        
        <c:if test="${empty login}">
            <jsp:forward page="error.jsp">
                <jsp:param name="error" value="Você precisa estar logado para ver essa página!" />
            </jsp:forward>
        </c:if> 
        <%@include  file="/resources/svg/logo.jsp" %>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
