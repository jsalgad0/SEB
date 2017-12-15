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
<form:form name="listaSignosForm" id="listaSignosForm" commandName="listaSignosForm" method="POST">
<form:hidden path="idAtencion" id="idAtencion"/>
<div id="contenedor_gral">
<div id="admin_contenido">

	<div class="admin_titulo">
		REGISTRO DE SIGNOS VITALES
	</div><!--
    --><div class="admin_pleca"></div>
    <div class="admin_titulo">
    	<form:input path="busquedaApellido" id="busquedaApellido" maxlength="25" cssClass="campo_supervisor tam_5" placeholder="Apellido Paterno"/>
    	<form:select path="idEstatus" class="selectt campo_supervisor tam_5" name="Estatus">
	        		<form:option value="-1" label="Todos" />
	        		<form:option value="0" label="Por Atender" />
	        		<form:option value="1" label="Atendidos" />
	    </form:select>
	    <input type="submit" class="btn_buscar" value="" />
    </div>
                    
    <div class="linea_supervisor margen4 margen_arriba2">

         <div class="titulo_supervisor verde margen7 tam_11">
          NOMBRE PACIENTE
         </div>
         
         <div class="titulo_supervisor verde tam_6">
          HORA
         </div>
		
        <div class="titulo_supervisor verde tam_6">
          ACCION
         </div>
         
         <div class="titulo_supervisor verde margen tam_1">
          ESTADO
         </div>

    </div>  
                 
    <div class="pleca_simple"></div> 
    
    <div class="contenedor_supervisor margen4" id="contenedorPacientes"> 
		<c:choose>
        	<c:when test="${empty lista}">
        	<div class="linea_supervisor">
 	        	<div class="cuerpo_supervisor gris margen9">
   	       			El día de hoy no hay ususarios pendientes de toma de signos
   	      		</div>
    		</div>
        	</c:when>
        	<c:otherwise>
	        	<c:forEach items="${lista}" var="listaUsuarios">
					<div class="linea_supervisor">
			         	<div class="texto_supervisor gris margen7 tam_11">
			         	${listaUsuarios.nombrePaciente}
			         	</div>
			         	<div class="texto_supervisor gris tam_6">
			          	${listaUsuarios.horaCita}
			         	</div>
			        	<div class="texto_supervisor gris tam_6">
			        	<c:choose>
				        	<c:when test="${listaUsuarios.estatusCita == 0}">
				        		<div class="btn btn_tomar_sig_vitales" onclick="tomaSignos(${listaUsuarios.idAtencion});"></div>
				        	</c:when>
			            	<c:otherwise>
			            		<div class="btn btn_tomar_sig_vitales"></div>
			            	</c:otherwise>
			            </c:choose>
			         	</div>
			            <div class="texto_supervisor gris margen tam_1 centrado">
			            <c:choose>
				        	<c:when test="${listaUsuarios.estatusCita == 0}">
				        		<div class="estado edo2"></div>
				        	</c:when>
			            	<c:otherwise>
			            		<div class="estado edo1"></div>
			            	</c:otherwise>
			            </c:choose>
			         	</div>
			    	</div>
				</c:forEach>
        	</c:otherwise>
        
        </c:choose>
	 </div> 
			
	<div class="linea_supervisor margen7 margen_arriba3">
            <div class="texto_supervisor">Atendidos</div>
            <div class="estado edo1"></div>
            <div class="texto_supervisor margen6">Por atender</div>
            <div class="estado edo2"></div>
     </div>
     
     <div class="demo margen7 margen_arriba2">
			<c:if test="${not empty lista}">
				<div id="paginador">                   
				</div>
			</c:if>
	</div> 
     
     <div class="linea_supervisor margen7">
            <div class="btn btn_cerrar" onclick="regresar()"></div>
     </div>
     
</div>
</div>
</form:form>

<script src="js/jquery.paginate.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function() {
			
			$("#paginador").paginate({
				count 		: ${listaSignosForm.count},
				start 		: 1,
				display     : ${listaSignosForm.display},
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF',
				mouse : 'press',
				onChange : function(page) {
				    $.getJSON("paginador", {page:page} ,function(response){ 
				    	$("#contenedorPacientes").empty(); 
			    		var filas = "";
			            $.each(response, function(index, item) {
			            
			            	filas += '<div class="linea_supervisor">';
			            	filas += '<div class="texto_supervisor gris margen7 tam_11">'+item.nombrePaciente+'</div><div class="texto_supervisor gris tam_6">'+item.horaCita+'</div>';
			            	filas += '<div class="texto_supervisor gris tam_6"><div class="btn btn_tomar_sig_vitales"></div></div>';
			            	filas += '<div class="texto_supervisor gris margen tam_1 centrado">';
			            	if(item.estatusCita==0){
			            		filas += '<div class="estado edo2"></div>';
			            	}else{
			            		filas += '<div class="estado edo1"></div>';
			            	}
			            	
			            	filas += '</div></div>';
			                $("#contenedorPacientes").append(filas); 
			                filas = "";
			            });
				    });
				}
			});
			
		});
		
		function tomaSignos(idAtencion){
			$("#idAtencion").val(idAtencion);
			$("#listaSignosForm").attr("action", "tomaSignos");
			$("#listaSignosForm").submit();
		}
		
		function regresar(){
			window.location.href = '/SAB/menu';
			}
			
		$("#busquedaApellido").alphanum({
       	allowSpace: true,
       	disallow: '´çÇ¿¨¡·'
   		});
		</script>

</body>
</html>