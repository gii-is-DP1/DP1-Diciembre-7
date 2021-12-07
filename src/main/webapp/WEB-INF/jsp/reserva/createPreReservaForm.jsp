<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="preReserva">
	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fechaInicio").datepicker({
											dateFormat : 'yy/mm/dd'
										});
									});
								</script>
								 <script>
										$(function() {
											$("#fechaFin").datepicker({
												dateFormat : 'yy/mm/dd'
											});
										});
									</script>
    </jsp:attribute>
	<jsp:body>   
	<h2>
		Nueva Reserva
	</h2>
	<c:if test="${!(empty message)}">
			<p>"${message}"</p>
	</c:if>	
	<form:form modelAttribute="preReserva" class="form-horizontal"
			id="add-preReserva-form" method="post"
			action="/cliente/${clienteid}/preReserva/new">
		<div class="form-group has-feedback">
			<input type="hidden" name="id" value="${preReserva.id}" />
			<div class="form-group has-feedback">
					<petclinic:inputField label="Fecha Inicio" name="fechaInicio" />
					<petclinic:inputField label="Fecha Fin" name="fechaFin" />
					<petclinic:inputField label="Ciudad" name="ciudad"/>
				<div class="control-group">
                 <label for="selectTipoVehiculo">Elige un tipo de vehiculo</label>
                 <form:select id="selectTipoVehiculo" class="form-control"
						path="tipoVehiculo" label="Tipo Vehiculo " size="5">
                 <c:forEach items="${tiposVehiculo}" var="t">
                     <c:set var="nombreTipo" value="${t.name}" />
                    <form:option name="tipoVehiculo" value="${t}"> <c:out
									value="${nombreTipo}"></c:out> </form:option>
                  </c:forEach>
                 </form:select>
                </div>
		</div>
	</div>
	<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Siguiente</button>
			</div>
		</div>
	</form:form>
	    </jsp:body>
</petclinic:layout>