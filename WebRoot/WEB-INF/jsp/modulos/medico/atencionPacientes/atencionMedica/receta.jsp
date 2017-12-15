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
	<form:form name='atencionRecetaForm' action="nuevoEstudioMedico" method='POST' commandName="atencionRecetaForm" id="atencionRecetaForm">
		<form:hidden path="idAtencion" id="idAtencion"/>
		<form:hidden path="idMedicamento" id="idMedicamento"/>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	        ${tomaSignosVo.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen tam_16">
	          Edad: 
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	           ${tomaSignosVo.edad} aÒos
	         </div>
	         
	         <div class="texto_supervisor verde tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${tomaSignosVo.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	           ${tomaSignosVo.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	           ${tomaSignosVo.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          ⁄ltima SomatometrÌa:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	         ${tomaSignosVo.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="javascript:atencionMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA M…DICA</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="javascript:receta();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA M…DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosLaboratorio();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosGabinete();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosOtros();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:licenciaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA M…DICA</a></li>
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
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCI”N</a></li>
   					</ul>
   				</li>   				
   			</ul>
   		</div>
   		
   	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	          RECETA M…DICA 
	    </div>
    </div>		
    
  	<div class="linea_supervisor margen">

            <form:input cssClass="campo_supervisor tam_20" placeholder="Medicamento" path="medicamento" />
            <div class="texto_chico3 gris tam_17 centrado">VÌa de AdministraciÛn:</div>
            <form:select path="idUnidad" cssClass="selectt campo_supervisor tam_3">
            	<form:option value="-1" label="Unidad" />
            	<form:options items="${catviasdeadminmedicamentos}" itemValue="viaDeAdmonMedicamentoId" itemLabel="viaDeAdmonMedicamento"/>
            </form:select>    		
            <form:input cssClass="campo_supervisor tam_16 centrado" placeholder="No." path="unidad" maxlength="2" />
	</div>            
	
	<div class="linea_supervisor margen">
    		<div class="texto_chico3 gris tam_17">Indicaciones:</div>
    		<form:input cssClass="campo_supervisor tam_16 centrado" placeholder="No." maxlength="2" path="dosisNo" />
            <form:input cssClass="campo_supervisor tam_7" placeholder="Dosis" maxlength="25" path="dosis" />
            
			<div class="texto_chico3 gris tam_16">cada</div>            
    		<form:input cssClass="campo_supervisor tam_16 centrado" placeholder="#" path="cada" maxlength="2" />
            <form:select path="idCada" cssClass="selectt campo_supervisor tam_17">
            	<form:option value="-1" label="Unidad" />
            	<form:options items="${catunidadesdetiemposMenorDia}" itemValue="unidadTiempoId" itemLabel="unidadTiempo"/>
            </form:select>    
            		
    		<div class="texto_chico3 gris tam_1">durante</div>
    		<form:input cssClass="campo_supervisor tam_16 centrado" placeholder="#" path="durante" maxlength="2"/>
            <form:select path="idDurante" cssClass="selectt campo_supervisor tam_4">
            	<form:option value="-1" label="Unidad" />
            	<form:options items="${catunidadesdetiemposMayorDia}" itemValue="unidadTiempoId" itemLabel="unidadTiempo"/>
            </form:select>    		    		
            <input class="btn_solo agregar_icono margen14" id="agregar" onclick="agregarReceta();" />
    </div>
    
    <div class="linea_supervisor margen">
   		<form:textarea cssClass="campo_supervisor tam_23" placeholder="Observaciones" path="observaciones" ></form:textarea>
    </div>
    
    <div class="linea_corta margen">
   		<div class="linea_corta texto_arriba margen_arriba2 borde2 desplazar tam_14c" id="tablaMedicamentos">
			
			<c:forEach items="${atencionRecetaForm.recetasVos}" var="recetaVo" varStatus="varStatus">
			<div class="linea_receta tam_14d" id="${varStatus.index}">
				<input type="hidden" value="x" id="recetaVo${recetaVo.idMedicamento}">
				<input type="hidden" value="${recetaVo.idMedicamento}" name="recetasVos[${varStatus.index}].idMedicamento">
				<input type="hidden" value="${recetaVo.idUnidad}" name="recetasVos[${varStatus.index}].idUnidad">
				<input type="hidden" value="${recetaVo.idCada}" name="recetasVos[${varStatus.index}].idCada">
				<input type="hidden" value="${recetaVo.idDurante}" name="recetasVos[${varStatus.index}].idDurante">
				<input type="hidden" value="${recetaVo.observaciones}" name="recetasVos[${varStatus.index}].observaciones">
				<input type="hidden" value="${recetaVo.unidad}" name="recetasVos[${varStatus.index}].unidad">
				<input type="hidden" value="${recetaVo.cada}" name="recetasVos[${varStatus.index}].cada">
				<input type="hidden" value="${recetaVo.durante}" name="recetasVos[${varStatus.index}].durante">
				<input type="hidden" value="${recetaVo.dosis}" name="recetasVos[${varStatus.index}].dosis">
				<input type="hidden" value="${recetaVo.dosisNo}" name="recetasVos[${varStatus.index}].dosisNo">
				<div class="linea_corta3">
					<div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">${recetaVo.medicamento}</div>
		            <div class="texto_supervisor tam_16 blanco centrado">${recetaVo.unidad}</div>
		            <div class="texto_supervisor tam_17 blanco centrado">${recetaVo.unidadDescripcion}</div>
		    		<div class="texto_supervisor blanco tam_16">cada</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">${recetaVo.cada}</div>
		            <div class="texto_supervisor tam_17 blanco centrado">${recetaVo.cadaDescripcion}</div>
		    		<div class="texto_supervisor blanco tam_1">durante</div>
		    		<div class="texto_supervisor tam_16 blanco centrado">${recetaVo.durante}</div>
		            <div class="texto_supervisor tam_6 blanco centrado">${recetaVo.duranteDescripcion}</div>
		      	</div>      
				<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;" onclick="eliminarReceta(${varStatus.index})">
	            </div>			
		 	</div>	
		 	</c:forEach> 		 			 		 			 			 			 	
							
		 </div>
	</div>
	
	<div class="linea_supervisor margen margen_arriba2">
   		<div class="titulo_supervisor gris tam_3">Receta resurtible
	    	<form:checkbox path="resurtible" />
	    </div>
	    <div class="texto_supervisor5 gris">cada</div>
    		<form:input path="recetaCada" cssClass="campo_supervisor tam_16 centrado" placeholder="#" maxlength="2"/>
    	<div class="texto_supervisor5 gris">mes(es) durante</div>	
    		<form:input path="recetaDurante" cssClass="campo_supervisor tam_16 centrado" placeholder="#" maxlength="2"/>	
    	<div class="texto_supervisor5 gris">mes(es)</div>	
    </div>
	
     <div class="linea_supervisor margen margen_arriba">
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
		
		$("#unidad").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		
		$("#dosisNo").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });		
		
		$("#cada").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		
		$("#durante").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		
		$("#recetaCada").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		
		$("#recetaDurante").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		
		$("#observaciones").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑',
	        allow: ' '
	    });	
		
		$("#dosis").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑',
	        allow: ' '
	    });		
	});
	
	function inicializaAutocomplete(){
			$("#medicamento").autocomplete({
			source: "medicamento",
	 	   	minLength: 3,
	        select: function( event, ui ){
	        	if (ui.item.idDiagnostico!=-1) {
	       	    	$("#medicamento").val(ui.item.medicamento);
	       	    	$("#idMedicamento").val(ui.item.idMedicamento);
	       	    	$("#agregar").prop( "disabled", false );
				}else{
	       	    	$("#medicamento").val("");
	       	    	$("#idMedicamento").val(0);
				}
	            return false;
			},
			focus: function (event, ui) {
				if (ui.item.idMedicamento!=-1) {
					$("#medicamento").val(ui.item.medicamento);
				}else{
	       	    	$("#medicamento").val("");
	       	    	$("#idMedicamento").val("");
				}
				return false;
	        }
	
		}).autocomplete("instance")._renderItem = function( ul, item ) {
		      return $("<li>")
		        .append(item.medicamento)
		        .appendTo(ul);
		};  		
	}
	
	function agregarReceta(){
		var idMedicamento = $("#idMedicamento").val();
		var medicamento = $("#medicamento").val();
		var unidad = $("#unidad").val();
		var unidadDescripcion = $("#idUnidad option:selected").text();
		var idUnidad = $("#idUnidad").val();
		var durante = $("#durante").val();
		var duranteDescripcion = $("#idDurante option:selected").text();
		var idDurante = $("#idDurante").val();
		var cada = $("#cada").val();
		var cadaDescripcion = $("#idCada option:selected").text();
		var idCada = $("#idCada").val();
		var dosis = $("#dosis").val();
		var dosisNo = $("#dosisNo").val();
		var observaciones = $("#observaciones").val();
		if($("#recetaVo"+idMedicamento).val() === undefined){
			if(unidad!=""){
				if(cada!=""){
					if(durante!=""){
						if(idUnidad!=-1){
							if(idCada!=-1){
								if(idDurante!=-1){
									if(dosisNo!=""){
										if(dosis!=""){
											var rows = $("#tablaMedicamentos .linea_receta").length;
											if(rows!=0){
												var myElem = document.getElementById(rows);
												while(myElem!=null){
													rows++;
													myElem = document.getElementById(rows);
												}	
											}
											
											var filas = '<div class="linea_receta tam_14d" id="'+rows+'">'+
														'<input type="hidden" value="x" id="recetaVo'+idMedicamento+'">'+
														'<input type="hidden" value="'+idMedicamento+'" name="recetasVos['+rows+'].idMedicamento">'+
														'<input type="hidden" value="'+idUnidad+'" name="recetasVos['+rows+'].idUnidad">'+
														'<input type="hidden" value="'+idCada+'" name="recetasVos['+rows+'].idCada">'+
														'<input type="hidden" value="'+idDurante+'" name="recetasVos['+rows+'].idDurante">'+
														'<input type="hidden" value="'+observaciones+'" name="recetasVos['+rows+'].observaciones">'+
														'<input type="hidden" value="'+unidad+'" name="recetasVos['+rows+'].unidad">'+
														'<input type="hidden" value="'+cada+'" name="recetasVos['+rows+'].cada">'+
														'<input type="hidden" value="'+durante+'" name="recetasVos['+rows+'].durante">'+
														'<input type="hidden" value="'+dosis+'" name="recetasVos['+rows+'].dosis">'+
														'<input type="hidden" value="'+dosisNo+'" name="recetasVos['+rows+'].dosisNo">'+
														'<div class="linea_corta3"><div class="texto_supervisor  blanco tam_9 margen5 margen_arriba3">'+medicamento.substring(0, 25)+'</div>'+
											            '<div class="texto_supervisor tam_16 blanco centrado">'+unidad+'</div>'+
											            '<div class="texto_supervisor tam_17 blanco centrado">'+unidadDescripcion+'</div>'+
											    		'<div class="texto_supervisor blanco tam_16">cada</div>'+
											    		'<div class="texto_supervisor tam_16 blanco centrado">'+cada+'</div>'+
											            '<div class="texto_supervisor tam_17 blanco centrado">'+cadaDescripcion+'</div>'+
											    		'<div class="texto_supervisor blanco tam_1">durante</div>'+
											    		'<div class="texto_supervisor tam_16 blanco centrado">'+durante+'</div>'+
											            '<div class="texto_supervisor tam_6 blanco centrado">'+duranteDescripcion+'</div></div>'+
									      	      		'<div class="texto_supervisor blanco tam_6 centrado bote_chico link3" style="margin-top:2px;" onclick="eliminarReceta('+rows+')"></div></div>';
											$("#tablaMedicamentos").append(filas);
											$("#agregar").prop( "disabled", true );	
											$("#medicamento").val("");
											$("#unidad").val("");
											$("#idUnidad").val("-1");
											$("#durante").val("");
											$("#idDurante").val("-1");
											$("#cada").val("");
											$("#idCada").val("-1");
											$("#dosis").val("");
											$("#dosisNo").val("");
											$("#observaciones").val("");											
										}else{
											$.fancybox.open({
												content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la dosis</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
												openEffect : 'elastic',
												openSpeed  : 150,
												closeEffect : 'elastic',
												closeSpeed  : 150,								
												autoSize: false,
												width: 470,
												height: 185
											});
										}
									}else{
										$.fancybox.open({
											content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la cantidad de dosis</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
											openEffect : 'elastic',
											openSpeed  : 150,
											closeEffect : 'elastic',
											closeSpeed  : 150,								
											autoSize: false,
											width: 470,
											height: 185
										});
									}									
								}else{
									$.fancybox.open({
										content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe seleccionar la duracion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
										openEffect : 'elastic',
										openSpeed  : 150,
										closeEffect : 'elastic',
										closeSpeed  : 150,								
										autoSize: false,
										width: 470,
										height: 185
									});	 	
								}
							}else{
								$.fancybox.open({
									content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe seleccionar la frecuencia de la dosis</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
									openEffect : 'elastic',
									openSpeed  : 150,
									closeEffect : 'elastic',
									closeSpeed  : 150,								
									autoSize: false,
									width: 470,
									height: 185
								});	 								
							}
						}else{
							$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe seleccionar el tipo de unidad</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
								autoSize: false,
								width: 470,
								height: 185
							});	 
						}	
					}else{
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la duracion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
							autoSize: false,
							width: 470,
							height: 185
						});	 
					}	
				}else{
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la dosis</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
						autoSize: false,
						width: 470,
						height: 185
					});	 
				}	
			}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la unidad</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
					autoSize: false,
					width: 470,
					height: 185
				});	 
			}
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe el medicamento</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	 
			$("#agregar").prop( "disabled", true );
			$("#medicamento").val("");		
		}

	}
	
	function eliminarReceta(rows){
		$("#tablaMedicamentos div#"+rows).remove();
	}
	
	function guardar(){
		var continuar = false;
		var resurtible = $("#resurtible").val();
		var recetaCada = $("#recetaCada").val();
		var recetaDurante = $("#recetaDurante").val();
		var rows = $("#tablaMedicamentos .linea_receta").length;

		if(rows == 0){
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar alguna Receta</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185
			});
		}else{
			if (resurtible=="true") {
				if (recetaCada!="") {
					if (recetaDurante!="") {
						continuar = true;
					}else{
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la frecuencia de la receta resurtible</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 470,
			 				height: 185
						});
					}	
				}else{
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la duracion de la receta resurtible</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}
			}else{
				continuar = true;
			}
			
			if (continuar) {
		 		var atencionRecetaForm = $("#atencionRecetaForm").serialize();
			    $.getJSON("saveReceta", atencionRecetaForm ,function(response){
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>Las Recetas fueron actualizados correctamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	}
	
	function cerrar(){
		$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
		$("#medicoAtencionPacientesForm").submit();	
	}	

</script>
</html>