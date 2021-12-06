<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vehiculo">

	<h2>Informacion del vehiculo</h2>


	<table class="table table-striped">
		<tr>
			<th>Marca</th>
			<td><b><c:out value="${vehiculo.marca}" /></b></td>
		</tr>
		<tr>
			<th>Modelo</th>
			<td><c:out value="${vehiculo.modelo}" /></td>
		</tr>
		<tr>
			<th>Stock</th>
			<td><c:out value="${vehiculo.stock}" /></td>
		</tr>
		<tr>
			<th>Precio base</th>
			<td><c:out value="${vehiculo.precioBase}" /></td>
		</tr>
		<tr>
			<th>Precio por dia</th>
			<td><c:out value="${vehiculo.precioPorDia}" /></td>
		</tr>

	</table>

	<spring:url value="/empresa/{empresaId}/oficina/{oficinaId}/vehiculo/{vehiculoId}/delete"
		var="deleteUrl">
		<spring:param name="empresaId" value="${oficina.empresa.id}" />
		<spring:param name="oficinaId" value="${oficina.id}" />
		<spring:param name="vehiculoId" value="${vehiculo.id}" />		
	</spring:url>
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Eliminar
		Vehiculo</a>
	<spring:url value="/empresa/{empresaId}/oficina/{oficinaId}/vehiculo/{vehiculoId}/edit" var="editUrl">
		<spring:param name="empresaId" value="${oficina.empresa.id}" />
		<spring:param name="oficinaId" value="${oficina.id}" />
		<spring:param name="vehiculoId" value="${vehiculo.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Actualizar
		Vehiculo</a>

</petclinic:layout>