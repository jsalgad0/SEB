<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='solicitudReferenciaForm' action="nuevoSolicitudReferencia" method='POST' commandName="solicitudReferenciaForm" id="solicitudReferenciaForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="editar" id="editar"/>
		<form:hidden path="finalizoAtencionMedica" id="finalizoAtencionMedica"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${solicitudReferenciaForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Motivo de la referencia:		
				</td>			
				<td>
					<form:input path="solicitudreferencia.motivoReferencia"/>
				</td>
			</tr>
			<tr>
				<td>
					Referencia Motivos:		
				</td>	
				<td>
					<form:radiobuttons path="idCatSoliReferenciaMotivos" items="${catSoliReferenciaMotivos}" itemLabel="soliReferenciaMotivo" itemValue="soliReferenciaMotivoId"/>
				</td>
			</tr>
			<tr>
				<td>
					Lugares:		
				</td>	
				<td>
					<form:select path="idLugarAtencion">
						<form:option value="-1" label="Seleccione un lugar" />
						<form:options items="${lugaresAtencion}" itemLabel="descripcion" itemValue="lugarDeAtencionId" />
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>
					Especialidad:		
				</td>	
				<td>
					<form:select path="idEspeciaidad">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
				</td>
			</tr>					
			<tr>
				<td>
					Motivo Del Envio:		
				</td>			
				<td>
					<form:textarea path="solicitudreferencia.motivoDelEnvio"/>
				</td>
			</tr>	
			<tr>
				<td>
					Resultados Laboratorio:		
				</td>			
				<td>
					<form:textarea path="solicitudreferencia.resultadosLaboratorio"/>
				</td>
			</tr>	
			<tr>
				<td>
					Refrerencia Por:		
				</td>	
				<td>
					<form:radiobuttons path="idCatSoliRefrerenciaPor" items="${catSoliRefrerenciaPor}" itemLabel="descripcion" itemValue="referenciaPorId"/>
				</td>
			</tr>
			<tr>
				<td>
					fecha inicio:		
				</td>			
				<td>
					<form:input path="fechaInicio" readonly="true" />
				</td>
			</tr>	
			<tr>
				<td>
					fecha fin:		
				</td>			
				<td>
					<form:input path="fechaFin" readonly="true" />
				</td>
			</tr>														
			<tr>
				<td colspan="2">
					<c:if test="${solicitudReferenciaForm.finalizoAtencionMedica == false}">
						<input type="submit" value="Guardar" />
					</c:if>
					<input type="button" value="Regresar" onclick="regresar();" />	
					<c:if test="${solicitudReferenciaForm.editar == true}">
						<input type="button" value="Contrareferencia" onclick="contrareferencia();" />
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

	<script type="text/javascript">
		
		function regresar(){
			$("#solicitudReferenciaForm").attr("action", "atencionMedica");
			$("#solicitudReferenciaForm").submit();
		}
		
		function contrareferencia(){
			$("#solicitudReferenciaForm").attr("action", "contrareferencia");
			$("#solicitudReferenciaForm").submit();
		}
		
	</script>
</body>
</html>