<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='constanciaAsistenciaForm' action="${pageContext.request.contextPath}/certificados/imprimirConstanciaDeAsistencia" method='POST' commandName="constanciaAsistenciaForm" id="constanciaAsistenciaForm">
		<form:hidden path="idAgenda"/>
		<table>
			<tr>
				<td colspan="2"><form:errors path="error" /><c:out value="${citasPresencialesForm.error}"></c:out></td>
			</tr>	
			<tr>
				<td>
					Nombre:
				</td>
				<td>
					<form:input path="nombre"/>
				</td>
			</tr>
			<tr>
				<td>
					Descripcion:
				</td>
				<td>
					<form:input path="descripcion"/>
				</td>
			</tr>
			<tr>
				<td>
					horaInicio:
				</td>
				<td>
					<form:input path="horaInicio"/>
				</td>
			</tr>	
			<tr>
				<td>
					horaFin:
				</td>
				<td>
					<form:input path="horaFin"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<input type="submit" value="Guardar" >
				</td>
			</tr>											
		</table>
	</form:form>

	<script type="text/javascript">

	</script>
</body>
</html>