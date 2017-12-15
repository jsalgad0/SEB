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
   		<input class="campo_supervisor tam_7" style="margin-right:0px !important;" placeholder="RFC *"/><!--
          --><input class="btn_solo buscar_icono" onclick="location.href='#';" />
   		<input class="campo_supervisor tam_18" placeholder="Nombre ó Razón Social *" />
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_7">Es necesario llenar este campo</div>
			<div class="error tam_18">Es necesario llenar este campo</div>
	 </div>  
  
	<div class="linea_supervisor margen">
		<div class="texto_supervisor5 verde tam_6 interlineado2">DIRECCIÓN</div>
   		<input class="campo_supervisor tam_20" placeholder="Calle" />
   		<input class="campo_supervisor tam_6" placeholder="# Externo" />
   		<input class="campo_supervisor tam_6" placeholder="# Interno" />
    </div>
    
    <div class="linea_supervisor margen">
	    <select class="selectt campo_supervisor tam_2" name="Estado *">
	            	<option>Estado</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_2" name="Delegación">
	            	<option>Delegación</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_9" name="Colonia *">
	            	<option>Colonia</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <input class="campo_supervisor tam_6" placeholder="C.P." />

	 </div>   
    
    <div class="linea_supervisor margen">
		<div class="texto_supervisor5 verde tam_8 interlineado2">CONTACTO</div>
   		<input class="campo_supervisor tam_20 margen_izq1" placeholder="Nombre" />
    </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_7 margen12" placeholder="Teléfono 1" />
   		<input class="campo_supervisor tam_7 margen5" placeholder="Teléfono 2" />
    </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_11 margen12" placeholder="Correo electrónico" />
    </div>
         

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>