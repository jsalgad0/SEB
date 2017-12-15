<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>


	<form:form name='recetaMedicaForm' action="nuevaRecetaMedica" method='POST' commandName="recetaMedicaForm" id="recetaMedicaForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="idMedicamento" id="idMedicamento"/>
		<form:hidden path="stock" id="stock"/>
		<form:hidden path="idReceta" id="idReceta"/>
		<form:hidden path="finalizoAtencionMedica" id="finalizoAtencionMedica"/>
		
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${listaPacientesForm.error}"></c:out><span id="errores"></span>
				</td>				
			</tr>		
			<tr>
				<td>
					Medicamento:		
				</td>
				<td>
					<form:input path="medicamento" autocomplete="off" id="medicamento" cssClass="autocomplete" cssStyle="width:700px" /><input type="button" onclick="borrar()" value="borrar" />	
				</td>				
			</tr>
			<tr>
				<td>
					Via de administracion:		
				</td>
				<td>
					<form:select path="IdViaDeAdmonMedicamento">
						<form:options items="${catviasdeadminmedicamentos}" itemLabel="viaDeAdmonMedicamento" itemValue="viaDeAdmonMedicamentoId"/>
					</form:select>	
				</td>				
			</tr>
			<tr>
				<td>
					Cantidad:		
				</td>
				<td>
					<form:input path="medicamentosreceta.cantidadUnidades" id="cantidadUnidades"/>		
				</td>				
			</tr>
			<tr>
				<td>
					Indicaciones:		
				</td>
				<td>
					<form:input path="medicamentosreceta.cantidadToma" id="cantidadToma"/>		
				</td>				
			</tr>
			<tr>
				<td>
					Unidad:		
				</td>
				<td>
					<form:input path="medicamentosreceta.unidad"/>	
				</td>				
			</tr>	
			<tr>
				<td>
					Cada:		
				</td>
				<td>
					<form:input path="medicamentosreceta.frecuenciaDeToma" id="frecuenciaDeToma"/>		
				</td>				
			</tr>	
			<tr>
				<td>
					Frecuencia Unidad De Tiempo:		
				</td>
				<td>		
					<form:select path="idUnidadTiempoFrecuencia">
						<form:options items="${catUnidadesDeTiempoMenorDia}" itemLabel="unidadTiempo" itemValue="unidadTiempoId"/>
					</form:select>
				</td>				
			</tr>		
			<tr>
				<td>
					Durante:		
				</td>
				<td>
					<form:input path="medicamentosreceta.duracionDeToma" id="duracionDeToma" />		
				</td>				
			</tr>	
			<tr>
				<td>
					Duracion Unidad De Tiempo:		
				</td>
				<td>		
					<form:select path="idUnidadTiempoDuracion">
						<form:options items="${catUnidadesDeTiempoMayorDia}" itemLabel="unidadTiempo" itemValue="unidadTiempoId"/>
					</form:select>
				</td>				
			</tr>
			<tr>
				<td>
					Observaciones:		
				</td>
				<td>		
					<form:input path="medicamentosreceta.observaciones" />
				</td>				
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${recetaMedicaForm.editar == false}">
						<c:if test="${recetaMedicaForm.finalizoAtencionMedica == false}">
							<input type="button" value="+" onclick="enviar();" />
						</c:if>
					</c:if>
					<c:if test="${recetaMedicaForm.editar == true}">
						<c:if test="${recetaMedicaForm.finalizoAtencionMedica == false}">
							<input type="button" value="Editar" onclick="enviarEdicion();" />
						</c:if>
					</c:if>					
				</td>				
			</tr>
			<c:if test="${recetaMedicaForm.editar == false}">
				<tr>
					<td colspan="2">
						<table>
							<c:forEach items="${medicamentosrecetas}" var="medicamentosreceta" >
								<tr>
									<td>
										${medicamentosreceta.cantidadUnidades}
									</td>
									<td>
										${medicamentosreceta.observaciones}
									</td>
									<td>
										<a href="#" onclick="editarReceta(${medicamentosreceta.recetas.recetaId}); false;">Editar</a>
									</td>
									<td>
										<c:if test="${recetaMedicaForm.finalizoAtencionMedica == false}">
											<a href="#" onclick="borrarReceta(${medicamentosreceta.recetas.recetaId}); false;">Borrar</a>
										</c:if>				
									</td>								
								</tr>		
							</c:forEach>
						</table>
					</td>				
				</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<a href="/SAB/menu">Regresar al Menu</a>		
				</td>				
			</tr>																									
		</table>
	</form:form>
	

	
	<div id="dialog-form" title="Medicamentos"> 
	</div>
	<script type="text/javascript">
		$(function() {
			dialog = $("#dialog-form").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});
		});
		
		$(".autocomplete").devbridgeAutocomplete({
		    serviceUrl: "medicamentos",
	 	    paramName: "busqueda",
	 	   	minChars: 3,
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },	
		    onSelect: function (suggestion) {
		    	$("#idMedicamento").val(suggestion.data);
		    	abrirDialog();
		    },
		    onInvalidateSelection:function() {
		    	$("#idMedicamento").val("0");
	        }
		});
		
		function abrirDialog(){
			var idMedicamento = $("#idMedicamento").val();
			var errorWs = false;
		    $.getJSON("seleccionMedicamentosRevision", {idMedicamento:idMedicamento} ,function(response){ 
		    	alert(response);
		    	alert(response.errorWs);
		    	errorWs = response.errorWs;
			    if(!errorWs){
					$("#dialog-form").empty();
					$("#dialog-form").append("<object type='text/html' data='seleccionMedicamentos?idMedicamento="+idMedicamento+"' width='100%' height='100%'></object>");
					dialog.dialog( "open" );		    	
			    }
		    });
		}
		
		function closeIframe(){
		    $("#dialog-form").dialog('close');
		    $("#dialog-form").empty();
		}
		
		function borrar(){
			$("#idMedicamento").val("0");
			$("#medicamento").val("");
			$("#stock").val("0");
		}
		
		function regresar(){
			$("#recetaMedicaForm").attr("action", "regresarAtencionMedica");
			$("#recetaMedicaForm").submit();
		}
		
		function enviar(){
			var idMedicamento = $("#idMedicamento").val();
			var stock = $("#stock").val();
			$("#errores").empty();
			if(idMedicamento == "0"){
				$("#errores").append("debe de seleccionar un medicamento</br>"); 
			}else{
				if(stock == 0){
					var r = confirm("El medicamento no se encuentra disppnible, ¿seguro que desea agregarlo?");
					if(r == true){
						$("#recetaMedicaForm").submit();
					}
				}else{
					$("#recetaMedicaForm").submit();
				}	
			}
			
		}
		
		function borrarReceta(id){
			$("#idReceta").val(id);
			$("#recetaMedicaForm").attr("action", "borrar");
			$("#recetaMedicaForm").submit();
		}
		
		function editarReceta(id){
			$("#idReceta").val(id);
			$("#recetaMedicaForm").attr("action", "editar");
			$("#recetaMedicaForm").submit();
		}		
		
		function enviarEdicion(){
			$("#recetaMedicaForm").attr("action", "editarReceta");
			$("#recetaMedicaForm").submit();			
		}
			
		$("#duracionDeToma").numeric();
		$("#frecuenciaDeToma").numeric();
		$("#cantidadUnidades").numeric();
		$("#cantidadToma").numeric();
	</script>
</body>
</html>