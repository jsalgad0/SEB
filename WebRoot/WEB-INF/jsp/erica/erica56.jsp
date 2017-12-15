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
   		<input type="file" class="campo_input tam_20" />
   		<div class="texto_supervisor5 verde tam_20 margen_arriba2">La carga del archivo puede tomar unos minutos, por favor, no interrumpa el proceso.</div>
    </div>

	
     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_cargar"></div>
     		<div class="btn_grande btn_log_proceso"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>