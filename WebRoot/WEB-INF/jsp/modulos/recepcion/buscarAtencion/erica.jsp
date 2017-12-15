<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body>
<form:form>

<div id="admin_contenido">
	<div class="linea_supervisor margen4">
		<div class="titulo_supervisor">BUSCAR ATENCIÓN:</div>
	     <input class="campo_supervisor tam_4" style="margin-right:0px !important;" placeholder="Folio Atención"/><!--
          --><input class="btn_solo buscar_icono" onclick="location.href='#';" />
      <div class="margen_izq2">
      	<input class="campo_supervisor tam_4" placeholder="Fecha atención" />
      </div>	
    </div>
    	
    <div class="admin_pleca"></div>
                    
    <div class="linea_supervisor">
        

    </div>   
    
    <div class="contenedor_recepcion">
    
    	<div class="linea_supervisor margen4">
        
        	<select class="selectt campo_supervisor tam_2" name="Temporal">
            	<option>Asegurador</option>
            	<option>2</option>
                <option>3</option>
            </select>
            
            <select class="selectt campo_supervisor tam_2" name="Temporal">
            	<option>Prestador</option>
            	<option>2</option>
                <option>3</option>
            </select>
            
            <select class="selectt campo_supervisor tam_11" name="Temporal">
            	<option>Convenio</option>
            	<option>2</option>
                <option>3</option>
            </select>
    	</div>
        
         <div class="linea_supervisor margen4"> 
            
            <div class="error tam_2">
            	Seleccione un Asegurador
            </div>
            
            <div class="error tam_2">
            	Seleccione un Prestador
            </div>
            
            <div class="error margen5 tam_9">
            	Seleccione un Convenio
            </div>
        
        </div>
        
        <div class="linea_supervisor margen4">
        
        	<select class="selectt campo_supervisor tam_4" name="Temporal">
            	<option>Tipo ID</option>
            	<option>2</option>
                <option>3</option>
            </select>
            
            <input class="campo_supervisor tam_7" placeholder="ID Paciente" />

            <div class="texto_supervisor gris tam_11">
            	
          		<input type="radio" name="derechohabiente-beneficiario" />DERECHOHABIENTE
            	<input type="radio" name="derechohabiente-beneficiario" />BENEFICIARIO
         	</div>
            
            <div class="texto_supervisor gris tam_7" style="font-family:quicksand-bold">
             		Asiste el paciente?
                    <input type="checkbox" name="asiste">
            </div>

    	</div>
        
        <div class="linea_supervisor margen4">
        	<div class="error tam_4">
            	Indique tipo de ID
            </div>
            
            <div class="error tam_7">
            	Indique ID del Paciente
            </div>
            
            <div class="error tam_9">
            	Indique tipo de paciente
            </div>
            
            <div class="error tam_7">
            	Indique si asitió el paciente
            </div>
 
        </div> 
    
     	<div class="linea_supervisor margen4">

            <input class="campo_supervisor tam_9" placeholder="Apellido Paterno" />
            <input class="campo_supervisor tam_9" placeholder="Apellido Materno" />
            <input class="campo_supervisor tam_5" placeholder="Nombre" />
            <input class="btn_buscar" onclick="location.href='#';" />
    	</div>
        
        <div class="linea_supervisor margen4"> 
            
            <div class="error tam_9">
            	Introduzca Apellido paterno
            </div>
            
            <div class="error tam_9">
            	Introduzca Apellido Materno
            </div>
            
            <div class="error margen5 tam_5">
            	Introduzca Nombre
            </div>
        
        </div>
        
        <div class="linea_supervisor margen4">

            <input id="datepicker" name="fechaNacimiento" class="campo_supervisor gris tam_4 datepicker" placeholder="Fecha de Nac" type="text" value=""/>
            <input class="campo_supervisor tam_12" placeholder="Teléfono" />
            <input class="campo_supervisor tam_11" placeholder="Correo Electrónico" />
            <input class="campo_supervisor tam_5" placeholder="No. Orden de Servicio"/>
    	</div> 
        
         <div class="linea_supervisor margen4"> 
            
            <div class="error tam_4">
            	Elija fecha de Nac
            </div>
            
            <div class="error tam_12">
            	Introduzca Teléfono
            </div>
            
            <div class="error margen5 tam_11">
            	Introduzca e-mail
            </div>
            <div class="error margen5 tam_5">
            	Introduzca orden de servicio
            </div>
        </div>
        
        <div class="linea_supervisor margen4">
            <input class="campo_supervisor tam_11" placeholder="Descripción" />
            <input class="campo_supervisor tam_8" placeholder="Cant" />
            <input class="campo_supervisor tam_5" placeholder="Médico"/>
          
           
            <input id="hr" class="campo_supervisor tam_8 margen5" />
          <input class="btn_solo agregar_icono" onclick="location.href='#';" />
    	</div>
        
        <div class="linea_supervisor margen4"> 
            
            <div class="error tam_11">
            	Escriba una Descripción
            </div>
            
            <div class="error tam_14">
            	Coloque Cant
            </div>
            
            <div class="error margen5 tam_9">
            	Seleccione Médico
            </div>
        
        </div>
        
