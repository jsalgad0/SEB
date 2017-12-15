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
 		<div class="pleca_menu_thin"></div>
  
	<div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="No. de serie externo *" />
   		<input class="campo_supervisor tam_10" placeholder="No. de serie interno" />
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_2">Es necesario llenar este campo</div>
	 </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="Código Lugar de atención *" />
   		<input class="campo_supervisor tam_10" placeholder="Lugar de atención *" />
    </div>
    
     <div class="linea3 margen">
			<div class="error tam_2">Es necesario llenar este campo</div>
			<div class="error tam_10">Es necesario llenar este campo</div>
	 </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="RFC Propietario *" />
   		<input class="campo_supervisor tam_10" placeholder="Nombre propietario" />
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_2">Es necesario llenar este campo</div>
	</div> 
	     

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar"></div>
     		<div class="btn btn_nuevo_lector"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>