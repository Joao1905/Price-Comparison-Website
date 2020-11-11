<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="resources/css/background.css"/>
        <link rel="stylesheet" href="resources/css/product.css"/>
        <link rel="stylesheet" href="resources/css/all.css"/>
        <link rel="stylesheet" href="resources/css/Chart.min.css"/>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <title>GPU Overview</title>
    </head>
    <body>
        <c:set var="login" value="${session_user.login}" />
        <c:set var="unique_name" value="${param.unique_name}" />
        
         <%@include  file="/tools/navbar.jsp" %>
        
        <c:if test="${empty unique_name}">
            <jsp:forward page="error.jsp">
                <jsp:param name="error" value="Esse modelo nao existe" />
            </jsp:forward>
        </c:if>

        <c:import url="GetGPUAttributes">
        </c:import>
         
         <c:if test="${empty gpu_products}">
            <jsp:forward page="error.jsp">
                <jsp:param name="error" value="Esse modelo nao existe 2" />
            </jsp:forward>
        </c:if>
        
         <c:set var="click_products" value="${gpu_products}" scope="session" />
         
         <div class="container">
             <div class="row">
                 
                 <div class="col s3" style="margin-top: 15vh">
                     <a href="ClickCounter?id=0" target="_blank">
                         <div class="card-panel" style="height:28rem; padding: 1rem; margin: 0; position: relative;">
                                <img src="${gpu_products[0].model.image}" style="width:78%; margin-left: 11%" onerror="this.onerror=null;this.src='resources/img/cpu/error.jpg';">
                                <c:set var="gpu_name" value="${fn:replace(gpu_products[0].model.name, 'Placa de Vídeo', '')}" />
                                <c:set var="gpu_name" value="${fn:replace(gpu_products[0].model.name, 'Placa de Video', '')}" />
                                <c:set var="gpu_name" value="${fn:replace(gpu_name, ',', '')}" />
                                <c:set var="gpu_name" value="${fn:split(gpu_name, ' ')}" />
                                <h4>
                                <c:forEach var="i" begin="0" end="5">
                                    <c:out value="${gpu_name[i]}" />
                                </c:forEach>
                                </h4>
                                <br>
                                <h4><b>R$ <c:out value="${gpu_products[0].price}" /></b></h4>
                                <img class="logo" src="resources/img/shops/logo${gpu_products[0].shop_id}.png" style="width:7rem; top: 5%;">
                          </div>
                      </a>
                </div>
                 
                 <div class="col s9" style="margin-top: 15vh">
                     <div class="card-panel" style="height:28rem; padding: 1rem; margin: 0">
                         <div class="valign-wrapper" style="position: relative; height:100%; width:100%">
                            <canvas id="myChart" style="width:95%; height:95%;"></canvas>
                         </div>
                     </div>
                 </div>
                 
             </div>
             <div class="row">
                 
                <c:if test="${!empty gpu_products[1]}">
                <div class="col s4">
                    <a href="ClickCounter?id=1" target="_blank">
                        <div class="card-panel" style="height:9.3rem; padding: 0.2rem; margin: 0; position: relative;">
                            <div class="container" style="width:100%">
                               <div class="row">
                                  <div class="col s5">
                                     <img class="logo" src="resources/img/shops/logo${gpu_products[1].shop_id}.png" style="width:6rem;margin-left: 7.5%; margin-top: 1%">
                                     <img src="${gpu_products[1].model.image}" style="height:9rem; margin-left: 10%" onerror="this.onerror=null;this.src='resources/img/cpu/error.jpg';">
                                  </div>
                                  <div class="col s7">
                                    <br>
                                    <h5>
                                    <c:forEach var="i" begin="0" end="5">
                                        <c:out value="${gpu_name[i]}" />
                                    </c:forEach>
                                    </h5>        
                                    <br>
                                    <h5><b>R$ <c:out value="${gpu_products[1].price}" /></b></h5>
                                  </div>
                              </div>
                          </div>
                       </div>
                   </a>
                </div>
                </c:if> 
                     
                <c:if test="${!empty gpu_products[2]}">
                <div class="col s4">
                    <a href="ClickCounter?id=2" target="_blank">
                        <div class="card-panel" style="height:9.3rem; padding: 0.2rem; margin: 0; position: relative;">
                            <div class="container" style="width:100%">
                               <div class="row">
                                  <div class="col s5">
                                      <img class="logo" src="resources/img/shops/logo${gpu_products[2].shop_id}.png" style="width:6rem;margin-left: 7.5%; margin-top: 1%">
                                     <img src="${gpu_products[2].model.image}" style="height:9rem; margin-left: 10%" onerror="this.onerror=null;this.src='resources/img/cpu/error.jpg';">
                                  </div>
                                  <div class="col s7">
                                      <br>
                                      <h5>
                                      <c:forEach var="i" begin="0" end="5">
                                          <c:out value="${gpu_name[i]}" />
                                      </c:forEach>
                                      </h5>               
                                      <br>
                                      <h5><b>R$ <c:out value="${gpu_products[2].price}" /></b></h5>
                                  </div>
                              </div>
                          </div>
                       </div>
                    </a>
                </div>
                </c:if> 
                     
                <c:if test="${!empty gpu_products[3]}">
                <div class="col s4">
                    <a href="ClickCounter?id=3" target="_blank">
                        <div class="card-panel" style="height:9.3rem; padding: 0.2rem; margin: 0; position: relative;">
                            <div class="container" style="width:100%">
                               <div class="row">
                                  <div class="col s5">
                                      <img class="logo" src="resources/img/shops/logo${gpu_products[3].shop_id}.png" style="width:6rem;margin-left: 7.5%; margin-top: 1%">
                                     <img src="${gpu_products[3].model.image}" style="height:9rem; margin-left: 10%" onerror="this.onerror=null;this.src='resources/img/cpu/error.jpg';">
                                  </div>
                                  <div class="col s7">
                                      <br>
                                      <h5>
                                      <c:forEach var="i" begin="0" end="5">
                                          <c:out value="${gpu_name[i]}" />
                                      </c:forEach>
                                      </h5>               
                                      <br>
                                      <h5><b>R$ <c:out value="${gpu_products[3].price}" /></b></h5>
                                  </div>
                              </div>
                          </div>
                       </div>
                   </a>
                </div>
                </c:if> 
                     
            </div>
         </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="resources/js/Chart.min.js"></script>
    <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: 
                <c:if test="${!empty historical_price[0]}">    
                {
                labels: [
                    <c:set var="max" value="${fn:length(historical_price[0])}" />
                    <c:forEach var="i" begin="1" end="${max}" step="1">
                        '${historical_price[0][max-i].date}',
                    </c:forEach>
                ],
                datasets: [{
                    label: '${gpu_products[0].shop_name}',
                    data: [
                    <c:set var="max" value="${fn:length(historical_price[0])}" />
                    <c:forEach var="i" begin="1" end="${max}" step="1">
                        '${historical_price[0][max-i].price}',
                    </c:forEach>
                    ],
                    backgroundColor: [
                        'rgba(21, 21, 21,0)',
                    ],
                    borderColor: [
                        'rgba(38, 121, 255,1)',
                    ],
                    borderWidth: 3 
                },
                </c:if>
                <c:if test="${!empty historical_price[1]}">
                {
                    label: '${gpu_products[1].shop_name}',
                    data: [
                    <c:set var="max" value="${fn:length(historical_price[1])}" />
                    <c:forEach var="i" begin="1" end="${max}" step="1">
                        '${historical_price[1][max-i].price}',
                    </c:forEach>
                    ],
                    backgroundColor: [
                        'rgba(81, 237, 154, 0)',
                    ],
                    borderColor: [
                        'rgba(122, 112, 255,1)',
                    ],
                    borderWidth: 3 
                },
                </c:if>
                <c:if test="${!empty historical_price[2]}">
                {
                    label: '${gpu_products[2].shop_name}',
                    data: [
                    <c:set var="max" value="${fn:length(historical_price[2])}" />
                    <c:forEach var="i" begin="1" end="${max}" step="1">
                        '${historical_price[2][max-i].price}',
                    </c:forEach>
                    ],
                    backgroundColor: [
                        'rgba(112, 81, 237, 0)',
                    ],
                    borderColor: [
                        'rgba(134, 66, 252, 1)',
                    ],
                    borderWidth: 3 
                },
                </c:if>
                <c:if test="${!empty historical_price[3]}">
                {
                    label: '${gpu_products[3].shop_name}',
                    data: [
                    <c:set var="max" value="${fn:length(historical_price[3])}" />
                    <c:forEach var="i" begin="1" end="${max}" step="1">
                        '${historical_price[3][max-i].price}',
                    </c:forEach>
                    ],
                    backgroundColor: [
                        'rgba(203, 237, 81, 0)',
                    ],
                    borderColor: [
                        'rgba(190, 38, 255, 1)',
                    ],
                    borderWidth: 3 
                }
                </c:if>
            ]
            },
            options: {
                responsive: true,
                scales: {
                   xAxes: [{
                       ticks: {
                           display: false //this will remove only the label
                       }
                   }]
                },
                title: {
                   display: true,
                   fontSize:22,
                   fontFamily: 'Roboto Condensed',
                   text: 'PREÇO - ÚLTIMOS 180 DIAS',
                   fontColor: '#444'
               }
           }
        });
        
    </script>
    </body>
</html>
