<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.paginate.js" type="text/javascript"></script>


<html>
<body>
<form:form name='medicoAtencionPacientesForm' action="inicioAtencionPacientes" method='POST'
			commandName="medicoAtencionPacientesForm" id="medicoAtencionPacientesForm">
	<form:hidden path="idAfiliado"/>
	<form:hidden path="idAtencion"/>

<div id="contenedor_gral">
<div id="admin_contenido">
 
<div class="admin_titulo">LISTA DE PACIENTES EN ESPERA</div><!--
    --><div class="admin_pleca"></div>  
  
  <div class="linea_supervisor">
  
  	<form:input path="busquedaApellidoPaterno" cssClass="campo_supervisor tam_5 margen13" cssStyle="margin-right:0px !important;" placeholder="Apellido Paterno"/><!--
          --><input class="btn_solo buscar_icono" onclick="enviar();" />
  
   	<form:input path="busquedaFecha" cssClass="campo_supervisor tam_4 margen"  cssStyle="margin-right:0px !important;" placeholder="Fecha" readonly="true" onchange="enviar();"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />
    
    <form:select path="busquedaidEstatus" class="selectt campo_supervisor tam_4 margen" onchange="enviar();">
    	<form:option value="-1">Por atender</form:option>
		<form:option value="1">Atendidos</form:option>
		<form:option value="0">Sin Atender</form:option>
    </form:select>	
                    
    <div class="linea_supervisor margen13 margen_arriba4">

         <div class="titulo_supervisor verde tam_10">
          NOMBRE PACIENTE
         </div>
         
         <div class="titulo_supervisor verde tam_6">
          HORA
         </div>
		
        <div class="titulo_supervisor verde tam_6 centrado">
          ACCION
         </div>
         
         <div class="titulo_supervisor verde margen tam_1">
          ESTADO
         </div>

    </div>  
                 
    <div class="pleca_sinmargen"></div> 
 
    <div class="contenedor_supervisor margen13" id="contenedorAfiliados">
    
    	<c:forEach items="${afiliados}" var="afiliado">
	     	<div class="linea_supervisor">
	
	         	<div class="texto_supervisor gris tam_10">
	         		${afiliado.nombre} ${afiliado.apellidoPaterno} ${afiliado.apellidoMaterno}
	         	</div>
	         
	         	<div class="texto_supervisor gris tam_6">
	          		${afiliado.hora}
	         	</div>
			
	        	<div class="texto_supervisor gris tam_6">
	            	<c:choose>
	            		<c:when test="${afiliado.atendido == true}">
	            			<div class="btn btn_atendido"></div>
	            		</c:when>
	            		<c:otherwise>
	            			<div class="btn btn_atender" onclick="irAtencion(${afiliado.atencionId});"></div>	
	            		</c:otherwise>	            		
	            	</c:choose>
	         	</div>
	            
	            <div class="texto_supervisor gris margen tam_1 centrado">
	            	<c:choose>
	            		<c:when test="${afiliado.atendido == true}">
	            			<div class="estado edo1"></div>	
	            		</c:when>
	            		<c:otherwise>
	            			<div class="estado edo3"></div>	
	            		</c:otherwise>
	            	</c:choose>
	         	</div>
	            
	    	</div>    	
    	</c:forEach>
 
	 </div>
			
	<div class="linea_supervisor margen13 margen_arriba4">
            <div class="texto_supervisor">Atendidos</div>
            <div class="estado edo1"></div>
            <div class="texto_supervisor margen6">En espera</div>
            <div class="estado edo3"></div>
     </div>
     
		 <div class="demo margen13 margen_arriba4">
		 	<c:choose>
		 		<c:when test="${not empty afiliados}">
		 			<div id="paginador"></div>
		 		</c:when>
		 	</c:choose> 
		 </div> 
     
     <div class="linea_supervisor margen13">
            <div class="btn btn_cerrar" onclick="cerrar();"></div>
     </div>
  </div>  
 </div>    
</div>
</form:form>

		<script type="text/javascript">
		$(function() {
			$("#busquedaFecha").datepicker({
				changeMonth: true,
				changeYear: true,
				maxDate: 0
			});
		});
		
		$(document).ready(function() {
			$("#datepicker").click(function() {
				  $("#busquedaFecha").datepicker("show");
			});
		});
		
		function enviar(){
			$("#medicoAtencionPacientesForm").submit();			
		}
		
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/menu";
		}	
		
		function irAtencion(idAtencion){
			$("#idAtencion").val(idAtencion);
			$("#medicoAtencionPacientesForm").attr("action", "notaMedica");	
			$("#medicoAtencionPacientesForm").submit();	
		}
		
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/menu";
		}
		
		$(function() {
			$("#paginador").paginate({
				count : ${medicoAtencionPacientesForm.count},
				start : 1,
				display : ${medicoAtencionPacientesForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
					var busquedaApellidoPaterno = $("#busquedaApellidoPaterno").val();
					var busquedaFecha = $("#busquedaFecha").val();
					var busquedaidEstatus = $("#busquedaidEstatus").val();
				    $.getJSON("paginador", {busquedaApellidoPaterno:busquedaApellidoPaterno, busquedaFecha:busquedaFecha, busquedaidEstatus:busquedaidEstatus, page:page} ,function(response){ 
				    	$("#contenedorAfiliados").empty(); 
			    		var filas = "";
			            $.each(response, function(index, item) {
			            	filas += '<div class="linea_supervisor"><div class="texto_supervisor gris tam_10">';
			            	filas += item.nombre+' '+item.nombre+' '+item.nombre+'</div><div class="texto_supervisor gris tam_6">15:00</div><div class="texto_supervisor gris tam_6"><div class="btn btn_atender"></div></div><div class="texto_supervisor gris margen tam_1 centrado"><div class="estado edo1"></div></div></div>';
			                $("#contenedorAfiliados").append(filas); 
			                filas = "";
			            });
				    });
				}
			});
		});
		</script>

</body>
</html>