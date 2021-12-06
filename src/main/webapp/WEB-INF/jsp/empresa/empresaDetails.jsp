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

	<spring:url value="{empresaId}/oficina/new" var="addUrl">
		<spring:param name="empresaId" value="${empresa.id}" />
	</spring:url>
	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New
		Oficina</a>

	<br />
	<br />
	<br />
	<h2>Sus Oficinas</h2>

	<table class="table table-striped">
		<c:forEach var="oficina" items="${empresa.oficinas}">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Ciudad</dt>
						<dd>
							<c:out value="${oficina.ciudad}" />
						</dd>
						<dt>Dirección</dt>
						<dd>
							<c:out value="${oficina.direccion}" />
						</dd>
						<dt>Código Postal</dt>
						<dd>
							<c:out value="${oficina.codigoPostal}" />
						</dd>
					</dl>
				</td>
				<td><spring:url value="/empresa/{empresaId}/oficina/{oficinaId}" var="oficinaUrl">
						<spring:param name="oficinaId" value="${oficina.id}" />
						<spring:param name="empresaId" value="${empresa.id}" />
					</spring:url> <a href="${fn:escapeXml(oficinaUrl)}">Vista Detallada</a></td>
			<tr>
		</c:forEach>
	</table>


</petclinic:layout>