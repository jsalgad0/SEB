<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
	<form:form name='aseguradorForm' action="inicio" method='POST'
		commandName="aseguradorForm" id="aseguradorForm">
		<a href="nuevo">Nuevo</a>
		<form:input path="busqueda" id="busqueda"/>
		<input type="submit" value="Buscar"/>
		<table>
			<tr>
				<td>Nombre</td>
				<td>RFC</td>
				<td>Editar</td>
				<td>Borrar</td>
			</tr>
			<c:forEach items="${aseguradores}" var="asegurador" >
				<tr>
					<td>
						${asegurador.nombreRazonSocial}
					</td>
					<td>
						${asegurador.rfc}
					</td>
					<td><a href="#"
							onclick="editar(${asegurador.aseguradorId}); false;">Editar</a></td>
						<td><a href="#"
							onclick="borrar(${asegurador.aseguradorId}); false;">Borrar</a></td>																	
				</tr>
			</c:forEach>
		</table>		
				<div id="demo5">                   
                </div>
	</form:form>
	<a href="/SAB/menu" >Regresar al Menu</a>

	<script type="text/javascript">
		$(function() {
			$("#demo5").paginate({
				count : 1,
				start : 1,
				display : 1,
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
					alert(page);
				}
			});
		});
		
		function borrar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idAsegurador").val(id);
			$("#aseguradorForm").append($(input));
			$("#aseguradorForm").attr("action", "borrar");
			$("#aseguradorForm").submit();
		}
		
		function editar(id){
			var input = $("<input>").attr("type", "hidden").attr("name", "idAsegurador").val(id);
			$("#aseguradorForm").append($(input));
			$("#aseguradorForm").attr("action", "inicioEditar");
			$("#aseguradorForm").submit();
		}
	</script>
</body>
</html>