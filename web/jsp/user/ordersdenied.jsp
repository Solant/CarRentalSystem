<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="..\css\style.css" type="text/css">
    </head>
    <body>
        <div class="page-wrapper"> 
            <div class="page-buffer">    
                <div class="header"> 
                    <c:import url='..\common\header.jsp' charEncoding="utf-8"/> 
                </div>
                <div id="menu">     
                    <c:import url="..\common\menu.jsp" charEncoding="utf-8"/> 
                </div>
                <div id="page-content">  



                    <h2>  <fmt:message key="order.denied"/> </h2>
                    <table id="usertable">
                        <tr>
                            <td class="heading">
                                <b>  <fmt:message key="user.order.car"/> </b>                                        
                            </td>
                            <td class="heading">
                                <b><fmt:message key="user.order.price"/></b>                                    
                            </td>
                            <td class="heading">
                                <b><fmt:message key="user.order.period"/></b> 
                            </td>
                            <td class="heading">
                                <b> <fmt:message key="user.order.sumToPay"/>, $ </b> 
                            </td> 
                            <td class="heading">
                                <b><fmt:message key="order.reason.deny"/></b>
                            </td> 
                        </tr>
                        <c:forEach var="elemR" items="${lstR}" varStatus="status">
                            <tr>  
                                <td>${elemR.carName} </td>
                                <td>${elemR.price}</td>
                                <td>${elemR.period}  </td>
                                <td>${elemR.sumToPay} </td>
                                <td>${elemR.refusalReason} </td>
                            </tr> 
                        </c:forEach> 
                    </table>
                    <br/>
                    <br/>
                </div>                
            </div>
        </div> 
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
