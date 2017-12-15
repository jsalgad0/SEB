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
    		
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	        
		</div>
    </div>        
 
  <div class="admin_pleca2"></div> 
  <div class="linea_90">	
                    
    <div class="linea_supervisor margen margen_arriba4">

         <div class="titulo_supervisor verde tam_7 margen5">
          FECHA DE ATENCIÓN
         </div>
         
         <div class="titulo_supervisor verde tam_26">
          PACIENTE
         </div>
		
        <div class="titulo_supervisor verde tam_7">
          AUTORIZACIÓN
         </div>

    </div>  
                 
    <div class="pleca_cont2"></div> 
 
    <div class="contenedor_supervisor margen">
    
     	<div class="linea_supervisor">

         	<div class="texto_chico2 gris tam_7 margen5">
         	05-Noviembre-2015
         	</div>
         
         	<div class="texto_chico2 verde tam_26 link2">
          	SAÚL VERA RODRIGUEZ
         	</div>
         	
         	<div class="texto_supervisor gris tam_1">
            	<input class="image_btn btn_pendiente margen_izq1" disabled />
         	</div>
            
    	</div> 
    	
     	<div class="linea_supervisor">

         	<div class="texto_chico2 gris tam_7 margen5">
         	05-Noviembre-2015
         	</div>
         
         	<div class="texto_chico2 verde tam_26 link2">
          	ILEANA SANDOVAL ORTIZ
         	</div>
         	
         	<div class="texto_supervisor gris tam_1">
            	<input class="image_btn btn_palomasi margen_izq1" disabled />
         	</div>
            
    	</div> 
    	
    	<div class="linea_supervisor">

         	<div class="texto_chico2 gris tam_7 margen5">
         	05-Noviembre-2015
         	</div>
         
         	<div class="texto_chico2 verde tam_26 link2">
          	ALFONSO CAMARENA PEREZ
         	</div>
         	
         	<div class="texto_supervisor gris tam_1">
            	<input class="image_btn btn_x margen_izq1" disabled />
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