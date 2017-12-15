<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>


	<form:form name='licenciaMedicaForm' action="nuevaLicenciaMedica" method='POST' commandName="licenciaMedicaForm" id="licenciaMedicaForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="editar" id="editar"/>
		<form:hidden path="finalizoAtencionMedica" id="finalizoAtencionMedica"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${listaPacientesForm.error}"></c:out><span id="errores"></span>
				</td>				
			</tr>		
			<tr>
				<td>
					Periodo de la licencia inicio:		
				</td>
				<td>
					<form:input path="vigenteDesde" id="vigenteDesde" readonly="true" onchange="verificaFechas()" />	
				</td>				
			</tr>
			<tr>
				<td>
					Periodo de la licencia fin:		
				</td>
				<td>
					<form:input path="vigenteHasta" id="vigenteHasta" readonly="true" onchange="verificaFechas()" />	
				</td>				
			</tr>
			<tr>
				<td>
					Dias:		
				</td>
				<td>
					<form:input path="dias" readonly="true" />	
				</td>				
			</tr>
			<tr>
				<td>
					Dias Con letra:		
				</td>
				<td>
					<form:input path="diasLetra" readonly="true" />	
				</td>				
			</tr>					
			<tr>
				<td>
					Motivos de la licencia:		
				</td>
				<td>
					<form:radiobuttons path="idLicMedicaMotivo" items="${catLicMedicaMotivos}" itemLabel="licMedicaMotivo" itemValue="licMedicaMotivoId" />
				</td>				
			</tr>
			<tr>
				<td>
					Caracter de la licencia:		
				</td>
				<td>
					<form:radiobuttons path="idLicMedicaCaracter" items="${catLicMedicaCaracteres}" itemLabel="licMedicaCaracter" itemValue="licMedicaCaracterId" />
				</td>				
			</tr>
			<tr>
				<td>
					Tipo de servicio otorgado:		
				</td>
				<td>
					<form:radiobuttons path="idLicMedicaTipoServicio" items="${catLicMedicaTiposServicio}" itemLabel="licMedicaTipoServicio" itemValue="licMedicaTipoServicioId" />
				</td>				
			</tr>						
			<tr>
				<td colspan="2">
					Licencias medicas enteriores		
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="Abrir Licencias" onclick="buscarLicencias()"/>
				</td>
			</tr>			
			<tr>
				<td colspan="2">
					<c:if test="${licenciaMedicaForm.finalizoAtencionMedica == false}">
						<input type="submit" value="Guardar"/>
					</c:if>
					
				</td>
			</tr>							
			<tr>
				<td colspan="2">
					<a href="/SAB/menu">Regresar al Menu</a>		
				</td>				
			</tr>													
		</table>
	</form:form>

	<div id="dialog-form" title="Licencias Medicas"> 
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$( "#vigenteDesde" ).datepicker({dateFormat: "dd/mm/yy"});
			$( "#vigenteHasta" ).datepicker({dateFormat: "dd/mm/yy"});
		});
		
		function verificaFechas(){
			var vigenteDesde = $("#vigenteDesde").val();
			var vigenteHasta = $("#vigenteHasta").val();
			if(vigenteDesde != "" && vigenteHasta != ""){
				$.getJSON("verificaFechas", {vigenteDesde:vigenteDesde, vigenteHasta:vigenteHasta} ,function(response){ 
					if(response.error==undefined){
				    	$("#dias").val(response.dias);
				    	$("#diasLetra").val(response.diasLetra);
				    	$("#errores").empty();
					}else{
						$("#errores").empty();
						$("#dias").val(0);
				    	$("#diasLetra").val("");
				    	$("#errores").append(response.error);
					}
			    });	
			} 
		}
		
		$(function() {
			dialog = $("#dialog-form").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});
		});
		
		function buscarLicencias(){
			var idAgenda = $("#idAgenda").val();
			$("#dialog-form").empty();
			$("#dialog-form").append("<object type='text/html' data='licenciasMedicas?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialog.dialog( "open" );
		}
	</script>
</body>
</html>