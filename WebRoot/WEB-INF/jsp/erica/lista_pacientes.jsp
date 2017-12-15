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

<div id="contenedor_gral">
<div id="admin_contenido">
 
<div class="admin_titulo">LISTA DE PACIENTES EN ESPERA</div><!--
    --><div class="admin_pleca"></div>  
  
  <div class="linea_supervisor">
  
  	<input class="campo_supervisor tam_5 margen13" style="margin-right:0px !important;" placeholder="Apellido Paterno"/><!--
          --><input class="btn_solo buscar_icono" onclick="location.href='#';" />
  
   	<input class="campo_supervisor tam_4 margen"  style="margin-right:0px !important;" placeholder="Fecha"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />
    
    <select class="selectt campo_supervisor tam_4 margen" name="Por_atender">
            	<option>Por atender</option>
            	<option>2</option>
                <option>3</option>
    </select>	
                    
    <div class="linea_supervisor margen13 margen_arriba4">

         <div class="titulo_supervisor verde tam_10">
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
                 
    <div class="pleca_sinmargen"></div> 
 
    <div class="contenedor_supervisor margen13">
    
     	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
         	ERIKA CONTRERAS HERNÁNDEZ
         	</div>
         
         	<div class="texto_supervisor gris tam_6">
          	15:00
         	</div>
		
        	<div class="texto_supervisor gris tam_6">
				<div class="btn btn_atendido"></div>
         	</div>
            
            <div class="texto_supervisor gris margen tam_1 centrado">
            	<div class="estado edo1"></div>
         	</div>
            
    	</div> 
    	
    	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
         	FERNANDO CARBAJAL PÉREZ
         	</div>
         
         	<div class="texto_supervisor gris tam_6">
          	15:00
         	</div>
		
        	<div class="texto_supervisor gris tam_6">
				<div class="btn btn_atendido"></div>
         	</div>
            
            <div class="texto_supervisor gris margen tam_1 centrado">
            	<div class="estado edo3"></div>
         	</div>
            
    	</div> 
    	
    	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
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
    	
    	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
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
    	
    	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
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
    	
    	<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
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
    	
		<div class="linea_supervisor">

         	<div class="texto_supervisor gris tam_10">
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
                     
	 </div>
			
	<div class="linea_supervisor margen13 margen_arriba4">
            <div class="texto_supervisor">Atendidos</div>
            <div class="estado edo1"></div>
            <div class="texto_supervisor margen6">En espera</div>
            <div class="estado edo3"></div>
     </div>
     
		 <div class="demo margen13 margen_arriba4">
				 <div id="paginador">                   
				 </div>
		 </div> 
     
     <div class="linea_supervisor margen13">
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