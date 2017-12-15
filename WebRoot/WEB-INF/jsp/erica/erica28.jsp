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
   <div class="pleca_menu"></div>
   		
	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	         RESUMEN DE LA ATENCIÓN
	    </div>
    </div>
    
    		<div class="linea_supervisor margen">
					<div class="linea_90 margen5 margen_arriba4 texto_arriba borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
							
							<div class="titulo_supervisor gris tam_12 margen2">
	    					</div>
							<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
			   		 </div> 
			   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
			   		 	<input class="btn_solo btn_imprimir" onclick="location.href='#';" /> 
			   		</div> 	  
    		</div> 
    		
    		<div class="linea_supervisor margen">
					<div class="linea_90 margen5 margen_arriba4 texto_arriba borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Medicamentos
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_2" placeholder="Descripción" />
							<input class="campo_supervisor3 tam_2" placeholder="Indicaciones" />
							
							<div class="titulo_supervisor gris tam_12 margen2">Medicamentos
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_2" placeholder="Descripción" />
							<input class="campo_supervisor3 tam_2" placeholder="Indicaciones" />							
			   		 </div>
			   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
			   		 	<input class="btn_solo btn_imprimir" onclick="location.href='#';" /> 
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
							<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
			   		 </div>
			   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
			   		 	<input class="btn_solo btn_imprimir" onclick="location.href='#';" /> 
			   		</div> 	    	
			</div>
			
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Est. de gabinete
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
							
							<div class="titulo_supervisor gris tam_12 margen2">
	    					</div>
							<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
			   		 </div>
			   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
			   		 	<input class="btn_solo btn_imprimir" onclick="location.href='#';" /> 
			   		</div> 	    	
			</div>	
			
    		<div class="linea_supervisor margen">
    				<div class="linea_90 margen5 margen_arriba4 borde4 desplazar tam_14e">
							<div class="titulo_supervisor gris tam_12 margen2">Otros estudios
	    					</div>	
							<input class="campo_supervisor3 tam_6 margen5" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
							
							<div class="titulo_supervisor gris tam_12 margen2">
	    					</div>
							<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" />
							<input class="campo_supervisor3 tam_20" placeholder="Observaciones" />
			   		 </div>
			   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
			   		 	<input class="btn_solo btn_imprimir" onclick="location.href='#';" /> 
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
							<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
			   		 </div>	    	
			</div>
			
    		<div class="linea_supervisor margen">
    				<div class="contenedor_medio margen5 margen_arriba4 borde4 tam_14f">
							<div class="titulo_supervisor gris tam_17 margen2">Referencia a:
	    					</div>	
								<input class="campo_supervisor3 tam_5" placeholder="Especialidad" />

							<input class="btn_solo btn_imprimir margen5" onclick="location.href='#';" />
					</div>
					<div class="linea_mini3 margen5 borde4 tam_14f">		
							<div class="texto_chico3 verde margen14 link2">Cuidados maternales
							</div>
								<input class="campo_transparente tam_0" />
							<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
					</div>
					<div class="linea_mini3 margen5 borde4 tam_14f">		
							<div class="texto_chico3 verde margen14 link2">Constancia de salud
							</div>
								<input class="campo_transparente tam_0" />
							<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
			   		 </div>	    	
			</div>								
	
     <div class="linea_supervisor margen">
            <div class="btn btn_imprimir_todo"></div>
            <div class="btn btn_cerrar_atn"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>