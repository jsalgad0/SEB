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
    
    <div id="admin_contenido3">
      <div class="linea_corta_cont">
       <div class="linea_corta">
   		<div class="texto_supervisor2 verde margen2">
	          SIGNOS VITALES
	    </div>
	   </div>
			
		<div class="linea_corta margen_arriba2 margen2">
	    	<input type="radio" name="signos" />
	    	<div class="texto_supervisor5 blanco">1a. vez</div>
	    	<input class="margen2" type="radio" name="signos" />
	    	<div class="texto_supervisor5 blanco">Subsecuente</div>
	    </div>	
	    
	     <div class="linea_corta margen_arriba2 margen2">
	    	<input class="campo_supervisor2 tam_8" placeholder="Peso*" /><!--
	    	--><input class="campo_1 tam_16" placeholder="Kg." disabled />
	    	
	    	<input class="campo_supervisor2 tam_8 margen6" placeholder="Altura*" /><!--
	    	--><input class="campo_1 tam_16" placeholder="cm." disabled />
	    	
	    	<input class="campo_supervisor2 tam_8 margen6" placeholder="T.A.*" /><!--
	    	--><input class="campo_1 tam_8" placeholder="mmHG" disabled />
	    	
	    	<input class="campo_supervisor2 tam_17 margen6" placeholder="Temperatura*" /><!--
	    	--><input class="campo_1 tam_16" placeholder="ºC" disabled />
	    	
	    	<input class="campo_supervisor2 tam_8 margen6" placeholder="IMC" /><!--
	    	--><input class="campo_1 tam_1" placeholder="kg/m2 &sup2;" disabled />
	    
	    </div>
	    
	     <div class="linea_corta margen2">
	    	<div class="error2 tam_8">Indique Peso</div>

	    	<div class="error2 tam_8 margen2">Indique Altura</div>

	    	<div class="error2 tam_8 margen">Indique TA</div>
	    	
	    	<div class="error2 tam_12 margen12">Indique Temperatura</div>
	    	
	    </div>
	    
	    
	     <div class="linea_corta margen_arriba2 margen2">
	     	    	
	    	<input class="campo_supervisor2 tam_8" placeholder="Frec. Resp." /><!--
	    	--><input class="campo_1 tam_8" placeholder="resp/min" disabled />
	    
	    	<input class="campo_supervisor2 tam_17 margen6" placeholder="Frec. Cardiaca" /><!--
	    	--><input class="campo_1 tam_8" placeholder="lat/min" disabled />
	    	
	    	<input class="campo_supervisor2 tam_6 margen6" placeholder="Glucosa" /><!--
	    	--><input class="campo_1 tam_8" placeholder="mg/dl" disabled />
	    	
	    	<input class="campo_supervisor2 tam_8 margen6" placeholder="Cintura" /><!--
	    	--><input class="campo_1 tam_16" placeholder="cm." disabled />
	 
	    </div>
	    
	    <div class="linea_corta margen2">
	    	<div class="error2 tam_8"></div>
	    </div>
	    
	   <div class="linea_corta margen_arriba2 margen2">
	    	
	    	<input class="campo_supervisor2 tam_12" style="height:1.8em;" placeholder="Sat. Oxígeno" /><!--
	    	--><input class="campo_1 tam_16" style="height:1.83em;" placeholder="%." disabled />
	    </div>
	   
	    
	   <div class="linea_corta margen_arriba2 margen2">
	   		<div class="texto_supervisor5 blanco">Tipo de sangre:</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 blanco">A</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 blanco">B</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 blanco">AB</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 blanco">O</div>
	    	<input class="margen2" type="radio" name="+-" />
	    	<div class="texto_supervisor5 blanco">Positivo</div>
	    	<input class="margen2" type="radio" name="+-" />
	    	<div class="texto_supervisor5 blanco">Negativo</div>
	    </div> 
	    
	    <div class="linea_mini margen2">

				<div class="texto_supervisor4 blanco margen_arriba5">
		          OBSERVACIONES
		        </div>
		        <textarea class="campo_supervisor2 comment izquierdo" name="comentarios" rows="2">...Observaciones...</textarea>
     	</div>   
	    
	     <div class="linea_corta margen2 margen_arriba">
	     	<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     	</div> 
	 
     </div>
     
         <div class="linea_mini">
     		<div class="texto_supervisor5 blanco margen_arriba">[Última revisión: 05-Nov-2015]</div>
     		
     		<div class="linea_corta margen">
     			
				<div class="texto_supervisor4 blanco margen_arriba5">
		          ALERTAS
		         </div>
		         
		         <div class="texto_chico">
		          Su IMC es de 24.22
		         </div>
		         
		          <div class="texto_chico">
		          Peso Normal entre 18.5 y 24.9
		         </div>
		         
		         <div class="texto_chico">
		          Su Temperatura es de 37.00
		         </div>
		         
		         <div class="texto_chico">
		          Tensión arterial normal
		         </div>
	         
	         </div>
     	</div>	

    
    </div>
    
     
</div>

</form:form>


</body>
</html>