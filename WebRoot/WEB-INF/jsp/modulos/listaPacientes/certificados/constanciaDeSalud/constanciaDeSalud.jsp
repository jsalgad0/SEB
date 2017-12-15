<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='constanciaSaludForm' action="${pageContext.request.contextPath}/certificados/imprimirConstanciaDeSalud" method='POST' commandName="constanciaSaludForm" id="constanciaSaludForm">
		<form:hidden path="idAgenda"/>
		<table>
			<tr>
				<td colspan="2"><form:errors path="error" /><c:out value="${constanciaSaludForm.error}"></c:out></td>
			</tr>	
			<tr>
				<td>
					Medico:
				</td>
				<td>
					<form:input path="medico"/>
				</td>
			</tr>
			<tr>
				<td>
					Certificado haber examinado a:
				</td>
				<td>
					<form:input readonly="true" path="nombre" />
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