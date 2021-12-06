<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="reservas">

	<h2>Informacion de la reserva</h2>


	<table class="table table-striped">
		<tr>
			<th>Fecha de Inicio</th>
			<td><b><c:out value="${reserva.fechaInicio}" /></b></td>
		</tr>
		<tr>
			<th>Fecha de Fin</th>
			<td><c:out value="${reserva.fechaFin}" /></td>
		</tr>
		<tr>
			<th>Precio Total</th>
			<td><c:out value="${reserva.precioFinal}" /></td>
		</tr>
	</table>

	<spring:url value="/cliente/{clienteId}/reserva/{reservaId}/delete"
		var="deleteUrl">
		<spring:param name="reservaId" value="${reserva.id}" />
		<spring:param name="clienteId" value="${cliente.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Cancelar
		Reserva</a>


	<br />
	<br />
	<br />
	<h2>Vehiculo</h2>
	<table class="table table-striped">
		<tr>
			<th>Marca</th>
			<td><b><c:out value="${reserva.vehiculo.marca}" /></b></td>
		</tr>
		<tr>
			<th>Modelo</th>
			<td><c:out value="${reserva.vehiculo.modelo}" /></td>
		</tr>
		<tr>
			<th>Precio base</th>
			<td><c:out value="${reserva.vehiculo.precioBase}" /></td>
		</tr>
		<tr>
			<th>Precio por dia</th>
			<td><c:out value="${reserva.vehiculo.precioPorDia}" /></td>

		</tr>
	</table>
	<br />
	<br />
	<br />
	<h2>Conductor</h2>
	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${reserva.conductor.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Experiencia</th>
			<td><c:out value="${reserva.conductor.experiencia}" /></td>
		</tr>
		<tr>
			<th>Salario base</th>
			<td><c:out value="${reserva.conductor.salarioBase}" /></td>
		</tr>
		<tr>
			<th>Salario por dia</th>
			<td><c:out value="${reserva.conductor.salarioPorDia}" /></td>

		</tr>
	</table>
</petclinic:layout>