<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="reserva">
	
	<h2>Nueva Reserva</h2>
	<form:form modelAttribute="reserva" class="form-horizontal"
			id="add-reserva-form" method="post"
			action="/cliente/${clienteId}/reserva/new">
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">

				<button class="btn btn-default" type="submit">Continuar con
					la reserva</button>
			</div>
		</div>
	</form:form>
	    </jsp:body>

</petclinic:layout>