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
<body style="overflow:hidden;">
<form:form>

<div id="contenedor4_popup" style="margin-left:40px; margin-top:30px;">

	<div class="icon_popup usuario1"></div>
	<div class="linea_popup"><h1>RESULTADO DE BÚSQUEDA DE ATENCIONES</h1></div>
    
    <div class="tabla_popup">   
        <div class="fila_popup">
        	<div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">Nombre
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo4">Fecha Nac
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">ID Interno
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo2">RFC
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo10">Fecha Atn
            </div>
    	</div> 
	</div>
	
 <div class="tabla_contenedor_popup desplazar tam_22">	
	<div class="tabla_popup">

		<c:forEach items="${listaAtencionesForm.atenciones}" var="atenciones" varStatus="varStatus" > 
							 <div class="fila_popup link" onclick="cargarAtencion('${atenciones.folio}')">
						        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">${atenciones.nombre} ${atenciones.apellidoPaterno} ${atenciones.apellidoMaterno}  
						            </div>
						            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">${atenciones.fechaDeNacimiento}
						            </div>
						            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">${atenciones.idIdentificador}
						            </div>
						            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">${atenciones.descripcionIdentificador}
						            </div>
						            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo10">${atenciones.fechaDeAtencion}
						            </div>
    						</div> 
		    			</c:forEach>
     </div>
  </div>

   <div class="btn_popup margen_fijo1">
   		    <div class="btn_cerrar_popup" onclick="cerrar();" ></div>
   </div>
</div>
</form:form>

<script>
cargarAtencion = function(folio){
	window.top.$("#folio").val(folio);
	parent.$.fancybox.close();
}

cerrar = function(){
	parent.$.fancybox.close();
}
</script>
</body>
</html>