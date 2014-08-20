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
                    <h2><fmt:message key="user.order.topay"/></h2>
                    <c:if test="${not empty success}"><div class="msg"><fmt:message key="pay.success"/></div></c:if>
                    <c:if test="${not empty fail}"><div class="msg"><fmt:message key="pay.fail"/></div></c:if>
                        <hr/>   
                    <c:forEach var="elem" items="${lst}" varStatus="status">
                        <form name ="ApplicationPay"  method="POST" action="carrent">
                            <input type="hidden" name="applid" value="${elem.id}" /> 
                            <b><fmt:message key="user.order.car"/>: </b>  ${elem.carName} <br/>
                            <b><fmt:message key="user.order.price"/> :</b> ${elem.price} <br/>
                            <b><fmt:message key="user.order.period"/> : </b> ${elem.period} <br/>
                            <b> <fmt:message key="user.order.sumToPay"/>: </b>  ${elem.sumToPay} <br/>
                            <input type="hidden" name="command" value="Pay" /> 
                            <input type="submit" value="<fmt:message key="user.order.pay" /> " />
                        </form> 
                        <form name ="ApplicationPay"  method="POST" action="carrent">
                            <input type="hidden" name="applid" value="${elem.id}" /> 
                            <input type="hidden" name="command" value="DeleteApplication" /> 
                            <input type="submit" value="<fmt:message key="user.order.no" /> " />
                        </form>
                        <hr/>
                    </c:forEach> 
                    <br/>
                    <br/>
                    <h2>  <fmt:message key="user.order.archive"/> </h2>
                    <c:forEach var="elemA" items="${lstA}" varStatus="status">
                        <b>  <fmt:message key="user.order.car"/>: </b>  ${elemA.carName} <br/>
                        <b><fmt:message key="user.order.price"/>:</b> ${elemA.price} <br/>
                        <b><fmt:message key="user.order.period"/>:</b>  ${elemA.period} <br/>
                        <b> <fmt:message key="user.order.sumToPay"/>: </b>  ${elemA.sumToPay} <br/>
                        <hr/>
                    </c:forEach> 
                    <br/>
                    <br/>
                    <h2>  <fmt:message key="order.denied"/> </h2>
                    <c:forEach var="elemR" items="${lstR}" varStatus="status">
                        <b>  <fmt:message key="user.order.car"/>: </b>  ${elemR.carName} <br/>
                        <b><fmt:message key="user.order.price"/>:</b> ${elemR.price} <br/>
                        <b><fmt:message key="user.order.period"/>:</b> ${elemR.period} <br/>
                        <b> <fmt:message key="user.order.sumToPay"/>: </b>  ${elemR.sumToPay} <br/>
                        <b> <fmt:message key="order.reason.deny"/>: </b>  ${elemR.refusalReason} <br/>
                        <hr/>
                    </c:forEach> 
                    <br/>
                    <br/>
                </div>                
            </div>
        </div> 
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
