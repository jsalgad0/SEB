<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<body>

	<form:form name='autorizacionEspecialForm' action="autorizarPermisoEspecialHuella" method='POST' commandName="autorizacionEspecialForm" id="autorizacionEspecialForm">
		<form:hidden path="idPermisoEspecial" id="idPermisoEspecial"/>
		<table>
			<tr>
				<td colspan="2"><form:errors path="error" /><c:out value="${autorizacionEspecialForm.error}"></c:out> <span id="errores"></span> </td>
			</tr>
			<tr>
				<td>Tipo autorizacion</td>
				<td>
					<form:select path="idTipoVigencia" onchange="fecha()">
						<form:option value="-1">TIPO DE VIGENCIA</form:option>
						<form:option value="1">TEMPORAL</form:option>
						<form:option value="2">PERMANENTE</form:option>
					</form:select> 
				</td>
			</tr>	
			<tr>
				<td>Tipo causal</td>
				<td>
					<form:select path="idCatCausa">
						<form:option value="-1">TIPO CAUSAL</form:option>
						<form:options items="${catCausas}" itemValue="causaId" itemLabel="causa" />
					</form:select> 
				</td>
			</tr>
			<tr id="filaFecha" style="display: none">
				<td>Fecha fin</td>
				<td>
					<form:input path="fechaFin" id="fechaFin" readonly="true" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="guardar">
				</td>
			</tr>			
		</table>
	</form:form>
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			$( "#fechaFin" ).datepicker({dateFormat: "dd/mm/yy"});
			
			fecha();
		});
		
		function fecha(){
			var idTipoVigencia = $("#idTipoVigencia").val();
			if(idTipoVigencia==1){
				$("#filaFecha").show();	
			}
		}
	</script>
</body>
</html>