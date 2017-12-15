<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>

<script type="text/javascript">
	function editar(){
		$("#conveniosForm").attr("action", "editar");
		$("#conveniosForm").submit();
	}
	
	function generarNombre(){
		var idPrestadores = $("#idPrestadores").val();
		var idAseguradores = $("#idAseguradores").val();
		var idLugarDeAtencion = $("#idLugarDeAtencion").val();
		if(idPrestadores!=-1 && idAseguradores!=-1 && idLugarDeAtencion!=-1){
		    $.getJSON("generarNombre", {idPrestadores:idPrestadores, idAseguradores:idAseguradores, idLugarDeAtencion:idLugarDeAtencion} ,function(response){
		    	$("#convenio").val(response);
		    });			
		}else{
			alert("Favor de llenar los campos previos");	
		}
	}
	
	function verificarElegir(){	
		$("#divElegir").show();
		$("#divCargar").hide();
		$("#divGenerar").hide();
	}
	
	function verificarCargar(){
		$("#divElegir").hide();
		$("#divCargar").show();
		$("#divGenerar").hide();	
	}
	
	function verificarGenerar(){
		$("#divElegir").hide();
		$("#divCargar").hide();
		$("#divGenerar").show();			
	}	
	
	$(document).ready(function(){
		$(".chosen-select").chosen({no_results_text: "No se encontraron resultados", width:"100%" });
		$( "#vigenteDesde" ).datepicker({dateFormat: "dd/mm/yy"});
		$( "#vigenteHasta" ).datepicker({dateFormat: "dd/mm/yy"});
		
		if($("#checkElegir").is(":checked")){
			verificarElegir();
		}
		if($("#checkCargar").is(":checked")){
			verificarCargar();
		}
		if($("#checkGenerar").is(":checked")){
			verificarGenerar();
		}
	});
	
</script>

<body>

	<form:form name='conveniosForm' action="nuevo" method='POST' commandName="conveniosForm" id="conveniosForm" enctype="multipart/form-data">
		<form:hidden path="convenios.convenioId"/>
		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /><c:out value="${cuadroPrestacionesForm.error}"></c:out> </td>
			</tr>
			<tr>
				<td>Prestador:</td>
				<td>
					<form:select path="idPrestadores" id="idPrestadores" cssClass="chosen-select">
						<form:option value="-1" label="-Selecciona un Prestador-" />
						<form:options items="${prestadores}" itemLabel="nombreRazonSocial" itemValue="prestadorId" ></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Asegurador:</td>
				<td>
					<form:select path="idAseguradores" id="idAseguradores" cssClass="chosen-select">
						<form:option value="-1" label="-Selecciona un Asegurador-" />
						<form:options items="${aseguradores}" itemLabel="nombreRazonSocial" itemValue="aseguradorId" ></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Lugar de atencion:</td>
				<td>
					<form:select path="idLugarDeAtencion" id="idLugarDeAtencion" cssClass="chosen-select">
						<form:option value="-1" label="-Selecciona un Lugar de Atencion-" />
						<form:options items="${lugaresatencion}" itemLabel="descripcion" itemValue="lugarDeAtencionId" ></form:options>
					</form:select>
				</td>
			</tr>						
			<tr>
				<td>Nombre:</td>
				<td><form:input path="convenios.convenio" id="convenio" readonly="true" /><input type="button" value="Generar Nombre" onclick="generarNombre();" /> </td>
			</tr>
			<tr>
				<td>Vigente desde:</td>
				<td><form:input path="vigenteDesde" id="vigenteDesde" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Vigente hasta:</td>
				<td><form:input path="vigenteHasta" id="vigenteHasta" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Cuadro de prestaciones:</td>
				<td>
					<form:radiobutton path="opcionCuadroPrestaciones" id="checkElegir" value="E" label="Elegir" onclick="verificarElegir()"/>
					<form:radiobutton path="opcionCuadroPrestaciones" id="checkCargar" value="C" label="Cargar" onclick="verificarCargar()"/>
					<form:radiobutton path="opcionCuadroPrestaciones" id="checkGenerar" value="G" label="Generar" onclick="verificarGenerar()"/>
				</td>
			</tr>	
		</table>
				
		<div id="divElegir" style="display: none">
			<table>
				<tr>
					<td>Cuadro de prestacion:</td>
					<td>
						<form:select path="idCuadroPrestacion" id="idCuadroPrestacion" cssClass="chosen-select">
							<form:option value="-1" label="-Selecciona un Cuadro de Prestacion-" />
							<form:options items="${cuadroPrestaciones}" itemLabel="cuadroPrestacion" itemValue="cuadroPrestacionId" ></form:options>
						</form:select>
					</td>
				</tr>
			</table>	
		</div>
		<div id="divCargar" style="display: none">
			<table>
				<tr>
					<td>Nombre:</td>
					<td><form:input path="cuadroprestaciones.cuadroPrestacion" /></td>
				</tr>
				<tr>
					<td>Archivo:</td>
					<td><input type="file" value="file" name="file"></td>
				</tr>
			</table>
		</div>		
		<div id="divGenerar" style="display: none">
			<table>
				<tr>
					<td>Nombre:</td>
					<td><form:input path="nombrecuadroPrestacion" /></td>
				</tr>
			</table>
		</div>	
		<br/>
		<input type="submit" value="Gaurdar"/>						
	</form:form>

</body>
</html>