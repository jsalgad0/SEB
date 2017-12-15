<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>

<body>

	<form:form name='atencionMedicaForm' action="nuevoAtencionMedica" method='POST' commandName="atencionMedicaForm" id="atencionMedicaForm">
		<form:hidden path="idAgenda"/>
		<form:hidden path="idPersona"/>
		<form:hidden path="afiliadotipoidentificador.cattipoidentificador.tipoIdentificadorId"/>
		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /><c:out value="${atencionMedicaForm.error}"></c:out> </td>
			</tr>		
			<tr>
				<td>Asegurador:</td>
				<td>
					<form:input path="agenda.aseguradores.nombreRazonSocial" readonly="true"/>
				</td>
			</tr>	
			<tr>
				<td>Prestador:</td>
				<td><form:input path="agenda.prestadores.nombreRazonSocial" readonly="true"/></td>
			</tr>			
			<tr>
				<td>Nombre:</td>
				<td>
					<form:select path="idConvenio" id="idConvenio" >
						<form:options items="${convenios}" itemLabel="convenio" itemValue="convenioId" ></form:options>
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>Identificador:</td>
				<td><form:input path="afiliadotipoidentificador.cattipoidentificador.tipoIdentificador" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Valor Identificador:</td>
				<td><form:input path="afiliadotipoidentificador.tipoIdValor" readonly="true" /></td>
			</tr>
			<tr>
				<td>Tipo:</td>
				<td>Derechohabiente - <form:radiobutton path="tipoAfiliado" value="D" /> Benefeciario - <form:radiobutton path="tipoAfiliado" value="B" /></td>
			</tr>
			<tr>
				<td>Fecha:</td>
				<td><form:input path="fechaDeNacimiento" readonly="true" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="agenda.afiliado.nombre" readonly="true" /></td>
			</tr>														
			<tr>
				<td>Apellido Paterno:</td>
				<td><form:input path="agenda.afiliado.apellidoPaterno" readonly="true" /></td>
			</tr>														
			<tr>
				<td>Apellido Materno:</td>
				<td><form:input path="agenda.afiliado.apellidoMaterno" readonly="true" /></td>
			</tr>
			<tr>
				<td>Telefono:</td>
				<td><form:input path="agenda.afiliado.telefono1" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Correo:</td>
				<td><form:input path="agenda.afiliado.mail" readonly="true" /></td>
			</tr>
			<c:if test="${not empty catPrestacion}">
				<tr>
					<td>Prestacion:</td>
					<td><form:hidden path="catPrestacion.prestacionId"/> ${catPrestacion.codigo} - ${catPrestacion.descripcion}</td>
				</tr>
			</c:if>
			<c:if test="${not empty prestacionPrestador}">
				<tr>
					<td>Prestacion:</td>
					<td><form:hidden path="prestacionprestador.prestacionPrestadorId"/> ${prestacionPrestador.codigo} - ${prestacionPrestador.descripcion}</td>
				</tr>
			</c:if>
			<tr>
				<td>Medico:</td>
				<td>${agenda.usuarios.nombre}</td>
			</tr>
			<tr>
				<td>Consultorio:</td>
				<td>${agenda.consultorio}</td>
			</tr>
			<tr>
				<td>Hora:</td>
				<td>${agenda.horaCita}</td>
			</tr>																																																													
			<tr>
				<td colspan='2'>
					<input type="submit" value="Gaurdar"/>
				</td>
			</tr>
		</table>
	</form:form>
	
	<script type="text/javascript">
	
	</script>
</body>
</html>