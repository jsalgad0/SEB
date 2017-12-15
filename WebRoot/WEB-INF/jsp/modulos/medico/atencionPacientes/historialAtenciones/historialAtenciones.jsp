<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>

<body style="overflow:hidden;">
<form:form name='historialAtencionesForm' action="inicioHistorialAtenciones" method='POST'
	commandName="historialAtencionesForm" id="historialAtencionesForm">
	<form:hidden path="idAtencion"/>
	<form:hidden path="idAfiliado" id="idAfiliadoHistorialAtencion" />
<div id="admin_contenido2">

   <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${infoSignos.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.edad} años
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${infoSignos.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${infoSignos.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>        
 
  <div class="admin_pleca2"></div> 
  <div class="linea_90">	
                    
    <div class="linea_supervisor margen margen_arriba4">

         <div class="titulo_supervisor verde tam_7">
          FECHA
         </div>
         
         <div class="titulo_supervisor verde tam_18">
          LUGAR DE ATENCIÓN
         </div>
		
        <div class="titulo_supervisor verde tam_7">
          PRESTACIÓN
         </div>

    </div>  
                 
    <div class="pleca_cont2"></div> 
 
    <div class="contenedor_supervisor margen" id="contenedorAtenciones">
		<c:forEach items="${historialAtencionesVos}" var="historialAtencionesVo">
	     	<div class="linea_supervisor">
	         	<div class="texto_chico2 gris tam_7">
	         	${historialAtencionesVo.fecha}
	         	</div>
	         	<div class="texto_chico2 verde tam_18 link2">
	          	${historialAtencionesVo.lugarAtencion}
	         	</div>
	         	<div class="texto_chico2 gris tam_7">
	          	${historialAtencionesVo.prestacion}
	         	</div>
	    	</div> 
		</c:forEach>    
         
	 </div>

     
		 <div class="demo margen margen_arriba4">
			<c:if test="${not empty historialAtencionesVos}">
				<div id="paginador">                   
				</div>		 		
			</c:if>
		 </div> 
     
     <div class="linea_supervisor margen">
            <div class="btn btn_cerrar" onclick="cerrar();"></div>
     </div>
  </div>
  
 </div>    

</form:form>
<script type="text/javascript">
	$(function() {
		$("#paginador").paginate({
			count : ${historialAtencionesForm.count},
			start : 1,
			display : ${historialAtencionesForm.display},
			border					: false,
			text_color  			: '#888',
			background_color    	: '#EEE',	
			text_hover_color  		: 'black',
			background_hover_color	: '#CFCFCF',
			mouse : 'press',
			onChange : function(page) {
				var idAfiliado = $("#idAfiliadoHistorialAtencion").val();
			    $.getJSON("paginadorHistorialAtenciones", {idAfiliado:idAfiliado, page:page} ,function(response){ 
			    	$("#contenedorAtenciones").empty(); 
		    		var filas = "";
		            $.each(response, function(index, item) {
		            	filas += '<div class="linea_supervisor"><div class="texto_chico2 gris tam_7">'+item.fecha+'</div>';
		            	filas += '<div class="texto_chico2 verde tam_18 link2">'+item.lugarAtencion+'</div>';
		            	filas += '<div class="texto_chico2 gris tam_7">'+item.prestacion+'</div></div>';
		    	     	$("#contenedorAtenciones").append(filas); 
		                filas = "";
		            });
			    });
			}
		});
	});
	
	$(document).ready(function(){
		$.ajaxSetup({cache: false});
	});
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}	
	
</script>

</body>

</html>