<!--  Tabla final correcta -->
  	      <div class="tabla2 margen4 margen_arriba">
	       <div class="tabla2">   
	        <div class="titulo_tabla alto">
	        	<div class="celda2 fondo_v texto_supervisor blanco tam_19">Código-Descripción
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Cant
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Valor
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Aporte
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Copago
	            </div>
	            <div class="celda2 fondo_v texto_supervisor bote blanco tam_1 centrado">
	            </div>
	    	</div> 
	       </div>
	     
	    <div class="tabla_contenedor desplazar tam_14 centrado">
	      <div id="tablaPacientes" class="tabla2 tam_14">  
	        <div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_19">98765478
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">$1500.00
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">$1600.00
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_8 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_1 centrado bote link3">
	           
	            </div>
	    	</div> 
	    	
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_19">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_8 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_1 centrado bote link3">
	            </div>
	    	</div>
	    	
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_19">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_8 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_1 centrado bote link3">
	            </div>
	    	</div> 
	    	
	    		    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_19">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_8 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_1 centrado bote link3">
	            </div>
	    	</div>
	    	
	      </div>	 
	     </div> 

	    </div>  
   
 </div>
    
     
     
     <div class="linea_supervisor margen4">
          <div class="btn btn_cerrar"></div>
          <div class="btn btn_certificar"></div>
          <div class="btn_grande btn_nolista"></div>
     </div> 
     
     <!--  Tabla con inputs incorrecta -->
     <div class="tabla margen4 margen_arriba">
      
      <div class="tabla">  
         <div class="linea_tabla">
        	<div class="celda fondo_v  blanco tam_2"><input class="sin_borde_bg texto_supervisor blanco tam_13" placeholder="Código - Descripción" disabled />
            </div>
            <div class="celda fondo_v  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" placeholder="Cant" disabled />
            </div>
            <div class="celda fondo_v  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" placeholder="Valor" disabled />
            </div>
            <div class="celda fondo_v  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" placeholder="Aporte" disabled />
            </div>
            <div class="celda fondo_v  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" placeholder="Copago" disabled />
            </div>
            <div class="celda fondo_v bote tam_15">
            </div>
    	</div>
      </div>
      
     <div class="tabla_contenedor desplazar tam_14">
      <div class="tabla tam_14">  
    	<div class="linea_tabla">
        	<div class="celda fondo_g  blanco tam_2"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g bote tam_15 link3">
            </div>
    	</div>
    	
    	<div class="linea_tabla">
        	<div class="celda fondo_g  blanco tam_2"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" />
            </div>
            <div class="celda fondo_g bote tam_15 link3">
            </div>
    	</div>
    	
	  </div>
     </div>
  </div>
     
     
</div>





</form:form>


</body>
</html>