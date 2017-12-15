<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='listaPacientesForm' action="atencionMedica" method='POST'
		commandName="listaPacientesForm" id="listaPacientesForm">
		<input type="hidden" id="busquedaAAux" value="${listaPacientesForm.busquedaA}">
		<input type="hidden" id="busquedaEAux" value="${listaPacientesForm.busquedaE}">
		<form:hidden path="idAgenda" id="idAgenda"/>
		busqueda Apellido Paterno:
		<form:input path="busquedaA" id="busquedaA" />
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
					<td>Hora cita</td>
					<td>Hora llegada</td>
					<td>Asistio</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaPacientes}" var="agenda">
					<tr>
						<td>${agenda.afiliado.nombre}
							${agenda.afiliado.apellidoPaterno}
							${agenda.afiliado.apellidoMaterno}</td>
						<td>${agenda.horaCita}</td>
						<td>${agenda.atencionmedica.horaAsistio}</td>
						<td><a href="#" onclick="enviar(${agenda.agendaId}); false;">${agenda.asistio}</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div id="paginador"></div>
		<a href="/SAB/menu">Regresar al Menu</a>
	</form:form>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".chosen-select").chosen({
				no_results_text : "No se encontraron resultados"
			});
		});
		
		function enviar(idAgenda){
			$("#idAgenda").val(idAgenda);
			$("#listaPacientesForm").submit();
		}
		
		$(function() {
			
			$("#paginador").paginate(
			{
				count : ${listaPacientesForm.count},
				start : 1,
				display : ${listaPacientesForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
					var busquedaA = $("#busquedaAAux").val();
					var busquedaE = $("#busquedaEAux").val();
					$.getJSON("paginador", {
						busquedaA : busquedaA,
						busquedaE : busquedaE,
						page : page
					}, function(response) {
						$("#tablaAgenda > tbody").empty();
						var filas = "";
						$.each(response, function(index, item) {
							filas += '<tr><td>' + item.afiliado.nombre + ' ' + item.afiliado.apellidoPaterno + ' ' + item.afiliado.apellidoMaterno + '</td>';
							filas += '<td>' + item.horaCita + '</td><td>' + item.atencionmedica.horaAsistio + '</td>';
							filas += '<td><a href="#" onclick="enviar(' + item.agendaId + ');">'+item.asistio+'</a></td></tr>';
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