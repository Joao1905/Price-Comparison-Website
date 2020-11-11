<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/all.css"/>
        <link rel="stylesheet" href="resources/css/background.css"/>
        <link href="https://fonts.googleapis.com/css?family=Bebas+Neue&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        
         <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <title>Trace ®</title>
    </head>
    <body>
               
            <c:set var="login" value="${session_user.login}" />
            
            <%@include  file="/tools/navbar.jsp" %>
        
        <section class="section-1">
            <h1 class="h1_mover">Computer Trace</h1>
            <h2 class="h2_mover">Esse é um site que encontra o menor preço<br>de peças  usando inteligência artificial. <br> Por enquanto, só temos os<br>preços para peças de computador, <br>mas logo chegarão novos produtos 👌🏻</h2>
        </section>
        
        <section class="section-2">
                <%@include  file="/resources/svg/computer.jsp" %>
                 <%@include  file="/resources/svg/logo.jsp" %>
        </section>
                
    <script src="https://kit.fontawesome.com/f18174f649.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
