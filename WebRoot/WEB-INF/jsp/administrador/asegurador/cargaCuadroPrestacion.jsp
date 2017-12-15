<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
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
<body style="overflow:hidden; class="centrar fondo_claro_popup">


<div id="contenedor_popup" class="centrar">
<!-- para el width:520px; height:380px; !-->
	<form:form action="cargaArchivoPrestacionesAsegurador" method='POST' commandName="cargaPrestacionesAseguradorForm" id="cargaPrestacionesAseguradorForm" enctype="multipart/form-data">
		<form:hidden path="error" />
		<form:hidden path="exito" />
	<div id="pre-load" style="display: none;"><div id="imagen-load"><img src="${pageContext.request.contextPath}/resources/img/load_flor.gif" alt="" /></div>
	</div>	

	<form:hidden path="rfc"/>
   	<form:input path="file" type="file" readonly="readonly" class="campo_input campo_size1_popup margen_arriba_popup" />
 <div class="linea3 margen">
		<div class="error tam_10 margen10"><form:errors path="file" /></div>
	</div>
	<div class="text_popup2 campo_size1_popup margen_arriba_popup">La carga del archivo puede tomar unos minutos, por favor, no interrumpa el proceso.</div>
   
   <div class="btn_popup margen_arriba_popup">

   		<div class="linea_botones_popup2">
   			<div class="btn_cargar_popup" id="cargaButton" onClick="cargaArchivo()"></div>
     		<div class="btn_log_popup" style="display:none" id="logButton"></div>
        	<div class="btn_cerrar_popup" onclick="cerrar()"></div>
        </div>    
   </div>
   
    <input type="hidden" id="flag" value="0"/>
   
   </form:form>
   	
</div>
</body>
<script type="text/javascript">
function cargaArchivo(){
	$("#flag").val(1);
	$("#pre-load").show();
	$("#cargaPrestacionesAseguradorForm").submit();
}



function cerrar(){
	var flag = $("#flag").val();
	if(flag == 0){
		var r = confirm("No ha cargado las prestaciones del asegurador, ¿Desea salir?");	
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
				parent.$("#botonCuadroPrestaciones").hide();
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