<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vehiculo">
	<h2>
		<c:if test="${vehiculo['new']}">Nuevo </c:if>
		Vehiculo
	</h2>
	<form:form modelAttribute="vehiculo" class="form-horizontal"
		id="add-vehiculo-form">
		<c:choose>
			<c:when test="${vehiculo['new']}">
				<div class="form-group has-feedback">
					<input type="hidden" name="id" value="${vehiculoId}" />
					<petclinic:inputField label="Marca" name="marca" />
					<petclinic:inputField label="Modelo" name="modelo" />
					<petclinic:inputField label="Stock" name="stock" />
					<petclinic:inputField label="Precio base" name="precioBase" />
					<petclinic:inputField label="Precio por dia" name="precioPorDia" />
					<petclinic:selectField label="Tipo de vehiculo" name="tipoVehiculo"
						size="10px" names="${tipoVehiculo}"></petclinic:selectField>

				</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="id" value="${vehiculoId}" />
				<input type="hidden" name="marca" value="${vehiculo.marca}" />
				<input type="hidden" name="modelo" value="${vehiculo.modelo}" />
				<petclinic:inputField label="Stock" name="stock" />
				<petclinic:inputField label="Precio base" name="precioBase" />
				<petclinic:inputField label="Precio por dia" name="precioPorDia" />
			</c:otherwise>
		</c:choose>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${vehiculo['new']}">
						<button class="btn btn-default" type="submit">Add
							Vehiculo</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Vehiculo</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>