<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
	<div id="contenedor">
		<div id="subcontenedor">
			<form:form method="post" action="datosUsuarioForm" commandName="datosUsuarioForm" id="datosUsuarioForm">
		   		<div class="texto">${userInfo.usuarios.nombre} ${userInfo.usuarios.apellidoPaterno} ${userInfo.usuarios.apellidoMaterno}
		   		</div>  
		        <div class="texto">${userInfo.usuarios.rfc}
		   		</div> 
		        
		   		<div class="texto2">Registrado en el lugar de Atención
		   		</div>
		   		<div class="campo_r">${userInfo.lugaresdeatencion.descripcion}
				</div>
		        
		        <c:if test="${datosUsuarioForm.especialidades != ''}">
			        <div class="texto2">Especialidades
			   		</div>
			   		<div class="campo_r">${datosUsuarioForm.especialidades}
			
					</div>
					        
		        </c:if>
		
		    	<c:if test="${datosUsuarioForm.mostrarBotones == true}">
			   		<div class="btn">
			        	<div class="btn_clave" onclick="clave();"></div>
			            <div class="btn_pregunta" onclick="pregunta();">
			            </div>
			        </div>		    	
		    	</c:if>

		   		<div class="btn"><div class="btn_cerrar1" onclick="parent.$.fancybox.close();"></div></div>
			</form:form>

	        
	    </div>
	   
	</div>
</body>

<script type="text/javascript">
	function clave(){
		$("#datosUsuarioForm").attr("action", "datosUsuarioClave");
		$("#datosUsuarioForm").submit();			
	}
	
	function pregunta(){
		$("#datosUsuarioForm").attr("action", "datosUsuarioPregunta");
		$("#datosUsuarioForm").submit();			
	}	
</script>
</html>