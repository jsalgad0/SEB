<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript">
$(function() {
	
});
</script>
<body>	
	<form:form method="post" action="${pageContext.request.contextPath}/informacionPaciente/registro"
				commandName="informacionPacienteForm">
		<span>
			<form:errors path="error"></form:errors>
		</span>
		<table>
			<tr>
				<td>
					Calle: 
				</td>
				<td>
					<form:input path="calle" id="calle" />
				</td>
			</tr>
			<tr>
				<td>
					Numero Interior: 
				</td>
				<td>
					<form:input path="numInt" id="numInt" />
				</td>
			</tr>
			<tr>
				<td>
					Numero Exterior: 
				</td>
				<td>
					<form:input path="numExt" id="numExt" />
				</td>
			</tr>
			<tr>
				<td>
					C P: 
				</td>
				<td>
					<form:input path="codigoPostal" id="codigoPostal" />
				</td>
			</tr>
			<tr>
				<td>
					Telefono: 
				</td>
				<td>
					<form:input path="telefono" id="telefono" />
				</td>
			</tr>
			<tr>
				<td>
					Correo: 
				</td>
				<td>
					<form:input path="mail" id="mail" />
				</td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td>
					<form:select path="idEstado" id="idEstado" onchange="municipios();">
						<form:option value="-1" label="-Selecciona un Estado-" />
						<form:options items="${catEstados}" itemLabel="estado" itemValue="estadoId" ></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Delegacion/Municipio:</td>
				<td>
					<form:select path="idMunicipio" id="idMunicipio" onchange="colonias();">
						<form:option value="-1" label="-Selecciona una Delegacion/Municipio-" />
						<form:options items="${catMunicipios}" itemLabel="municipio" itemValue="municipioId"></form:options>
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>Colonia:</td>
				<td>
					<form:select path="idColonia" id="idColonia">
						<form:option value="-1" label="-Selecciona una Colonia-" />
						<form:options items="${catColonias}" itemLabel="colonia" itemValue="ColoniaId"></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Estado Civil:</td>
				<td>
					<form:select path="idEstadoCivil" id="idEstadoCivil">
						<form:option value="-1" label="-Selecciona un EstadoCivil-" />
						<form:options items="${estadoCivil}" itemLabel="descripcion" itemValue="estadoCivilId"></form:options>
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>Escolaridad:</td>
				<td>
					<form:select path="idEscolaridad" id="idEscolaridad">
						<form:option value="-1" label="-Selecciona una Escolaridad-" />
						<form:options items="${escolaridad}" itemLabel="descripcion" itemValue="escolaridadId"></form:options>
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>Ocupacion:</td>
				<td>
					<form:select path="idOcupacion" id="idOcupacion">
						<form:option value="-1" label="-Selecciona una Ocupacion-" />
						<form:options items="${ocupacion}" itemLabel="descripcion" itemValue="ocupacionId"></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					Nacionalidad: 
				</td>
				<td>
					<form:input path="nacionalidad" id="nacionalidad" />
				</td>
			</tr>
			<tr>
				<td>
					Religion: 
				</td>
				<td>
					<form:input path="religion" id="religion" />
				</td>
			</tr>
			<tr>
				<td>
					Nivel Socioeconomico: 
				</td>
				<td>
					<form:input path="nivelSocioEconomico" id="nivelSocioEconomico" />
				</td>
			</tr>
			<tr>
				<td>
					Grupo Sanguineo:
				</td>
				<td>
					<form:radiobuttons path="idGrupoSanguineo" items="${grupoSanguineo }" itemLabel="grupoSanguineo" itemValue="grupoSanguineoId" />
				</td>
				<td>
					Positivo <form:radiobutton path="tipoSanguineo" valor="1" />
					Negativo <form:radiobutton path="tipoSanguineo" valor="0" />
				</td>
			</tr>
			<tr>
				<td>
					Inmigrante: <form:checkbox path="idInmigrante" value="1"/> 
				</td>
				<td>
					Indigena: <form:checkbox path="idInmigrante" value="1"/>
				</td>
			</tr>
			<tr>
				<td>
					Responsable Nombre: 
				</td>
				<td>
					<form:input path="responsableNombre" id="responsableNombre" />
				</td>
			</tr>
			<tr>
				<td>
					Responsable Edad: 
				</td>
				<td>
					<form:input path="responsableEdad" id="responsableEdad" />
				</td>
			</tr>
			<tr>
				<td>
					Responsable Parentezco: 
				</td>
				<td>
					<form:input path="responsableParentezco" id="responsableParentezco" />
				</td>
			</tr>
			<tr>
				<td>
					Responsable Direccion: 
				</td>
				<td>
					<form:input path="responsableDireccion" id="responsableDireccion" />
				</td>
			</tr>
			<tr>
				<td>
					Responsable Lugar: 
				</td>
				<td>
					<form:input path="responsableLugar" id="responsableLugar" />
				</td>
			</tr>
			<tr>
				<td>
					Responsable Telefono: 
				</td>
				<td>
					<form:input path="responsableTelefono" id="responsableTelefono" />
				</td>
			</tr>
		</table>
		<form:hidden path="idAgenda" id="idAgenda" />
		<form:hidden path="editar" id="editar" />
		<input type="submit" value="guardar"/>
		<a href="/sab/listaPacientes/atencionMedica?idAgenda=${informacionPacienteForm.idAgenda}">Regresar</a>
	</form:form>
</body>
</html>