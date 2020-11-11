<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resources/css/background.css"/>
        <link rel="stylesheet" href="resources/css/all.css"/>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Change Password</title>
    </head>
    <body>
        
        <c:set var="login" value="${session_user.login}" />
        
        <%@include  file="/tools/navbar.jsp" %>
        
        <c:if test="${!empty login}">
        <div class="container">
            <div class="row">
                <div class="col s4 offset-s4" style="margin-top: 13vh !important;">
                    <div class="card-panel">
                        <div class="row">
                         <div class="col s10 offset-s1">

                             <h5 style="margin-left: 16%">Password Change</h5>
                             
                            <form action="ChangePasswd" method="POST" autocomplete="off">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">filter_1</i>
                                <input type="text" id="change_pass1" class="autocomplete" name="changepass1" value="" required>
                                <label for="change_pass1">Old Password</label>
                              </div>
                              <div class="input-field col s12">
                                <i class="material-icons prefix">filter_2</i>
                                <input type="text" id="change_pass2" class="autocomplete" name="changepass2" value="" required>
                                <label for="change_pass2">New Password</label>
                              </div>
                              <div class="input-field col s12">
                                <i class="material-icons prefix">filter_3</i>
                                <input type="text" id="change_pass3" class="autocomplete" name="changepass3" value="" required>
                                <label for="change_pass3">Repeat New Password</label>
                              </div>
                                <div class="col s6 offset-s3">
                                     <br>
                                    <button class="btn waves-effect waves-light" type="submit" name="action" value="Ok">Submit
                                      <i class="material-icons right">check</i>
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
            
        <c:if test="${empty login}">
            <jsp:forward page="error.jsp">
                <jsp:param name="error" value="Você precisa estar logado para mudar sua senha!" />
            </jsp:forward>
        </c:if>
            
                
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
