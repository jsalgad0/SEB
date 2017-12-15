<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	<input type="hidden" id="actualizaDatos" value="0"/>
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
		<form:form name='medicoAtencionPacientesForm' action="inicio" method='POST' commandName="medicoAtencionPacientesForm" id="medicoAtencionPacientesForm">
			<form:hidden path="idAfiliado"/>
			<form:hidden path="idAtencion"/>
			<div class="menu_medico_atn">
            	<div class="menu_item item11"  onclick="inicioAtencion();" >
                	<div class="texto_medico"><p>INFORMACIÓN DEL PACIENTE</p></div>
                </div>
                
                <div class="menu_item item12" onclick="historiaClinica()">
                	<div class="texto_recepcion"><p>HISTORIA CLÍNICA</p></div>
                </div>
                <div class="menu_item item13" onclick="historialDiagnostico()">
                	<div class="texto_recepcion"><p>HISTORIAL POR DIAGNÓSTICO</p></div>
                </div>
                <div class="menu_item item14" onclick="historialAtenciones()">
                	<div class="texto_recepcion"><p>HISTORIAL DE ATENCIONES</p></div>
                </div>
                
                <div class="menu_item item15" onclick="atencionMedica()">
                	<div class="texto_recepcion"><p>ATENCIÓN MÉDICA</p></div>
                </div>
                
                <div class="menu_item item0" onclick="regresar();">
                	<div class="texto_recepcion"><p>REGRESAR</p></div>
                </div>
            </div>
		</form:form>
	</div>	
	
	<script type="text/javascript">
		function inicioAtencion(){
		if($("#actualizaDatos").val() == 0){
			$("#medicoAtencionPacientesForm").attr("action", "informacionPaciente");
			$("#medicoAtencionPacientesForm").submit();
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "inicioAtencion");
					$("#medicoAtencionPacientesForm").submit();
				}
			}
		}
		
		function historiaClinica(){
		if($("#actualizaDatos").val() == 0){
			$("#medicoAtencionPacientesForm").attr("action", "antecedentesFamiliares");
			$("#medicoAtencionPacientesForm").submit();		
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
						$("#medicoAtencionPacientesForm").attr("action", "antecedentesFamiliares");
						$("#medicoAtencionPacientesForm").submit();		
				}
			}	
		}
		
		function historialAtenciones(){
		if($("#actualizaDatos").val() == 0){
			$("#medicoAtencionPacientesForm").attr("action", "historialAtenciones");
			$("#medicoAtencionPacientesForm").submit();				
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
						$("#medicoAtencionPacientesForm").attr("action", "historialAtenciones");
						$("#medicoAtencionPacientesForm").submit();				
				}
			}
				
		}	
		
		function historialDiagnostico(){
		if($("#actualizaDatos").val() == 0){
			$("#medicoAtencionPacientesForm").attr("action", "historialDiagnostico");
			$("#medicoAtencionPacientesForm").submit();						
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
						$("#medicoAtencionPacientesForm").attr("action", "historialDiagnostico");
						$("#medicoAtencionPacientesForm").submit();					
				}
			}
				
		}	
		
		function atencionMedica(){
		if($("#actualizaDatos").val() == 0){
			$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
			$("#medicoAtencionPacientesForm").submit();						
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
						$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
						$("#medicoAtencionPacientesForm").submit();						
				}
			}
		
					
		}
		
		function otrosDiagnosticos(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "otrosDiagnosticos");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
						$("#medicoAtencionPacientesForm").attr("action", "otrosDiagnosticos");
						$("#medicoAtencionPacientesForm").submit();							
				}
			}
					
		}	
		
		function signosVitales(){
		if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "signosVitales");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "signosVitales");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
					
		}	
		
		function estudiosLaboratorio(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}
		
		function estudiosGabinete(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}	
		
		function estudiosOtros(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}
		
		function receta(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "receta");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "receta");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}
		
		function certificados(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaAsistencia");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaAsistencia");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }		
		
		function terminar(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "atencionTerminar");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "atencionTerminar");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}
		
		function solicitudReferencia(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "solicitudReferencia");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "solicitudReferencia");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}	
		
		function licenciaMedica(){
			if($("#actualizaDatos").val() == 0){
				$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
				$("#medicoAtencionPacientesForm").submit();							
			}else{
				var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
					$("#medicoAtencionPacientesForm").submit();						
				}
			}
		}		
		
		function regresar(){
		if($("#actualizaDatos").val() == 0){
				window.location.href = '/SAB/menu';							
			}else{
			var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					window.location.href = '/SAB/menu';						
				}
			}
		
			
		}
	</script>		
	