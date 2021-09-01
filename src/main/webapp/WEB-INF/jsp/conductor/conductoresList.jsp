<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="conductores">
	<h2>Conductores</h2>

	<table id="conductorTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 200px;">Años de experiencia</th>
				<th style="width: 120px">Salario base</th>
				<th style="width: 120px">Salario por dia</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="conductor">
				<tr>
					<td><spring:url value="/conductores/{conductorId}"
							var="conductorUrl">
							<spring:param name="conductorId" value="${conductor.id}" />
						</spring:url> <a href="${fn:escapeXml(conductorUrl)}"> <c:out
								value="${conductor.name}" /></a></td>
					<td><c:out value="${conductor.experiencia}" /></td>
					<td><c:out value="${conductor.salarioBase}" /></td>
					<td><c:out value="${conductor.salarioPorDia}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>