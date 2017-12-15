<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='lugarAtencionForm' action="lugarAtencion" method='POST'
		commandName="lugarAtencionForm" id="lugarAtencionForm">
		<a href="inicio">Nuevo</a>
		<form:input path="busqueda" id="busqueda"/>
		<input type="submit" value="Buscar"/>
		<table id="tablaLugaresAtencion">
			<thead>
				<tr>
					<td>Descripcion</td>
					<td>Codigo</td>					
					<td>Direccion</td>
					<td>Editar</td>
					<td>Borrar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lugarAtencion}" var="lugarAtencion">
					<tr>
						<td>${lugarAtencion.descripcion}</td>
						<td>${lugarAtencion.codigoLugarAtencion}</td>
						<td>${lugarAtencion.calleyNo}</td>
						<td><a href="#"
							onclick="editar(${lugarAtencion.lugarDeAtencionId}); false;">Editar</a></td>
						<td><a href="#"
							onclick="borrar(${lugarAtencion.lugarDeAtencionId}); false;">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div id="paginador"></div>
		<a href="/SAB/menu" >Regresar al Menu</a>
	</form:form>

	<script type="text/javascript">
		$(function() {
			$("#paginador").paginate({
				count : ${lugarAtencionForm.count},
				start : 1,
				display : ${lugarAtencionForm.display},
				border : true,
				border_color : '#fff',
				text_color : '#fff',
				background_color : 'black',
				border_hover_color : '#ccc',
				text_hover_color : '#000',
				background_hover_color : '#fff',
				images : false,
				mouse : 'press',
				onChange : function(page) {
					var busqueda = $("#busqueda").val();
				    $.getJSON("paginador", {busqueda:busqueda, page:page} ,function(response){ 
				    	$("#tablaLugaresAtencion > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.descripcion+'</td><td>'+item.codigoLugarAtencion+'</td><td>'+item.calleyNo+'</td>';
				            	filas += '<td><a href="#" onclick="editar('+item.lugarDeAtencionId+'); false;">Editar</a></td>';
				            	filas += '<td><a href="#" onclick="borrar('+item.lugarDeAtencionId+'); false;">Borrar</a></td></tr>';
				                $("#tablaLugaresAtencion tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});
		
		function borrar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "IdLugarAtencion").val(id);
			$("#lugarAtencionForm").append($(input));
			$("#lugarAtencionForm").attr("action", "borrar");
			$("#lugarAtencionForm").submit();
		}
		
		function editar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "IdLugarAtencion").val(id);
			$("#lugarAtencionForm").append($(input));
			$("#lugarAtencionForm").attr("action", "inicioEditar");
			$("#lugarAtencionForm").submit();
		}
		

	</script>
</body>
</html>