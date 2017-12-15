<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<script type="text/javascript">
	function editar(){
		$("#aseguradorForm").attr("action", "editar");
		$("#aseguradorForm").submit();
	}
</script>
</head>
<body>
	<form:form method="post" action="${pageContext.request.contextPath}/asegurador/alta"
				commandName="aseguradorForm">
			<span>
				<form:errors path="error"></form:errors>
			</span>
			<form:hidden path="idAsegurador" id="idAsegurador" value="${asegurador.idAsegurador }"/>
			<table>
			<tr>
				<td>RFC:</td>
				<td><form:input path="asegurador.rfc" id="rfc" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="asegurador.nombreRazonSocial" id="nombreRazonSocial" /></td>
			</tr>
			<tr>
				<td>correoElectronico:</td>
				<td><form:input path="asegurador.correoElectronico" id="correoElectronico" /></td>
			</tr>	
			<tr>
				<td>cp:</td>
				<td><form:input path="asegurador.cp" id="cp" /></td>
			</tr>	
			<tr>
				<td>calle:</td>
				<td><form:input path="asegurador.calle" id="calle" /></td>
			</tr>	
			<tr>
				<td>noExt:</td>
				<td><form:input path="asegurador.noExt" id="noExt" /></td>
			</tr>	
			<tr>
				<td>noInt:</td>
				<td><form:input path="asegurador.noInt" id="noInt" /></td>
			</tr>	
			<tr>
				<td>telefono:</td>
				<td><form:input path="asegurador.telefono" id="telefono" /></td>
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
				<c:choose>
					<c:when test="${aseguradorForm.editar}">
						<input type="button" value="Editar" onclick="editar();" />
					</c:when>
					<c:otherwise>
						<td colspan='2'><input type="submit" value="guardar"/></td>
					</c:otherwise>
				</c:choose>				
			</tr>
		</table>
	</form:form>
	<a href="/SAB/menu" >Regresar al Menu</a>
</body>
</html>