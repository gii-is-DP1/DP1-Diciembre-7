<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="conductor">
    <h2>Servicios</h2>
    
    <table id="serviciosTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Nombre del conductor</th>
            <th>Fecha de Inicio</th>
            <th>Fecha de Fin</th>
            <th style="width: 200px;">Ciudad</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="servicio">
            <tr>
            <td>
                    <c:out value="${servicio.cliente.nombre}"/>
                </td>
                <td>
                    <c:out value="${servicio.fechaInicio}"/>
                </td>
                <td>
                    <c:out value="${servicio.fechaFin}"/>
                </td>        
                <td>
                    <c:out value="${servicio.ciudad}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>