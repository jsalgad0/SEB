<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body style="overflow:hidden;">
	<form:form name='pacientesRecibidosForm' action="pacientesRecibidos" method='POST'
		commandName="pacientesRecibidosForm" id="pacientesRecibidosForm">
	<div id="contenedor_gral">
	<div id="admin_contenido">
		<div class="linea_supervisor margen4">
			<c:choose>
				<c:when test="${pacientesRecibidosForm.todoRecepcion eq '0' }">
					<input type="checkbox" id="todoRecepcionCheck"  />		
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="todoRecepcionCheck" checked="checked" />
				</c:otherwise>
			</c:choose>
			
			<form:hidden id="todoRecepcion" path="todoRecepcion" name="todoRecepcion"/>
			<div class="titulo_supervisor">TODOS LOS RECEPCIONISTAS</div>	
	    </div>
		<div class="admin_pleca"></div>
	    
	    <div class="linea_supervisor margen4">
	    	<form:hidden id="pagina" path="pagina" />
	        <form:input class="campo_supervisor tam_7" id="nombre" path="nombre" placeholder="Nombres" />
	        <form:input class="campo_supervisor tam_2" id="apellidoPaterno" path="apellidoPaterno" placeholder="Apellido Paterno" />
	        <form:input class="campo_supervisor tam_2" id="apellidoMaterno" path="apellidoMaterno" placeholder="Apellido Materno" />
	        <input class="btn_buscar" type="button" id="envia" onclick="enviar();" value="" />
	    </div>
	    
	    <div class="tabla2 margen4 margen_arriba" id="divUsuariosRecibidos">
	    <c:choose>
	    	<c:when test="${not empty lista }">	    		
		       <div class="tabla2">   
		        <div class="titulo_tabla alto">
		        	<div class="celda2 fondo_v texto_supervisor blanco tam_9">Paciente
		            </div>
		            <div class="celda2 fondo_v texto_supervisor blanco tam_17 centrado">Hr. Llegada
		            </div>
		            <div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Hr. Cita
		            </div>
		            <div class="celda2 fondo_v texto_supervisor blanco tam_7">MÈdico
		            </div>
		            <div class="celda2 fondo_v texto_supervisor blanco tam_4">Especialidad
		            </div>
		            <div class="celda2 fondo_v texto_supervisor blanco tam_1 centrado">Estado
		            </div>
		    	</div> 
		       </div>
		    
		    <div class="tabla2_contenedor alto_tabla">
		      <div id="tablaPacientes" class="tabla2 alto_tabla">
		      	<c:forEach items="${lista }" var="pacientes">
		      		<div class="linea_tabla alto">
			        	<div class="celda2 texto_supervisor blanco tam_9">${pacientes.paciente }
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_17 centrado">${pacientes.horaLlegada }
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_8 centrado">${pacientes.horaCita }
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_7">${pacientes.medico }
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_4">${pacientes.especialidad }
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_1 centrado">
				            <c:choose>
				            	<c:when test="${pacientes.estado eq '1' }">
				            		<div class="estado edo1"></div>
				            	</c:when>
				            	<c:otherwise>
				            		<div class="estado edo2"></div>
				            	</c:otherwise>
				            </c:choose>
			            </div>
			    	</div>
		      	</c:forEach>  
		         
			      </div>	 
			     </div> 
			
			    </c:when>
		   	<c:otherwise>
		    	<div class="linea_supervisor margen2">
		         	<div class="cuerpo_supervisor gris">
		          	No hay atenciones pendientes por autorizar.
		         	</div>
		    	</div>
		    	<br>
	    	</c:otherwise>
	    </c:choose> 
	    </div>
    	<c:if test="${not empty lista }">
		     <div class="linea_supervisor margen4 margen_arriba2">
		            <div class="texto_supervisor">Atendidos</div>
		            <div class="estado edo1"></div>
		            <div class="texto_supervisor margen6">Por atender</div>
		            <div class="estado edo2"></div>
		     </div>
	     
			 <div class="demo margen4 margen_arriba2">
					 <div id="paginador">                   
					 </div>
			 </div>
		 </c:if> 
	     <div class="linea_supervisor margen4">
	            <div class="btn btn_cerrar" onclick="cerrar();"></div>
	     </div>
	     
	</div>
	</div>
	</form:form>
	
	<script src="js/jquery.paginate.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/menu";
		}
		
			function enviar(){
				if($("#todoRecepcionCheck").is(':checked')){
					$("#todoRecepcion").val(1);	
				}else{
					$("#todoRecepcion").val(0);
				}				
				$("#pacientesRecibidosForm").submit();
			}
		
			$(function() {			
				
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
				
				$("#paginador").paginate({
					count : ${pacientesRecibidosForm.count},
					start : 1,
					display : ${pacientesRecibidosForm.display},
					border					: false,
					text_color  			: '#888',
					background_color    	: '#EEE',	
					text_hover_color  		: 'black',
					background_hover_color	: '#CFCFCF',
					mouse : 'press',
					onChange : function(page) {
						var todos = $("#todoRecepcion").val();
						var busquedaNombre = $("#nombre").val();
						var busquedaApellidoPaterno = $("#apellidoPaterno").val();
						var busquedaApellidoMaterno = $("#apellidoMaterno").val();
					    $.getJSON("paginadorPacientesRecibidos", {nombre:busquedaNombre, apellidoPaterno:busquedaApellidoPaterno, apellidoMaterno:busquedaApellidoMaterno, page:page, todos:todos} ,function(response){
					    	$("#divUsuariosRecibidos").empty();
					    	var filas = "";
					    	filas += '<div class="tabla2"><div class="titulo_tabla alto"><div class="celda2 fondo_v texto_supervisor blanco tam_9">Paciente</div><div class="celda2 fondo_v texto_supervisor blanco tam_17 centrado">Hr. Llegada</div><div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Hr. Cita</div><div class="celda2 fondo_v texto_supervisor blanco tam_7">MÈdico</div><div class="celda2 fondo_v texto_supervisor blanco tam_4">Especialidad</div><div class="celda2 fondo_v texto_supervisor blanco tam_1 centrado">Estado</div></div></div>';
				    		filas += '<div class="tabla2_contenedor alto_tabla"><div id="tablaPacientes" class="tabla2 alto_tabla">';
					    	$.each(response, function(index, item) {
					    		filas += '<div class="linea_tabla alto"><div class="celda2 texto_supervisor blanco tam_9">'+item.paciente+'</div>';
					    		filas += '<div class="celda2 texto_supervisor blanco tam_17 centrado">'+item.horaLlegada+'</div>';
						        filas += '<div class="celda2 texto_supervisor blanco tam_8 centrado">'+item.horaCita+'</div>';
						        filas += '<div class="celda2 texto_supervisor blanco tam_7">'+item.medico+'</div>';
						        filas += '<div class="celda2 texto_supervisor blanco tam_4">'+item.especialidad+'</div><div class="celda2 texto_supervisor blanco tam_1 centrado">';
								if(item.estado == '1'){
									filas += '<div class="estado edo1"></div>';
								}else{
									filas += '<div class="estado edo2"></div>';
								}
								filas += '</div></div>';
				            });
					    	filas += '</div></div>';
					    	$("#divUsuariosRecibidos").append(filas); 
			                filas = "";
					    });
					}
				});
				
			});
		</script>

</body>
</html>