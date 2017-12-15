<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<body>

	<form:form name='finalizarAtencionForm' action="finalizar" method='POST' commandName="finalizarAtencionForm" id="finalizarAtencionForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="rfc" id="rfc"/>
		<form:hidden path="nombre" id="nombre"/>
		<form:hidden path="apellidoPaterno" id="apellidoPaterno"/>
		<form:hidden path="apellidoMaterno" id="apellidoMaterno"/>
		<form:hidden path="sexo" id="sexo"/>
		<form:hidden path="fechaDeNacimiento" id="fechaDeNacimiento"/>
		<form:hidden path="estado" id="estado"/>
		<form:hidden path="idUsuario" id="idUsuario"/>
		<form:hidden path="huellaAfiliado" id="huellaAfiliado"/>
		<form:hidden path="huellaMedico" id="huellaMedico"/>
		<form:hidden path="idAfiliado" id="idAfiliado"/>
		<form:hidden path="nombreAfiliado" id="nombreAfiliado"/>
		<form:hidden path="apellidoPaternoAfiliado" id="apellidoPaternoAfiliado"/>
		<form:hidden path="apellidoMaternoAfiliado" id="apellidoMaternoAfiliado"/>
		<form:hidden path="sexoAfiliado" id="sexoAfiliado"/>
		<form:hidden path="fechaDeNacimientoAfiliado" id="fechaDeNacimientoAfiliado"/>
		<form:hidden path="estadoAfiliado" id="estadoAfiliado"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${finalizarAtencionForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Diagnosticos:		
				</td>
				<td>
					<form:input path="diagnosticos" cssStyle="width:600px"/>		
				</td>				
			</tr>
			<tr>
				<td>
					Prestaciones:		
				</td>
				<td>
					<form:input path="prestaciones" cssStyle="width:600px"/>
					
					<!-- <a href="http://seb:8080/SAB/listaPacientes/prestaciones?idAgenda=${finalizarAtencionForm.idAgenda}">Editar</a> -->
				</td>				
			</tr>
			<tr>
				<td>
					Medicamentos:		
				</td>
				<td>
					<form:input path="medicamentos" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Receta Medica" onclick="imprimirRecetaMedica()" />
					</c:if>
					<!-- <a href="http://seb:8080/SAB/listaPacientes/recetaMedica?idAgenda=${finalizarAtencionForm.idAgenda}">Editar</a> -->
				</td>				
			</tr>	
			<tr>
				<td>
					Estudios Laboratorio:		
				</td>
				<td>
					<form:input path="estudiosLaboratorio" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Constancia Asistencia" onclick="constanciaAsistencia()" />
					</c:if>					
					<!-- <a href="http://seb:8080/SAB/listaPacientes/estudiosMedicos?idAgenda=${finalizarAtencionForm.idAgenda}&idEstudios=1">Editar</a> -->
				</td>				
			</tr>
			<tr>
				<td>
					Estudios Gabinete:		
				</td>
				<td>
					<form:input path="estudiosGabinete" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Constancia Salud" onclick="constanciaSalud()" />
					</c:if>										
					<!-- <a href="http://seb:8080/SAB/listaPacientes/estudiosMedicos?idAgenda=${finalizarAtencionForm.idAgenda}&idEstudios=2">Editar</a> -->
				</td>				
			</tr>
			<tr>
				<td>
					Otros Estudios:		
				</td>
				<td>
					<form:input path="otrosEstudios" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Licencia Medica" onclick="imprimirLicenciaMedica()" />
					</c:if>										
					<!-- <a href="http://seb:8080/SAB/listaPacientes/estudiosMedicos?idAgenda=${finalizarAtencionForm.idAgenda}&idEstudios=3">Editar</a> -->
					
				</td>				
			</tr>		
			<tr>
				<td>
					Licencia Medica:		
				</td>
				<td>
					<form:input path="licenciaMedica" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Constancia Cuidados Maternales" onclick="constanciaCuidadosMaternales()" />
					</c:if>						
					<!-- <a href="http://seb:8080/SAB/listaPacientes/licenciaMedica?idAgenda=${finalizarAtencionForm.idAgenda}">Editar</a> -->
				</td>				
			</tr>
			<tr>
				<td>
					Referencia Especialista:		
				</td>
				<td>
					<form:input path="referenciaEspecialista" cssStyle="width:600px"/>
					<c:if test="${finalizarAtencionForm.atencionFinalizada == true}">
						<input type="button" value="Estudios Laboratorio" onclick="imprimirEstudiosLaboratorio()" />
						<input type="button" value="Estudios Gabinete" onclick="imprimirEstudiosGabinete()" />
						<input type="button" value="Estudios Otros" onclick="imprimirEstudiosOtros()" />
						<input type="button" value="Solicitud Referencia" onclick="imprimirSolicitudReferencia()" />
						<input type="button" value="Solicitud Contrareferencia" onclick="imprimirSolicitudContrareferencia()" />												
					</c:if>
					<!-- <a href="http://seb:8080/SAB/listaPacientes/solicitudReferencia?idAgenda=${finalizarAtencionForm.idAgenda}">Editar</a> -->
				</td>				
			</tr>	
			<tr>
				<td>
					Contrareferencia:		
				</td>
				<td>
					<form:input path="contrareferenciaS" cssStyle="width:600px"/><!-- <a href="http://seb:8080/SAB/listaPacientes/contrareferencia?idAgenda=${finalizarAtencionForm.idAgenda}">Editar</a> -->
				</td>				
			</tr>																							
			<tr>
				<td colspan="2">
					<c:choose>
						<c:when test="${finalizarAtencionForm.atencionFinalizada == true}">
							<input type="button" value="Imprimir Todo" onclick="imprimirTodo()" />
						</c:when>
						<c:otherwise>
							<input type="button" value="Finalizar Atencion" onclick="autenticar()" />
						</c:otherwise>
					</c:choose>
					<input type="button" value="Imprimir Todo" onclick="imprimirTodo()" />
					<a href="/SAB/menu">Regresar al Menu</a>
				</td>				
			</tr>																											
		</table>
		<div id="dialog-constancia-asistencia" title="Constancia de asistencia"> </div>
		<div id="dialog-constancia-salud" title="Constancia de salud"> </div>
		<div id="dialog-constancia-maternales" title="Constancia de Cuidados Maternales"> </div>
	</form:form>
	
	<script type="text/javascript">
		$(function() {
			dialog = $("#dialog-constancia-asistencia").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});
	
			dialogSalud = $("#dialog-constancia-salud").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});	
			
			dialogMaternales = $("#dialog-constancia-maternales").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});				
		});
		
		function constanciaCuidadosMaternales(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-maternales").empty();
			$("#dialog-constancia-maternales").append("<object type='text/html' data='"+ctx+"/certificados/constanciaCuidadosMaternales?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialogMaternales.dialog( "open" );
		}
		
		function constanciaAsistencia(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-asistencia").empty();
			$("#dialog-constancia-asistencia").append("<object type='text/html' data='"+ctx+"/certificados/constanciaDeAsistencia?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialog.dialog( "open" );
		}
		
		function constanciaSalud(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-salud").empty();
			$("#dialog-constancia-salud").append("<object type='text/html' data='"+ctx+"/certificados/constanciaDeSalud?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialogSalud.dialog( "open" );
		}	
		
		function prestacionesF(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			window.location.href = ctx+"/listaPacientes/prestaciones?idAgenda="+idAgenda;
		}
		
		function solicitudReferencia(){
			$("#finalizarAtencionForm").attr("action", "solicitudReferencia");
			$("#finalizarAtencionForm").submit();
		}
		
		function diagnosticos(){
			$("#finalizarAtencionForm").attr("action", "diagnosticos");
			$("#finalizarAtencionForm").submit();
		}
		
		function imprimirRecetaMedica(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirRecetaMedica?idAgenda="+idAgenda;			
		}
		
		function imprimirLicenciaMedica(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirLicenciaMedica?idAgenda="+idAgenda;
		}
		
		function imprimirEstudiosLaboratorio(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirEstudiosLaboratorio?idAgenda="+idAgenda;
		}	
		
		function imprimirEstudiosGabinete(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirEstudiosGabinete?idAgenda="+idAgenda;
		}	
		
		function imprimirEstudiosOtros(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirEstudiosOtros?idAgenda="+idAgenda;
		}	
		
		function imprimirSolicitudReferencia(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirSolicitudReferencia?idAgenda="+idAgenda;
		}	
		
		function imprimirSolicitudContrareferencia(){
			var ctx = "${pageContext.request.contextPath}";
			var idAgenda = $("#idAgenda").val();
			location.href = ctx+"/certificados/imprimirSolicitudContrareferencia?idAgenda="+idAgenda;
		}		
		
		function estudiosMedicos(id){
			$("#idEstudios").val(id);
			$("#finalizarAtencionForm").attr("action", "estudiosMedicos");
			$("#finalizarAtencionForm").submit();
		}	
		
		function contanciaDeAsistencia(){
			$("#finalizarAtencionForm").attr("action", "certificados");
			$("#finalizarAtencionForm").submit();			
		}
		
		function contrareferencia(){
			$("#finalizarAtencionForm").attr("action", "contrareferencia");
			$("#finalizarAtencionForm").submit();
		}
		
		function autenticar(){
			var rfc = $("#rfc").val();
			var nombre = $("#nombre").val();
			var apellidoPaterno = $("#apellidoPaterno").val();
			var apellidoMaterno = $("#apellidoMaterno").val();
			var sexo = $("#sexo").val();
			var fechaDeNacimiento = $("#fechaDeNacimiento").val();
			var estado = $("#estado").val();
			var idUsuario = $("#idUsuario").val();
			var huellaAfiliado = $("#huellaAfiliado").val();
			var huellaMedico = $("#huellaMedico").val();
			var idAfiliado = $("#idAfiliado").val();
			var nombreAfiliado = $("#nombreAfiliado").val();
			var apellidoPaternoAfiliado = $("#apellidoPaternoAfiliado").val();
			var apellidoMaternoAfiliado = $("#apellidoMaternoAfiliado").val();
			var sexoAfiliado = $("#sexoAfiliado").val();
			var fechaDeNacimientoAfiliado = $("#fechaDeNacimientoAfiliado").val();
			var estadoAfiliado = $("#estadoAfiliado").val();			
			if (huellaMedico) {
				if (IniciarUsuario(idUsuario, 0, 0, rfc, "RFC", nombre, apellidoPaterno , apellidoMaterno, sexo, fechaDeNacimiento, estado)) {
					if (huellaAfiliado) {
						if (IniciarUsuario(idUsuario, 0, 0, idAfiliado, "AfiliadoId", nombreAfiliado, apellidoPaternoAfiliado , apellidoMaternoAfiliado, sexoAfiliado, fechaDeNacimientoAfiliado, estadoAfiliado)) {
							$("#finalizarAtencionForm").submit();
						}else{
							alert("no paso la autenticacion de huella");	
						}	
					}					
				}else{
					alert("no paso la autenticacion de huella");	
				}	
			}
			
		}		
		
		function imprimirTodo(){
			$("#finalizarAtencionForm").attr("action", "imprimirTodo");
			$("#finalizarAtencionForm").submit();			
		}
	</script>
</body>
</html>