<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
<div id="contenedor2">
	<h1>Cambiar pregunta secreta</h1>
    
    <form:form method="post" action="actualizarDatosUsuarioPregunta" commandName="datosUsuarioForm" id="datosUsuarioForm">
    	<div class="texto4"><p>Seleccionar y responder la pregunta actual</p>
		</div> 
		
		<form:select path="idPreguntaAntigua" class="cssmenu selectt">
			<form:option value="-1" label="Seleccione una pregunta" />
			<form:options items="${preguntas}" itemLabel="descripcion" itemValue="id"/>
		</form:select>
		
		<div class="linea5">
			<div class="error"><form:errors path="idPreguntaAntigua" />${datosUsuarioForm.errorIdPreguntaAntigua}</div>
		</div>		
	    
	    <form:input path="respuestaAntigua" id="respuestaAntigua" class="campo_r2" placeholder="Ingrese Respuesta" maxlength="90" />
	                       
		<div class="linea5">
			<div class="error"><form:errors path="respuestaAntigua" />${datosUsuarioForm.errorRespuestaAntigua}</div>
		</div>
		
	   	<div class="texto4"><p>Seleccionar y responder nueva pregunta secreta</p>
	   	</div> 
	
		<form:select path="idPregunta" class="cssmenu selectt">
			<form:option value="-1" label="Seleccione una pregunta" />
			<form:options items="${preguntas}" itemLabel="descripcion" itemValue="id"/>
		</form:select>
		
		<div class="linea5">
			<div class="error"><form:errors path="idPregunta" /></div>
		</div>		
	
		<form:input path="respuesta" id="respuesta" class="campo_r2" placeholder="Ingrese Respuesta" maxlength="90" />
	   
		<div class="linea5">
			<div class="error"><form:errors path="respuesta" /></div>
		</div>
	   
		<form:password path="clave" id="clave" class="campo_r2" placeholder="Ingrese ContraseÒa" maxlength="30" />
	   
		<div class="linea5">
			<div class="error"><form:errors path="clave" />${datosUsuarioForm.errorClave}</div><div class="exito">${datosUsuarioForm.exito}</div>
		</div>
	   
		<div class="btn2">
			<div class="btn_guardar" onclick="actualizarDatosUsuarioPregunta()"></div>
			<div class="btn_cerrar" onclick="datosUsuario();"></div>
	    </div> 
    
    </form:form>
   
</div>
</body>

<script type="text/javascript">
function datosUsuario(){
	$("#datosUsuarioForm").attr("action", "datosUsuario");
	$("#datosUsuarioForm").submit();			
}	

function actualizarDatosUsuarioPregunta(){
	$("#datosUsuarioForm").submit();
}
$( document ).ready(function() {
		$("#respuestaAntigua").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®∑'
	    });
	    $("#respuesta").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®∑'
	    });
	});
</script>
</html>