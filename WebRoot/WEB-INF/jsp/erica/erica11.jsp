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
 
   	<div class="admin_contenidoFrame2">
   		<div class="linea_corta2">
   		<div class="pleca_menu"></div><!--     
        --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li  class="pactivaroja"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
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
   		
				 <div class="contenedor_medio margen texto_arriba">
					<div class="titulo_HC fondo_r">
								<div class="texto_supervisor5 blanco fondo_r tam_7">PRESTACIONES SOLICITADAS</div>
					</div>
						
						<div class="linea_corta margen5 margen_arriba texto_arriba borde2 desplazar tam_14b">
								
							<input class="campo_supervisor3 tam_8 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_11" placeholder="Descripción" />
							
							<input class="campo_supervisor3 tam_8 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_11" placeholder="Descripción" />
							
							<input class="campo_supervisor3 tam_8 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_11 margen_abajo1" placeholder="Descripción" />
							
			   		  </div>
			   		  
			   		  <div class="linea_corta margen5 margen_arriba texto_arriba borde1">
								
							<div class="texto_supervisor4 verde margen2 margen_arriba">
	          					REFERENCIA ESPECIALISTA
	         				</div>
							<input class="campo_supervisor3 tam_10 margen2 margen_abajo1" placeholder="ESPECIALIDAD" />
							
			   		  </div>
			   		  
					  <div class="linea_corta margen5 margen_arriba texto_arriba borde2 desplazar alto_tabla3">
								
						 <div class="linea_supervisor margen">
					   		<textarea class="campo_supervisor tam_25" placeholder="Descripción de Informe o Resultados"></textarea>
					   		<div class="texto_supervisor gris tam_6">
									<div class="btn btn_subir"></div>
					        </div>  
					    </div>
							<iframe width="100%" height="330" src="${pageContext.request.contextPath}/resources/img/ejemplo_expediente.pdf"></iframe>	
			   		  </div>			   		  
				   
				</div>
				
				<div class="contenedor_medio margen5 texto_arriba">
					<div class="titulo_HC fondo_r">
							<div class="texto_supervisor5 blanco fondo_r tam_12">RECETA</div>
							<div class="texto_chico2 blanco fondo_r tam_12">FECHA:20-nov-2015</div>
							<div class="texto_chico2 blanco fondo_r tam_12">FOLIO:154345678</div>
					</div>
					
					<div class="linea_corta margen5 margen_arriba texto_arriba borde1">
								
							<div class="linea_supervisor texto_chico3 verde margen2">MEDICAMENTO
								<input class="campo_supervisor3 tam_11" placeholder="Nombre medicamento" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2">INDICACIONES
								<input class="campo_supervisor3 tam_11" placeholder="Indicaciones" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2 margen_abajo1">ENTREGADOS
								<input class="campo_supervisor3 tam_11 margen5" placeholder="Cant. entregada" />
							</div>
							
			   		</div>

					<div class="linea_corta margen5 margen_arriba texto_arriba borde1">
								
							<div class="linea_supervisor texto_chico3 verde margen2">MEDICAMENTO
								<input class="campo_supervisor3 tam_11" placeholder="Nombre medicamento" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2">INDICACIONES
								<input class="campo_supervisor3 tam_11" placeholder="Indicaciones" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2 margen_abajo1">ENTREGADOS
								<input class="campo_supervisor3 tam_11 margen5" placeholder="Cant. entregada" />
							</div>
							
			   		</div>

					<div class="linea_corta margen5 margen_arriba texto_arriba borde1">
								
							<div class="linea_supervisor texto_chico3 verde margen2">MEDICAMENTO
								<input class="campo_supervisor3 tam_11" placeholder="Nombre medicamento" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2">INDICACIONES
								<input class="campo_supervisor3 tam_11" placeholder="Indicaciones" />
							</div>
							<div class="linea_supervisor texto_chico3 verde margen2 margen_abajo1">ENTREGADOS
								<input class="campo_supervisor3 tam_11 margen5" placeholder="Cant. entregada" />
							</div>
							
			   		</div>		   					   		
			   		
				</div>
			</div>	
		</div>
	
	<div class="linea_supervisor margen">
			<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>   
</div>

</form:form>


</body>
</html>