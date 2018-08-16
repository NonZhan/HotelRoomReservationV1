<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "z" uri = "/WEB-INF/tag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOTEL</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    
    <body style="background-image: url(img/fon.jpg);">
        
    <center> 
            
        <div class="logo">
            <img src="img/logo.png" alt=""/>
        </div>
        
        <form action="FilterLink" method="POST">
            <input type="hidden" name="link" value="doLoginJSP"/>
            <button><fmt:message key="login"/></button>   
        </form>
        
        <br><br>
        
        <jsp:include page="login.jsp" />
        <form action="FilterLink" method="POST">
            <input type="hidden" name="link" value="doRegisterJSP"/>
            <button><fmt:message key="registration"/></button>   
        </form>
                   
    </center>

    </body>
</html>
