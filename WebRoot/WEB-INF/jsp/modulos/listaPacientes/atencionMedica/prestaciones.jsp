<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='listaPacientesForm' action="nuevoPrestacion" method='POST' commandName="listaPacientesForm" id="listaPacientesForm">
	<form:hidden path="idPrestacionAux" id="idPrestacionAux"/>
		<form:hidden path="idAgenda" id="idAgenda"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${listaPacientesForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Prestaciones:		
				</td>			
				<td>
					<input type="text" autocomplete="off" id="prestacionAux" style="width: 700px" />
					<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
						<input type="button" value="Añadir" id="agregar" onclick="agregarPrestacion()" disabled >
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<table id="tablaPrestacion">
						<c:forEach items="${listaPacientesForm.prestacionesAutocompleteVos}" var="autocompleteVo" varStatus="varStatus" >
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
					<c:if test="${listaPacientesForm.finalizoAtencionMedica == false}">
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
			$("#listaPacientesForm").attr("action", "atencionMedica");
			$("#listaPacientesForm").submit();
		}
		
		function borrarPrestacion(id){
			$("table#tablaPrestacion tr#"+id).remove();
		}
		
		function agregarPrestacion(){
			var idPrestacionAux = $("#idPrestacionAux").val();
			var prestacionAux = $("#prestacionAux").val();
			if($("#autocompleteVos"+idPrestacionAux).val() === undefined){
				var rows = $("#tablaPrestacion tr").length;
				if(rows!=0){
					var myElem = document.getElementById(rows);
					while(myElem!=null){
						rows++;
					}	
				}
				
		    	var filas = '<tr id='+rows+'><td><input type="hidden" id="autocompleteVos'+idPrestacionAux+'" value="x"/><input type="hidden" name="prestacionesAutocompleteVos['+rows+'].value" value="'+prestacionAux+'"/><input type="hidden" name="prestacionesAutocompleteVos['+rows+'].data" value="'+idPrestacionAux+'"/>'+prestacionAux+'</td><td><input type="button" value="borrar" onclick="borrarPrestacion('+rows+')"/> </td></tr>';		
				$("#tablaPrestacion").append(filas);
				$("#agregar").prop( "disabled", true );
				$("#prestacionAux").val("");
			}else{
				alert("Ya existe la prestacion");
				$("#agregar").prop( "disabled", true );
				$("#prestacionAux").val("");
			}	
		}
		
    	$("#prestacionAux").autocomplete({
		    serviceUrl: "catPrestaciones",
	 	    paramName: "busqueda",
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },
    	    onSelect: function (suggestion) {
    	    	$("#prestacionAux").val(suggestion.value);
    	    	$("#idPrestacionAux").val(suggestion.data);
    	    	$("#agregar").prop( "disabled", false );
    	    },
		    onInvalidateSelection:function() {
		    	$("#idPrestacionAux").val("");
	        }
    	});
	
	</script>
</body>
</html>