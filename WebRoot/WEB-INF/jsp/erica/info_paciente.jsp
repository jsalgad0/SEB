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
<body>
<form:form>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor back_paciente margen_arriba2" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          SALVADOR CONTRERAS HERNÁNDEZ
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 años
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          55 kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
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
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="admin_pleca"></div>
                    
    <div class="linea_97 margen4 margen_arriba2">

         <div class="titulo_supervisor verde margen7 tam_10">
          NOMBRE PACIENTE
         </div>
         
         <div class="titulo_supervisor verde tam_6">
          HORA
         </div>
		
        <div class="titulo_supervisor verde tam_6">
          ACCION
         </div>
         
         <div class="titulo_supervisor verde margen tam_1">
          ESTADO
         </div>

    </div>  
                 
    <div class="pleca_simple"></div> 
    
     <div class="linea_97 margen4 margen_arriba2">

         <div class="titulo_supervisor verde margen7 tam_10">
          LISTA DE PACIENTES EN ESPERA
         </div>
           
                 
    <div class="pleca_simple"></div> 
    
    <div class="contenedor_supervisor margen4">
    
     	<div class="linea_97">

         	<div class="texto_supervisor gris margen7 tam_10">
         	ERIKA CONTRERAS HERNÁNDEZ
         	</div>
         
         	<div class="texto_supervisor gris tam_6">
          	15:00
         	</div>
		
        	<div class="texto_supervisor gris tam_6">
				<div class="btn btn_atender"></div>
         	</div>
            
            <div class="texto_supervisor gris margen tam_1 centrado">
            	<div class="estado edo1"></div>
         	</div>
            
    	</div> 
    	
    	<div class="linea_97">

         	<div class="texto_supervisor gris margen7 tam_10">
         	FERNANDO CARBAJAL PÉREZ
         	</div>
         
         	<div class="texto_supervisor gris tam_6">
          	15:00
         	</div>
		
        	<div class="texto_supervisor gris tam_6">
				<div class="btn btn_atender"></div>
         	</div>
            
            <div class="texto_supervisor gris margen tam_1 centrado">
            	<div class="estado edo3"></div>
         	</div>
            
    	</div> 

                     
	 </div>
			
	<div class="linea_97 margen7 margen_arriba2">
            <div class="texto_supervisor">Atendidos</div>
            <div class="estado edo1"></div>
            <div class="texto_supervisor margen6">En espera</div>
            <div class="estado edo3"></div>
     </div>
     
		 <div class="demo margen7 margen_arriba2">
				 <div id="paginador">                   
				 </div>
		 </div> 
     
     <div class="linea_97 margen7">
            <div class="btn btn_cerrar"></div>
     </div>
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