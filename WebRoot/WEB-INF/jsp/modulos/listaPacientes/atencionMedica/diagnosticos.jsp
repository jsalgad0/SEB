<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='listaPacientesForm' action="nuevoDiagnostico" method='POST' commandName="listaPacientesForm" id="listaPacientesForm">
		<form:hidden path="idDiagnosticoAux" id="idDiagnosticoAux"/>
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="editar" id="editar"/>
		<form:hidden path="notamedica.notaMedicaId" id="notaMedicaId"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${listaPacientesForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					diagnostico:		
				</td>			
				<td>
					<input type="text" autocomplete="off" id="diagnosticoAux" style="width: 700px" />
					<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
						<input type="button" value="Añadir" id="agregar" onclick="agregarDiagnostico()" disabled >
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<table id="tablaDiagnostico">
						<c:forEach items="${listaPacientesForm.autocompleteVos}" var="autocompleteVo" varStatus="varStatus" >
							<tr id="${varStatus.index}">
								<td><input type="hidden" id="autocompleteVos${autocompleteVo.data}" value="x"/><input type="hidden" name="autocompleteVos[${varStatus.index}].value" value="${autocompleteVo.value}"/><input type="hidden" name="autocompleteVos[${varStatus.index}].data" value="${autocompleteVo.data}" />${autocompleteVo.value} </td>
								<td>
									<c:if test="${estudiosMedicosForm.finalizoAtencionMedica == false}">
										<input type="button" value="borrar" onclick="borrarDiagnostico(${varStatus.index})"/>
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
		
		function borrarDiagnostico(id){
			$("table#tablaDiagnostico tr#"+id).remove();
		}
		
		function agregarDiagnostico(){
			var idDiagnosticoAux = $("#idDiagnosticoAux").val();
			var diagnosticoAux = $("#diagnosticoAux").val();
			if($("#autocompleteVos"+idDiagnosticoAux).val() === undefined){
				var rows = $("#tablaDiagnostico tr").length;
				if(rows!=0){
					var myElem = document.getElementById(rows);
					while(myElem!=null){
						rows++;
					}	
				}
				
		    	var filas = '<tr id='+rows+'><td><input type="hidden" id="autocompleteVos'+idDiagnosticoAux+'" value="x"/><input type="hidden" name="autocompleteVos['+rows+'].value" value="'+diagnosticoAux+'"/><input type="hidden" name="autocompleteVos['+rows+'].data" value="'+idDiagnosticoAux+'"/>'+diagnosticoAux+'</td><td><input type="button" value="borrar" onclick="borrarDiagnostico('+rows+')"/> </td></tr>';		
				$("#tablaDiagnostico").append(filas);
				$("#agregar").prop( "disabled", true );
				$("#diagnosticoAux").val("");
			}else{
				alert("Ya existe el diagnostico");
				$("#agregar").prop( "disabled", true );
				$("#diagnosticoAux").val("");
			}	
		}
		
    	$("#diagnosticoAux").autocomplete({
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
    	    	$("#diagnosticoAux").val(suggestion.value);
    	    	$("#idDiagnosticoAux").val(suggestion.data);
    	    	$("#agregar").prop( "disabled", false );
    	    },
		    onInvalidateSelection:function() {
		    	$("#idDiagnosticoAux").val("");
	        }
    	});
	
	</script>
</body>
</html>