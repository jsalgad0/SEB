<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='registroLectoresForm' action="registroLectores" method='POST'
		commandName="registroLectoresForm" id="registroLectoresForm">
		<a href="inicio">Nuevo</a>
		<form:input path="busqueda" id="busqueda"/>
		<input type="submit" value="Buscar"/>
		<table id="tablaLectores">
			<thead>
				<tr>
					<td>noDeSerie</td>
					<td>propietarioLector</td>					
					<td>descripcion</td>
					<td>Editar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${registroLectores}" var="lector">
					<tr>
						<td>${lector.noDeSerie}</td>
						<td>${lector.propietarioslector.propietarioLector}</td>
						<td>${lector.lugaresdeatencion.descripcion}</td>
						<td><a href="#"
							onclick="editar(${lector.lectorId}); false;">Editar</a></td>
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
				count : ${registroLectoresForm.count},
				start : 1,
				display : ${registroLectoresForm.display},
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
				    	$("#tablaLectores > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.noDeSerie+'</td><td>'+item.propietarioLector.propietarioLector+'</td><td>'+item.lugarAtencion.descripcion+'</td>';
				            	filas += '<td><a href="#" onclick="editar('+item.lectorId+'); false;">Editar</a></td></tr>';
				                $("#tablaLectores tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});
		
		function editar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idLector").val(id);
			$("#registroLectoresForm").append($(input));
			$("#registroLectoresForm").attr("action", "inicioEditar");
			$("#registroLectoresForm").submit();
		}
		

	</script>
</body>
</html>