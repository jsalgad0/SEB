<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_admin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow: hidden;">
	<form:form name='aseguradorForm' action="aseguradorNuevo" method='POST' commandName="aseguradorForm" id="aseguradorForm" enctype="multipart/form-data">
		<form:hidden path="error" />
		<form:hidden path="exito" />
		<div id="admin_contenido2">


			<div class="linea_supervisor margen_arriba3" style="height: 72px;">

				<div class="linea_supervisor margen_arriba4"></div>
			</div>

			<div class="admin_contenidoFrame">

				<div class="linea_corta2">

					<div class="linea_supervisor margen">
						<form:input path="rfc" cssClass="campo_supervisor tam_7 mayusculas" placeholder="RFC *" />
						<form:input path="asegurador" cssClass="campo_supervisor tam_18 mayusculas" placeholder="Nombre Û RazÛn Social *" maxlength="100" />
					</div>

					<div class="linea3 margen">
						<div class="error tam_7"><form:errors path="rfc" /></div>
						<div class="error tam_18"><form:errors path="asegurador" /></div>
					</div>

					<div class="linea_supervisor margen">
						<div class="texto_supervisor5 verde tam_6 interlineado2">DIRECCI”N</div>
						<form:input path="calle" cssClass="campo_supervisor tam_20" placeholder="Calle *" maxlength="100" />
						<form:input path="numeroExterno" cssClass="campo_supervisor tam_6" placeholder="# Externo*" maxlength="10" />
						<form:input path="numeroInterno" cssClass="campo_supervisor tam_6" placeholder="# Interno*" maxlength="10" />
					</div>

					<div class="linea_supervisor margen10">
						<div class="error tam_20"><form:errors path="calle" /></div>
						<div class="error tam_6 margen14"><form:errors path="numeroExterno" /></div>
						<div class="error tam_6 margen14"><form:errors path="numeroInterno" /></div>
					</div>

					<div class="linea_supervisor margen">
						<form:select path="idEstado" id="idEstado" onchange="municipios();" cssClass="selectt campo_supervisor tam_2">
							<form:option value="-1" label="Estado" />
							<form:options items="${catEstados}" itemLabel="estado" itemValue="estadoId"></form:options>
						</form:select>

						<form:select path="idMunicipio" id="idMunicipio" onchange="colonias();" cssClass="selectt campo_supervisor tam_2">
							<form:option value="-1" label="DelegaciÛn" />
							<form:options items="${catMunicipios}" itemLabel="municipio" itemValue="municipioId"></form:options>
						</form:select>

						<form:select path="idColonia" id="idColonia" cssClass="selectt campo_supervisor tam_9">
							<form:option value="-1" label="Colonia" />
							<form:options items="${catColonias}" itemLabel="colonia" itemValue="ColoniaId"></form:options>
						</form:select>

						<form:input path="cp" cssClass="campo_supervisor tam_6" placeholder="C.P. *" maxlength="5" />

					</div>

					<div class="linea_supervisor margen">
						<div class="error tam_2"><form:errors path="idEstado" /></div>
						<div class="error tam_2"><form:errors path="idMunicipio" /></div>
						<div class="error tam_9"><form:errors path="idColonia" /></div>
						<div class="error tam_6"><form:errors path="cp" /></div>
					</div>

					<div class="linea_supervisor margen">
						<div class="texto_supervisor5 verde tam_8 interlineado2">CONTACTO</div>
						<form:input path="nombre" cssClass="campo_supervisor tam_20 margen_izq1 mayusculas" placeholder="Nombre *" maxlength="100" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_20"><form:errors path="nombre" /></div>
					</div>

					<div class="linea_supervisor margen">
						<form:input path="telefono1" cssClass="campo_supervisor tam_7 margen12" placeholder="TelÈfono 1 *" maxlength="10" />
						<form:input path="telefono2" cssClass="campo_supervisor tam_7 margen5" placeholder="TelÈfono 2" maxlength="10" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_7"><form:errors path="telefono1" /></div>
						<div class="error tam_7 margen5"><form:errors path="telefono2" /></div>
					</div>

					<div class="linea_supervisor margen">
						<form:input path="correo" cssClass="campo_supervisor tam_11 margen12" placeholder="Correo electrÛnico *" maxlength="50" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_7"><form:errors path="correo" /></div>
					</div>

					<div class="linea_supervisor margen margen_arriba">
						<div class="btn btn_guardar" id="guardar" onclick="guardar()"></div>
						<div onclick="cargaCuadroPrestacion()" style="display:" id="botonCuadroPrestaciones" class="custom-input-file btn_grande btn_cuadro_prest" ></div>
						
						<div style="display:none" id="botonCuadroMedicamentos" class="btn_grande btn_cuadro_med" onclick="cuadroMed()"></div>
						<div class="btn btn_cerrar" onclick="cerrar()"></div>
					</div>
					<div class="linea3 margen">
						<div class="error tam_10 margen10"><form:errors path="file" /></div>
					</div>
					<div class="linea_supervisor margen margen_arriba">
						<div></div>
						
						<div></div>
						<div></div>

					</div>					
					
				</div>
			</div>
		</div>

	</form:form>


</body>

<script type="text/javascript">
	function guardar() {
		$("#aseguradorForm").submit();
	}

	function cerrar() {
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx + "/administrador/asegurador";
	}

	function cargaCuadroPrestacion(){
		var ctx = "${pageContext.request.contextPath}";
		var rfc = $("#rfc").val();
		
		if(rfc!= ""){
			$.fancybox
			.open({
				href : ctx + '/administrador/inicioCargarPrestacionesAsegurador?rfc='+rfc,
				type : 'iframe',
				padding : 5,
				autoSize: false,
				width: 520,
				height: 380,
			});
	
		}
	}
	
	$(document)
			.ready(
					function() {
						$.ajaxSetup({
							cache : false
						});

						$("#rfc").alphanum({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});
						$("#asegurador").alphanum({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});
						$("#calle").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#numeroExterno").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#numeroInterno").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#cp").numeric({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});
						$("#nombre").alpha({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#telefono1").numeric({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});
						$("#telefono2").numeric({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});
						$("#correo").alphanum({
							allowSpace : false,
							allow : '@.-_',
							disallow : '¥Á«ø®°∑'
						});

						
						
						
						var error = $("#error").val();
						var exito = $("#exito").val();
						if (error != "") {
							$.fancybox
									.open({
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
							$("#botonCuadroPrestaciones").show();
							//$("#botonCuadroMedicamentos").show();
							$("#guardar").hide();
							$.fancybox
									.open({
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