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
   		<input class="campo_supervisor tam_27" style="margin-right:0px !important;" placeholder="Nombre ó Código de la prestación*"/><!--
          --><input class="btn_solo buscar_icono" onclick="location.href='#';" />
   		
    </div> 
    
    <div class="linea3 margen">
			<div class="error tam_27">Es necesario llenar este campo para realizar la busqueda</div>
	 </div>
  
	<div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_17" placeholder="Código*" />
   		<input class="campo_supervisor tam_17 margen6" placeholder="Subcódigo" />
   		<input class="campo_supervisor tam_18 margen6" placeholder="Nombre*" />
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_17">Campo necesario</div>
			<div class="error tam_17 margen6"></div>
			<div class="error tam_18 margen6">Campo necesario</div>
	 </div>
    
    <div class="linea_supervisor margen">
	    <select class="selectt campo_supervisor tam_2" name="Nivel I*">
	            	<option>Nivel 1</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_2" name="Nivel II">
	            	<option>Nivel 2</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_9" name="Nivel III">
	            	<option>Nivel 3</option>
	            	<option>2</option>
	                <option>3</option>
	    </select> 
	   
	 </div>
	 
	 <div class="linea3 margen">
			<div class="error tam_2">Campo necesario</div>
	 </div> 
	 
	 <div class="linea_supervisor margen">
	    <select class="selectt campo_supervisor tam_2" name="Nivel IV">
	            	<option>Nivel 4</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_2" name="Nivel V">
	            	<option>Nivel 5</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	   
	 </div>  
    
    <div class="linea_supervisor margen">
		<div class="texto_supervisor4 verde tam_7 interlineado2 margen_arriba2">Clasificación Uso</div>
   		<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 verde">Consultorio</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 verde">Laboratorio</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 verde">Gabinete</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 verde">Otros</div>
    </div>
    
<div class="linea_supervisor margen">
	    <select class="selectt campo_supervisor tam_2" name="Especialidad">
	            	<option>Nivel 1</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_2" name="Especialidad">
	            	<option>Nivel 2</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>
	    
	    <select class="selectt campo_supervisor tam_9" name="Especialidad">
	            	<option>Nivel 3</option>
	            	<option>2</option>
	                <option>3</option>
	    </select> 
	   
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