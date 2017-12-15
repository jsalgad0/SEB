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
<form:form name='historialPorDiagnosticoForm' action="historialDiagnosticoDetallado" method='POST'
	commandName="historialPorDiagnosticoForm" id="historialPorDiagnosticoForm">
	<form:hidden path="idAtencion" id="idAtencionHistorialDiagnostico"/>
	<form:hidden path="idDiagnostico"/>
	<form:hidden path="idAfiliado" id="idAfiliadoHistorialDiagnostico" />
                    
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

         <div class="titulo_supervisor verde">
          HISTORIAL DE ATENCIONES PARA EL DIAGNÓSTICO:
         </div>
        <div class="titulo_supervisor gris2">
          ${historialPorDiagnosticoForm.diagnostico}
         </div>

    </div>  
                 
    <div class="pleca_cont2"></div> 	
                       
    <div class="linea_supervisor margen margen_arriba4">

         <div class="titulo_supervisor verde tam_7">
          FECHA
         </div>
         
         <div class="titulo_supervisor verde tam_10">
          LUGAR DE ATENCIÓN
         </div>
		
        <div class="titulo_supervisor verde tam_9">
          PRESTACIÓN
         </div>

    </div>  
                 
    <div class="pleca_cont2"></div>                        
 
    <div class="contenedor_supervisor margen">
    
		<c:forEach items="${historialPorDiagnosticoVos}" var="historialPorDiagnosticoVo">
	     	<div class="linea_supervisor">
	         	<div class="texto_chico2 gris tam_7">
	         	${historialPorDiagnosticoVo.fecha}
	         	</div>
	         	<div class="texto_chico2 verde tam_10 link2" onclick="enviar(${historialPorDiagnosticoVo.idAtencion});">
	          	${historialPorDiagnosticoVo.lugarAtencion}
	         	</div>
	         	<div class="texto_chico2 gris tam_9">
	          	${historialPorDiagnosticoVo.prestacion}
	         	</div>
	    	</div> 
		</c:forEach> 
                     
	 </div>

     
		 <div class="demo margen margen_arriba4">
				 <div id="paginador">                   
				 </div>
		 </div> 
     
     <div class="linea_supervisor margen">
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>
  
 </div>    

</form:form>
<script type="text/javascript">
	$(function() {
		$("#paginador").paginate({
			count : ${historialPorDiagnosticoForm.count},
			start : 1,
			display : ${historialPorDiagnosticoForm.display},
			border					: false,
			text_color  			: '#888',
			background_color    	: '#EEE',	
			text_hover_color  		: 'black',
			background_hover_color	: '#CFCFCF',
			mouse : 'press',
			onChange : function(page) {
				var idDiagnostico = $("#idDiagnostico").val();
				var idAfiliado = $("#idAfiliadoHistorialDiagnostico").val();
			    $.getJSON("paginadorHistorialDiagnosticoDetallado", {idAfiliado:idAfiliado, idDiagnostico:idDiagnostico, page:page} ,function(response){ 
			    	$("#contenedorDiagnosticos").empty(); 
		    		var filas = "";
		            $.each(response, function(index, item) {
		            	filas += '<div class="linea_supervisor"><div class="texto_chico2 gris tam_7">'+item.fecha+'</div>';
		            	filas += '<div class="texto_chico2 verde tam_10 link2" onclick="enviar('+item.idAtencion+');">'+item.lugarAtencion+'</div>';
		            	filas += '<div class="texto_chico2 gris tam_9">'+item.prestacion+'</div></div>';
		    	     	$("#contenedorDiagnosticos").append(filas); 
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
	
	function enviar(idAtencion){
		$("#idAtencionHistorialDiagnostico").val(idAtencion);
		$("#historialPorDiagnosticoForm").submit();		
	}
</script>
</body>
</html>