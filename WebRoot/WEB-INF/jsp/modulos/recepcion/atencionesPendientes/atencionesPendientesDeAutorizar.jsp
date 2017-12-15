<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
</head>
<body>
	<form:form name='recepcionAtencionesPendientesForm' action="recuperarAtencionesPendientes" method='POST'
		commandName="recepcionAtencionesPendientesForm" id="recepcionAtencionesPendientesForm">

		<form:hidden path="idAtencionMedica"/>
		
		<div id="contenedor_gral">		
		<div id="admin_contenido">
			<div class="admin_titulo">ATENCIONES PENDIENTES DE AUTORIZACIÓN</div><!--
		    --><div class="admin_pleca"></div>
		                    
	   		<div class="linea_supervisor margen4 margen_arriba2">
	
				<div class="titulo_supervisor verde margen2 tam_2">
				 NOMBRE PACIENTE
				</div>
	         
				<div class="titulo_supervisor verde tam_18">
				 MOTIVO
				</div>
			
				<div class="titulo_supervisor verde tam_1">
				  AUTORIZACIÓN
				</div>
	
	
			</div>  
	                
		    <div class="pleca_simple"></div> 
		    
		    <div class="contenedor_supervisor margen4">
		    
		    	<c:if test="${not empty atencionesPendientes}">
						<c:forEach items="${atencionesPendientes}" var="atencionPendiente" varStatus="index">
							<div class="linea_supervisor alto2" id="contenedorUsuarios">
					         	<div class="texto_supervisor gris margen2 tam_2">
					         	${atencionPendiente.nombre}
					         	</div>		         
					         	<div class="texto_supervisor3 gris  tam_18">
					          	${atencionPendiente.motivo}
					         	</div>
									<c:choose>
										<c:when test="${atencionPendiente.autorizado == true}">
										<div class="texto_supervisor gris tam_1">
											<input type="button" class="btn btn_continuar margen_izq1" onclick="recuperarCita(${atencionPendiente.idAtencionMedica})">
										</div>
										</c:when>
										<c:otherwise>
										<div class="texto_supervisor gris tam_1">
											<input class="image_btn btn_pendiente margen_izq1" disabled />
										</div>
										</c:otherwise>
									</c:choose>
							</div>			         		
						</c:forEach>
		    	</c:if>
		     	 
	
	   			<c:if test="${empty atencionesPendientes}">
		   			<div class="linea_supervisor margen2">
			         	<div class="cuerpo_supervisor gris">
			          	No hay atenciones pendientes por autorizar.
			         	</div>
			    	</div>
	   			</c:if>
		      	        
	 		</div>
	    
			<div class="demo margen margen_arriba2">
				<c:if test="${not empty atencionesPendientes}">
					<div id="paginador">                   
					</div>
				</c:if>
			</div> 
	 
			<div class="linea_supervisor margen margen_arriba">
	        		<div class="btn btn_cerrar" onclick="cerrar();"></div>
			</div>
	    
		</div>	
		</div>							
	</form:form>
		
	<script type="text/javascript">
	
		function recuperarCita(idAtencion){
			
			$("#idAtencionMedica").val(idAtencion);
			$("#recepcionAtencionesPendientesForm").submit();
		}
		
		
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/menu";
		}
		
		function autorizarRechazar(idAtencionMedica, tipoMotivo, autorizarRechazar, idAfiliado){
			var ctx = "${pageContext.request.contextPath}";
			if (tipoMotivo == 2 && autorizarRechazar == 1) {
				$("#idAfiliado").val(idAfiliado);
				$("#idAtencionMedica").val(idAtencionMedica);			
				$("#supervisorAtencionesPendientesForm").attr("action", "autorizacionIdentidad");
				$("#supervisorAtencionesPendientesForm").submit();
			}else{
				$.fancybox.open({
					href : ctx + '/supervisor/atencionesPendientesMotivo?idAtencionMedica='+idAtencionMedica+'&tipoMotivo='+tipoMotivo+'&idAfiliado='+idAfiliado+'&autorizarRechazar='+autorizarRechazar,
					type : 'iframe',
					padding : 5,
					autoSize: false,
					width: 450,
					height: 200,
					afterClose : function(){
						var actualizoEstatus = $("#actualizoEstatus").val();
						if (actualizoEstatus == "true") {
							location.reload();	
						}	
					}
				});				
			}
		}
		
		$(function() {
			$("#paginador").paginate({
				count : ${recepcionAtencionesPendientesForm.count},
				start : 1,
				display : ${recepcionAtencionesPendientesForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
				    $.getJSON("paginador", {page:page} ,function(response){ 
				    	$("#contenedorUsuarios").empty(); 
			    		var filas = "";
			    		 $.each(response, function(index, item) {
			            	filas += '<div class="texto_supervisor gris margen2 tam_2">'+item.nombre+'</div>';
			            	filas += '<div class="texto_supervisor gris  tam_18">'+item.motivo+'</div>';
			            	
			            	if(item.autorizado == true){
			            	filas += '<div class="texto_supervisor gris tam_1">';
			            	filas += '<input type="button" class="btn btn_continuar margen_izq1" onclick="recuperarCita('+item.idAtencionMedica+')">';
			            	filas += '</div>';
			            	}else{
			            	filas += '<div class="texto_supervisor gris tam_1 interlineado">';
			            	filas += '<input class="image_btn btn_pendiente margen_izq1" disabled />';
			            	filas += '</div>';
			            	}
			            				            	
			                $("#contenedorUsuarios").append(filas); 
			                filas = "";
			            });
				    });
				}
			});
		});
	</script>
</body>
</html>