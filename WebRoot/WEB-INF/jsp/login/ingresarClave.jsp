<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_usuarioregistrado.css" rel="stylesheet">

</head>
<body>

<div id="contenedor">
	<h1>Registro de Usuario</h1>
	<form:form name='loginForm' action="validarClave" method='POST' commandName="loginForm" id="loginForm" >
		<form:hidden path="idUsuario"/>
		<form:hidden path="terminoIngresarClave"/>
		
		<form:input cssClass="campo_e" placeholder="Ingrese RFC" path="rfc" readonly="true" />
	    <div class="texto"><p>Seleccionar y responder una de las siguientes preguntas</p>
	    </div> 
	
			<form:select path="idPregunta" cssClass="selectt cssmenu">
				<form:option value="-1" label="Seleccione una pregunta" />
				<form:options items="${preguntas}" itemValue="id" itemLabel="descripcion" />
                   	
			</form:select>
	
        <div class="linea3">
       		<div class="error">${loginForm.errorPregunta}</div>
   		</div>
   		
	   <form:input cssClass="campo_r" path="respuesta" placeHolder="Ingrese Respuesta" />
	   <div class="linea3">
	        <div class="error">${loginForm.errorRespuesta}</div>
	   </div>	   
	   
	   <form:password cssClass="campo_r" path="ingresaClave" placeholder="Ingrese Contraseña" />
	   <div class="linea3">
	        <div class="error">${loginForm.errorClave}</div>
	   </div>	   
	   
	   <form:password cssClass="campo_r" path="confirmaClave" placeholder="Confirme la Contraseña" />
	   <div class="linea3">
	        <div class="error">${loginForm.errorConfirmacionClave}</div>
	   </div>	   
	   
	   <div class="btn"><input type="submit" class="btn_guardar" value=""></div>  
	</form:form>

   
</div>
</body>
<script type="text/javascript">
	$( document ).ready(function() {
		var terminoIngresarClave = $("#terminoIngresarClave").val();
		if(terminoIngresarClave == "true"){
			parent.$.fancybox.close();
			var ctx = "${pageContext.request.contextPath}";
			parent.window.location.href = ctx+"/login";			
		}
	});	
</script>

</html>