<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
</head>
<body style="overflow:hidden;">
<div id="contenedor_gral">
<div id="admin_contenido">
	<div class="admin_titulo">NOTAS MÉDICAS NO FIRMADAS</div><!--
    --><div class="admin_pleca"></div>
                    
    <div class="linea_supervisor margen2 margen_arriba2">

         <div class="titulo_supervisor verde margen2 tam_9">
          PACIENTE
         </div>
         
         <div class="titulo_supervisor verde margen2 tam_5">
          MÉDICO
         </div>
		
        <div class="titulo_supervisor verde tam_4 ">
          ACCIÓN
         </div>

    </div>  
                 
    <div class="pleca_simple"></div> 
    
    <div class="contenedor_supervisor">
    	<c:choose>
    		<c:when test="${ empty notas}">
    			<div class="linea_supervisor margen2">
		         	<div class="cuerpo_supervisor margen2 gris">
		          	No hay notas médicas sin firmar.
		         	</div>
		    	</div>
    		</c:when>
    		<c:otherwise>
    			<c:forEach items="${notas}" var="nota">
			     	<div class="linea_supervisor margen2" id="contenedorNotas">
			
			         <div class="texto_supervisor gris margen2 tam_9">
			         	${nota.paciente }
			         </div>
			         
			        <div class="texto_supervisor gris margen2 tam_5">
			          ${nota.medico }
			        </div>
					
		        	<div class="texto_supervisor gris tam_9">
			          	<input type="button" class="btn btn_autorizar" onclick="motivo(${nota.idNota}, 2, 1, ${nota.idAfiliado }, ${nota.medicoAfiliado });">
			            <input type="button" class="btn btn_rechazar" onclick="motivo(${nota.idNota}, 2, 2, ${nota.idAfiliado }, ${nota.medicoAfiliado });">
		         	</div>
			            
			    	</div>
		    	</c:forEach>  
    		</c:otherwise>
    	</c:choose>
	 </div>
     
     <div class="linea_supervisor_corta margen margen_arriba">
            <div class="btn btn_cerrar" onclick="cerrar();"></div>
        </div>
     
</div>
</div>
	<script type="text/javascript">
		function cerrar(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/menu";
		}
		
		function motivo(idAtencionMedica, tipoMotivo, autorizarRechazar, idAfiliado, medicoAfiliado){
			var ctx = "${pageContext.request.contextPath}";
			$.fancybox.open({
				href : ctx + '/supervisor/notasNoFirmadasMotivo?idAtencionMedica='+idAtencionMedica+'&tipoMotivo='+tipoMotivo+'&idAfiliado='+idAfiliado+'&autorizarRechazar='+autorizarRechazar+'&medicoAfiliado='+medicoAfiliado,
				type : 'iframe',
				padding : 5,
				autoSize: false,
				width: 450,
				height: 200,
				afterClose : function(){
					location.reload();	
				}
			});	
		}
	</script>
</body>
</html>