<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="css/mystyles.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.1/bootstrap3-typeahead.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
    <script src="js/simple-bootstrap-paginator.js"></script>
    <script src="js/myscripts.js"></script>
    <script src="js/valiadateinput.js"></script>
    <script src="js/updateuser.js"></script>


</head>

<body id="translatebody">

<%
    request.setAttribute("userSession", request.getSession().getAttribute("sessionname"));
    request.setAttribute("perList", request.getSession().getAttribute("permissions"));
%>

<input type="hidden" id="lblSessionUser" value="${userSession}"/>

<fmt:bundle basename="jstlmessages">

    <div class="container-fluid">
        <div id="trjumbotron" class="jumbotron">
            <div id="siteheader">
                <div class="top-buffer" id="divheader">
                    <h3><b><fmt:message key="index.welcome.message"></fmt:message></b></h3>
                    <hr style="color: #aa80ff;border: 1px solid;">
                </div>

                <div id="navbarMain">
                    <nav class="navbar navbar-inverse navbar-fixed-top">
                        <div class="container-fluid">
                            <ul class="nav navbar-nav nav-tabs">
                                <div class="navbar-header">
                                    <h3 style="color: #aa80ff"><fmt:message key="index.welcome"></fmt:message>
                                        <b><%= session.getAttribute("sessionname")%>
                                            ..&nbsp; </b></h3>
                                </div>

                                    <%--setting translation tab--%>
                                <c:forEach var="pers" items="${perList}">
                                    <c:choose>
                                        <c:when test="${pers == 'Translate' || 'Search_user'}">
                                            <li class="active"><a data-toggle="tab" href="#transcontent">
                                                <fmt:message key="index.translator.label"></fmt:message> </a></li>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>


                                    <%--setting user management tab--%>
                                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"
                                                        id="usermgtTab">
                                    <fmt:message key="navbar.usermgt.label"></fmt:message>
                                    <span class="caret"></span></a>
                                    <ul class="dropdown-menu">

                                    <c:forEach var="pers" items="${perList}">
                                <c:choose>
                                <c:when test="${pers == 'Add_user'}">

                                            <%--setting user add tab--%>
                                    <li><a data-toggle="tab" href="#userAddcontent" id="userAddLink">
                                            <fmt:message key="navbar.adduser.label"></fmt:message> </a></li>
                                        </c:when>
                                        </c:choose>
                                        </c:forEach>

                                            <%--setting user search tab--%>
                                        <c:forEach var="pers" items="${perList}">
                                            <c:choose>
                                                <c:when test="${pers == 'Search_user' || 'Edit_user' || 'Add_user' || 'Delete_user'}">
                                                    <li><a data-toggle="tab" href="#userSearchcontent"
                                                           id="userSearchLink">
                                                        <fmt:message key="navbar.searchuser.label"></fmt:message> </a>
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-in"></span>
                                    <fmt:message key="navbar.logout.label"></fmt:message> </a>
                                </li>
                            </ul>
                        </div>

                    </nav>
                </div>
            </div>

            <div class="tab-content">
                <div id="transcontent" class="tab-pane fade in active">

                    <c:forEach var="pers" items="${perList}">
                        <c:choose>
                            <c:when test="${pers == 'Translate'}">

                                <%@include file="logintranslate.jsp" %>

                            </c:when>
                        </c:choose>
                    </c:forEach>

                </div>
                <div id="userAddcontent" class="tab-pane fade">
                    <c:forEach var="pers" items="${perList}">
                        <c:choose>
                            <c:when test="${pers == 'Add_user'}">

                                <%@include file="adduser.jsp" %>

                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>

                <div id="userSearchcontent" class="tab-pane fade">
                    <c:forEach var="pers" items="${perList}">
                        <c:choose>
                            <c:when test="${pers == 'Search_user'}">

                                <%@include file="searchuser.jsp" %>

                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>

            <%@include file="footer.jsp" %>
        </div>
    </div>


    <%--Unique username checking function--%>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#txtuname").focusout(function () {
                var uname = $(this).val();
                if (uname !== "" && uname.length < 20) {
                    $(".status").html("<img src='images/loader.gif'> Checking availability...");
                    $.ajax
                    ({
                        type: "POST",
                        url: "UsernameValidate",
                        data: "uname=" + uname,
                        success: function (msg) {
                            $(".status").html(msg);
                        }
                    });
                }
                else {
                    $(".status").html("<i>username should be less than 20 chars<i>");
                }
            });
        });
    </script>
    <script type="text/javascript" src="js/searchuser.js"></script>


    <!--javascript and css files for update DOB calendar -->

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

    <%--Date picker function for registering user--%>
    <script>
        $(document).ready(function () {
            var date_input = $('input[name="date"]');
            var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
            date_input.datepicker({
                format: 'yyyy-mm-dd',
                container: container,
                todayHighlight: true,
                autoclose: true,
                startView: 2,
                defaultViewDate: {year: 1990},
                endDate: '-1d'
            })
        })
    </script>

    <%--Date picker function for updating user--%>
    <script>
        $(document).ready(function () {
            var date_input = $('input[name="dateUpdt"]');
            var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
            date_input.datepicker({
                format: 'yyyy-mm-dd',
                container: container,
                todayHighlight: true,
                autoclose: true,
                startView: 2,
                defaultViewDate: {year: 1990},
                endDate: '-1d'
            })
        })
    </script>
    <!--//javascript and css files for update DOB calendar -->
</fmt:bundle>
</body>
</html>
