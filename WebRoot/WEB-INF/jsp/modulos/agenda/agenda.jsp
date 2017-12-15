<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<form:form name='agendaForm' action="recepcionPresencial" method='POST'
		commandName="agendaForm" id="agendaForm">
		<input type="hidden" id="busquedaMAux" value="${agendaForm.busquedaM}">
		<input type="hidden" id="busquedaAAux" value="${agendaForm.busquedaA}">
		<input type="hidden" id="busquedaFAux" value="${agendaForm.busquedaF}">
		<input type="hidden" id="busquedaEAux" value="${agendaForm.busquedaE}">
		<form:hidden path="idAgenda" id="idAgenda"/>
		busqueda Medico:
		<form:select path="busquedaM" id="busquedaM" cssClass="chosen-select"
			onchange="this.form.submit()">
			<form:option value="-1" label="-Selecciona un Medico-" />
			<form:options items="${medicos}" itemLabel="nombre"
				itemValue="usuarioId"></form:options>
		</form:select>		
		busqueda Apellido Paterno:
		<form:input path="busquedaA" id="busquedaA" />
		busqueda Fecha:
		<form:input path="busquedaF" id="busquedaF" readonly="true" />
		busqueda Estatus:
		<form:select path="busquedaE" id="busquedaE" cssClass="chosen-select">
			<form:option value="0" label="Ausente" />
			<form:option value="1" label="Presente" />
			<form:option value="" label="Todos" />
		</form:select>
		<input type="submit" value="Buscar" />
		<table id="tablaAgenda">
			<thead>
				<tr>
					<td>Nombre Paciente</td>
					<td>Consultorio</td>
					<td>Hora cita</td>
					<td>Asistio</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${agendas}" var="agenda">
					<tr>
						<td>${agenda.afiliado.nombre}
							${agenda.afiliado.apellidoPaterno}
							${agenda.afiliado.apellidoMaterno}</td>
						<td>${agenda.consultorio}</td>
						<td>${agenda.horaCita}</td>
						<td>No<input type="checkbox" onclick="abrirDialog(${agenda.agendaId});"/>/Si<input type="checkbox" onclick="enviarRecepcionPresencial(${agenda.agendaId});">${agenda.asistio}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div id="paginador"></div>
		<a href="/SAB/menu">Regresar al Menu</a>
	</form:form>

	<div id="dialog-form" title="Acompañantes  o Personas de Confianza Registradas"> 
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".chosen-select").chosen({
				no_results_text : "No se encontraron resultados"
			});
			$("#busquedaF").datepicker({
				dateFormat : "dd/mm/yy"
			});
		});
		
		function abrirDialog(idAgenda){
			$("#idAgenda").val(idAgenda);
			$("#dialog-form").empty();
			$("#dialog-form").append("<object type='text/html' data='inicioPersonaConfianza?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialog.dialog( "open" );
		}
		
		function enviarRecepcionPresencial(idAgenda){
			$("#idAgenda").val(idAgenda);
			$("#agendaForm").submit();
		}

		$(function() {
			
			dialog = $("#dialog-form").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});
			
			$("#paginador").paginate(
			{
				count : ${agendaForm.count},
				start : 1,
				display : ${agendaForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				images : false,
				mouse : 'press',
				onChange : function(page) {
					var busquedaM = $("#busquedaMAux").val();
					var busquedaA = $("#busquedaAAux").val();
					var busquedaF = $("#busquedaFAux").val();
					var busquedaE = $("#busquedaEAux").val();
					$.getJSON("paginador", {
						busquedaM : busquedaM,
						busquedaA : busquedaA,
						busquedaF : busquedaF,
						busquedaE : busquedaE,
						page : page
					}, function(response) {
						$("#tablaAgenda > tbody").empty();
						var filas = "";
						$.each(response, function(index, item) {
							filas += '<tr><td>' + item.afiliado.nombre + ' ' + item.afiliado.apellidoPaterno + ' ' + item.afiliado.apellidoMaterno + '</td>';
							filas += '<td>' + item.consultorio + '</td><td>' + item.horaCita + '</td>';
							filas += '<td>No<input type="checkbox" onclick="abrirDialog(' + item.agendaId + ');"/>/Si<input type="checkbox" onclick="enviarRecepcionPresencial(' + item.agendaId + ');"/>'+item.asistio+'</td></tr>';
							$("#tablaAgenda tbody").append(filas);
							filas = "";
						});
					});
				}
			});
		});
		

	</script>
</body>
</html>