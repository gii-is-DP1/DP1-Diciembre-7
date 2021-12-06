<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="empresa">
    <h2>Oficinas</h2>
    
    <table id="oficinasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Ciudad</th>
            <th>Direccion</th>
            <th>Codigo Postal</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="oficina">
            <tr>
                <td>
                    <c:out value="${oficina.ciudad}"/>
                </td>
                <td>
                <spring:url value="/empresa/{empresaId}/oficina/{oficinaId}" var="oficinaUrl">
                        <spring:param name="oficinaId" value="${oficina.id}"/>
                        <spring:param name="empresaId" value="${oficina.empresa.id}"/>
                    </spring:url>
                    
                    <a href="${fn:escapeXml(oficinaUrl)}"><c:out value="${oficina.direccion}"/></a>
                </td>
                <td>
                    <c:out value="${oficina.codigoPostal}"/>
                </td>        
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>