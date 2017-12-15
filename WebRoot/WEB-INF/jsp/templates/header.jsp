<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
	<div id="logo"></div><!--
    --><div id="info_head1">
		<span class="texto_head">${userInfo.lugaresdeatencion.descripcion}</span>
	</div><!--            
    --><div class="info_head3">
		<span class="texto_head">${userInfo.usuarios.nombre} ${userInfo.usuarios.apellidoPaterno}  ${userInfo.usuarios.apellidoMaterno}</span>
	</div><!--
	--><div id="info_head2" title="Mi Cuenta"><div class="usuario" id="fancybox-manual-a" href="#"></div>
		</div><!-- 		
	</div><!--           
    --><div class="info_head4">
		<span class="texto_head">${userInfo.rolNombre}</span>
	</div><!--            
    --><div class="info_head4">
		<span class="texto_head"
			style="text-decoration:underline; cursor:pointer;" onclick="javascript:formSubmit()">Cerrar
			Sesión</span>
	</div><!--
    --><div id="fecha">
		<span id="fecha_i"> <script>
				 var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    var today = dd+'/'+mm+'/'+yyyy;

				document.write(today);
				</script>
		</span>
	</div>
</div>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
 	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

  <script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
		
		$("#fancybox-manual-a").click(function() {
			var ctx = "${pageContext.request.contextPath}";
			$.fancybox.open({
				href : ctx + '/datosUsuario/datosUsuario',
				type : 'iframe',

				openEffect : 'elastic',
				openSpeed  : 150,

				closeEffect : 'elastic',
				closeSpeed  : 150,
				
				closeClick : true,
				
				autoSize: false,
 				width: 500,
 				height: 465

			});
		});
	});
	</script>	