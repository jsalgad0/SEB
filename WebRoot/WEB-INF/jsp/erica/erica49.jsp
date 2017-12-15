<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_admin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">

<html>

<script type="text/javascript">
function volcarSelects(emisorId, receptorId){
	
	// Accedemos a los 2 selects
	var emisor = document.getElementById(emisorId);
	var receptor = document.getElementById(receptorId);
	
	// Obtenemos algunos datos necesarios
	var posicion = receptor.options.length;
	var selecionado = emisor.selectedIndex;
	
	if(selecionado != -1) {
	
		var volcado = emisor.options[selecionado];
		
		// Volcamos la opcion al select receptor y lo eliminamos del emisor
		receptor.options[posicion] = new Option(volcado.text, volcado.value);
		emisor.options[selecionado] = null;
		emisor.selectedIndex=selecionado;
		if(selecionado>emisor.length-1){
			emisor.selectedIndex=emisor.length-1;
		}

	}
}
</script>

<body style="overflow:hidden;">
<form:form>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor margen_arriba3" style="height:72px;">
    	
   		<div class="linea_supervisor margen_arriba4">     
	
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_7" placeholder="Código*" />
   		<input class="campo_supervisor tam_18" placeholder="Nombre ó Descripción *" />
    </div>
    
    <div class="linea_supervisor margen">
			<div class="error tam_7">Es necesario llenar este campo</div>
			<div class="error tam_18">Es necesario llenar este campo</div>
	 </div> 
  
	<div class="linea_supervisor margen">
		<div class="texto_supervisor5 verde tam_6 interlineado2">DIRECCIÓN</div>
   		<input class="campo_supervisor tam_20" placeholder="Calle *" />
   		<input class="campo_supervisor tam_6" placeholder="# Externo*" />
   		<input class="campo_supervisor tam_6" placeholder="# Interno*" />
    </div>
    
    <div class="linea_supervisor margen10">
			<div class="error tam_20">Es necesario llenar este campo</div>
			<div class="error tam_6 margen14">No externo</div>
			<div class="error tam_6 margen14">No interno</div>
	</div>
    
    <div class="linea_supervisor margen">
    
    	<select class="selectt campo_supervisor tam_2" name="Estado *">
	            	<option>Estado</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_2" name="Delegación *">
	            	<option>Delegación</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_9" name="Colonia *">
	            	<option>Colonia</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <input class="campo_supervisor tam_6" placeholder="C.P. *" />
	    
	    
	 </div> 
	 
	 <div class="linea_supervisor margen">
			<div class="error tam_2">Se requiere el Estado</div>
			<div class="error tam_2">Se requiere la Delegación</div>
			<div class="error tam_9">Se requiere la Colonia</div>
			<div class="error tam_6">Indique CP</div>
	</div>  
    
    <div class="linea_supervisor margen">
		<div class="texto_supervisor5 verde tam_8 interlineado2">CONTACTO</div>
   		<input class="campo_supervisor tam_10 margen_izq1" placeholder="Nombre *" />
   		<input class="campo_supervisor tam_7" placeholder="Teléfono 1 *" />
   		<input class="campo_supervisor tam_7" placeholder="Teléfono 2" />
    </div>
    
    <div class="linea3 margen10">
			<div class="error tam_10">Es necesario el nombre</div>
			<div class="error tam_7">Es necesario un teléfono</div>
			<div class="error tam_7">Es necesario un teléfono</div>
	</div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_11 margen12" placeholder="Correo electrónico *" />
    </div>
    
    <div class="linea3 margen10">
			<div class="error tam_7">Es necesario el email</div>
	</div>
	
	<div class="linea3 margen margen">
	
	<div class="texto_supervisor4 verde">
	         Asignación de Roles <br /> al  Lugar de Atención
	</div>         
 
     <div class="archivos_caja">Catálogo de Roles
     
	<select class="campo_cajax" id="primero" style="width:20vw; height: 8vw;" name="primero" multiple="multiple">
		<option value="1">Administrador</option>
		<option value="2">Recepcionista</option>
		<option value="3">Médico</option>
		<option value="4">Enfermera</option>
		<option value="5">Supervisor</option>
		<option value="6">Dentista</option>
		<option value="7">Finanzas</option>
	</select>
  
     </div>
     
	 <!-- Fijaros que a la funcion le paso el ID del select que envia y el segundo parametro es el ID que recibe -->
	<input style="margin-top:5vw;" type="button" value="&rarr;" onclick="volcarSelects('primero', 'segundo');" />
	
	<!-- En este caso quiero poder "quitar" opciones, por lo que invierto el orden de los parametros pasados a la función -->
	<input style="margin-top:5vw;" type="button" value="&larr;" onclick="volcarSelects('segundo', 'primero');" />
	     

     <div class="archivos_caja">Roles del Lugar de Atención
     <select class="campo_cajax"  style="width:20vw; height: 8vw;" id="segundo" name="segundo" multiple="multiple">
	</select>
     </div>
		
	</div> 
	
	    

     <div class="linea_supervisor margen margen_arriba2">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>