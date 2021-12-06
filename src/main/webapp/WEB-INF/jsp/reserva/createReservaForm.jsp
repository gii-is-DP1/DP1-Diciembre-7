<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="reserva">
	<jsp:body>   
	<h2>
		Nueva Reserva
	</h2>
	<p>${Message}</p>
	<form:form modelAttribute="reserva" class="form-horizontal"
			id="add-reserva-form" method="post"
			action="/cliente/${clienteid}/reserva/new">
		<div class="form-group has-feedback">
			<input type="hidden" name="id" value="${reserva.id}" />
			<!--  <input type="hidden" name="fechaInicio" value="${preReserva.fechaInicio}" />
			<input type="hidden" name="fechaFin" value="${preReserva.fechaFin}" />-->
			<form:input path="fechaInicio" value="${preReserva.fechaInicio}" disabled="true" />
			<form:input path="fechaFin" value="${preReserva.fechaFin}" disabled="true" />
			<input type="hidden" name="ciudad" value="${preReserva.ciudad}" />
			<div class="control-group">
                 <label for="selectVehiculo">Choose a Vehiculo</label>
                 <form:select id="selectVehiculo" class="form-control"
						path="vehiculo" label="Vehiculo " size="5">
                 <c:forEach items="${vehiculos}" var="v">
                     <c:set var="nameVehiculo"
								value="${v.marca}  ${v.modelo}" />
                    <form:option name="vehiculo" value="${v}"> <c:out
									value="${nameVehiculo}"></c:out> </form:option>
                  </c:forEach>
                 </form:select>
                </div>
                <div class="control-group">
                 <label for="selectConductor">Choose a Conductor</label>
                 <form:select id="selectConductor" class="form-control"
						path="conductor" label="Conductor " size="5">
                 <c:forEach items="${conductores}" var="c">
                     <c:set var="nameConductor" value="${c.nombre}" />
                    <form:option name="conductor" value="${c}"> <c:out
									value="${nameConductor}"></c:out> </form:option>
                  </c:forEach>
                 </form:select>
                </div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Hacer Reserva</button>
			</div>
		</div>
	</form:form>
	    </jsp:body>
</petclinic:layout>