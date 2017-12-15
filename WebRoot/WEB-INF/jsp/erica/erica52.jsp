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
   		<input class="campo_supervisor tam_2" placeholder="Código Lugar de atención *" />
   		<input class="campo_supervisor tam_10" placeholder="Lugar de atención *" />
    </div>
    
    	<div class="linea3 margen">
			<div class="error tam_2">Es necesario llenar este campo</div>
			<div class="error tam_10">Es necesario llenar este campo</div>
		</div>
    
    <div class="linea_supervisor margen">
	    <select class="selectt campo_supervisor tam_2" name="Nombre Asegurador *">
	            	<option>Nombre Asegurador</option>
	            	<option>2</option>
	                <option>3</option>
	    </select>

	    <select class="selectt campo_supervisor tam_7 name="RFC Prestador *">
	            	<option>RFC Prestador</option>
	            	<option>COHE840921LW5</option>
	                <option>3</option>
	    </select>
	    
	    <input class="campo_supervisor tam_10" placeholder="Nombre prestador *" />
	 </div>
	 
	 <div class="linea3 margen">
			<div class="error tam_2">Es necesario llenar este campo</div>
			<div class="error tam_7">Es necesario llenar este campo</div>
			<div class="error tam_10">Es necesario llenar este campo</div>
	 </div>
    
    <!--  
    <div class="linea_supervisor margen">
     		<div class="btn btn_registrar_convenio"></div>
     </div> 
	-->
  
	<div class="linea_supervisor margen margen_arriba2">
		
		<input class="campo_supervisor tam_18" placeholder="Nombre del convenio *" />
   		<input class="campo_supervisor tam_19" placeholder="Identificador del convenio según el Asegurador *" />
   		
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_18">Es necesario llenar este campo</div>
			<div class="error tam_19">Es necesario llenar este campo</div>
	 </div>
    
   <div class="linea_supervisor margen">
		
		<input class="campo_supervisor tam_4"  style="margin-right:0px !important;" placeholder="Vigencia inicial"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" /> 
          
          <input class="campo_supervisor tam_4"  style="margin-right:0px !important;" placeholder="Vigencia final"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />       
	</div>
	
	    <div class="linea3 margen">
			<div class="error tam_4">Indicar fecha inicial</div>
			<div class="error tam_4 margen4">Indicar fecha final</div>
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