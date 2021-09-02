<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="oficina">
	<h2>
		<c:if test="${reserva['new']}">Nueva </c:if>
		Reserva
	</h2>
	<form:form modelAttribute="oficina" class="form-horizontal"
		id="add-reserva-form">
		<div class="form-group has-feedback">
			<div class="form-group">
				<label class="col-sm-2 control-label">Empresa</label>
				<div class="col-sm-10">
					<c:out value="${oficina.empresa.nombre}" />
				</div>
			</div>
			<input type="hidden" name="id" value="${oficina.id}"/>
			<petclinic:inputField label="Ciudad" name="ciudad" />
			<petclinic:inputField label="Dirección" name="direccion" />
			<petclinic:inputField label="Código Postal" name="codigoPostal" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Add Oficina</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>