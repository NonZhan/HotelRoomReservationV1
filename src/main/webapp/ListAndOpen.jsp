<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "z" uri = "/WEB-INF/tag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LIST + OPEN</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        
        <h1><fmt:message key="Invoicethecustomer"/></h1>
        
        <form method="POST" action="Controller">
            
            <input type="hidden" name="action" value="invoice" />
            
            <p> <fmt:message key="EMAILcustomer"/> </p>
            
            <input type="text" name="email">
            
            <br><br>
            
            <p> <fmt:message key="Theaddressoftheproposedroom"/></p>
            
            <input type="text" name="address">
            
            <br><br>
            
            <input type="submit" value="<fmt:message key="SENDINVOICE"/>">
            
            <br><br>
            
        </form>
        
        <p> ${invoice_info} </p>
        
        <a href="addopenroom.jsp"><fmt:message key="ADDROOM"/></a> <br><br>
        
        <h1><fmt:message key="Roomsareavailable"/></h1>
        <em> <z:ListOpenRooms/> </em>
        
        <h1><fmt:message key="Roomrequests"/></h1>
        <z:ListReqRooms/>
        
    </body>
</html>
