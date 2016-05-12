<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="css/mystyles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="js/myscripts.js"></script>

    <title>Login</title>

</head>

<body>
<%--<div id="siteheader">
    <div class="top-buffer" id="divheader">
        <h2>Welcome <b><%= session.getAttribute("sessionname")%>... !</b></h2>
    </div>
    <div id="navbarMain">
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li>Welcome !</li>
                    <li class="active"><a href="#">Translator</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="" data-toggle="modal" data-target="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>
<div>

</div>--%>
<div class="container-fluid" >
    <div class="jumbotron" background-size="80%" >
    <div class="modal fade" id="loginModal" role="dialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Login here.. <span style="float:right"
                                                               class="glyphicon glyphicon-pencil"></span></h4>
                </div>
                <div class="modal-body">

                    <form role="form" action="Login" method="post">
                        <div class="form-group">
                            <label><span class="glyphicon glyphicon-user"></span>&nbsp;User Name:</label>
                            <input type="text" class="form-control" name="txtUn" placeholder="Enter your name" required>
                        </div>
                        <div class="form-group">
                            <label><span class="glyphicon glyphicon-lock"></span>&nbsp;Password:</label>
                            <input type="password" class="form-control" name="txtPw" placeholder="Enter password" required>
                        </div>
                        <div>
                                     <p style="color:red; font-size:14px; font-style:Italic; ">
                                     <strong>
                                        <%
                                            String err = (String) request.getAttribute("errorMessage");
                                            if (err!= null) {
                                             out.println(err);
                                            }
                                        %>
                                     </strong>
                                     </p>
                        </div>
                </div>
                <div class="modal-footer">

                    <div><button type="submit" class="btn btn-default glyphicon glyphicon-log-in">&nbsp;Login</button></div>
                </div>
                </form>
            </div>

        </div>
    </div>
</div>
</div>
</body>
</html>
