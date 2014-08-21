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
                    <c:import url="..\common\menu_admin.jsp" charEncoding="utf-8"/> 
                </div>
                <div id="page-content">  
                    <h2><fmt:message key="menu.atc"/></h2>
                    
                    <table class="cartable">
                        <c:if test="${not empty csuccess}"><div class="msg"><fmt:message key="confirm.success"/></div></c:if>
                        <c:if test="${not empty cfail}"><div class="msg"><fmt:message key="confirm.deny"/></div></c:if>
                        <hr/>
                        <c:forEach var="elem" items="${lst}" varStatus="status">
                            <tr>
                                <td>
                                    <b><fmt:message key="order.car.id"/></b> ${elem.id} <br/>
                                    <b><fmt:message key="order.name"/></b> ${elem.carName} <br/>
                                    <b><fmt:message key="order.sum"/></b> ${elem.sumToPay} <br/>
                                    <b><fmt:message key="column.surname"/></b> ${elem.clientSurname} <br/>
                                    <b><fmt:message key="column.passNum"/></b> ${elem.passNum} <br/>
                                    <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                                        <input type="hidden" name="applId" value="${elem.id}" /> 
                                        <input type="hidden" name="command" value="confirm" /> 
                                        <input type="submit" value="<fmt:message key="order.confirm" /> " />
                                    </form>

                                    <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                                        <input type="hidden" name="command" value="deny" /> 
                                        <input type="hidden" name="applId" value="${elem.id}" /> 
                                        <fmt:message key="order.reason"/>
                                        <input type="text" name="reasonForRefusal" value="" required/>
                                        <input type="submit" value="<fmt:message key="order.deny" /> " />
                                    </form>

                                </td>
                            </tr>
                            <tr>
                                <td><br/></td>

                            </tr>

                        </c:forEach>
                    </table>
                    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                </div>

            </div> 

        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
