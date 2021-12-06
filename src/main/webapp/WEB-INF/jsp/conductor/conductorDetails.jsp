<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="conductores">

	<h2>Informacion del conductor</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${conductor.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${conductor.email}" /></td>
		</tr>
		<tr>
			<th>Telefono</th>
			<td><c:out value="${conductor.telefono}" /></td>
		</tr>
		<tr>
			<th>DNI</th>
			<td><c:out value="${conductor.dni}" /></td>
		</tr>
		<tr>
			<th>Experiencia</th>
			<td><c:out value="${conductor.experiencia}" /></td>
		</tr>
		<tr>
			<th>Permiso de coche</th>
			<c:if test="${conductor.permisoCoche}">
			<td><c:out value="Disponible" /></td>
			</c:if>	
			<c:if test="${!conductor.permisoCoche}">
			<td><c:out value="No disponible" /></td>
			</c:if>
		</tr>
		<tr>
			<th>Permiso de barco</th>
			<c:if test="${conductor.permisoBarco}">
			<td><c:out value="Disponible" /></td>
			</c:if>	
			<c:if test="${!conductor.permisoBarco}">
			<td><c:out value="No disponible" /></td>
			</c:if>
		</tr>
		<tr>
			<th>Salario base</th>
			<td><c:out value="${conductor.salarioBase}" /></td>
		</tr>
		<tr>
			<th>Salario por dia</th>
			<td><c:out value="${conductor.salarioPorDia}" /></td>
		</tr>
	</table>

	<spring:url value="{conductorId}/edit" var="editUrl">
		<spring:param name="conductorId" value="${conductor.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit
		Conductor</a>

</petclinic:layout>