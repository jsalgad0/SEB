<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_admin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
<!-- Add fancyBox -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.pack.js"></script>

<!-- Optionally add helpers - button, thumbnail and/or media -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-media.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.js"></script>

<html>
<body style="overflow:hidden;">
	<form:form action="cargaCatalogoSab" method='POST' commandName="cargaCatalogoSabForm" id="cargaCatalogoSabForm" enctype="multipart/form-data">
		<form:hidden path="error" />
		<form:hidden path="exito" />
		
<div id="admin_contenido2">

                    
    <div class="linea_supervisor margen_arriba3" style="height:72px;">
    	
   		<div class="linea_supervisor margen_arriba4">     
	
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  <div id="pre-load" style="display: none;"><div id="imagen-load"><img src="${pageContext.request.contextPath}/resources/img/load_flor.gif" alt="" /></div>
	</div>	
  
  <div class="linea_corta2">

    
    <div class="linea_supervisor margen">
   		<form:input path="file" type="file" class="campo_input tam_20" readonly="readonly"/>
	<div class="linea3 margen">
		<div class="error tam_10 margen10"><form:errors path="file" /></div>
	</div>
   		<div class="texto_supervisor5 verde tam_20 margen_arriba2">La carga del archivo puede tomar unos minutos, por favor, no interrumpa el proceso.</div>
    </div>

	
     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_cargar" id="cargaButton" onclick="cargaArchivo()"></div>
     		<div class="btn_grande btn_log_proceso" onclick="abrirLog()" style="display:" id="logButton"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
<script type="text/javascript">
function cargaArchivo(){
	$("#flag").val(1);
	$("#pre-load").show();
	$("#cargaCatalogoSabForm").submit();
}

function abrirLog(){
	var ctx = "${pageContext.request.contextPath}";
	
	$.fancybox
	.open({
		href : ctx + '/administrador/bitacoraSab',
		type : 'iframe',
		padding : 5,
		autoSize: false,
		width: 520,
		height: 380,
	});
}

function cerrar(){
	var flag = $("#flag").val();
	if(flag == 0){
		var r = confirm("No han cargado las prestaciones, ¿Desea salir?");	
		if (r == true) {
			parent.$.fancybox.close();
		}
	}else{
		parent.$.fancybox.close();
	}
	
}


$(document)
.ready(
		function() {
			$.ajaxSetup({
				cache : false
			});

		    var error = $("#error").val();
			var exito = $("#exito").val();
			if (error != "") {
				$("#logButton").show();
				$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"
									+ error
									+ "</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed : 150,
							closeEffect : 'elastic',
							closeSpeed : 150,
							autoSize : false,
							width : 470,
							height : 185
						});
			}
			if (exito != "") {
				$("#logButton").show();
				$("#cargaButton").hide();
				$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"
									+ exito
									+ "</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed : 150,
							closeEffect : 'elastic',
							closeSpeed : 150,
							autoSize : false,
							width : 470,
							height : 185
						});
			}

			
			
			
		});

</script>
</html>