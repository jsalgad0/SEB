<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>


<body>
<div id="contenedor_gral">

	<div id="pre-load" style="display: none;"><div id="imagen-load"><img src="${pageContext.request.contextPath}/resources/img/load_flor.gif" alt="" /></div>
	</div>
<div id="admin_contenido">
	<form:form action="actualizarAtencionPendiente" method='POST' commandName="recuperarAtencionForm">
		
		<form:hidden path="idMedico"/>
		<form:hidden path="idAtencionMedica"/>
		<form:hidden path="idAgenda"/>
		<form:hidden path="idAfiliado"/>
		
		
		<form:hidden path="idConvenio"/>
		<div class="admin_titulo">Folio de Atención
    		<input class="campo_supervisor tam_6" placeholder="Folio" value="${recuperarAtencionForm.folio}" disabled="disabled" />
		</div>
		
	    <div class="admin_pleca"></div>
	                    
	    <div class="linea_supervisor">
	        
	
	    </div>
	    
		<div class="contenedor_recepcion">
		
	    	<div class="linea_supervisor margen4">
				<select class="selectt campo_supervisor tam_2" disabled="disabled">
					<option value="${recuperarAtencionForm.idAsegurador}">${recuperarAtencionForm.descripcionAsegurador} </option>
				</select>

				<select class="selectt campo_supervisor tam_2" disabled="disabled">
					<option value="${recuperarAtencionForm.idPrestador}">${recuperarAtencionForm.descripcionPrestador} </option>
				</select>
				
				<select class="selectt campo_supervisor tam_11" disabled="disabled" >
					<option value="${recuperarAtencionForm.idConvenio}">${recuperarAtencionForm.descripcionConvenio} </option>
				</select>					            
	    	</div>		
		
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>

	        <div class="linea_supervisor margen4">
				<select class="selectt campo_supervisor tam_4" disabled="disabled">
					<option value="${recuperarAtencionForm.idIdentificador}">${recuperarAtencionForm.descripcionIdentificador} </option>
				</select>        
				
				<input class="campo_supervisor tam_7" placeholder="ID Paciente" disabled="disabled" value="${recuperarAtencionForm.idPaciente} "/>			
				
				
	            <div class="texto_supervisor gris tam_11">
	            	<div id="derechohabienteDiv">DERECHOHABIENTE<input type="radio" id="tipoAfiliado" value="D" label="DERECHOHABIENTE" disabled="true" /> BENEFICIARIO<input type="radio" path="tipoAfiliado" value="B" label="BENEFICIARIO" disabled="true"/></div>
	            	<div id="sexoAfiliadoDiv" style="display: none;"><input type="radio" id="sexoAfiliado" value="1" label="MASCULINO" /><input type="radio" id="sexoAfiliado" value="2" label="FEMENINO" /></div>
	         	</div>
	            
	            <div class="texto_supervisor gris tam_7" style="font-family:quicksand-bold">
	             		¿Asiste el paciente?
	             		<c:choose>
	             			<c:when test="${recuperarAtencionForm.asistio == true }">
								<input type="checkbox" checked="checked" disabled="true" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" disabled="true" />
							</c:otherwise>
	             		</c:choose>
	            </div>
	
	    	</div>	
    	
	        
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
			 
	     	<div class="linea_supervisor margen4">
	     		<input id="apellidoPaterno" class="campo_supervisor tam_9"  disabled="disabled" placeholder="Apellido Paterno" value="${recuperarAtencionForm.apellidoPaterno}"/>
	     		<input id="apellidoMaterno" class="campo_supervisor tam_9" disabled="disabled" placeholder="Apellido Materno" value="${recuperarAtencionForm.apellidoMaterno}"/>
	     		<input id="nombre" class="campo_supervisor tam_5" placeholder="Nombre" disabled="disabled" value="${recuperarAtencionForm.nombre}"/>
	            
	            <input type="button" id="nuevo" value="nuevo" onclick="nuevoAfiliado();" style="display: none;"/>
	    	</div>	
	    	
	    	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
	    		
	        <div class="linea_supervisor margen4">
	            <input class="campo_supervisor gris tam_4 datepicker" placeholder="Fecha de Nac" disabled="disabled" value="${recuperarAtencionForm.fechaDeNac}" />
	            <form:input path="telefono" id="telefono1" cssClass="campo_supervisor tam_12" placeholder="Teléfono" maxlength="10" value="${recuperarAtencionForm.telefono}"/>
	            <form:input path="mail" id="mail" cssClass="campo_supervisor tam_11" placeholder="Correo Electrónico" maxlength="50" value="${recuperarAtencionForm.mail}"/>
	    	</div> 
	    	
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
			
		
			
			
	        <div class="linea_supervisor margen4" id="divPrestaciones">
	            <input class="campo_supervisor tam_11 mayusculas" placeholder="Descripción" id="prestacion" />
	            <input type="hidden" id="idPrestacion">
	            <form:input path="cantidad" cssClass="campo_supervisor tam_8" maxlength="2" placeholder="Cant"/>
	            <form:input path="medico" cssClass="campo_supervisor tam_5" cssStyle="margin-right:0px !important; display:none;" placeholder="Médico" readonly="true"/>
	            <input class="btn_solo buscar_icono" onclick="seleccionarMedico()" id="botonSeleccionarMedico" style="display: none;"/>
	           
	           <form:input path="idTiempo" cssClass="campo_supervisor tam_8 margen5" readonly="true" cssStyle="display:none;"/>
	          	<input class="btn_solo agregar_icono" id="agregar" onclick="agregarPrestacion()" disabled />
	          
	    	</div>
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
	    	<c:choose>
	    	<c:when test="${not empty recuperarAtencionForm.autocompleteVos  }"> 
	    	
			<div class="tabla2 margen4 margen_arriba" id="tablaPrincipalPrestaciones">
      
				<div class="tabla2">  
					<div class="titulo_tabla alto">
			        	<div class="celda2 fondo_v texto_supervisor blanco tam_19">Código-Descripción
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Cant
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Valor
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Aporte
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Copago
			            </div>
			            <div class="celda2 fondo_v texto_supervisor bote blanco tam_1 centrado">
			            </div>
			    	</div>
	      		</div>
	      
				<div class="tabla_contenedor desplazar tam_14 centrado">
					<div class="tabla2 tam_14" id="tablaPrestaciones"> 
						<c:forEach items="${recuperarAtencionForm.autocompleteVos}" var="autocompleteVo" varStatus="varStatus" > 
							<div class="linea_tabla alto" id="${varStatus.index}">
								<input type="hidden" id="autocompleteVos${autocompleteVo.value}" value="x"/>
								<input type="hidden" name="autocompleteVos[${varStatus.index}].value" value="${autocompleteVo.value}"/>
								<input type="hidden" name="autocompleteVos[${varStatus.index}].label" id="label${varStatus.index}" value="${autocompleteVo.codigo} / ${autocompleteVo.label}" />
								
									<input type="hidden" name="autocompleteVos[${varStatus.index}].valor" id="valorHidden${autocompleteVo.value}"/>
				    				<input type="hidden" name="autocompleteVos[${varStatus.index}].aporte" id="aporteHidden${autocompleteVo.value}"/>
				    				<input type="hidden" name="autocompleteVos[${varStatus.index}].copago" id="copagoHidden${autocompleteVo.value}"/>
				    				<input type="hidden" name="autocompleteVos[${varStatus.index}].cantidad" value="${autocompleteVo.cantidad}"/>
								
								<div class="celda2 texto_supervisor blanco tam_19">${autocompleteVo.codigo} / ${autocompleteVo.label}</div>
								<div class="celda2 texto_supervisor blanco tam_6 centrado">${autocompleteVo.cantidad}</div>
		            			<div class="celda2 texto_supervisor blanco tam_6 centrado" id="valor${autocompleteVo.value}"></div>
		            			<div class="celda2 texto_supervisor blanco tam_6 centrado"  id="aporte${autocompleteVo.value}"></div>
		            			<div class="celda2 texto_supervisor blanco tam_8 centrado"  id="copago${autocompleteVo.value}"></div>
		            			<div class="celda2 texto_supervisor blanco tam_1 centrado bote link3" onclick="borrarPrestacion('${varStatus.index}')"></div>
		    				</div>
		    			</c:forEach>
		    			
					</div>
	     		</div>
			</div> 	
			</c:when>
			<c:otherwise>
			<div class="tabla2 margen4 margen_arriba" id="tablaPrincipalPrestaciones" style="display:none">
      
				<div class="tabla2" >  
					<div class="titulo_tabla alto">
			        	<div class="celda2 fondo_v texto_supervisor blanco tam_19">Código-Descripción
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Cant
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Valor
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Aporte
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Copago
			            </div>
			            <div class="celda2 fondo_v texto_supervisor bote blanco tam_1 centrado">
			            </div>
			    	</div>
	      		</div>
	      
				<div class="tabla_contenedor desplazar tam_14 centrado" >
					<div class="tabla2 tam_14" id="tablaPrestaciones"> 
						
					</div>
	     		</div>
			</div> 	
			</c:otherwise>
			</c:choose>		 	
					
			<div id="dialog-form" title="Acompañantes o Personas de Confianza Registradas"> 
			</div>
			
			<div id="dialog-form-medicos" title="Seleccion de medicos"> 
			</div>
			
			<div id="divMedico" style="display: none;">
				<table id="tablaMedicos">
				</table>
				<input type="button" value="Guardar" onclick="enviarForma();"/>
			</div>
			
			<div class="linea_supervisor margen4">
				<div class="btn btn_valorizar" id="botonValorizar" onclick="valorizar();"></div>
				<div class="btn btn_guardar" id="botonGuardar" style="display: none;" onclick="guardar();"></div>
				<div class="btn btn_cerrar" onclick="cerrar();"></div>
			</div> 		
		</div>				
	</form:form>

