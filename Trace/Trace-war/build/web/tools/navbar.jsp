<nav style="position:fixed; z-index: 10" class="grey darken-4">
    <div class="nav-wrapper">
      <ul id="nav-mobile">
        <li style="margin-left: 16.5vw;"><a href="home.jsp">Home</a></li>
        
        <c:if test="${empty login}">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
                
                <li style="float: right !important; margin-right: 1vw;">    
                   <div class="center row">
                      <div col="12">
                        <div class="row" id="topbarsearch">
                          <div class="input-field col s s12 white-text">
                            <i class="white-text material-icons prefix" style="vertical-align: top;top: 2px !important;position: fixed; left: 50vw;">search</i>
                            <input type="text" placeholder="Procurar peças" id="autocomplete-inputt" class="autocomplete white-text" style="position: fixed; left: 50vw; width: 33%; top: 1.1rem; height: 2rem">
                            </div>
                          </div>
                        </div>
                      </div>          
                  </li>
        </c:if>
        
        
        <c:if test="${!empty login}">
        <li style="float: right !important; margin-right: 16.5vw; text-align: right;">
                <form id="logout_form" action="ControlLogout" method="POST">
                    <a href="javascript:{}" onclick="document.getElementById('logout_form').submit(); return false;"><i class="material-icons">directions_run</i></a>
                </form>
        </li>
        <li class="profile_navbar" style="float: right !important; text-align: right;"><a href="profile.jsp"><c:out value="${login}" />  <i class="material-icons" style="display: inline-flex; vertical-align: top;">person</i></a></li>
        
        
        <li style="float: right !important; margin-right: 1vw;">    
                   <div class="center row">
                      <div col="12">
                        <div class="row" id="topbarsearch">
                          <div class="input-field col s s12 white-text">
                            <i class="white-text material-icons prefix" style="vertical-align: top;top: 2px !important;position: fixed; left: 30vw;">search</i>
                            <input type="text" placeholder="Procurar" id="autocomplete-inputt" class="autocomplete white-text" style="position: fixed; left: 30vw; width: 30%; top: 1.1rem; height: 2rem">
                            </div>
                          </div>
                        </div>
                      </div>          
                  </li>
         </c:if>
        
      </ul>
    </div>
  </nav>