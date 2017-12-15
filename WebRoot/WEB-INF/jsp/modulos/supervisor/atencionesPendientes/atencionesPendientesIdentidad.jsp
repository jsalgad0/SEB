<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
</head>
<body>
<div id="contenedor_gral">
<div id="admin_contenido">
	<form:form name='supervisorAtencionesPendientesForm' action="actualizarAtencionMedicaIdentidad" method='POST'
		commandName="supervisorAtencionesPendientesForm" id="supervisorAtencionesPendientesForm">
		<form:hidden path="idAfiliado"/>
		<form:hidden path="idAtencionMedica"/>
		<form:hidden path="finalizo"/>
		
		<div class="admin_titulo">AUTORIZACIÓN DE HUELLA</div>
	    	
	    <div class="admin_pleca"></div>
	                    
	    <div class="linea_supervisor">
	        
	
	    </div>   
	    
	    <div class="contenedor_supervisor">
	    
	     	<div class="linea_supervisor margen4">
	        
	        	<form:input path="rfc" class="campo_supervisor tam_5" placeholder="RFC" readonly="true" />
	            <form:input path="nombre" class="campo_supervisor tam_7" placeholder="Nombre" readonly="true" />
	            <form:input path="apellidoPaterno" class="campo_supervisor tam_5" placeholder="Apellido Paterno" readonly="true" />
	            <form:input path="apellidoMaterno" class="campo_supervisor tam_5" placeholder="Apellido Materno" readonly="true" />
	    	</div>
	        
	        <div class="linea_supervisor margen4"> 
	        	<div class="error tam_5">
	            	
	            </div>
	            
	            <div class="error tam_7">
	            	
	            </div>
	            
	            <div class="error tam_5">
	            	
	            </div>
	            
	            <div class="error tam_5">
	            	
	            </div>
	        
	        </div> 
	        
	        <div class="linea_supervisor margen4">
	        
	        	<form:select path="idCausa" class="selectt campo_supervisor tam_5" name="Temporal">
	        		<form:option value="-1" label="Tipo Causal" />
	        		<form:options items="${causas}" itemLabel="causa" itemValue="causaId" />
	            </form:select>
	            
	            <div class="texto_supervisor gris tam_7" style="font-family:quicksand-bold">
	             		TIPO DE AUTORIZACIÓN:
	                </div>
	            
	            <div class="texto_supervisor gris tam_2">
	            	<form:radiobutton path="idTipoAutorizacion" label="TEMPORAL" value="1" onclick="fechas('1')"/>
	            	<form:radiobutton path="idTipoAutorizacion" label="PERMANENTE" value="2" onclick="fechas('2')"/>
	         	</div>
	
	    	</div>
	        
	        <div class="linea_supervisor margen4">
	        	<div class="error tam_5">
	            	<form:errors path="idCausa" />
	            </div>
	            
	            <div class="error tam_7">
	            	<form:errors path="idTipoAutorizacion" />
	            </div>
	 
	        </div>
	        
	        <div class="linea_supervisor margen4" id="divFechas" >
	        	<div class="texto_supervisor gris tam_6">
	          	Fecha inicio
	         	</div>
	            
	            <form:input path="fechaInicio" class="campo_supervisor gris tam_12 negrita" placeholder="Fecha Inicio" readonly="true"/>
	     
	            <div class="texto_supervisor gris tam_8">
	          	Fecha fin
	         	</div>
	            
	            <form:input path="fechaFin" class="campo_supervisor gris tam_4 datepicker" placeholder="Fecha Fin" value="" readonly="true" />
	        
	        </div>
	        
	        <div class="linea_supervisor margen4">
	        	<div class="error tam_5">
	            </div>
	            
	            <div class="error tam_7">
	            	<form:errors path="fechaFin" />
	 
		        </div>
		        
		        <div class="linea_supervisor margen_arriba">
		        	<div class="btn btn_guardar" onclick="enviar();"></div>
		            <div class="btn btn_cerrar" onclick="cerrar();"></div>
		        </div>      
			</div>
		</div>
 	</form:form>
</div>
</div>
<script type="text/javascript">

	$( document ).ready(function() {
		var finalizo = $("#finalizo").val();
		if (finalizo == "true") {
			irAtencionesPendientes();
		}
		
	});
	
	$(function() {
	    $( ".datepicker" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      minDate:0
	    });
	});
	
	function enviar(){
		$("#supervisorAtencionesPendientesForm").submit();
	}
	
	function cerrar(){
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup ask\"></div><h1>No se ha guardado la Autorización Especial, desea continuar</h1><div class=\"btn_popup\"><div class=\"btn_si_popup\" onclick=\"irAtencionesPendientes();\"></div><div class=\"btn_no_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
			type : 'iframe',
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			autoSize: false,
			width: 500,
			height: 190
		});
	}
	
	function irAtencionesPendientes(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/supervisor/inicioAtencionesPendientes";
	}

	function fechas(tipo){
		if(tipo == '1'){
		$("#divFechas").show();
		}else{
		$("#divFechas").hide();
		}	
	}
	

</script>
</body>
</html>