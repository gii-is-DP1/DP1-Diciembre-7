<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="cliente">
	<h2>
		<c:if test="${cliente['new']}">New </c:if>
		Cliente
	</h2>
	<c:if test="${!(empty message)}">
			<p>"${message}"</p>
	</c:if>	
	<form:form modelAttribute="cliente" class="form-horizontal"
		id="add-cliente-form">
		<c:choose>
			<c:when test="${cliente['new']}">
				<div class="form-group has-feedback">
					<petclinic:inputField label="Nombre" name="nombre" />
					<petclinic:inputField label="Telefono" name="telefono" />
					<petclinic:inputField label="Email" name="email" />
					<petclinic:inputField label="DNI" name="dni" />
					<petclinic:inputField label="Direccion" name="direccion" />
					<petclinic:inputField label="Username" name="user.username" />
					<petclinic:inputField label="Password" name="user.password" />
				</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="dni" value="${cliente.dni}"/>
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="password" value="${password}"/>
				<petclinic:inputField label="Nombre" name="nombre" />
				<petclinic:inputField label="Telefono" name="telefono" />
				<petclinic:inputField label="Email" name="email" />
				<petclinic:inputField label="Direccion" name="direccion"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${cliente['new']}">
						<button class="btn btn-default" type="submit">Add Cliente</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Cliente</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>
