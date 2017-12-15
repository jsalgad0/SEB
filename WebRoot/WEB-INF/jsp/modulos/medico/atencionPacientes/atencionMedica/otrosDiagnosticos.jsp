<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;">
<form:form name='atencionOtrosDiagnosticosForm' action="saveAtencionOtrosDiagnosticosForm" method='POST'
			commandName="atencionOtrosDiagnosticosForm" id="atencionOtrosDiagnosticosForm">
			<form:hidden path="idAtencion"/>
			<form:hidden path="idDiagnosticoPrincipal"/>
			<input type="hidden" id="idDiagnostico">
			<input type="hidden" id="codigo">

<div id="admin_contenido2">

                    
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${infoSignos.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.edad} años
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${infoSignos.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${infoSignos.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>
    
    <div id="admin_contenidoFrame3">
   	  <div class="linea_corta2">
   		<div class="pleca_menu"></div><!--     
        --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li class="pactiva"><a href="javascript:atencionMedica();"><img src=""class="icono_pestania icono_notamedica"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:receta();"><img src=""class="icono_pestania icono_receta"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosLaboratorio();"><img src=""class="icono_pestania icono_elaboratorio"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosGabinete();"><img src=""class="icono_pestania icono_egabinete"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosOtros();"><img src=""class="icono_pestania icono_otrose"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:licenciaMedica();"><img src=""class="icono_pestania icono_lic_medica"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:solicitudReferencia();"><img src=""class="icono_pestania icono_referencia"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:certificados();"><img src=""class="icono_pestania icono_certificados"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">CERTIFICADOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:terminar();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/terminar.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCIÓN</a></li>
   					</ul>
   				</li>   				
   			</ul>
   		</div>
   	  </div>
   	</div>  	  
    
    <div id="admin_contenido3">
      <div class="linea_corta_cont">
       <div class="linea_corta">
   		<div class="texto_supervisor2 verde margen2">
	          OTROS DIAGNÓSTICOS
	    </div>
	   </div>
			
		<div class="linea_corta margen_arriba4 margen2">
	    	<input class="campo_supervisor2 tam_10" placeholder="Ingrese Código CIE10 o descripción" id="diagnostico" />
	    	<div class="texto_supervisor5 blanco" style="display: none;">
	    	<form:radiobuttons items="${cattipodiagnosticos}" path="idTipoDiagnostico" itemLabel="descripcion" itemValue="id"/>
	    	</div>
	    	 <input class="margen2 btn_solo agregar_icono" onclick="agregarDaignostico()" id="agregar"/>
	    </div>	
	    
	     <div class="linea_corta margen_arriba2 margen2">
		 <div class="tabla2 margen_arriba"> 
	     
	    <div class="tabla_contenedor desplazar alto_tabla centrado">
	        <div class="linea_tabla alto">
	        	<div class="celda2 fondo_v texto_supervisor blanco tam_10 centrado">Código
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_27 centrado">Descripción
	            </div>
	            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado bote">
	            </div>
	    	</div>
	    	
	    	<div id="tablaDiagnosticos">
				<c:forEach items="${diagnosticoVos}" var="diagnosticoVo" varStatus="varStatus">
			        <div class="linea_tabla alto" id="diagnosticoVo_${varStatus.index}">
			        	<input type="hidden" value="x" id="diagnosticoVo${diagnosticoVo.idDiagnostico}">
			        	<input type="hidden" value="${diagnosticoVo.codigo}" name="diagnosticoVos[${varStatus.index}].codigo">
			        	<input type="hidden" value="${diagnosticoVo.diagnostico}" name="diagnosticoVos[${varStatus.index}].diagnostico">
			        	<input type="hidden" value="${diagnosticoVo.idDiagnostico}" name="diagnosticoVos[${varStatus.index}].idDiagnostico">
			        	<input type="hidden" value="${diagnosticoVo.idTipoDiagnostico}" name="diagnosticoVos[${varStatus.index}].idTipoDiagnostico">
			        	<div class="celda2 texto_supervisor blanco tam_10 centrado">${diagnosticoVo.codigo}
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_27 centrado">${diagnosticoVo.diagnostico}
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3" onclick="eliminarDiagnostico(${varStatus.index})">
			            </div>
			    	</div>			
				</c:forEach>	    		       	
			</div>   		 
	     </div> 

	    </div>

	    </div>
	 
	     <div class="linea_corta margen2 margen_arriba4">
	     	<div class="btn btn_guardar" onclick="guardar()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     	</div> 
	 
     </div>

    
    </div>
    
     
</div>

</form:form>


</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$.ajaxSetup({ cache: false });
		inicializaAutocomplete();
		$("#agregar").prop( "disabled", true );
	});
	
	function inicializaAutocomplete(){
			$("#diagnostico").autocomplete({
			source: "diagnostico",
	 	   	minLength: 3,
	        select: function( event, ui ){
	        	if (ui.item.idDiagnostico!=-1) {
	       	    	$("#diagnostico").val(ui.item.diagnostico);
	       	    	$("#idDiagnostico").val(ui.item.idDiagnostico);
	       	    	$("#codigo").val(ui.item.codigo);
	       	    	$("#idTipoDiagnostico").val(ui.item.idTipoDiagnostico);
	       	    	$("#agregar").prop( "disabled", false );
				}else{
	       	    	$("#diagnostico").val("");
	       	    	$("#idDiagnostico").val("");
	       	    	$("#codigo").val("");
				}
	            return false;
			},
			focus: function (event, ui) {
				if (ui.item.idDiagnostico!=-1) {
					$("#diagnostico").val(ui.item.diagnostico);
				}else{
	       	    	$("#diagnostico").val("");
	       	    	$("#idDiagnostico").val("");
	       	    	$("#codigo").val("");
				}
				return false;
	        }
	
		}).autocomplete("instance")._renderItem = function( ul, item ) {
		      return $("<li>")
		        .append(item.diagnostico)
		        .appendTo(ul);
		};  		
	}
	
	function agregarDaignostico(){
		var idDiagnostico = $("#idDiagnostico").val();
		var codigo = $("#codigo").val();
		var diagnostico = $("#diagnostico").val();
		var idDiagnosticoPrincipal = $("#idDiagnosticoPrincipal").val();
		var idTipoDiagnostico = $("input[name=idTipoDiagnostico]:checked").val();
		if($("#diagnosticoVo"+idDiagnostico).val() === undefined){
			if(idDiagnosticoPrincipal!=idDiagnostico){
				var rows = $("#tablaDiagnosticos .linea_tabla").length;
				if(rows!=0){
					var myElem = document.getElementById(rows);
					while(myElem!=null){
						rows++;
						myElem = document.getElementById(rows);
					}	
				}
				
				var filas = '<div class="linea_tabla alto" id="diagnosticoVo_'+rows+'">'+	
							'<input type="hidden" value="'+codigo+'" name="diagnosticoVos['+rows+'].codigo">'+
							'<input type="hidden" value="'+diagnostico+'" name="diagnosticoVos['+rows+'].diagnostico">'+
							'<input type="hidden" value="'+idDiagnostico+'" name="diagnosticoVos['+rows+'].idDiagnostico">'+
							'<input type="hidden" value="'+idTipoDiagnostico+'" name="diagnosticoVos['+rows+'].idTipoDiagnostico">'+
		    				'<div class="celda2 texto_supervisor blanco tam_10 centrado">'+codigo+'</div>'+
		    				'<div class="celda2 texto_supervisor blanco tam_27 centrado">'+diagnostico+'</div>'+
		    				'<div class="celda2 texto_supervisor blanco tam_6 centrado bote link3" onclick="eliminarDiagnostico('+rows+')"></div></div>';         		
				$("#tablaDiagnosticos").append(filas);
				$("#agregar").prop( "disabled", true );	
			}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe el diagnostico como principal</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
					autoSize: false,
					width: 470,
					height: 185
				});	 
				$("#agregar").prop( "disabled", true );
				$("#diagnostico").val("");
			}
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe el diagnostico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	 
			$("#agregar").prop( "disabled", true );
			$("#diagnostico").val("");		
		}
		$("#codigo").val("");
		$("#diagnostico").val("");
	}
	
	function eliminarDiagnostico(rows){
		$("#tablaDiagnosticos div#diagnosticoVo_"+rows).remove();
	}
	
	function guardar(){
		var rows = $("#tablaDiagnosticos .linea_tabla").length;
		if(rows!=0){
			var myElem = document.getElementById(rows);
			while(myElem!=null){
				rows++;
				myElem = document.getElementById(rows);
			}	
		}
		if(rows == 0){
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar algun Diagnostico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185
			});
		}else{
	 		var atencionOtrosDiagnosticosForm = $("#atencionOtrosDiagnosticosForm").serialize();
		    $.getJSON("saveDiagnosticos", atencionOtrosDiagnosticosForm ,function(response){
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>Los diagnosticos fueron actualizados correctamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
	 				autoSize: false,
	 				width: 480,
	 				height: 200
				});	
		    }); 
			
		}
	}
	
	function cerrar(){
		$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
		$("#medicoAtencionPacientesForm").submit();	
	}	

</script>
</html>
