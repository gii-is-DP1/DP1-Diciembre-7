<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="empresa">
	<h2>
		<c:if test="${empresa['new']}">New </c:if>
		Empresa
	</h2>
	<form:form modelAttribute="empresa" class="form-horizontal"
		id="add-empresa-form">
		<c:choose>
			<c:when test="${empresa['new']}">
				<div class="form-group has-feedback">
					<petclinic:inputField label="Nombre" name="nombre" />
					<petclinic:inputField label="Telefono" name="telefono" />
					<petclinic:inputField label="Email" name="email" />
					<petclinic:inputField label="Pais" name="pais" />
					<petclinic:inputField label="Username" name="user.username" />
					<petclinic:inputField label="Password" name="user.password" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group has-feedback">
					<petclinic:inputField label="Nombre" name="nombre" />
					<petclinic:inputField label="Telefono" name="telefono"/>
					<petclinic:inputField label="Email" name="email" />
					<petclinic:inputField label="Pais" name="pais" />
				</div>
			</c:otherwise>
		</c:choose>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${empresa['new']}">
						<button class="btn btn-default" type="submit">Add Empresa</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Empresa</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>