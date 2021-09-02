<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2>Bienvenido a Bookar</h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/Bookar.jpg" htmlEscape="true" var="bookarImage"/>
            <img class="img-responsive" src="${bookarImage}"/>
        </div>
    </div>
</petclinic:layout>
