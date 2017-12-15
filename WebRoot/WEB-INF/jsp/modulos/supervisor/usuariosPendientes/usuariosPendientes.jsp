<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript">

$(document).ready(



alertExito = function(){
if($("#respuestaAutentia").val() != ""){
	$.fancybox.open({
		content : "<div id=\"contenedor3\"><div class=\"icon usuario2\"></div><h1>Se ha registrado correctamente la huella del usuario</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
		openEffect : 'elastic',
		openSpeed  : 150,
		closeEffect : 'elastic',
		closeSpeed  : 150,								
		autoSize: false,
		width: 470,
		height: 185
	});
}

}

);

function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}		

function mostrarHuella(idUsuario,rfc,nombre,apellidoPaterno,apellidoMaterno,sexo,fechaDeNacimiento, estadoId){
		if (IniciarUsuario(idUsuario, 0, 0, rfc, "RFC",  nombre, apellidoPaterno, apellidoMaterno, sexo, fechaDeNacimiento, "9")) {
			$("#idUsuario").val(idUsuario);
			$("#supervisorUsuariosPendientesForm").submit();
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La huela no fue verificada.</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185,
 				afterClose : function(){
 					$("#idUsuario").val(idUsuario);
					$("#supervisorUsuariosPendientesForm").attr("action","asignarClave");
					$("#supervisorUsuariosPendientesForm").submit();
 				}
			});
			
		}
	}
</script>
<body style="overflow:hidden;">	
<form:form name='supervisorUsuariosPendientesForm' action="agregarHuellaUsuarioPendiente" method='POST'
		   commandName="supervisorUsuariosPendientesForm" id="supervisorUsuariosPendientesForm">
			
			
	<form:hidden path="idUsuario"/>
	<form:hidden path="respuestaAutentia"/>
	<div id="contenedor_gral">
	<div id="admin_contenido">
	<div class="admin_titulo">USUARIOS PENDIENTES DE AUTORIZAR</div><!--
    --><div class="admin_pleca"></div>
                    
    <div class="linea_supervisor margen2 margen_arriba2">
    
    	<div class="titulo_supervisor verde margen2 tam_2">
          NOMBRE PACIENTE
         </div>
         
         <!-- div class="titulo_supervisor verde margen tam_2">
          MOTIVO
         </div-->

    </div>  
                 
    <div class="pleca_simple"></div> 
    
    <div class="contenedor_supervisor">	
        
        <c:choose>
        	<c:when test="${empty listaUsuarios}">
        	<div class="linea_supervisor">
 	        	<div class="cuerpo_supervisor gris margen2">
   	       			El día de hoy no hay ususarios pendientes de autorizar
   	      		</div>
    		</div>
        	</c:when>
        	<c:otherwise>
	        	 <c:forEach items="${listaUsuarios}" var="listaUsuarios">
	        	         <div class="linea_supervisor margen2 margen_arriba2">
        
        					<div class="texto_supervisor gris link margen2 tam_9" onclick="mostrarHuella(${listaUsuarios.usuarioId},'${listaUsuarios.rfc}','${listaUsuarios.nombre}','${listaUsuarios.apellidoPaterno}','${listaUsuarios.apellidoMaterno}',${listaUsuarios.sexo},'${listaUsuarios.fechaDeNacimiento}',${listaUsuarios.idEstado})">
					         		<h7>${listaUsuarios.nombre} ${listaUsuarios.apellidoPaterno} ${listaUsuarios.apellidoMaterno} </h7>
					         	</div>

    					</div> 
					         	
					         	
				</c:forEach>
        	</c:otherwise>
        
        </c:choose>
       
        	
            
         	<!--div class="texto_supervisor gris margen tam_2">
          	Usuario mayor de 70 años.
         	</div-->

    	 
    </div>
    
     <div class="demo margen2 margen_arriba">
    		<div id="demo2">                   
    		</div>
 	 </div> 
     
     <div class="linea_supervisor_corta margen margen_arriba">
            <div class="btn btn_cerrar" onclick="cerrar();"></div>
     </div>           

</div>
</div>
		<div id="paginador"></div>
	</form:form>
	
</body>

<script type="text/javascript">
	
	
	
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
				    $.getJSON("paginador2", {page:page} ,function(response){ 
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
</html>