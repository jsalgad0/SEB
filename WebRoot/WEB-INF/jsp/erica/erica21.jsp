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
	          SALVADOR CONTRERAS HERN�NDEZ
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	          <div class="texto_supervisor verde margen tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 a�os
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
	          �ltima Somatometr�a:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          12-noviembre-2015
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">CERTIFICADOS</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
   		
   	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	          RECETA M�DICA 
	    </div>
    </div>		
    
  	<div class="linea_supervisor margen">

            <input class="campo_supervisor tam_20" placeholder="Medicamento" />
            <div class="texto_chico3 gris tam_17 centrado">V�a de Administraci�n:</div>
            <select class="selectt campo_supervisor tam_3" name="Unidad">
            	<option>Unidad</option>
            	<option>Intradermica</option>
                <option>Subcut�nea</option>
                <option>Oral</option>
    		</select>
            <input class="campo_supervisor tam_16 centrado" placeholder="No." />
            
    </div>
    
    <div class="linea_supervisor margen">
    		<div class="texto_supervisor3 verde tam_17">Indicaciones:</div>
    		<input class="campo_supervisor tam_16 centrado" placeholder="No." />
            <input class="campo_supervisor tam_7" placeholder="Dosis" />		
    		<div class="texto_chico3 gris tam_16">cada</div>
    		<input class="campo_supervisor tam_16 centrado" placeholder="#" />
            <select class="selectt campo_supervisor tam_17" name="Unidad">
            	<option>Unidad</option>
            	<option>Hora</option>
                <option>D�a(s)</option>
    		</select>
    		<div class="texto_chico3 gris tam_1">durante</div>
    		<input class="campo_supervisor tam_16 centrado" placeholder="#" />
            <select class="selectt campo_supervisor tam_4" name="Unidad">
            	<option>Unidad</option>
            	<option>D�as</option>
                <option>Semanas</option>
    		</select>
             <input class="btn_solo agregar_icono margen14" onclick="location.href='#';" />
    </div>
    
    <div class="linea_supervisor margen">
   		<textarea class="campo_supervisor tam_23" placeholder="Observaciones"></textarea>
    </div>
    
    <div class="linea_corta margen">
   		<div class="linea_corta texto_arriba margen_arriba2 borde2 desplazar tam_14c">
			
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>
		 	
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>
		 	
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>	
		 	
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>
		 	
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>	
		 	
			<div class="linea_receta tam_14d">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">Tempra (medicamento)</div>
		            <div class="texto_supervisor tam_16 blanco centrado">2</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Pastillas</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">8</div>
		            <div class="texto_supervisor tam_17 blanco centrado">Horas</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">4</div>
		            <div class="texto_supervisor tam_6 blanco centrado">Semanas</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;">
	            </div>			
		 	</div>		 		 			 		 			 			 			 	
							
		 </div>
	</div>
	
	<div class="linea_supervisor margen margen_arriba2">
   		<div class="titulo_supervisor gris tam_3">Receta resurtible
	    	<input type="checkbox" name="recetar" />
	    </div>
	    <div class="texto_supervisor5 gris">cada</div>
    		<input class="campo_supervisor tam_16 centrado" placeholder="#" />
    	<div class="texto_supervisor5 gris">mes(es) durante</div>	
    		<input class="campo_supervisor tam_16 centrado" placeholder="#" />	
    	<div class="texto_supervisor5 gris">mes(es)</div>	
    </div>
	
     <div class="linea_supervisor margen margen_arriba2">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
            <div class="btn btn_receta_ant"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>