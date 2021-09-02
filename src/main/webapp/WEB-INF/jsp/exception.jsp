<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/bookar.jpg" var="bookarImage"/>
    <img src="${bookarImage}"/>

    <h2>Ha habido un error...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
