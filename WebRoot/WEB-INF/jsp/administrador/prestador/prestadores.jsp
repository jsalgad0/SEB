<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='prestadorForm' action="prestadores" method='POST'
		commandName="prestadorForm" id="prestadorForm">
		<a href="inicio">Nuevo</a>
		<form:input path="busqueda" id="busqueda"/>
		<input type="submit" value="Buscar"/>
		<table id="tablaPrestadores">
			<thead>
				<tr>
					<td>Nombre</td>
					<td>RFC</td>					
					<td>Persona</td>
					<td>Editar</td>
					<td>Borrar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prestadores}" var="prestador">
					<tr>
						<td>${prestador.nombreRazonSocial}</td>
						<td>${prestador.rfc}</td>
						<td>${prestador.cattipospersonas.tipoPersona}</td>
						<td><a href="#"
							onclick="editar(${prestador.prestadorId}); false;">Editar</a></td>
						<td><a href="#"
							onclick="borrar(${prestador.prestadorId}); false;">Borrar</a></td>
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
				count : ${prestadorForm.count},
				start : 1,
				display : ${prestadorForm.display},
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
				    	$("#tablaPrestadores > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.nombreRazonSocial+'</td><td>'+item.rfc+'</td><td>'+item.cattipospersonas.tipoPersona+'</td>';
				            	filas += '<td><a href="#" onclick="editar('+item.prestadorId+'); false;">Editar</a></td>';
				            	filas += '<td><a href="#" onclick="borrar('+item.prestadorId+'); false;">Borrar</a></td></tr>';
				                $("#tablaPrestadores tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});
		
		function borrar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idPrestador").val(id);
			$("#prestadorForm").append($(input));
			$("#prestadorForm").attr("action", "borrar");
			$("#prestadorForm").submit();
		}
		
		function editar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idPrestador").val(id);
			$("#prestadorForm").append($(input));
			$("#prestadorForm").attr("action", "inicioEditar");
			$("#prestadorForm").submit();
		}
		

	</script>
</body>
</html>