<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resources/css/background3.css"/>
        <link rel="stylesheet" href="resources/css/forms.css"/>
        <link rel="stylesheet" href="resources/css/all.css"/>
        <title>Profile Page</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
    </head>
    <body>
        
        <c:set var="login" value="${session_user.login}" />
        
        <%@include  file="/tools/navbar.jsp" %>
        
        <div class="container">
            <div class="row">
                <div class="col s6 offset-s3" style="margin-top: 13vh !important;">
                    <div class="card-panel">
                        <div class="row">
                         <div class="col s12">
                             
                             <c:set var="param_error" value="${param.error}" scope="session" />
                             
                             <c:import url="ErrorSanitizer">
                             </c:import>
                             
                             <div class="col s3">
                             <i class="material-icons" style="font-size: 90px; margin-top: 10%">report_problem</i>
                             </div>
                             
                             <div class="col s8" style="margin-top: 2%">
                                 <h5>Error</h5>
                                 <p><c:out value="${error}" /></p> 
                                 <p><c:out value="${sessionScope.param_error}" /></p> 
                             </div>

                             <c:set var="param_error" value="" scope="session" />
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="/resources/svg/logo.jsp" %>
      
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
