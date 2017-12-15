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
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
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
   				
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/terminar.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCIÓN</a></li>
   					</ul>
   				</li>   				
   			</ul>
   		</div>
   		
	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	         TERMINAR ATENCIÓN
	    </div>
    </div>
    
    		<div class="linea_supervisor margen">
					<div class="linea_90 margen5 margen_arriba4 texto_arriba borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
			   		 </div>    
    		</div> 
    		
    		<div class="linea_supervisor margen">
					<div class="linea_90 margen5 margen_arriba4 texto_arriba borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Medicamentos
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_2" placeholder="Descripción" />
							<input class="campo_supervisor3 tam_2" placeholder="Indicaciones" />
														
			   		 </div>		   		     
    		</div>
    		
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Exám. Clínicos
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
							
							<div class="titulo_supervisor gris tam_12 margen2">
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
	
			   		 </div>	    	
			</div>
			
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Est. de gabinete
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
							
				
			   		 </div>	    	
			</div>	
			
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 desplazar tam_14f">
							<div class="titulo_supervisor gris tam_12 margen2">Otros estudios
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
					
			   		 </div>	    	
			</div>
			
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 tam_14f">
							<div class="titulo_supervisor gris tam_12 margen2">Licencia médica
	    					</div>	
							<div class="texto_chico3 verde margen5">Motivo
								<input class="campo_supervisor3 tam_4" placeholder="Motivo" />
							</div>
							<div class="texto_chico3 verde">Carácter
								<input class="campo_supervisor3 tam_4" placeholder="Carácter" />
							</div>
							
							<div class="texto_chico3 verde">Desde
								<input class="campo_supervisor3 tam_8" placeholder="fecha" />
							</div>
							
							<div class="texto_chico3 verde">Hasta
								<input class="campo_supervisor3 tam_8" placeholder="fecha" />
							</div>
			   		 </div>	    	
			</div>
			
    		<div class="linea_supervisor margen">
    				<div class="contenedor_medio margen5 margen_arriba4 borde4 tam_14f">
							<div class="titulo_supervisor gris tam_17 margen2">Referencia a:
	    					</div>	
								<input class="campo_supervisor3 tam_5" placeholder="Especialidad" />

					</div>
					<div class="linea_mini3 margen5 borde4 tam_14f">		
							<div class="texto_chico3 verde margen14 link2">Cuidados maternales
							</div>
								<input class="campo_transparente tam_0" />
					</div>
					<div class="linea_mini3 margen5 borde4 tam_14f">		
							<div class="texto_chico3 verde margen14 link2">Constancia de salud
							</div>
								<input class="campo_transparente tam_0" />
			   		 </div>	    	
			</div>											
	
     <div class="linea_supervisor margen margen_arriba3">
            <div class="btn btn_firmar"></div>
            <div class="btn btn_cerrar_resumen"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>