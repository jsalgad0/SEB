<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script>
$(document).ready(function() {
	
	if($("#finalizo").val() == 1){
		$.fancybox.open({
			content : "<div id=\"contenedor3\"><div class=\"icon usuario2\"></div><h1>Autorización especial asignada exitosamente.</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185,
 				afterClose : function(){
 					irUsuariosPendientes();
 				}
		});
		
		
	}
	
	var d = new Date();
	var mes = d.getMonth()+1;
	if(mes<10){
		mes = "0"+mes;
	}
	var fechaInicial = d.getDate() + "/" + mes + "/" + d.getFullYear();
	$("#fechaInicio").val(fechaInicial);
		
});
</script>
<body>
<div id="contenedor_gral">
<div id="admin_contenido">
	<form:form name='supervisorUsuariosPendientesForm' action="actualizarClaveUsuarioIdentidad" method='POST'
		commandName="supervisorUsuariosPendientesForm" id="supervisorUsuariosPendientesForm">
		<form:hidden path="idUsuario"/>
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
	            
	            <div class="error margen5 tam_5">
	            	
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
	            	<form:radiobutton path="idTipoAutorizacion" label="TEMPORAL" onclick="fechaTemporal()" value="1"/>
	            	<form:radiobutton path="idTipoAutorizacion" label="PERMANENTE" onclick="fechaPermanente()" value="2"/>
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
	        
	        <div class="linea_supervisor margen4" id="divFechas">
	        	<div class="texto_supervisor gris tam_6">
	          	Fecha inicio
	         	</div>
	            
	            <form:input path="fechaInicio" class="campo_supervisor gris tam_12 negrita" placeholder="Fecha Inicio" readonly="true"/>
	     
	            <div class="texto_supervisor gris tam_8">
	          	Fecha fin
	         	</div>
	            
	            <form:input path="fechaFin" class="campo_supervisor gris tam_4 datepicker negrita" placeholder="Fecha Fin" readonly="true" value=""/>
	        
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
	$(function() {
	    $( ".datepicker" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      minDate:0
	    });
	});
	
	function enviar(){
		$("#supervisorUsuariosPendientesForm").submit();
	}
	
function cerrar(){
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup ask\"></div><h1>No se ha guardado la Autorización Especial, ¿Desea continuar?</h1><div class=\"btn_popup\"><div class=\"btn_si_popup\" onclick=\"irUsuariosPendientes();\"></div><div class=\"btn_no_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	
	
	
	function irUsuariosPendientes(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/supervisor/inicioUsuariosPendientes";
	}
	
	function fechaPermanente(){
		var d = new Date();
		var mes = d.getMonth()+1;
		if(mes<10){
			mes = "0"+mes;
		}
		var fechaFin = d.getDate() + "/" + mes + "/" + (d.getFullYear()+100);
		$("#fechaFin").val(fechaFin);
		$("#fechaFin").removeClass("datePicker");
		$("#divFechas").hide();
		
	}
	
	function fechaTemporal(){
		$("#fechaFin").val('');
		$("#fechaFin").addClass("datePicker");
		$("#divFechas").show();
	}

	$(document).ready(function(){
		
		if($("#idTipoAutorizacion1").attr("checked") != "checked"){
		 fechaPermanente();
		}
		
	});


</script>
</body>
</html>