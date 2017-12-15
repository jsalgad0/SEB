<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='estudiosMedicosForm' action="nuevoEstudioMedico" method='POST' commandName="estudiosMedicosForm" id="estudiosMedicosForm">
	<form:hidden path="idprestacion" id="idprestacion"/>
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="idEstudios" id="idEstudios"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${estudiosMedicosForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Prestaciones:		
				</td>			
				<td>
					<input type="text" autocomplete="off" id="prestacion" style="width: 700px" />
					<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
						<input type="button" value="Añadir" id="agregar" onclick="agregarPrestacion()" disabled >
					</c:if>
	
				</td>
			</tr>
			<tr>
				<td>
					<table id="tablaPrestacion">
						<c:forEach items="${estudiosMedicosForm.prestacionesAutocompleteVos}" var="autocompleteVo" varStatus="varStatus" >
							<tr id="${varStatus.index}">
								<td><input type="hidden" id="autocompleteVos${autocompleteVo.data}" value="x"/><input type="hidden" name="prestacionesAutocompleteVos[${varStatus.index}].value" value="${autocompleteVo.value}"/><input type="hidden" name="prestacionesAutocompleteVos[${varStatus.index}].data" value="${autocompleteVo.data}" />${autocompleteVo.value} </td>
								<td>
									<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
										<input type="button" value="borrar" onclick="borrarPrestacion(${varStatus.index})"/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>				
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
						<input type="submit" value="Guardar" />
					</c:if>
					<input type="button" value="Regresar" onclick="regresar();" />	
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
			$("#estudiosMedicosForm").attr("action", "atencionMedica");
			$("#estudiosMedicosForm").submit();
		}
		
		function borrarPrestacion(id){
			$("table#tablaPrestacion tr#"+id).remove();
		}
		
		function agregarPrestacion(){
			var idprestacion = $("#idprestacion").val();
			var prestacion = $("#prestacion").val();
			if($("#autocompleteVos"+idprestacion).val() === undefined){
				var rows = $("#tablaPrestacion tr").length;
				if(rows!=0){
					var myElem = document.getElementById(rows);
					while(myElem!=null){
						rows++;
					}	
				}
				
		    	var filas = '<tr id='+rows+'><td><input type="hidden" id="autocompleteVos'+idprestacion+'" value="x"/><input type="hidden" name="prestacionesAutocompleteVos['+rows+'].value" value="'+prestacion+'"/><input type="hidden" name="prestacionesAutocompleteVos['+rows+'].data" value="'+idprestacion+'"/>'+prestacion+'</td><td><input type="button" value="borrar" onclick="borrarPrestacion('+rows+')"/> </td></tr>';		
				$("#tablaPrestacion").append(filas);
				$("#agregar").prop( "disabled", true );
				$("#prestacion").val("");
			}else{
				alert("Ya existe la prestacion");
				$("#agregar").prop( "disabled", true );
				$("#prestacion").val("");
			}	
		}
    	
    	function inicializaAutocomplete(){
     		$("#prestacion").autocomplete({
    			source: "prestacionEstudios?idAtencion="+$("#idAtencion").val(),
    	 	   	minLength: 1,
                select: function( event, ui ){
                	if (ui.item.value!="-1") {
               	    	$("#prestacion").val(ui.item.label);
               	    	$("#idPrestacion").val(ui.item.value);
               	    	$("#agregar").prop( "disabled", false );
    				}else{
               	    	$("#prestacion").val("");
               	    	$("#idPrestacion").val("");
    				}
                    return false;
    			},
    			focus: function (event, ui) {
    				if (ui.item.value!="-1") {
    					$("#prestacion").val(ui.item.label);
    				}else{
               	    	$("#prestacion").val("");
               	    	$("#idPrestacion").val("");
    				}
    				return false;
                }
    		});  		
    	}    	
	
    	$(document).ready(function(){
    		$.ajaxSetup({ cache: false });
    		inicializaAutocomplete();
    	});
	</script>
</body>
</html>