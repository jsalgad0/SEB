<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
</head>
<body>
	<div id="contenido">
		<form:form name='mantenimientoModificarUsuarioForm' action="inicioModificarUsuario" method='POST'
			commandName="mantenimientoModificarUsuarioForm" id="mantenimientoModificarUsuarioForm">
			<form:hidden path="idUsuario" id="idUsuario"/>
			<form:hidden path="idVigencia" id="idVigencia"/>
			<form:hidden path="sinResultados" id="sinResultados"/>
			
           	<div class="linea1b">
           		<form:input path="busquedaRfc" id="busquedaRfc" cssClass="campo_sm size4 mayusculas" maxlength="15" placeholder="RFC"/>
				<div style="display:inline-block; width:3px; height:6px; margin-right:4px;"><h6>/</h6></div>
				<form:input path="busquedaNombre" id="busquedaNombre" cssClass="campo size2 mayusculas" maxlength="45" placeholder="Nombre"/>
				<form:input path="busquedaApellidoPaterno" id="busquedaApellidoPaterno" cssClass="campo size1 mayusculas" maxlength="45" placeholder="Apellido Paterno"/>	
				<form:input path="busquedaApellidoMaterno" id="busquedaApellidoMaterno" cssClass="campo size1 mayusculas" maxlength="45" placeholder="Apellido Materno"/>	
				<input type="submit" class="btn_buscar" value="" />
			</div>
			
            <div class="linea1 margen3">
            	<div class="texto_tit verde margen2">
                <h7>NOMBRE</h7>
                </div>
                
                <div class="texto_tit pad verde">
                <h7>RFC</h7>
                </div>
                
                <div class="texto_corto verde">
                <h7>FECHA DE NAC</h7>
                </div>

                <div class="texto_corto verde">
                <h7>ESTADO</h7>
                </div>
            </div>
                    
                    <div class="fakeHr">_______________________________________________________________________________</div>
                    
                    <div id=contenedorUsuarios class="lista_usuario">
						<c:forEach items="${usuarios}" var="usuario">
		                    <div  class="linea4">
		                    	<div class="texto_tit gris" onclick="modificarUsuario(${usuario.usuarioId});">
		                        <h7>${usuario.nombre}</h7>
		                        </div>
		                        <div class="texto_tit gris pad">
		                        <h7>${usuario.rfc}</h7>
		                        </div>
		                        <div class="texto_corto gris">
		                        <h7>${usuario.fechaDeNacimiento}</h7>
		                    	</div>
		                        <div class="texto_corto gris">
		                        <h7>${usuario.vigencia}</h7>
		                    	</div>		                    	
		                    </div>
						</c:forEach>
					</div>
                    <div class="paginador" id="paginas">
                    <div class="demo">
                		<div id="paginador">                   
                		</div>
            		</div>
                    </div>
					
		</form:form>
		
       	<div class="linea4">

           	<div class="linea7">
           	<div class="btn btn_cerrar" onclick="cerrar();">
           	</div>
           	</div>
       	</div>		
	</div>
	<script type="text/javascript">
		$( document ).ready(function() {
			$("#busquedaRfc").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
		    });		
			$("#busquedaNombre").alpha({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });		
			$("#busquedaApellidoPaterno").alpha({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });
			$("#busquedaApellidoMaterno").alpha({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });
		
			$.ajaxSetup({ cache: false });
			var sinResultados = $("#sinResultados").val();
			var idUsuario = $("#idUsuario").val();
			var idVigencia = $("#idVigencia").val();
			
 			if (sinResultados=="true") {
 				var busquedaRfc = $("#busquedaRfc").val();
 				var busquedaNombre = $("#busquedaNombre").val();
 				var busquedaApellidoPaterno = $("#busquedaApellidoPaterno").val();
 				var busquedaApellidoMaterno = $("#busquedaApellidoMaterno").val();
 				
 				if (busquedaRfc!="" || busquedaNombre!="" || busquedaApellidoPaterno!="" || busquedaApellidoMaterno!="") {
 	 				$.fancybox.open({
 	 					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Usuario no est· registrado en el sistema</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
 	 					openEffect : 'elastic',
 	 					openSpeed  : 150,
 	 					closeEffect : 'elastic',
 	 					closeSpeed  : 150,								
 	 	 				autoSize: false,
 	 	 				width: 470,
 	 	 				height: 185
 	 				});	 							
				}

			}else{
				if (idUsuario!=0) {
					if (idVigencia == 1) {
	 	 				$.fancybox.open({
	 	 					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Usuario en estado Bloqueado, utilice la opcion Usuarios con clave Bloqueados</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
	 	 					openEffect : 'elastic',
	 	 					openSpeed  : 150,
	 	 					closeEffect : 'elastic',
	 	 					closeSpeed  : 150,								
	 	 	 				autoSize: false,
	 	 	 				width: 470,
	 	 	 				height: 185
	 	 				});	
					}else{
						modificarUsuario(idUsuario);	
					}
					
				}
			}
		});
		
		function modificarUsuario(idUsuario){
			$("#idUsuario").val(idUsuario);
			$("#mantenimientoModificarUsuarioForm").attr("action", "modificarUsuarios");
			$("#mantenimientoModificarUsuarioForm").submit();
		}
		
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/mantenimientoUsuarios/inicio";
		}		
	
		$(function() {
			$("#paginador").paginate({
				count : ${mantenimientoModificarUsuarioForm.count},
				start : 1,
				display : ${mantenimientoModificarUsuarioForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
					var busquedaRfc = $("#busquedaRfc").val();
					var busquedaNombre = $("#busquedaNombre").val();
					var busquedaApellidoPaterno = $("#busquedaApellidoPaterno").val();
					var busquedaApellidoMaterno = $("#busquedaApellidoMaterno").val();
				    $.getJSON("paginador", {busquedaRfc:busquedaRfc, busquedaNombre:busquedaNombre, busquedaApellidoPaterno:busquedaApellidoPaterno, busquedaApellidoMaterno:busquedaApellidoMaterno, page:page} ,function(response){ 
				    	$("#contenedorUsuarios").empty(); 
			    		var filas = "";
			            $.each(response, function(index, item) {
			            	filas += '<div  class="linea4"><div class="texto_tit gris" onclick="modificarUsuario('+item.usuarioId+');">';
			            	filas += '<h7>'+item.nombre+'</h7></div><div class="texto_tit gris pad"><h7>'+item.rfc+'</h7></div><div class="texto_corto gris"><h7>'+item.fechaDeNacimiento+'</h7></div><div class="texto_corto gris"><h7>'+item.vigencia+'</h7></div></div>';
			                $("#contenedorUsuarios").append(filas); 
			                filas = "";
			            });
				    });
				}
			});
		});

	</script>
</body>
</html>