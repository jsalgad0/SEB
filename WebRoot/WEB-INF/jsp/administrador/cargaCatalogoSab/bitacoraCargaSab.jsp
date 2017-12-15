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

<body style="overflow:hidden; background-color:#FFF;">

<form:form>
                    
<div id="contenedor_popup" class="centrar">

 <!-- para Log -->


<!-- para el width:520px; height:380px; !-->
	
	<div class="contenido_log scroll_popup">
   		<div class="caja_popup">
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Estatus archivo o carga</div>
   				<div class="cell_popup tam_log1">Filas con error</div>
   				<div class="cell_popup tam_log3">Prestaciones leídas</div>
   			</div>
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>
   			
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>
   			
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>
   			<div class="row_popup">
   				<div class="cell_popup tam_log2">Fallido</div>
   				<div class="cell_popup tam_log1">30</div>
   				<div class="cell_popup tam_log3">600</div>
   			</div>

   		</div>
 	</div>

   
   <div class="btn_popup"> 
   		<div class="linea_botones_popup2">
        	<div class="btn_regresar_popup margen_popup" onclick="parent.$.fancybox.close();"></div>
        	<div class="btn_imprimir_popup"></div>
        </div>    
   </div>


</div>    

</form:form>

</body>

</html>