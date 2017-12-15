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
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/ante_familiares.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES FAMILIARES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/personales_nopat.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">PERSONALES NO PATOLÓGICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/ante_personales.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES PERSONALES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/gineco.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">GINECO-OBSTÉTRICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/exploracion_fisica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPLORACIÓN FÍSICA</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/tipo_familia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text3 texto_pestania"><a href="#">FASE CICLO VITAL / TIPO DE FAMILIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/familiograma.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">FAMILIOGRAMA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/expediente_digital.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPEDIENTE DIGITAL</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
   		
   		<div class="admin_contenidoFrame2">
				 <div class="contenedor_medio margen margen_arriba texto_arriba">
						<div class="titulo_HC fondo_v">
								<div class="texto_supervisor5 blanco fondo_v tam_9">FASE DEL CICLO VITAL</div>
						</div>
							
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Matrimonio</div>
					   <input type="checkbox" name="ciclo_vital" />
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Expansión</div>
					   <input type="checkbox" name="ciclo_vital" />
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Dispersión</div>
					   <input type="checkbox" name="ciclo_vital" />
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Independencia</div>
					   <input type="checkbox" name="ciclo_vital" />
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Retiro y Muerte</div>
					   <input type="checkbox" name="ciclo_vital" />
				   
				</div>
				
				<div class="contenedor_medio margen5 margen_arriba texto_arriba">
                	<div class="titulo_HC fondo_v">
                    	<div class="texto_supervisor5 blanco fondo_v tam_7">TIPO DE FAMILIA</div>
                	</div>
                                                                              
                	<div class="contenedor_medio margen8 margen_arriba2 texto_arriba">
                    	<ul>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia1" class="margen_der2" type="radio" value="1"><label for="idTipoFamilia1">Moderna</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia2" class="margen_der2" type="radio" value="2"><label for="idTipoFamilia2">Rural</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia3" class="margen_der2" type="radio" value="3"><label for="idTipoFamilia3">Integrada</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia4" class="margen_der2" type="radio" value="4"><label for="idTipoFamilia4">Nuclear</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia5" class="margen_der2" type="radio" value="5"><label for="idTipoFamilia5">Tradicional</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia6" class="margen_der2" type="radio" value="6"><label for="idTipoFamilia6">Urbana</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia7" class="margen_der2" type="radio" value="7"><label for="idTipoFamilia7">Semi-Urbana</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia8" class="margen_der2" type="radio" value="8"><label for="idTipoFamilia8">Extensa</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia9" class="margen_der2" type="radio" value="9"><label for="idTipoFamilia9">Desintegrada</label></li>
                        	<li class="texto_supervisor5 gris margen7 interlineado3"><input name="idTipoFamilia" id="idTipoFamilia10" class="margen_der2" type="radio" value="10"><label for="idTipoFamilia10">Extensa Compuesta</label></li>
                    	</ul>
                    </div>
                                                                              
                 </div>

		</div>
   		
		
		
		
	

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>