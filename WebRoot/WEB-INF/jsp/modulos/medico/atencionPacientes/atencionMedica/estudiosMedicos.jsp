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
	<form:form name='atencionEstudiosMedicosForm' action="nuevoEstudioMedico" method='POST' commandName="atencionEstudiosMedicosForm" id="atencionEstudiosMedicosForm">
		<form:hidden path="idPrestacion" id="idPrestacion"/>
		<form:hidden path="idAtencion" id="idAtencion"/>
		<form:hidden path="idEstudios" id="idEstudios"/>
		<form:hidden path="codigo" id="codigo"/>
		<form:hidden path="orden" id="orden"/>
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
   				<li  id="notaMedica"><a href="javascript:atencionMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:receta();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li id="pestanalaboratorio"><a href="javascript:estudiosLaboratorio();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li id="pestanaGabinete"><a href="javascript:estudiosGabinete();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li id="pestanaOtros"><a href="javascript:estudiosOtros();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:licenciaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:solicitudReferencia();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:certificados();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>
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
      <div class="linea_corta_cont margen5">
       <div class="linea_corta">
   		<div class="texto_supervisor2 verde margen2">
	          <c:if test="${atencionEstudiosMedicosForm.idEstudios == 1}">
	          ORDEN DE ESTUDIOS DE GABINETE
	          </c:if>
	          <c:if test="${atencionEstudiosMedicosForm.idEstudios == 2}">
	          ORDEN DE ESTUDIOS DE LABORATORIO
	          </c:if>
	          <c:if test="${atencionEstudiosMedicosForm.idEstudios == 3}">
	          ORDEN DE OTROS ESTUDIOS
	          </c:if>
	          <c:if test="${atencionEstudiosMedicosForm.idEstudios == 4}">
	          PRESTACIONES REALIZADAS
	          </c:if>	          
	    </div>
	   </div>
			
		<div class="linea_supervisor margen_arriba4 margen2">
	    	<form:input path="prestacion" cssClass="campo_estudios tam_30" placeholder="Ingrese Código o descripción" />
	    	
	    	 <input class="margen5 btn_solo agregar_icono" id="agregar" onclick="agregarPrestacion()" />
	    </div>	
	    
	    <div class="linea_corta margen_arriba2 margen2">
		 <div class="tabla2 margen_arriba"> 
	     
	    <div class="tabla_contenedor desplazar alto_tabla2 centrado">

	        <div class="linea_tabla alto">
	        	<div class="celda3 fondo_v texto_supervisor blanco tam_10 centrado">Código
	            </div>
	            <div class="celda3 fondo_v texto_supervisor blanco tam_27 centrado">Descripción
	            </div>
	            <div class="celda3 fondo_v texto_supervisor blanco tam_6 centrado bote">
	            </div>
	    	</div>
	    	
			<div id="tablaPrestacion">
				<c:forEach items="${atencionEstudiosMedicosForm.prestacionesAutocompleteVos}" var="autocompleteVo" varStatus="varStatus" >
			        <div class="linea_tabla alto" id="${varStatus.index}">
			        	<input type="hidden" id="autocompleteVos${autocompleteVo.value}" value="x"/>
			        	<input type="hidden" name="prestacionesAutocompleteVos[${varStatus.index}].value" value="${autocompleteVo.value}"/>
			        	<div class="celda2 texto_supervisor blanco tam_10 centrado">${autocompleteVo.codigo}
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_27 centrado">${autocompleteVo.label}
			            </div>
			            <div class="celda2 texto_supervisor blanco tam_6 centrado bote link3" onclick="borrarPrestacion(${varStatus.index})">
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
	<script type="text/javascript">
		
		function borrarPrestacion(id){
			$("#tablaPrestacion div#"+id).remove();
		}
		
		function agregarPrestacion(){
			var idPrestacion = $("#idPrestacion").val();
			var prestacion = $("#prestacion").val();
			var codigo = $("#codigo").val();
			if($("#autocompleteVos"+idPrestacion).val() === undefined){
				var rows = $("#tablaPrestacion .linea_tabla").length;
				if(rows!=0){
					var myElem = document.getElementById(rows);
					while(myElem!=null){
						rows++;
					}	
				}
				
		    	var filas = '<div class="linea_tabla alto" id='+rows+'>'+
				        	'<input type="hidden" id="autocompleteVos'+idPrestacion+'" value="x"/>'+
				        	'<input type="hidden" name="prestacionesAutocompleteVos['+rows+'].value" value="'+idPrestacion+'"/>'+
				        	'<div class="celda2 texto_supervisor blanco tam_10 centrado">'+codigo+'</div>'+
				            '<div class="celda2 texto_supervisor blanco tam_27 centrado">'+prestacion+'</div>'+
				            '<div class="celda2 texto_supervisor blanco tam_6 centrado bote link3" onclick="borrarPrestacion('+rows+')"></div></div>';
	    			    	
				$("#tablaPrestacion").append(filas);
				$("#agregar").prop( "disabled", true );
				$("#prestacion").val("");
			}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe el estudio</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
					autoSize: false,
					width: 470,
					height: 185
				});	 
				$("#agregar").prop( "disabled", true );
				$("#prestacion").val("");
			}	
		}
    	
    	function inicializaAutocomplete(){
     		$("#prestacion").autocomplete({
    			source: "prestacionEstudios?idAtencion="+$("#idAtencion").val()+"&idServicio="+$("#idEstudios").val(),
    	 	   	minLength: 1,
                select: function( event, ui ){
                	if (ui.item.value!="-1") {
               	    	$("#prestacion").val(ui.item.label);
               	    	$("#idPrestacion").val(ui.item.value);
               	    	$("#codigo").val(ui.item.codigo);
               	    	$("#agregar").prop( "disabled", false );
    				}else{
               	    	$("#prestacion").val("");
               	    	$("#idPrestacion").val(0);
    				}
                    return false;
    			},
    			focus: function (event, ui) {
    				if (ui.item.value!="-1") {
    					$("#prestacion").val(ui.item.label);
    				}else{
               	    	$("#prestacion").val("");
               	    	$("#idPrestacion").val(0);
    				}
    				return false;
                }
    		});  		
    	} 
    	
    	function guardar(){
    		var rows = $("#tablaPrestacion .linea_tabla").length;
    		if(rows!=0){
    			var myElem = document.getElementById(rows);
    			while(myElem!=null){
    				rows++;
    				myElem = document.getElementById(rows);
    			}	
    		}
    		if(rows == 0){
    			$.fancybox.open({
    				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar algun Estudio</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
    				openEffect : 'elastic',
    				openSpeed  : 150,
    				closeEffect : 'elastic',
    				closeSpeed  : 150,								
     				autoSize: false,
     				width: 470,
     				height: 185
    			});
    		}else{
        		var atencionEstudiosMedicosForm = $("#atencionEstudiosMedicosForm").serialize();
    		    $.getJSON("nuevoEstudioMedico", atencionEstudiosMedicosForm ,function(response){
    		    	$("#orden").val(response.orden);
    				$.fancybox.open({
    					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>Los estudios fueron actualizados correctamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	
    	$(document).ready(function(){
    		$.ajaxSetup({ cache: false });
    		inicializaAutocomplete();
    		
    		switch($("#idEstudios").val()) {
	    		case "2":
		        $("#pestanalaboratorio").addClass("pactiva");
		        break;
		    	case "1":
		        $("#pestanaGabinete").addClass("pactiva");
		        break;
		        case "3":
		        $("#pestanaOtros").addClass("pactiva");
		        break;
		        case "4":
			    $("#notaMedica").addClass("pactiva");		        
		        break;
    		}
    		
    	});
    	
	        function notaMedica(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }
			
			function recetaMedica(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "recetaMedica");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "recetaMedica");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }
		    
		    function estudiosLaboratorio(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }
		    
		    function estudiosGabinete(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }
		    
		    function EstudiosOtros(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
							parent.$("#medicoAtencionPacientesForm").submit();				
						}
		     	}
		    }
		    
		    function licenciaMedica(){
		     if(parent.$("#actualizaDatos").val() == 0){
		    	parent.$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
				parent.$("#medicoAtencionPacientesForm").submit();
		     }else{
		     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
						if(respuesta){
							parent.$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
							parent.$("#medicoAtencionPacientesForm").submit();				
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
    	
    	function cerrar(){
    		$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
    		$("#medicoAtencionPacientesForm").submit();	
    	}	    	
	</script>

</body>
</html>