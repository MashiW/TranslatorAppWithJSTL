<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<div id="siteheader">
    <div class="top-buffer" id="divheader">
        <h2><fmt:message key="index.welcome"></fmt:message><b><%= session.getAttribute("sessionname")%>... !</b></h2>
    </div>
    <div id="navbarMain">
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#"><fmt:message key="index.translator.label"></fmt:message> </a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <fmt:message key="navbar.usermgt.label"></fmt:message><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><fmt:message key="navbar.adduser.label"></fmt:message> </a></li>
                            <li><a href="#"><fmt:message key="navbar.searchuser.label"></fmt:message> </a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="LogoutServlet"><fmt:message key="navbar.logout.label"></fmt:message> <span
                            class="glyphicon glyphicon-log-out"></span> </a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>