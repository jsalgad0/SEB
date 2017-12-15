<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">



<html>
<body style="overflow:hidden;">
<form:form>
<div id="contenedor_popup">
<!-- para el width:520px; height:380px; !-->
	<div class="icon_popup huella"></div>
   	<input class="campo_popup campo_size1_popup" placeholder="Motivo de Autorización/Rechazo">
   
   <div class="btn_popup">
   		<div class="linea_botones_popup">
   			<div class="btn_aceptar_popup" onclick="parent.$.fancybox.close();"></div>
            <div class="margen2_popup"></div> 
        	<div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
        </div>    
   </div>
    
   
</div>

</form:form>


</body>
</html>