</div>
</div>
	
	<script type="text/javascript">
	
	
	
function validaTelCorreo(){
			var flag = false;
			var verificaTelefono = false;
			var verificaCorreo = false;
			var telefono = $("#telefono1").val();
			var mail = $("#mail").val();
			if(telefono!=""){
				if (telefono.length!=10 || isNaN(telefono)){
					verificaTelefono = false;
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el Telefono</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
					verificaTelefono = true;	
				} 
			}else{
				verificaTelefono = true;
			}
			
			if(mail!=""){
				if (!validateEmail(mail)){
					verificaCorreo = false;
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el Correo Electronico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
					verificaCorreo = true;	
				} 
			}else{
				verificaCorreo = true;
			}			
			
			if (verificaTelefono && verificaCorreo) {
				flag = true;
			}
			
			return flag;
}

	function guardar(){
	var flag = validaTelCorreo();
	
	if(flag == true){
				var recuperarAtencionForm = $("#recuperarAtencionForm").serialize();
				$("#pre-load").show();
			    $.getJSON("actualizarAtencionPendiente", recuperarAtencionForm ,function(response){
			    	if(response.error != undefined && response.error != ""){
			    	
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});				    		
			    	}else{
			    	
			    		//$("#folio").val(response.folio);				    	
				    	//var vigencia = $("#vigencia").val();
						//var autentia = $("#autentia").val();
						
						/*if(vigencia == "true" && autentia == "true"){
							mensaje = "Atencion Medica guardada con el folio: "+response.folio;
						}else if(autentia == "false"){
							mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Identidad no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
						}else if (vigencia == "false"){
							mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Vigencia no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
						}else{
							mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Identidad y Vigencia no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
						}
						*/
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>Atención médica actualizada</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200,
			 				afterClose : function(){
			 					var ctx = "${pageContext.request.contextPath}";
			 					window.location.href = ctx+"/menu";
			 				}
						});	
			    	}
			    }).complete(function(){$("#pre-load").hide();});	
		}
	}
	
	function seleccionarMedico(){
		abrirDialogMedico();
	} 
	
	
	function valorizar(){
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows!=0){
			var recuperarAtencionForm = $("#recuperarAtencionForm").serialize();
			$("#pre-load").show();
		    $.getJSON("valorizarPendientes", recuperarAtencionForm ,function(response){
		    	var autocompleteVos = response.autocompleteVos;
		    	for (var i = 0; i < autocompleteVos.length; i++) {
		    		 $("#valor"+autocompleteVos[i].value).empty();
		    		 $("#aporte"+autocompleteVos[i].value).empty();
		    		 $("#copago"+autocompleteVos[i].value).empty();
		    		 
		    		 $("#valor"+autocompleteVos[i].value).append(autocompleteVos[i].valor);
		    		 $("#aporte"+autocompleteVos[i].value).append(autocompleteVos[i].aporte);
		    		 $("#copago"+autocompleteVos[i].value).append(autocompleteVos[i].copago);
		    		 $("#valorHidden"+autocompleteVos[i].value).val(autocompleteVos[i].valor);
		    		 $("#aporteHidden"+autocompleteVos[i].value).val(autocompleteVos[i].aporte);
		    		 $("#copagoHidden"+autocompleteVos[i].value).val(autocompleteVos[i].copago);
				}
				
				$("#botonGuardar").show();
		    }).complete(function(){$("#pre-load").hide();});
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar una prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	
	function abrirDialog(idAfiliado){
		var ctx = "${pageContext.request.contextPath}";
		$.fancybox.open({
			href : ctx + '/recepcion/inicioPersonaConfianza?idAfiliado='+idAfiliado,
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 860,
			height: 465,
			afterClose : function(){
				var idPersona = $("#idPersona").val();
			}
		});
	}
	
	function abrirDialogMedico(){
		var ctx = "${pageContext.request.contextPath}";
		$.fancybox.open({
			href : ctx + '/recepcion/seleccionarMedico',
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 860,
			height: 440,
			afterClose : function(){
				var idPersona = $("#idPersona").val();
			}
		});		
	}
		
	function inicializaAutocomplete(){
 		$("#prestacion").autocomplete({
			source: "prestacion?idConvenio="+$("#idConvenio").val(),
	 	   	minLength: 1,
            select: function( event, ui ){
            	var label = ui.item.label;
       	    	$("#prestacion").val(ui.item.label);
       	    	$("#idPrestacion").val(ui.item.value);
       	    	$("#cantidad").val(1);
       	    	if (label.indexOf("CONSULTA") != -1) {
       	    		$("#validarMedico").val(true);
       	    		var medicoPrestaciones = $("#medicoPrestaciones").val();
    				if(medicoPrestaciones == undefined){
           	    		$("#medico").show();
           	    		$("#botonSeleccionarMedico").show();
           	    		$("#idTiempo").show();    					
    				}else{
    	       	    	$("#prestacion").val("");
    	       	    	$("#idPrestacion").val("");
    	       	    	$("#cantidad").val("");    					
    					$.fancybox.open({
    						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe una prestacion con Medico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
					$("#validarMedico").val(false);
					$("#medico").hide();
					$("#botonSeleccionarMedico").hide();
					$("#idTiempo").hide();
				}
       	    	$("#agregar").prop( "disabled", false );
                return false;
			},
			focus: function (event, ui) {
				$("#prestacion").val(ui.item.label);
				return false;
            }

		});  		
	}
	
	function agregarPrestacion(){
		var idPrestacion = $("#idPrestacion").val();
		var prestacion = $("#prestacion").val();
		var cantidad = $("#cantidad").val();
		var validarMedico = $("#validarMedico").val();
		var agregar = false;
		if (prestacion.trim() != "") {
			if(cantidad != ""){
	
					if(prestacion.indexOf("CONSULTA") != -1 && $("#tablaPrestaciones .linea_tabla").length>0){
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>No se puede agregar la prestación</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 470,
			 				height: 185
						});	
					}else{
						agregar = true;
					}					
				
				if (agregar) {
					if($("#autocompleteVos"+idPrestacion).val() === undefined){
						
						var rows = $("#tablaPrestaciones .linea_tabla").length;
						if(rows!=0){
							var myElem = document.getElementById(rows);
							while(myElem!=null){
								rows++;
								myElem = document.getElementById(rows);
							}	
						}
				    	var filas = '<div class="linea_tabla alto" id="'+rows+'">'+
				    				'<input type="hidden" id="autocompleteVos'+idPrestacion+'" value="x"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].label" id="label'+rows+'" value="'+prestacion+'"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].value" value="'+idPrestacion+'"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].valor" id="valorHidden'+idPrestacion+'"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].aporte" id="aporteHidden'+idPrestacion+'"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].copago" id="copagoHidden'+idPrestacion+'"/>'+
				    				'<input type="hidden" name="autocompleteVos['+rows+'].cantidad" value="'+cantidad+'"/>';
				    				
				    	if (validarMedico == "true") {
				    		filas = filas + '<input type="hidden" id="medicoPrestaciones" value="x"/>';
						}			
					
					filas = filas + '<div class="celda2 texto_supervisor blanco tam_19">'+prestacion+'</div>'+			
			    			'<div class="celda2 texto_supervisor blanco tam_6 centrado">'+cantidad+'</div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_6 centrado" id="valor'+idPrestacion+'"></div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_6 centrado" id="aporte'+idPrestacion+'"></div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_8 centrado" id="copago'+idPrestacion+'"></div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_1 centrado bote link3" onclick="borrarPrestacion('+rows+')"></div></div>';  			
			        		
						$("#tablaPrestaciones").append(filas);
						
						deshabilitarAgregarPrestacion(prestacion);
						$("#botonValorizar").show();
						$("#prestacion").val("");
						$("#cantidad").val("");
						$("#tablaPrincipalPrestaciones").show();
						$("#medico").val("");
						//$("#idTiempo").val("");
						$("#medico").hide();
						$("#botonSeleccionarMedico").hide();
						$("#idTiempo").hide();
						$("#botonGuardar").hide();
					}else{
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe la prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
						$("#cantidad").val("");	
						$("#botonGuardar").hide();				
					}	
								
				}			
			}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la Cantidad</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la Prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	
	function habilitarAgregarPrestacion(id){
		var labelPrestacion = $("#label"+id).val(); 
		if (labelPrestacion .indexOf("CONSULTA") != -1) {
			$("#prestacion").attr('disabled',false);
			$("#agregar").show();
		}
	}
	
	function deshabilitarAgregarPrestacion(prestacion){
		if (prestacion.indexOf("CONSULTA") != -1) {
			$("#prestacion").attr('disabled',true);
			$("#agregar").hide();
		}
	}
	
	function borrarPrestacion(id){
		habilitarAgregarPrestacion(id);
		$("#tablaPrestaciones div#"+id).remove();
		if($("#tablaPrestaciones .linea_tabla").length ==0){
			$("#tablaPrincipalPrestaciones").hide();	
			$("#botonGuardar").hide();
			$("#botonValorizar").hide();
		}
	}	
	
	function validarPrestacionConsulta(){		
		
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows!=0){
			for(var i=0; i< rows;i++){
				var prestaciones = $("#label"+i).val();
				deshabilitarAgregarPrestacion(prestaciones);
				
			}
		}
	}
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}
	
	
	$(document).ready(function(){
		$.ajaxSetup({ cache: false });
		inicializaAutocomplete();
		validarPrestacionConsulta();
	});

	</script>
</body>
</html>