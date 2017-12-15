<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='constanciaCuidadosMaternalesForm' action="${pageContext.request.contextPath}/certificados/imprimirConstanciaCuidadosMaternales" method='POST' commandName="constanciaCuidadosMaternalesForm" id="constanciaCuidadosMaternalesForm">
		<form:hidden path="idAgenda"/>
		<table>
			<tr>
				<td colspan="2"><form:errors path="error" /><c:out value="${constanciaCuidadosMaternalesForm.error}"></c:out></td>
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
					categoria:
				</td>
				<td>
					<form:input path="categoria"/>
				</td>
			</tr>
			<tr>
				<td>
					clave:
				</td>
				<td>
					<form:input path="clave"/>
				</td>
			</tr>	
			<tr>
				<td>
					adscripcion:
				</td>
				<td>
					<form:input path="adscripcion"/>
				</td>
			</tr>
			<tr>
				<td>
					horario:
				</td>
				<td>
					<form:input path="horario"/>
				</td>
			</tr>
			<tr>
				<td>
					diagnostico:
				</td>
				<td>
					<form:input path="diagnostico"/>
				</td>
			</tr>
			<tr>
				<td>
					nombreClave:
				</td>
				<td>
					<form:input path="nombreClave"/>
				</td>
			</tr>
			<tr>
				<td>
					diaInicio:
				</td>
				<td>
					<form:input path="diaInicio"/>
				</td>
			</tr>
			<tr>
				<td>
					mesInicio:
				</td>
				<td>
					<form:input path="mesInicio"/>
				</td>
			</tr>
			<tr>
				<td>
					anioInicio:
				</td>
				<td>
					<form:input path="anioInicio"/>
				</td>
			</tr>
			<tr>
				<td>
					diaFin:
				</td>
				<td>
					<form:input path="diaFin"/>
				</td>
			</tr>
			<tr>
				<td>
					mesFin:
				</td>
				<td>
					<form:input path="mesFin"/>
				</td>
			</tr>	
			<tr>
				<td>
					anioFin:
				</td>
				<td>
					<form:input path="anioFin"/>
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