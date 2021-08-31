<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">

    <h2>Informacion del cliente</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cliente.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${cliente.email}"/></td>
        </tr>
        <tr>
            <th>Telefono</th>
            <td><c:out value="${cliente.telefono}"/></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${cliente.direccion}"/></td>
        </tr>
        <tr>
            <th>DNI</th>
            <td><c:out value="${cliente.dni}"/></td>
        </tr>
    </table>

    <spring:url value="{clienteId}/edit" var="editUrl">
        <spring:param name="clienteId" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Cliente</a>

</petclinic:layout>