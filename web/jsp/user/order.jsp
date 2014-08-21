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
        <link rel="shortcut icon" href="..\img\favicon.ico" type="image/x-icon" />

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
                    <h2><fmt:message key="order.title"/></h2>
                    <div id="back"> 
                        <form name ="BackForm"  method="POST" action="carrent" >
                            <input type="hidden" name="command" value="backCarsU"/> 
                            <input type="submit" value="<fmt:message key="menu.users.back" /> "/>
                        </form> 
                    </div>
                    <br/>

                    <b> <fmt:message key="order.name"/> </b> ${carName} <br/>
                    <b> <fmt:message key="order.price"/> </b> ${carPrice} <fmt:message key="cars.currency"/>
                    <br/>
                    <img src="${carImage}"/> <br/>
                    <form name ="order"  method="POST" action="carrent" class="order">
                        <input type="hidden" name="command" value="OrderRe" /> 
                        <p><fmt:message key="order.date"/></p> 
                        <input type="date" name="date" value="" required=""/>
                        <div class="notice"><fmt:message key="order.minmax"/></div>
                        <input  type="number" min="1" max="30" name="period" value="" placeholder="" />
                        <input type="submit" value="<fmt:message key="cars.order"/>" />
                    </form> 
                    <!-- insert calcutator here? -->


                </div>               
            </div> 
        </div>

        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
