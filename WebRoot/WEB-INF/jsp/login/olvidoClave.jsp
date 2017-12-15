<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_olvidopassword.css" rel="stylesheet">

</head>
<body style="overflow-y:hidden">

<div id="contenedor_iframe">
	<h1>:: Recuperar Clave ::</h1> 
	<form:form name='loginForm' action="validarRespuesta" method='POST' commandName="loginForm" id="loginForm" >
		<form:hidden path="idUsuario"/>
		<form:hidden path="terminoIngresarClave"/>
		<form:hidden path="respuestaExitosa"/>
		<form:hidden path="pregunta"/>
		<form:hidden path="irLogin"/>
		
		
		<div class="contenedor_frame">  
			<div class="texto">
				<p>RFC</p>
 			</div> 
   			
   			<form:input cssClass="campo" placeholder="Introduzca su RFC" path="rfc" readonly="true" />
 		</div>
		
	    <div class="contenedor_frame">  
			<div class="texto2">
				<p>
					Responda a la siguiente pregunta:
				</p>
			</div>
	      
			<div class="campo_pregunta" id="#">${loginForm.pregunta}
			</div>
	      
	      	<form:input cssClass="campo_texto" path="respuesta" placeHolder="Respuesta" /><!--
				--><div class="caja_arrow2">
					<div id="flecha2" onclick="validarRespuesta();">
					</div>
				</div>     
	    	<div class="linea3">
	        	<div class="error">${loginForm.errorRespuesta}</div>
	        </div> 
	  	</div>		

  		<div class="contenedor_frame2">
  			<div class="seccion"><img src="${pageContext.request.contextPath}/resources/img/padlock-lock-icon1.png" width="200" alt="contraseña" height="200" style="margin-left:50px;" />
        	</div>
        
        
	        <div class="seccion" id="divClave" style="display: none;">
				<div class="texto2"><p>Nueva contraseña</p></div>
				<form:password cssClass="campo2" path="ingresaClave" />
	               
	        	<div class="linea2">
	        		<div class="error">${loginForm.errorClave}</div>
	        	</div> 
	
	               
				<div class="texto2"><p>Confirmar contraseña</p></div>
	          	<form:password cssClass="campo2" path="confirmaClave" />
				<div class="linea2">
					<div class="error">${loginForm.errorConfirmacionClave}</div>
	        	</div>
	               
	          	<div class="btn_guardar" onclick="validarRespuesta();">
	          	</div>  
	          
	          	<div class="btn_cerrar" onclick="parent.$.fancybox.close();">
	          	</div>   
	    
	       </div> 
	              
  		</div>     
	</form:form>

   
</div>
</body>
<script type="text/javascript">
	$( document ).ready(function() {
		var respuestaExitosa = $("#respuestaExitosa").val();
		if(respuestaExitosa == "true"){
			$("#divClave").show();
			$("#loginForm").attr("action", "validarClaveNueva");
		}		
		
		var terminoIngresarClave = $("#terminoIngresarClave").val();
		if(terminoIngresarClave == "true"){
			parent.$.fancybox.close();
			var ctx = "${pageContext.request.contextPath}";
			parent.window.location.href = ctx+"/login";			
		}
		
		var irLogin = $("#irLogin").val();
		if (irLogin == "true") {
			setTimeout(function(){ 
				var ctx = "${pageContext.request.contextPath}";
				parent.window.location.href = ctx+"/login";	
			}, 2000);
		}
	});	
	
	function validarRespuesta(){
		$("#loginForm").submit();
	}

	
</script>

</html>