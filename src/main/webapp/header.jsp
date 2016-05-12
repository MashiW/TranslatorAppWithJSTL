<div id="siteheader">
    <div class="top-buffer" id="divheader">
        <h2>Welcome <b><%= session.getAttribute("sessionname")%>... !</b></h2>
    </div>
    <div id="navbarMain">
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Translator</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User
                        Management <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Add User</a></li>
                            <li><a href="#">Search User</a></li>
                            <li><a href="#">Edit User</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="LogoutServlet">Logout<span class="glyphicon glyphicon-log-out"></span> </a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>