<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>

	<form:form name='contrareferenciaForm' action="nuevoContrareferencia" method='POST' commandName="contrareferenciaForm" id="contrareferenciaForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="editar" id="editar"/>
		<form:hidden path="idDiagnostico" id="idDiagnostico"/>
		<form:hidden path="contrareferencia.contrareferenciaId" id="idContrareferencia"/>
		<form:hidden path="finalizoAtencionMedica" id="finalizoAtencionMedica"/>
		<table>
			<tr>
				<td colspan="2">
					<form:errors path="error" /><c:out value="${contrareferenciaForm.error}"></c:out> 		
				</td>				
			</tr>		
			<tr>
				<td>
					Fecha:		
				</td>			
				<td>
					<form:input path="fecha" id="fecha" readonly="true" />
				</td>
			</tr>
			<tr>
				<td>
					Lugar:		
				</td>	
				<td>
					<form:input path="lugarAtencion"/>
				</td>
			</tr>
			<tr>
				<td>
					Motivo:		
				</td>	
				<td>
					<form:input path="contrareferencia.motivoContrareferencia"/>
				</td>
			</tr>		
			<tr>
				<td>
					Total Interconsultas:		
				</td>	
				<td>
					<form:input path="contrareferencia.numInterConsultas" id="numInterConsultas"/>
				</td>
			</tr>
			<tr>
				<td>
					Total de Consultas
				</td>	
				<td>
					<form:input path="contrareferencia.numConsultas" id="numConsultas"/>
				</td>
			</tr>						
			<tr>
				<td>
					Diagnostico:		
				</td>
				<td>
					<form:input path="diagnostico" autocomplete="off" id="diagnostico" cssClass="autocomplete" cssStyle="width:700px" />	
				</td>				
			</tr>
			<tr>
				<td>
					Resultados Valoracion:
				</td>	
				<td>
					<form:input path="contrareferencia.resultadosValoracion"/>
				</td>
			</tr>							
			<tr>
				<td>
					Indicaciones:
				</td>	
				<td>
					<form:input path="contrareferencia.indicaciones"/>
				</td>
			</tr>																			
			<tr>
				<td colspan="2">
					<c:if test="${contrareferenciaForm.finalizoAtencionMedica == false}">
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
		
		$(".autocomplete").devbridgeAutocomplete({
		    serviceUrl: "cies10",
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
		    	$("#idDiagnostico").val(suggestion.data);
		    },
		    onInvalidateSelection:function() {
		    	$("#idDiagnostico").val("0");
	        }
		});
		
		function regresar(){
			$("#estudiosMedicosForm").attr("action", "atencionMedica");
			$("#estudiosMedicosForm").submit();
		}
		
		$(document).ready(function(){
			$( "#fecha" ).datepicker({dateFormat: "dd/mm/yy"});
			$("#numInterConsultas").numeric();
			$("#numConsultas").numeric();
		});
		
	</script>
</body>
</html>