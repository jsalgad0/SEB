<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='conveniosForm' action="convenios" method='POST'
		commandName="conveniosForm" id="conveniosForm">
		<a href="inicio">Nuevo</a>
		busqueda Asegurador:
		<form:input path="busquedaA" id="busquedaA"/>		
		busqueda Prestador:
		<form:input path="busquedaP" id="busquedaP"/>
		busqueda Lugar:
		<form:input path="busquedaL" id="busquedaL"/>

		<input type="submit" value="Buscar"/>
		<table id="tablaConvenios">
			<thead>
				<tr>
					<td>Convenio</td>
					<td>Asegurador</td>					
					<td>Prestador</td>
					<td>Lugar de Atencion</td>
					<td>Borrar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${convenios}" var="convenios">
					<tr>
						<td>${convenios.convenio}</td>
						<td>${convenios.aseguradores.nombreRazonSocial}</td>
						<td>${convenios.prestadores.nombreRazonSocial}</td>
						<td>${convenios.lugaresdeatencion.descripcion}</td>
						<td><a href="#"
							onclick="borrar(${convenios.convenioId}); false;">Borrar</a></td>
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
				count : ${conveniosForm.count},
				start : 1,
				display : ${conveniosForm.display},
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
					var busquedaP = $("#busquedaP").val();
					var busquedaL = $("#busquedaL").val();
					var busquedaA = $("#busquedaA").val();
				    $.getJSON("paginador", {busquedaP:busquedaP, busquedaL:busquedaL, busquedaA:busquedaA, page:page} ,function(response){ 
				    	$("#tablaConvenios > tbody").empty(); 
				    		var filas = "";
				            $.each(response, function(index, item) {
				            	filas += '<tr><td>'+item.convenio+'</td><td>'+item.aseguradores.nombreRazonSocial+'</td><td>'+item.prestadores.nombreRazonSocial+'</td><td>'+item.lugaresdeatencion.descripcion+'</td>';
				            	filas += '<td><a href="#" onclick="borrar('+item.convenioId+'); false;">Borrar</a></td></tr>';
				                $("#tablaConvenios tbody").append(filas); 
				                filas = "";
				            });
				    });
				}
			});
		});
		
		function borrar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idConvenio").val(id);
			$("#conveniosForm").append($(input));
			$("#conveniosForm").attr("action", "borrar");
			$("#conveniosForm").submit();
		}
		

	</script>
</body>
</html>