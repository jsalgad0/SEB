<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>

<html>
<body style="overflow:hidden;">
	<form:form name='atencionTerminarForm' action="finTerminar" method='POST' commandName="atencionTerminarForm" id="atencionTerminarForm">
		<form:hidden path="idAtencion" id="idAtencion"/>
		<form:hidden path="idAfiliadoTerminar" id="idAfiliadoTerminar"/>
		<form:hidden path="nombreAfiliado" id="nombreAfiliado"/>
		<form:hidden path="apellidoPaternoAfiliado" id="apellidoPaternoAfiliado"/>
		<form:hidden path="apellidoMaternoAfiliado" id="apellidoMaternoAfiliado"/>
		<form:hidden path="sexoAfiliado" id="sexoAfiliado"/>
		<form:hidden path="fechaNacimientoAfiliado" id="fechaNacimientoAfiliado"/>
		<form:hidden path="estadoAfiliado" id="estadoAfiliado"/>
		<form:hidden path="idUsuario" id="idUsuario"/>
		<form:hidden path="rfcUsuario" id="rfcUsuario"/>
		<form:hidden path="nombreUsuario" id="nombreUsuario"/>
		<form:hidden path="apellidoPaternoUsuario" id="apellidoPaternoUsuario"/>
		<form:hidden path="apellidoMaternoUsuario" id="apellidoMaternoUsuario"/>
		<form:hidden path="sexoUsuario" id="sexoUsuario"/>
		<form:hidden path="fechaNacimientoUsuario" id="fechaNacimientoUsuario"/>
		<form:hidden path="estadoUsuario" id="estadoUsuario"/>
		<form:hidden path="codigoAutentia" id="codigoAutentia"/>
		<form:hidden path="mensajeAutentia" id="mensajeAutentia"/>
		<form:hidden path="huellaAfiliado" id="huellaAfiliado"/>
		<form:hidden path="huellaMedico" id="huellaMedico"/>
		<form:hidden path="esNecesarioHuellaAfiliado" id="esNecesarioHuellaAfiliado"/>
		<form:hidden path="usarClave" id="usarClave"/>
		<form:hidden path="claveCorrecta" id="claveCorrecta"/>
		<form:hidden path="autorizacionMedico" id="autorizacionMedico"/>
		<form:hidden path="autorizacionAfiliado" id="autorizacionAfiliado"/>
		<form:hidden path="llenadoLicenciaMedica" id="llenadoLicenciaMedica"/>
		<form:hidden path="llenadoReferencia" id="llenadoReferencia"/>
		<form:hidden path="llenadoCuidadosMaternales" id="llenadoCuidadosMaternales"/>
		<form:hidden path="llenadoConstanciaSalud" id="llenadoConstanciaSalud"/>
		
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
	           ${tomaSignosVo.edad} años
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
	          Última Somatometría:
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
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:receta();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
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
   				
   				<li class="pactiva"><a href="javascript:terminar();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/terminar.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCIÓN</a></li>
   					</ul>
   				</li>   				
   			</ul>
   		</div>
   		
	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	         TERMINAR ATENCIÓN
	    </div>
    </div>
    
    		<c:if test="${not empty atencionTerminarForm.diagnosticoVos || not empty atencionTerminarForm.consultorioVos}">
	    		<div class="linea_supervisor margen">
						<div class="linea_90 margen5 margen_arriba4 texto_arriba borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.diagnosticoVos}" var="diagnosticoVo">
									<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${diagnosticoVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${diagnosticoVo.diagnostico}" />
		    					</c:forEach>
		    					
		    					<c:forEach items="${atencionTerminarForm.consultorioVos}" var="consultorioVos">
									<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${consultorioVos.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${consultorioVos.label}" />
		    					</c:forEach>		    					
				   		 </div>	   		     
	    		</div>    		
    		</c:if>
 
			<c:if test="${not empty atencionTerminarForm.recetasVos}">    		
	    		<div class="linea_supervisor margen">
						<div class="linea_90 margen5 margen_arriba4 texto_arriba borde2 desplazar tam_14e">
								<c:forEach items="${atencionTerminarForm.recetasVos}" var="recetasVo">
									<div class="titulo_supervisor gris tam_12 margen2">Medicamentos
			    					</div>	
									<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${recetasVo.idMedicamento}" />
									<input class="campo_supervisor3 tam_2" placeholder="Descripción" value="${recetasVo.medicamento}" />
									<input class="campo_supervisor3 tam_2" placeholder="Indicaciones" value="${recetasVo.unidad} ${recetasVo.unidadDescripcion} cada ${recetasVo.cada} ${recetasVo.cadaDescripcion} durante ${recetasVo.durante} ${recetasVo.duranteDescripcion}"/>
								</c:forEach>
															
				   		 </div>		   		     
	    		</div>
    		</c:if>
    		
    		<c:if test="${not empty atencionTerminarForm.laboratorioVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.laboratorioVos}" var="laboratorioVo">
									<div class="titulo_supervisor gris tam_12 margen2">Exám. Clínicos
			    					</div>		    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${laboratorioVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${laboratorioVo.label}" />
		    					</c:forEach>	
				   		 </div>	    	
				</div>
			</c:if>			
			
			<c:if test="${not empty atencionTerminarForm.gabineteVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.gabineteVos}" var="gabineteVo">
									<div class="titulo_supervisor gris tam_12 margen2">Est. de gabinete
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${gabineteVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${gabineteVo.label}" />
		    					</c:forEach>
				   		 </div>	    	
				</div>	
			</c:if>
			
			<c:if test="${not empty atencionTerminarForm.otrosVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14f">
		    					<c:forEach items="${atencionTerminarForm.otrosVos}" var="otrosVo">
									<div class="titulo_supervisor gris tam_12 margen2">Otros estudios
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${otrosVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${otrosVo.label}" />
		    					</c:forEach>
				   		 </div>	    	
				</div>
			</c:if>
			
			<c:if test="${atencionTerminarForm.llenadoLicenciaMedica == true}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 tam_14f">
								<div class="titulo_supervisor gris tam_12 margen2">Licencia médica
		    					</div>	
								<div class="texto_chico3 verde margen2">Motivo
									<input class="campo_supervisor3 tam_4" placeholder="Motivo" value="${atencionTerminarForm.licenciaMedicaVo.motivo}" />
								</div>
								<div class="texto_chico3 verde">Carácter
									<input class="campo_supervisor3 tam_4" placeholder="Carácter" value="${atencionTerminarForm.licenciaMedicaVo.caracter}" />
								</div>
								
								<div class="texto_chico3 verde">Desde
									<input class="campo_supervisor3 tam_8" placeholder="fecha" value="${atencionTerminarForm.licenciaMedicaVo.fechaDesde}" />
								</div>
								
								<div class="texto_chico3 verde">Hasta
									<input class="campo_supervisor3 tam_8" placeholder="fecha" value="${atencionTerminarForm.licenciaMedicaVo.fechaHasta}" />
								</div>
				   		 </div>	    	
				</div>			
			</c:if>

			
    		<div class="linea_supervisor margen">
    				<c:if test="${atencionTerminarForm.llenadoReferencia == true}">
    				<div class="contenedor_medio margen5 margen_arriba4 borde2 tam_14f">
							<div class="titulo_supervisor gris tam_17 margen2">Referencia a:
	    					</div>	
							<input class="campo_supervisor3 tam_5" placeholder="Especialidad" value="${atencionTerminarForm.especialidad}" />
					</div> 
					</c:if>
					<c:if test="${atencionTerminarForm.llenadoCuidadosMaternales == true}">
						<div class="linea_mini3 margen5 borde2 tam_14f">		
								<div class="texto_chico3 verde margen14 link2">Cuidados maternales
								</div>
								<input class="campo_transparente tam_0" />
						</div>
					</c:if>
					<c:if test="${atencionTerminarForm.llenadoConstanciaSalud == true}">
					<div class="linea_mini3 margen5 borde2 tam_14f">		
							<div class="texto_chico3 verde margen14 link2">Constancia de salud
							</div>
							<input class="campo_transparente tam_0" />
			   		 </div>		    	
			   		 </c:if>
			</div>											
	
     <div class="linea_supervisor margen margen_arriba3">
            <div class="btn btn_firmar" onclick="firmar()"></div>
            <div class="btn btn_cerrar_resumen" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script type="text/javascript">
	function firmar(){
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup ask\"></div><h1>Esta acción no permite realizar más cambios a la Nota Médica, desea continuar?</h1><div class=\"btn_popup\"><div class=\"btn_si_popup\" onclick=\"$.fancybox.close();\"></div><div class=\"btn_no_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
			type : 'iframe',
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			autoSize: false,
			width: 500,
			height: 190,
			afterClose : function(){	
				setTimeout(huellaAfiliado, 50);			
			}
		});		
	}
	
    function cerrar(){
		$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
		$("#medicoAtencionPacientesForm").submit();	
	}	
	
	function huellaAfiliado(){
		var huellaAfiliado = $("#huellaAfiliado").val();
		var esNecesarioHuellaAfiliado = $("#esNecesarioHuellaAfiliado").val();
		if (esNecesarioHuellaAfiliado == 1) {
			if (huellaAfiliado == 1) {
				if (IniciarUsuario(0, $("#idAfiliadoTerminar").val(), 0, $("#idAfiliadoTerminar").val(), "AfiliadoId", $("#nombreAfiliado").val(), $("#apellidoPaternoAfiliado").val(), $("#apellidoMaternoAfiliado").val(), $("#sexoAfiliado").val(), $("#fechaNacimientoAfiliado").val(), $("#estadoAfiliado").val())) {
					$("#autorizacionAfiliado").val(1);
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La huella del paciente es exitosa</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
						autoSize: false,
						width: 470,
						height: 185,
						afterClose : function(){	
							setTimeout(huellaMedico, 50);			
						}				
					});
				}else{
					$("#autorizacionAfiliado").val(2);
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La huella ingresada no coincide con la registrada para el paciente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
						autoSize: false,
						width: 470,
						height: 185,
						afterClose : function(){	
							setTimeout(huellaMedico, 50);			
						}				
					});
				}
			}else{
				$("#autorizacionAfiliado").val(3);
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Paciente con Autorización especial</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
					autoSize: false,
					width: 470,
					height: 185,
					afterClose : function(){	
						setTimeout(huellaMedico, 50);			
					}				
				});
			}			
		}else{
			$("#autorizacionAfiliado").val(0);
			huellaMedico();
		}

	}

	function huellaMedico(){
		var usarClave = $("#usarClave").val();
		if (usarClave != 1) {
			if (IniciarUsuario($("#idUsuario").val(), 0, 0, $("#rfcUsuario").val(), "RFC",  $("#nombreUsuario").val(), $("#apellidoPaternoUsuario").val(), $("#apellidoMaternoUsuario").val(), $("#sexoUsuario").val(), $("#fechaNacimientoUsuario").val(), $("#estadoUsuario").val())) {
				$("#autorizacionMedico").val(0);
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La huella del medico es exitosa</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
					autoSize: false,
					width: 470,
					height: 185,
					afterClose : function(){	
						$("#atencionTerminarForm").submit();			
					}								
				});
			}else{
				$("#autorizacionMedico").val(1);
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La huella ingresada no coincide con la registrada para el medico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
	 				autoSize: false,
	 				width: 470,
	 				height: 185,
					afterClose : function(){	
						window.location.href = '/SAB/menu';			
					}	
				});
			}	
		}else{
    		var ctx = "${pageContext.request.contextPath}";
    		$.fancybox.open({
    			href : ctx + '/medico/mostrarClave',
    			type : 'iframe',
    			padding : 5,
    			autoSize: false,
    			width: 520,
    			height: 380,
    			afterClose : function(){
    				var claveCorrecta = $("#claveCorrecta").val();
    				if (claveCorrecta==1) {
    					$("#autorizacionMedico").val(2);
    					$("#atencionTerminarForm").submit();
					}else{
						$("#autorizacionMedico").val(3);
						setTimeout(mensajeClaveMedico, 50);
					}
 				}
    		});
		}
	}
		
	function mensajeClaveMedico(){
		$.fancybox.open({
			content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>La clave ingresada no coincide con la registrada para el usuario. El Término de Atención debe ser autorizado por la Dirección Médica</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185,
				afterClose : function(){	
					window.location.href = '/SAB/menu';			
				}					
		});
	}
			 				
</script>

</body>
</html>