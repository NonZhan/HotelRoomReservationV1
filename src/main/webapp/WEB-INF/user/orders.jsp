<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        
        <h1><fmt:message key="Theorderisaccepted"/></h1>
        
        <form method="POST" action="FilterLink">
            
            <input type="hidden" name="link" value="doCabinetJSP" />
            
            <input type="submit" value="<fmt:message key="Returntocabinet"/>">
            
        </form>

    </body>
</html>
