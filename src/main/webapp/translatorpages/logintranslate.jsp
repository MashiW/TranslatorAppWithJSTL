<%@page import="java.util.ArrayList" %>
<div>
    <form role="form" action="TranslateServlet" method="post">
        <div class="row top-buffer">

            <div class="col-md-2"></div>
            <div class="col-md-4">
                <strong><fmt:message key="translator.fromtext.label"></fmt:message> </strong> <br/>

                <%
                    String s1 = (String) request.getSession().getAttribute("fromText");
                    request.setAttribute("s1", s1);
                %>

                <c:if test="${not empty s1}">
                    <textarea name="txtFromText" id="fromText" class="form-control input-md">${s1}</textarea>
                </c:if>
                <c:if test="${empty s1}">
                    <textarea name="txtFromText" id="fromText" class="form-control input-md"></textarea>
                </c:if>
                <br>

                <div class="top-buffer">
                    <select name="frmType" class="form-control" style="width:150px;">

                        <%
                            ArrayList<String> ar = new ArrayList<String>();
                            ar = (ArrayList<String>) request.getAttribute("list");
                            request.setAttribute("ar", ar);
                            request.setAttribute("selectedFrom", (request.getAttribute("selectedFrom")));
                        %>

                        <c:forEach varStatus="j" items="${ar}">
                            <c:set var="selectedfrom" value="${ar[j.index]}"/>
                            <c:choose>
                                <c:when test="${selectedfrom eq selectedFrom}">
                                    <option selected>${selectedfrom}</option>
                                </c:when>
                                <c:otherwise>
                                    <option>${selectedfrom}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col-md-4">
                <strong> <fmt:message key="translator.totext.label"></fmt:message> </strong> <br>

                <%
                    String s2 = (String) request.getAttribute("textReply");
                    request.setAttribute("s2", s2);
                %>

                <c:if test="${not empty s2}">
                    <textarea name="txtToText" id="toText" class="form-control">${s2}</textarea>
                </c:if>

                <c:if test="${empty s2}">
                    <textarea name="txtToText" id="toText" class="form-control"></textarea>
                </c:if>

                <br>

                <div class="top-buffer">
                    <select name="toType" class="form-control" style="width:150px;">

                        <%
                            ArrayList<String> ar2 = new ArrayList<String>();
                            ar2 = (ArrayList<String>) request.getAttribute("list");
                            request.setAttribute("ar2", ar2);
                            request.setAttribute("selectedTo", (request.getAttribute("selectedTo")));
                        %>

                        <c:forEach varStatus="i" items="${ar2}">
                            <c:set var="selectedto" value="${ar2[i.index]}"/>
                            <c:choose>
                                <c:when test="${selectedto eq selectedTo}">
                                    <option selected>${selectedto} </option>
                                </c:when>
                                <c:otherwise>
                                    <option>${selectedto} </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                    </select>

                </div>
            </div>
            <div class="col-md-2"></div>
        </div>

        <div class="top-buffer" style="margin:5% 30% 0 30%; align-content: center;">
            <button type="button" onclick="swapText()" class="btn btn-default">
                <fmt:message key="translator.swaptext.button"></fmt:message>
            </button>
            <input id="btnTransTxt" type="submit" class="btn btn-default" value="<fmt:message key="translator.translate.button">
                </fmt:message>" align>
        </div>
    </form>
    <br>

    <div class="row top-buffer">
        <div class="row">
            <h5><b>

                <fmt:message key="yandex.url.label"/></b><a href="http://translate.yandex.com/">
                <fmt:message key="yandex.url.textlink"/></a></h5>

        </div>
    </div>
</div>