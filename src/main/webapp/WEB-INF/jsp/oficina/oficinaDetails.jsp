<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="oficina">

	<h2>Informacion de la Oficina</h2>


	<table class="table table-striped">
		<tr>
			<th>Empresa</th>
			<td><b><c:out value="${oficina.empresa.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Ciudad</th>
			<td><c:out value="${oficina.ciudad}" /></td>
		</tr>
		<tr>
			<th>Direccion</th>
			<td><c:out value="${oficina.direccion}" /></td>
		</tr>
		<tr>
			<th>Código Postal</th>
			<td><c:out value="${oficina.codigoPostal}" /></td>
		</tr>
	</table>

	<spring:url value="{oficinaId}/vehiculo/new" var="addUrl">
		<spring:param name="oficinaId" value="${oficina.id}" />
	</spring:url>
	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New
		Vehiculo</a>

	<spring:url value="/empresa/{empresaId}/oficina/{oficinaId}/delete" var="deleteUrl">
		<spring:param name="oficinaId" value="${oficina.id}" />
		<spring:param name="empresaId" value="${oficina.empresa.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete
		Oficina</a>

	<br />
	<br />
	<br />
	<h2>Flota de vehiculos</h2>

	<table class="table table-striped">
		<c:forEach var="vehiculo" items="${oficina.vehiculos}">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Marca</dt>
						<dd>
							<c:out value="${vehiculo.marca}" />
						</dd>
						<dt>Modelo</dt>
						<dd>
							<c:out value="${vehiculo.modelo}" />
						</dd>
						<dt>Stock</dt>
						<dd>
							<c:out value="${vehiculo.stock}" />
						</dd>
						<dt>Precio Base</dt>
						<dd>
							<c:out value="${vehiculo.precioBase}" />
						</dd>
						<dt>Precio por Día</dt>
						<dd>
							<c:out value="${vehiculo.precioPorDia}" />
						</dd>
					</dl>
				</td>
				<td><spring:url value="/vehiculo/{vehiculoId}"
						var="vehiculoUrl">
						<spring:param name="vehiculoId" value="${vehiculo.id}" />
					</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}">Vista Detallada</a></td>
			<tr>
		</c:forEach>
	</table>


</petclinic:layout>