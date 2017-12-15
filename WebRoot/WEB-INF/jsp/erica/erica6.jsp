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
<body style="overflow:hidden;" class="centrar fondo_claro_popup">
<form:form>

<div id="contenedor_popup" class="centrar">
<!-- para el width:520px; height:380px; !-->
	
   	<input type="file" class="campo_popup campo_size1_popup margen_arriba_popup2" />

   	<div class="text_popup2 campo_size1_popup margen_arriba_popup">La carga del archivo puede tomar unos minutos, por favor, no interrumpa el proceso.</div>
   
   <div class="btn_popup margen_arriba_popup">

   		<div class="linea_botones_popup2">
   			<div class="btn_cargar_popup"></div>
     		<div class="btn_log_popup"></div>
        	<div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
        </div>    
   </div>
   
 
 
   
   
</div>
    
   


</form:form>


</body>
</html>