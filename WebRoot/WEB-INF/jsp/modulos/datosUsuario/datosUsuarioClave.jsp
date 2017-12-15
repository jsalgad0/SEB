<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
<div id="contenedor_iframe">
	
	<h1> Cambiar la Clave </h1> 
 	<form:form method="post" action="actualizarDatosUsuarioClave" commandName="datosUsuarioForm" id="datosUsuarioForm">
 		<div class="contenedor_frame2">
			<div class="seccion1"><img src="${pageContext.request.contextPath}/resources/img/padlock-lock-icon1.png" width="115" alt="contraseña" style="margin-left:2px; margin-top:25px;" />
	        </div>
	        
	        <div class="seccion2">
				<div class="texto3"><p>Contraseña Actual</p>
				</div>
	      
				<form:password path="clave" id="clave" class="campo2" maxlength="30"/>
				
				<div class="linea3">
					<div class="error">${datosUsuarioForm.errorClave}</div>
				</div>
				
				<div class="texto3"><p>Nueva contraseña</p>
				</div>
	
				<form:password path="nuevaClave" id="nuevaClave" class="campo2" maxlength="30"/>
	               
				<div class="linea3">
					<div class="error">${datosUsuarioForm.errorNuevaClave}</div>
				</div>
	               
	            
	          	<div class="texto3"><p>Confirmar contraseña</p>
	          	</div>
	      
				<form:password path="nuevaClaveConfirmacion" id="nuevaClaveConfirmacion" class="campo2" maxlength="30"/>
	               
				<div class="linea3">
					<div class="error">${datosUsuarioForm.errorNuevaClaveConfirmacion}</div><div class="exito">${datosUsuarioForm.exito}</div>
				</div>
	               
				<div class="btn_guardar" onclick="actualizarDatosUsuarioClave();">
				</div>  
	          
				<div class="btn_cerrar" onclick="datosUsuario();">
				</div>   
			</div> 
		</div>  
 	</form:form>

</div>
</body>

<script type="text/javascript">
	function datosUsuario(){
		$("#datosUsuarioForm").attr("action", "datosUsuario");
		$("#datosUsuarioForm").submit();			
	}
	
	function actualizarDatosUsuarioClave(){
		$("#datosUsuarioForm").submit();
	}
</script>
</html>