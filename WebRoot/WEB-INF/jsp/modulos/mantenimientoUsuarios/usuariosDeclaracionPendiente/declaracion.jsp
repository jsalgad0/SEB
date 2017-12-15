<div id="contenedor">
	<h1>DECLARACIÓN DE ADMINISTRADOR Y/U OPERADOR DEL SISTEMA SAB</h1>
   <div class="texto"><p>Estoy enterado de que con el fin de brindar a nuestros derechohabientes una atención de salud más fácil, más completa, más moderna y más segura, estamos instalando un sistema computacional para el registro de todas sus atenciones médicas. </p>
   </div>     
    <div class="texto"><p>El Sistema de Atención de Beneficiarios (SAB), es una plataforma computacional que permite registrar los servicios médicos otorgados a derechohabientes y verificar su identidad a través del uso de un lector biométrico de huellas dactilares.</p>
    </div>
    
   <div class="texto">
   		<p>En este sentido declaro y acepto la responsabilidad y las obligaciones que de esto se derivan relacionadas con la operación directa y la correcta utilización del Sistema SAB, y que como parte de mis funciones está la de ingresar a dicho sistema información personal y sensible del derechohabiente, respecto de la cual asumo la responsabilidad como Encargado, sujetándome a lo establecido en el marco de la Ley Federal de Protección de Datos Personales en Posesión de los Particulares.
        </p>
   </div>     
   <h2 class="" type="text">${usuario.nombre} ${usuario.apellidoPaterno} ${usuario.apellidoMaterno}</h2>
   
   <div class="btn">
   		<div class="btn_acepto" onclick="aceptar();"></div> 
   		<div class="btn_noacepto" onclick="noAceptar();"></div>
   </div> 
   
</div>

<script type="text/javascript">
	function aceptar(){
		parent.$.fancybox.close();
		parent.document.getElementById("respuesta").value = "true";
	}
	
	function noAceptar(){
		parent.$.fancybox.close();
		parent.document.getElementById("respuesta").value = "false";
	}
</script>