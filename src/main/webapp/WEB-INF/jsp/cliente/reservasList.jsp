<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cliente">
    <h2>Reservas</h2>
    
    <table id="reservasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha de Inicio</th>
            <th>Fecha de Fin</th>
            <th style="width: 200px;">Precio Final</th>
            <th style="width: 200px;">Ciudad</th>
            <th>Detalles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="reserva">
            <tr>
                <td>
                    <c:out value="${reserva.fechaInicio}"/>
                </td>
                <td>
                    <c:out value="${reserva.fechaFin}"/>
                </td>
                <td>
                    <c:out value="${reserva.precioFinal}"/>
                </td>        
                <td>
                    <c:out value="${reserva.ciudad}"/>
                </td>
                <td>
                                <spring:url value="/cliente/{clienteId}/reserva/{reservaId}" var="reservaUrl">
                        <spring:param name="reservaId" value="${reserva.id}"/>
                        <spring:param name="clienteId" value="${reserva.cliente.id}"/>
                    </spring:url>
                    
                    <a href="${fn:escapeXml(reservaUrl)}"><c:out value="Detalles"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
