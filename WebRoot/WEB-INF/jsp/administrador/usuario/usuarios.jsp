<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript">
function borrar(id){
	var input = $("<input>").attr("type", "hidden").attr("name", "idUsuario").val(id);
	$("#usuarioForm").append($(input));
	$("#usuarioForm").attr("action", "borrar");
	$("#usuarioForm").submit();
}

function editar(id){
	var input = $("<input>").attr("type", "hidden").attr("name", "idUsuario").val(id);
	$("#usuarioForm").append($(input));
	$("#usuarioForm").attr("action", "inicioEditarUsuario");
	$("#usuarioForm").submit();
}
</script>
<body>

	<form:form name='usuarioForm' action="usuarios" method='POST'
		commandName="usuarioForm" id="usuarioForm" align="center">
		<a href="alta">Nuevo</a>
		<form:input path="busqueda" id="busqueda"/>
		<input type="submit" value="Buscar"/>
		<table id="tablaUsuarios" align="center" >
			<thead>
				<tr>
					<td>Nombre</td>
					<td>RFC</td>
					<td>Editar</td>
					<td>Borrar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.nombre} ${usuario.apellidoPaterno} ${usuario.apellidoMaterno}</td>
						<td>${usuario.rfc}</td>
						<td><a href="#"
							onclick="editar(${usuario.usuarioId}); false;">Editar</a></td>
						<td><a href="#"
							onclick="borrar(${usuario.usuarioId}); false;">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
			
			<td> <div id="paginador" /> </td>
			<td> <a href="/SAB/menu">Regresar al Menu</a> </td>
		</table>
		
		
	</form:form>

	<script type="text/javascript">
		$(function() {
			$("#paginador").paginate({
				count : ${usuarioForm.count},
				start : 1,
				display : ${usuarioForm.display},
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
				    	$("#tablaUsuarios > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.nombre+' '+item.apellidoPaterno+' '+item.apellidoMaterno+'</td><td>'+item.rfc+'</td>';
				            	filas += '<td><a href="#" onclick="editar('+item.usuarioId+'); false;">Editar</a></td>';
				            	filas += '<td><a href="#" onclick="borrar('+item.usuarioId+'); false;">Borrar</a></td></tr>';
				                $("#tablaUsuarios tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});
</script>
</body>
</html>