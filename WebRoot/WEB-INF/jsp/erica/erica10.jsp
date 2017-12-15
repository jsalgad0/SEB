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
                    
<div id="admin_contenido2">

   <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          SALVADOR CONTRERAS HERNÁNDEZ
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 años
	         </div>
	         
	         <div class="texto_supervisor verde tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          55 kg
	         </div>
			
	         <div class="texto_supervisor verde tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          170.00 cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          120/80
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          12-noviembre-2015
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
          GASTRITIS
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
    
     	<div class="linea_supervisor">

         	<div class="texto_chico2 gris tam_7">
         	05-Noviembre-2015
         	</div>
         
         	<div class="texto_chico2 verde tam_10 link2">
          	Hospital General XX de Noviembre
         	</div>
         	
         	<div class="texto_chico2 gris tam_9">
          	Consulta especialista
         	</div>
            
    	</div> 
    	
     	<div class="linea_supervisor">

         	<div class="texto_chico2 gris tam_7">
         	05-Noviembre-2015
         	</div>
         
         	<div class="texto_chico2 verde tam_10 link2">
          	Hospital General XX de Noviembre
         	</div>
         	
         	<div class="texto_chico2 gris tam_9">
          	Consulta médica
         	</div>
            
    	</div>     	

                     
	 </div>

     
		 <div class="demo margen margen_arriba4">
				 <div id="paginador">                   
				 </div>
		 </div> 
     
     <div class="linea_supervisor margen">
            <div class="btn btn_cerrar"></div>
     </div>
  </div>
  
 </div>    

</form:form>
<script src="js/jquery.paginate.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function() {
			
			$("#paginador").paginate({
				count 		: 10,
				start 		: 1,
				display     : 7,
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF'
			});
			
		});
		</script>

</body>
</html>