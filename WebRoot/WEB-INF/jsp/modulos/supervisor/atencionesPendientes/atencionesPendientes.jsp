<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
</head>
<body style="overflow:hidden;">
	<form:form name='supervisorAtencionesPendientesForm' action="inicioModificarUsuario" method='POST'
		commandName="supervisorAtencionesPendientesForm" id="supervisorAtencionesPendientesForm">
		<form:hidden path="idAfiliado"/>
		<form:hidden path="idAtencionMedica"/>
		<form:hidden path="actualizoEstatus"/>
		<div id="contenedor_gral">		
		<div id="admin_contenido">
			<div class="admin_titulo">ATENCIONES PENDIENTES DE AUTORIZACIÓN</div><!--
		    --><div class="admin_pleca"></div>
		                    
	   		<div class="linea_supervisor margen_arriba2">
	
				<div class="titulo_supervisor verde margen7 tam_2">
				 NOMBRE PACIENTE
				</div>
	         
				<div class="titulo_supervisor verde tam_7">
				 MOTIVO
				</div>
			
				<div class="titulo_supervisor verde tam_4">
				  ACCIÓN
				</div>
	
			</div>  
	                
		    <div class="pleca_simple"></div> 
		    
		    <div class="contenedor_supervisor">
		    
		    	<c:if test="${not empty atencionesPendientes}">
					<c:forEach items="${atencionesPendientes}" var="atencionPendiente">
						<div class="linea_supervisor" id="contenedorUsuarios">
				         	<div class="texto_supervisor gris margen7 tam_2">
				         	${atencionPendiente.nombre}
				         	</div>
			         
				         	<div class="texto_supervisor gris  tam_7">
				          	${atencionPendiente.motivo}
				         	</div>
					
				        	<div class="texto_supervisor gris tam_2">
				          		<input type="button" class="btn btn_autorizar" onclick="autorizarRechazar(${atencionPendiente.idAtencionMedica},${atencionPendiente.tipoMotivo},1,${atencionPendiente.idAfiliado})">
				            	<input type="button" class="btn btn_rechazar" onclick="autorizarRechazar(${atencionPendiente.idAtencionMedica},${atencionPendiente.tipoMotivo},0,${atencionPendiente.idAfiliado})">
			         		</div>
			         	</div>
					</c:forEach>
		    	</c:if>
		     	 
	
	   			<c:if test="${empty atencionesPendientes}">
		   			<div class="linea_supervisor margen7">
			         	<div class="cuerpo_supervisor gris">
			          	No hay atenciones pendientes por autorizar.
			         	</div>
			    	</div>
	   			</c:if>
		      	        
	 		</div>
	    
			<div class="demo margen7 margen_arriba2">
				<c:if test="${not empty atencionesPendientes}">
					<div id="paginador">                   
					</div>
				</c:if>
			</div> 
	 
			<div class="linea_supervisor margen7 margen_arriba">
	        		<div class="btn btn_cerrar" onclick="cerrar();"></div>
			</div>
	    
		</div>	
		</div>							
	</form:form>
		
	<script type="text/javascript">
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
				count : ${supervisorAtencionesPendientesForm.count},
				start : 1,
				display : ${supervisorAtencionesPendientesForm.display},
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
			            	filas += '<div class="texto_supervisor gris margen7 tam_2">'+item.nombre+'</div>';
			            	filas += '<div class="texto_supervisor gris  tam_7">'+item.nombre+'</div>';
			            	filas += '<div class="texto_supervisor gris tam_2"><input type="button" class="btn btn_autorizar" onclick="autorizar('+item.idAtencionMedica+','+item.tipoMotivo+',1,'+item.idAfiliado+')">';
			            	filas += '<input type="button" class="btn btn_rechazar" onclick="rechazar('+item.idAtencionMedica+','+item.tipoMotivo+',0,'+item.idAfiliado+')"></div>';
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