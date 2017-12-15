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
    
    <div id="admin_contenidoFrame3">
   	  <div class="linea_corta2">
   		<div class="pleca_menu"></div><!--     
        --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
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
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
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
   	  </div>
   	</div>  	  
    
    <div id="admin_contenido3">
      <div class="linea_corta_cont">
       <div class="linea_corta">
   		<div class="texto_supervisor2 verde margen2">
	          OTROS DIAGNÓSTICOS
	    </div>
	   </div>
			
		<div class="linea_corta margen_arriba4 margen2">
	    	<input class="campo_supervisor2 tam_10" placeholder="Ingrese Código CIE10 o descripción" />
	    	<input type="radio" name="def" />
	    	<div class="texto_supervisor5 blanco">Definitivo</div>
	    	<input class="margen2" type="radio" name="def" />
	    	<div class="texto_supervisor5 blanco">Presuntivo</div>
	    	 <input class="margen2 btn_solo agregar_icono" onclick="location.href='#';" />
	    </div>	
	    
	     <div class="linea_corta margen_arriba2 margen2">
		 <div class="tabla2 margen_arriba"> 
	     
	    <div class="tabla_contenedor desplazar alto_tabla centrado">

	        <div class="linea_tabla alto">
	        	<div class="celda2 fondo_v texto_supervisor blanco tam_10 centrado">Código
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_27 centrado">Descripción
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado bote">
	            </div>
	    	</div>
	    		       
	        <div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">333221
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Descripción1 2 3 4
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div>
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">2323
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div> 	
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div> 	
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div> 
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">2323
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div> 	
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div>
	    	<div class="linea_tabla alto">
	        	<div class="celda2 texto_supervisor blanco tam_10 centrado">23232
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_27 centrado">Texto simulado2234
	            </div>
	            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3">
	            </div>
	    	</div> 	
	 		 	    		 
	     </div> 

	    </div>

	    </div>
	 
	     <div class="linea_corta margen2 margen_arriba4">
	     	<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     	</div> 
	 
     </div>

    
    </div>
    
     
</div>

</form:form>


</body>
</html>