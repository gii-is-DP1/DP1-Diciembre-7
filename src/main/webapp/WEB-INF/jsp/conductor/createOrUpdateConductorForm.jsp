<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="conductor">
	<h2>
		<c:if test="${conductor['new']}">New </c:if>
		Conductor
	</h2>
	<form:form modelAttribute="conductor" class="form-horizontal"
		id="add-conductor-form">
		<c:choose>
			<c:when test="${conductor['new']}">
				<div class="form-group has-feedback">
					<petclinic:inputField label="Nombre" name="nombre" />
					<petclinic:inputField label="Telefono" name="telefono" />
					<petclinic:inputField label="Email" name="email" />
					<petclinic:inputField label="DNI" name="dni" />
					<petclinic:inputField label="Ciudad" name="ciudad" />
					<petclinic:inputField label="Experiencia" name="experiencia" />
					<form:checkbox path="permisoCoche" label="Permiso coche" />
					<form:checkbox path="permisoBarco" label="Permiso barco" />
					<petclinic:selectField label="" name="" size="" names=""></petclinic:selectField>
					<petclinic:inputField label="Salario base" name="salarioBase" />
					<petclinic:inputField label="Salario por dia" name="salarioPorDia" />
					<petclinic:inputField label="Username" name="user.username" />
					<petclinic:inputField label="Password" name="user.password" hidden=true/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group has-feedback">
					<petclinic:inputField label="Nombre" name="nombre" />
					<petclinic:inputField label="Telefono" name="telefono" />
					<petclinic:inputField label="Email" name="email" />
					<petclinic:inputField label="Ciudad" name="ciudad" />
					<petclinic:inputField label="Experiencia" name="experiencia" />
					<form:checkbox path="permisoCoche" label="Permiso coche" />
					<form:checkbox path="permisoBarco" label="Permiso barco" />
					<petclinic:inputField label="Salario base" name="salarioBase" />
					<petclinic:inputField label="Salario por dia" name="salarioPorDia" />
				</div>
			</c:otherwise>
		</c:choose>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${conductor['new']}">
						<button class="btn btn-default" type="submit">Add
							Conductor</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Conductor</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>
