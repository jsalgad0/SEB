<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='listaPacientesForm' action="notaMedica" method='POST' commandName="listaPacientesForm" id="listaPacientesForm">
		<form:hidden path="idDiagnostico" id="idDiagnostico"/>
		<form:hidden path="finalizarAtencion" id="finalizarAtencion"/>
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="banderaError" id="banderaError"/>
		<form:hidden path="idEstudios" id="idEstudios"/>
		<form:hidden path="editar" id="editar"/>
		<form:hidden path="notamedica.notaMedicaId" id="notaMedicaId"/>
		<form:hidden path="finalizoAtencionMedica" id="finalizoAtencionMedica"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${listaPacientesForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Peso:		
				</td>
				<td>
					${signosVitales.peso}		
				</td>				
			</tr>
			<tr>
				<td>
					Altura:		
				</td>
				<td>
					${signosVitales.altura}		
				</td>				
			</tr>
			<tr>
				<td>
					T.A.:		
				</td>
				<td>
					${signosVitales.tensionArterial}		
				</td>				
			</tr>
			<tr>
				<td>
					Ultima Somatometria:		
				</td>
				<td>
							
				</td>				
			</tr>
			<tr>
				<td>
					antecedentes:		
				</td>
				<td>
					<form:input path="notamedica.antecedentes" id="antecedentes" />		
				</td>				
			</tr>	
			<tr>
				<td>
					sintomas:		
				</td>
				<td>
					<form:input path="notamedica.sintomas" id="sintomas" />		
				</td>				
			</tr>	
			<tr>
				<td>
					observaciones:		
				</td>
				<td>
					<form:input path="notamedica.observaciones" id="observaciones" />		
				</td>				
			</tr>		
			<tr>
				<td>
					planAseguir:		
				</td>
				<td>
					<form:input path="notamedica.planAseguir" id="planAseguir" />		
				</td>				
			</tr>	
			<tr>
				<td>
					diagnostico:		
				</td>
				<td>
					<form:input path="diagnostico" autocomplete="off" id="diagnostico" cssStyle="width:500px" /><input type="button" value="Otros Diagnosticos" onclick="diagnosticos()" />		
				</td>				
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${listaPacientesForm.finalizoAtencionMedica == false}">
						<input type="submit" value="Guardar" />
					</c:if>
				</td>				
			</tr>			
			<tr>
				<td colspan="2">
					<button onclick="regresar()" >Regresar al Menu</button>
					<a href="/sab/informacionPaciente/inicio?idAgenda=${listaPacientesForm.idAgenda}">Informacion del Paciente</a>
					<a href="/sab/historiaClinica/inicio?idAgenda=${listaPacientesForm.idAgenda}">Historia Clinica</a>
					<a href="/sab/signos/tomaSignos?idAgenda=${listaPacientesForm.idAgenda}">Signos Vitales</a>
					<c:if test="${listaPacientesForm.finalizoAtencionMedica == true}">
						<a href="/sab/certificados/certificados?idAgenda=${listaPacientesForm.idAgenda}">Certificados</a>
					</c:if>	
					<a onclick="finalizar()" href="#" href="">Finalizar Atencion</a>
				</td>				
			</tr>																									
		</table>
	</form:form>
	
	<c:if test="${listaPacientesForm.editar == true}">
	
		<table>
			<tr>
				<td>
					<a href="#" onclick="prestaciones()" >Prestaciones Realizadas</a>
				</td>
				<td>
					<a href="#" onclick="recetaMedica()" >Receta Medica</a>
				</td>
				<td>
					<a href="#" onclick="licenciaMedica()" >Licencia Medica</a>
				</td>
				<td>
					<a href="#" onclick="estudiosMedicos(1)" >Estudios Laboratorio</a>
				</td>
				<td>
					<a href="#" onclick="estudiosMedicos(2)" >Estudios Gabinete</a>
				</td>
				<td>
					<a href="#" onclick="solicitudReferencia()" >Solicitud Referencia</a>
				</td>
				<td>
					<a href="#" onclick="estudiosMedicos(3)" >Otros Servicios</a>
				</td>																								
			</tr>
		</table>	
	</c:if>
	

	<script type="text/javascript">
	
		function regresar(){
			var ctx = "${pageContext.request.contextPath}";
			var finalizoAtencionMedica = $("#finalizoAtencionMedica").val();
			if(!finalizoAtencionMedica){
				var res = confirm("No ha finalizado la atencion, ¿desea regresar al menu?");
				if (res) {
					window.location.href = ctx+"/menu";
					return false;
				}
			}
		}	
		
		function finalizar(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			var finalizarAtencion = $("#finalizarAtencion").val();
			if(finalizarAtencion == "true"){
				window.location.href = ctx+"/finalizarAtencion/finalizarAtencion?idAgenda="+idAgenda;	
			}else{
				alert("Debe de llenar la Nota medica y la seccion de Signos Vitales");
			}
						
		}
	
		function prestaciones(){
			$("#listaPacientesForm").attr("action", "prestaciones");
			$("#listaPacientesForm").submit();
		}
		
		function solicitudReferencia(){
			$("#listaPacientesForm").attr("action", "solicitudReferencia");
			$("#listaPacientesForm").submit();
		}
		
		function diagnosticos(){
			$("#listaPacientesForm").attr("action", "diagnosticos");
			$("#listaPacientesForm").submit();
		}
		
		function recetaMedica(){
			$("#listaPacientesForm").attr("action", "recetaMedica");
			$("#listaPacientesForm").submit();
		}
		
		function licenciaMedica(){
			$("#listaPacientesForm").attr("action", "licenciaMedica");
			$("#listaPacientesForm").submit();
		}
		
		function estudiosMedicos(id){
			$("#idEstudios").val(id);
			$("#listaPacientesForm").attr("action", "estudiosMedicos");
			$("#listaPacientesForm").submit();
		}	
		
		function contanciaDeAsistencia(){
			$("#listaPacientesForm").attr("action", "certificados");
			$("#listaPacientesForm").submit();			
		}
		
		$("#diagnostico").autocomplete({
		    serviceUrl: "cies10",
	 	    paramName: "busqueda",
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },
		    onSelect: function (suggestion) {
		    	$("#idDiagnostico").val(suggestion.data);
		    },
		    onInvalidateSelection:function() {
		    	$("#idDiagnostico").val("");
	        }
		});
	</script>
</body>
</html>