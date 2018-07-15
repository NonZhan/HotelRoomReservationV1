<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Open Room</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        <form method="POST" action="Controller">
            
            <input type="hidden" name="action" value="addroom" />
            
            <h1> <fmt:message key="ADDRESS"/> </h1>
            
            <input type="text" name="address">
            
            <br><br>
            
            <h1> <fmt:message key="NumberRooms"/> </h1>
            
            <input type="text" name="numberrooms">
            
            <br><br>
            
            <h1> <fmt:message key="Price"/> </h1>
            
            <input type="text" name="price">
            
            <br><br>
            
            <input type="submit" value="<fmt:message key="ADDROOM"/>">
            
        </form>
    </body>
</html>
