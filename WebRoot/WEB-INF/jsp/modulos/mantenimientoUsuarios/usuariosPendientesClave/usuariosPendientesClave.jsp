<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
	<div id="contenido">
		<div class="linea1">
			</div>
			
        <div class="linea1">
			<div class="texto_tit verde margen2">
				<h7>USUARIOS PENDIENTES</h7>
			</div>
		</div>
		<br/>
		<hr width="40%" align="left" style="margin-left:8%;" color="#C5C1C1"/>	
		<c:forEach items="${usuariosPendienteClave}" var="usuario">
			<div class="linea4">
				<div class="texto_gris2" onclick="enviar(${usuario.usuarioId})">
					<h7>${usuario.nombre} ${usuario.apellidoPaterno} ${usuario.apellidoMaterno}</h7>
				</div>
			</div>					
		</c:forEach>
		<div class="linea6 margen2">
			<div class="btn btn_cerrar" onclick="cerrar();"></div>
		</div>		
	</div>
</body>

<script type="text/javascript">
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/mantenimientoUsuarios/inicio";
	}		
</script>
</html>