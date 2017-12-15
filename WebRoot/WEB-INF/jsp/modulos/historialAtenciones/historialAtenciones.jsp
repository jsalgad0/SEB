<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='prestadorForm' action="atencionMedica" method='POST'
		commandName="prestadorForm" id="prestadorForm">
		<table id="tablaHistorial">
			<thead>
				<tr>
					<td>Fecha Atencion</td>
					<td>Lugar de Atencion</td>					
					<td>Prestacion</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			</tbody>

		</table>
		<div id="paginador"></div>
		<a href="/SAB/menu" >Regresar al Menu</a>
	</form:form>

	<script type="text/javascript">
		$(function() {
			$("#paginador").paginate({
				count : ${prestadorForm.count},
				start : 1,
				display : ${prestadorForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
					var busqueda = $("#busqueda").val();
				    $.getJSON("paginador", {busqueda:busqueda, page:page} ,function(response){ 
				    	$("#tablaPrestadores > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.nombreRazonSocial+'</td><td>'+item.rfc+'</td><td>'+item.cattipospersonas.tipoPersona+'</td>';
				            	filas += '<td><a href="#" onclick="editar('+item.prestadorId+'); false;">Editar</a></td>';
				            	filas += '<td><a href="#" onclick="borrar('+item.prestadorId+'); false;">Borrar</a></td></tr>';
				                $("#tablaHistorial tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});

	</script>
</body>
</html>