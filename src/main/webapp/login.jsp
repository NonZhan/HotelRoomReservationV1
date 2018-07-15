<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "z" uri = "/WEB-INF/tag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authorization</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body style="background-color: gainsboro;">
        
    <center>
        
        <c:if test="${IncorrectPassword != null}">
            <br><br><br><br><br><br>
        </c:if>
        
        <div class="authorization">
        
            <form method="POST" action="Controller">

                <input type="hidden" name="action" value="dologin" />

                <fmt:message key="EMAIL"/>

                <input type="text" name="email" value="master">

                <br><br>

                <fmt:message key="PASS"/>

                <input type="password" name="password" value="master"> 

                <br><br>

                <input type="submit" value="OK">

            </form>
            
        </div>
        
        <c:if test="${IncorrectPassword != null}">
            <br><br>
            <div class="IncorrectPassword"> 
                ${IncorrectPassword}
            </div>

            <div class="IncorrectPasswordImg">
                <img style="" src="img/no_password.png"/>
            </div>

        </c:if>
    
    </center>   
        
    </body>
</html>
