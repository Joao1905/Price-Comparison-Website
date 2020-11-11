<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/all.css"/>
        <link rel="stylesheet" href="resources/css/box.css"/>      
        <link rel="stylesheet" href="resources/css/background4.css"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:700' rel='stylesheet' type='text/css'>
        
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <title>GPU</title>
    </head>
    <body>
        
        <c:set var="login" value="${session_user.login}" />
        
        <%@include  file="/tools/navbar.jsp" %>
        
        <section class="boxsection-3 hide-on-large-only">
        </section>
        
        <section class="boxsection-1 hide-on-med-and-down">
            <div class="card-panel hide-on-med-and-down" style="position: fixed; width:20.2rem; height:3.9rem; background-color: #555555; margin-top: 4rem; padding:0;z-index: 1">
                 <i class="material-icons" id="dark_mode" onclick="darkMode()">brightness_2</i>
                 <i class="material-icons" id="up_page" onclick="upPage()">keyboard_arrow_up</i>
                 <i class="material-icons" id="down_page" onclick="downPage()">keyboard_arrow_down</i>
                 <a href="home.jsp" ><%@include  file="/resources/svg/logo2.jsp" %></a>
             </div>
            
            <div class="card-panel" style="position: fixed; height:100vh;  width:20rem; margin-left: 0%; background-color: #282828;">
                 
                <form action="ControlBoxCPU" method="POST" autocomplete="off">
                
                <h1 id="Filter">DESIGNER</h1>
                
                            <p style="margin-top: 13.5rem">
                                <label>
                                  <input type="checkbox" class="filled-in" checked="checked" name="amd" value="amd"/>
                                  <span>AMD</span>
                                </label>
                            </p>
                              
                            <p style="margin-top: 0.6rem">
                                <label>
                                  <input type="checkbox" class="filled-in" checked="checked" name="intel" value="intel"/>
                                  <span>Intel</span>
                                </label>
                            </p>
                            
                <h1 id="Filter2">PREÇO MÁXIMO - R$</h1>

                        <p class="range-field" style="margin-top: 6rem;">
                            <input type="range" id="test5" min="0" max="15000" style="border: 0px;margin-top: 2%;" name="price" value="15000"/>
                        </p>
                        
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="Ok" style="margin-top:2rem;">Submit
                                      <i class="material-icons right">send</i>
                       </button>
                        
                    </form>
                
                <%@include  file="/resources/svg/logo3.jsp" %>

                </div>
        </section>
             
        <section class="boxsection-2">
            <c:set var="amd" value="${amd}" scope="request"/>
            <c:set var="intel" value="${intel}" scope="request"/>
            <c:set var="price" value="${price}" scope="request"/>
            <c:import url="CreateBoxGPU">
            </c:import>
        </section>
        
        
       
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="resources/js/box.js"></script>
    </body>
</html>
