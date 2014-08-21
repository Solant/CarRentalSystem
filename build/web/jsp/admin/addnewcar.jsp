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
                    <div id="welcome_msg">
                        <h2><fmt:message key="menu.cars.addnew"/></h2>

                        <br/>
                        <br/>
                        <div id="back"> 
                            <form name ="BackForm"  method="POST" action="carrent" >
                                <input type="hidden" name="command" value="backCars"/> 
                                <input type="submit" value="<fmt:message key="menu.users.back" /> "/>
                            </form> 
                        </div><br/><br/>
                        <hr/>
                        <form name ="AddingNewCar"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="command" value="newcar" /> 
                            <b> <fmt:message key="car.add.name"/></b>
                            <input type="text" name="carname" value="" required> <br/> <br/>
                            <b> <fmt:message key="car.add.price"/></b>
                            <input type="number" name="price" min="1" value="" required> <br/><br/>
                            <b> <fmt:message key="car.add.image"/></b>
                            <div class="notice"><fmt:message key="carimagechange.notice"/></div>
                            <input type="text" name="image" value="" required> <br/><br/>
                            <input type="submit" value="<fmt:message key="car.add"/> " />
                            <c:if test="${not empty csuccess}"><div class="msg"><fmt:message key="car.add.success"/></div></c:if>
                            <c:if test="${not empty cfail}"><div class="msg"><fmt:message key="car.add.fail"/></div></c:if>
                            </form>
                            <br/>
                        </div>               
                    </div>
                </div> 

            </div>
            <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
