<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<body>
	<div id="contenido">
		<form:form method="post" action="agregarUsuarioNuevo" commandName="mantenimientoUsuarioNuevoForm" id="mantenimientoUsuarioNuevoForm">
			<form:hidden path="estadoUsuario"/>
			<form:hidden path="idUsuario"/>
			<input type="hidden" id="respuesta" >
			<div class="linea1">
			</div>			
		
			<div class="linea1">
				<div class="texto_tit2 verde margen2">
					USUARIOS CON DECLARACIÓN NO ACEPTADA
				</div>
			</div>
			<br/>
			<hr width="40%" align="left" style="margin-left:8%;" color="#C5C1C1"/>
			<c:forEach items="${usuariosDeclaracionPendiente}" var="usuario">
				<div class="linea4">
					<div class="texto_gris2" onclick="abrirDeclaracion('${usuario.nombre}','${usuario.apellidoPaterno}','${usuario.apellidoMaterno}','${usuario.rfc}','${usuario.usuarioId}','${usuario.fechaDeNacimiento}','${usuario.sexo}','${usuario.idEstado}')">
						<h7>${usuario.nombre} ${usuario.apellidoPaterno} ${usuario.apellidoMaterno}</h7>
					</div>
				</div>					
			</c:forEach>	
		</form:form>
		
        <div class="linea6 margen2">
           <div class="btn btn_cerrar" onclick="cerrar();"></div>
        </div>		
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({ cache: false });
		$(".fancybox").fancybox();
	});
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/mantenimientoUsuarios/inicio";
	}

	function abrirDeclaracion(nombre, apellidoPaterno, apellidoMaterno, rfc, idUsuario, fechaNacimiento, sexo, idEstado){
		var ctx = "${pageContext.request.contextPath}";
		$("#respuesta").val("false");
		$.fancybox.open({
			href : ctx + '/mantenimientoUsuarios/mostrarDeclaracionPendiente?idUsuario='+idUsuario,
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 770,
			height: 590,
			afterClose : function(){
				var r = $("#respuesta").val();
				var ctx = "${pageContext.request.contextPath}";
				if (r == "true") {
					$("#idUsuario").val(idUsuario);
					if (IniciarUsuario(idUsuario, 0, 0, rfc, "RFC", nombre, apellidoPaterno, apellidoMaterno, sexo, fechaNacimiento, idEstado)) {
						$("#estadoUsuario").val(6);
						var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
						$.getJSON("updateUsuario", mantenimientoUsuarioNuevoForm ,function(response){
							if(response.errores.length == 0){
								$.fancybox.open({
									content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1>Usuario creado exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
									openEffect : 'elastic',
									openSpeed  : 150,
									closeEffect : 'elastic',
									closeSpeed  : 150,
									autoSize: false,
					 				width: 370,
					 				height: 150,
					 				afterClose : function(){
					 					var ctx = "${pageContext.request.contextPath}";
					 					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
					 				}
								});
							}else{
				    			$("#errores").empty();
				    			$.each(response.errores, function(index, item) {
				    				$("#errores").append(item+"</br>"); 
				                });	
				    		}
							
						});	
					}else{
						$("#estadoUsuario").val(1);
						var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
						$.getJSON("updateUsuario", mantenimientoUsuarioNuevoForm ,function(response){
							alert("No se puede Enrolar huella del usuario, debe solicitar Autorización para uso de Clave");
							window.location.href = ctx+"/mantenimientoUsuarios/inicio";
						});					
					}
				}else{
					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
				}
            }			
		});		
	}
</script>
</html>