<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.alphanum.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script><!--calendario datepicker -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tamanio_datepicker.css">	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">
</head>
<body style="overflow:hidden">
<div id="contenedor4_popup">
	<form:form name='personaConfianzaForm' action="lugarAtencion" method='POST' commandName="personaConfianzaForm" id="personaConfianzaForm">
	
		<div class="icon_popup usuario1"></div>
		<div class="linea_popup"><h1>SELECCIONAR ACOMPA—ANTE</h1></div>
	    
	    <div class="tabla_popup">   
	        <div class="fila_popup">
	        	<div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">Nombre</div>
	            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo4">Fecha Nac</div>
	            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo4">Ident Interno</div>
	            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo2">RFC</div>
	            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo5">CURP</div>
	    	</div> 
		</div>	
		
		<div class="tabla_contenedor_popup desplazar tam_14">	
			<div class="tabla_popup" id="tablapersonaConfianzaBody">
				<c:forEach items="${personasConfianza}" var="personaConfianza">
					<div class="fila_popup" onclick="elegirPersona(${personaConfianza.personaId});">
	        			<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">${personaConfianza.nombre}</div>
	            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980</div>
	            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">${personaConfianza.cattipoidentificador.tipoIdentificador}</div>
            			<c:if test="${personaConfianza.cattipoidentificador.tipoIdentificadorId == 1}">
		            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">${personaConfianza.valorTipoIdentificador}</div>
		            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5"></div>	            			
						</c:if>
            			<c:if test="${personaConfianza.cattipoidentificador.tipoIdentificadorId == 2}">
		            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2"></div>
		            		<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">${personaConfianza.valorTipoIdentificador}</div>	            			
						</c:if>	            		
	    	 		</div>				
				</c:forEach>			
	    	 </div>
		</div> 
	</form:form>
	
	<form:form name='personaConfianzaForm' action="${pageContext.request.contextPath}/agenda/nuevoPersonaConfianza" method='POST' commandName="personaConfianzaForm" id="personaConfianzaForm1">
		<form:hidden path="idAgenda"/>
		<form:hidden path="idPersona"/>
		<form:hidden path="idAfiliado"/>
		
		<div class="linea_supervisor">
			<form:select path="idTipoIdentificador" id="idTipoIdentificador" cssClass="selectt campo_popup ancho1" >
				<form:option value="-1" label="Tipo ID" />
				<form:options items="${tipoIdentificador}" itemLabel="tipoIdentificador" itemValue="tipoIdentificadorId" ></form:options>
			</form:select>
            
            <form:input path="personasdeconfianza.valorTipoIdentificador" class="campo_popup ancho2" placeholder="RFC/ID AcompaÒante" maxlength="18"/>
    	</div>
        
        <div class="linea_supervisor">
        	<div class="error_popup ancho1">
            	<form:errors path="idTipoIdentificador" />
            </div>
            
            <div class="error_popup ancho2">
            	<form:errors path="personasdeconfianza.valorTipoIdentificador" />
            </div>
 
        </div>
        
        <div class="linea_supervisor">
        	<form:input path="personasdeconfianza.nombre" id="nombre" cssClass="campo_popup ancho3" placeholder="Nombre" maxlength="60"/>
        	<form:input path="personasdeconfianza.apellidoPaterno" id="apellidoPaterno" cssClass="campo_popup ancho3" placeholder="Apellido Paterno" maxlength="60"/>
        	<form:input path="personasdeconfianza.apellidoMaterno" id="apellidoMaterno" cssClass="campo_popup ancho3" placeholder="Apellido Materno" maxlength="60"/>
    	</div>
        
        <div class="linea_supervisor"> 
            <div class="error_popup ancho3">
            	<form:errors path="personasdeconfianza.nombre" />
            </div>
            <div class="error_popup ancho3">
            	<form:errors path="personasdeconfianza.apellidoPaterno" />
            </div>
            
            <div class="error_popup ancho3">
            	<form:errors path="personasdeconfianza.apellidoMaterno" />
            </div>
        </div>
        
         <div class="linea_supervisor">
  
            <form:input path="fechaNacimiento" id="fechaNacimiento" class="campo_popup ancho2" placeholder="Fecha de Nac" readonly="true" />

            <div class="text_popup gris alto_campo ancho3">
            	<form:radiobutton path="idSexo" value="1" label="Masculino" cssClass="genero2"/>
            	<form:radiobutton path="idSexo" value="2" label="Femenino" cssClass="genero2"/>
         	</div>
    	</div>
        
        <div class="linea_supervisor">
        	<div class="error_popup ancho2">
            	<form:errors path="fechaNacimiento" />
            </div>
            
            <div class="error_popup ancho3">
            	<form:errors path="idSexo" />
            </div>
 
        </div>
   
		<div class="btn_popup">
   			<div class="btn_certificar_popup" onclick="enviar();"></div>
   		    <div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
		</div>
	</form:form>	
</div>
	
	<script type="text/javascript">
		$( document ).ready(function() {
			if(typeof window.top.$("#idAfiliado").val() === "undefined"){
				var idAgenda = $("#idAgenda").val();
				var idPersona = $("#idPersona").val();
				if(idPersona!=0){
					window.top.location.href = "recepcion?idAgenda="+idAgenda+"&idPersona="+idPersona;
				}	
			}else{
				var idPersona = $("#idPersona").val();
				if(idPersona!=0){
					window.top.$("#idPersona").val(idPersona);
					parent.$.fancybox.close();
				}
			}
			
			$("#nombre").alphanum({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });
			
			$("#apellidoPaterno").alphanum({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });
			
			$("#apellidoMaterno").alphanum({
		        allowSpace: true,
		        disallow: '¥Á«ø®°∑'
		    });			
			
		});
		
		function elegirPersona(idPersona){
			if(typeof window.top.$("#idAfiliado").val() === "undefined"){
				var idAgenda = $("#idAgenda").val();
				window.top.location.href = "recepcion?idAgenda="+idAgenda+"&idPersona="+idPersona;
			}else{
				if(idPersona!=0){
					window.top.$("#idPersona").val(idPersona);
					parent.$.fancybox.close();
				}
			}
		}
		
		$(function() {
			$("#fechaNacimiento").datepicker({
				changeMonth: true,
				changeYear: true,
				maxDate: 0
			});
		});	
		
		function enviar(){
			$("#personaConfianzaForm1").submit();
		}

	</script>
</body>
</html>