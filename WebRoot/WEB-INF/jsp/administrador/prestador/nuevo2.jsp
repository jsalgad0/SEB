<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>

<script type="text/javascript">
	function editar(){
		$("#prestadorForm").attr("action", "editar");
		$("#prestadorForm").submit();
	}
</script>

<body>

	<form:form name='prestadorForm' action="nuevo" method='POST' commandName="prestadorForm" id="prestadorForm">
		<form:hidden path="prestadores.prestadorId"/>
		<form:hidden path="usarTablaPrestacionesSabTemp"/>
		<form:hidden path="nombreRazonSocialTemp"/>
		<form:hidden path="rfcTemp"/>		
		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /><c:out value="${prestadorForm.error}"></c:out> </td>
			</tr>		
			<tr>
				<td>RFC:</td>
				<td><form:input path="prestadores.rfc" id="rfc" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="prestadores.nombreRazonSocial" id="nombreRazonSocial" /></td>
			</tr>
			<tr>
				<td>correoElectronico:</td>
				<td><form:input path="prestadores.correoElectronico" id="correoElectronico" /></td>
			</tr>	
			<tr>
				<td>cp:</td>
				<td><form:input path="prestadores.cp" id="cp" /></td>
			</tr>	
			<tr>
				<td>calle:</td>
				<td><form:input path="prestadores.calle" id="calle" /></td>
			</tr>	
			<tr>
				<td>noExt:</td>
				<td><form:input path="prestadores.noExt" id="noExt" /></td>
			</tr>	
			<tr>
				<td>noInt:</td>
				<td><form:input path="prestadores.noInt" id="noInt" /></td>
			</tr>	
			<tr>
				<td>telefono:</td>
				<td><form:input path="prestadores.telefono" id="telefono" /></td>
			</tr>
			<tr>
				<td>usarTablaPrestacionesSab:</td>
				<td><form:checkbox path="usarTablaPrestacionesSab"/></td>
			</tr>																		
			<tr>
				<td>Tipo Persona:</td>
				<td>
					<form:select path="idTipoPersona" id="idTipoPersona" >
						<form:option value="-1" label="-Selecciona un Tipo Persona-" />
						<form:options items="${catTiposPersonas}" itemLabel="tipoPersona" itemValue="tipoPersonaId" ></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Tipo Prestador:</td>
				<td>
					<form:select path="idTipoPrestador" id="idTipoPrestador" >
						<form:option value="-1" label="-Selecciona un Tipo Prestador-" />
						<form:options items="${catTipoPrestador}" itemLabel="tipoPrestador" itemValue="tipoPrestadorId" ></form:options>
					</form:select>
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
				<td colspan='2'>
					
					<c:choose>
						<c:when test="${prestadorForm.editar}">
							<input type="button" value="Editar" onclick="javascript:editar();" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Gaurdar"/>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr>
				<td colspan='2'>
					<a href="/SAB/menu">Regresar al Menu</a>
				</td>
			</tr>			
		</table>
	</form:form>

</body>
</html>