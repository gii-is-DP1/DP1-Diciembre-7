<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="empresas">

	<h2>Informacion de la empresa</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${empresa.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${empresa.email}" /></td>
		</tr>
		<tr>
			<th>Telefono</th>
			<td><c:out value="${empresa.telefono}" /></td>
		</tr>
		<tr>
			<th>Pais</th>
			<td><c:out value="${empresa.pais}" /></td>
		</tr>
	</table>

	<spring:url value="{empresaId}/edit" var="editUrl">
		<spring:param name="empresaId" value="${empresa.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit
		Empresa</a>

</petclinic:layout>