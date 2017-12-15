<div id="contenedor">
	<h1>DECLARACI�N DE ADMINISTRADOR Y/U OPERADOR DEL SISTEMA SAB</h1>
   <div class="texto"><p>Estoy enterado de que con el fin de brindar a nuestros derechohabientes una atenci�n de salud m�s f�cil, m�s completa, m�s moderna y m�s segura, estamos instalando un sistema computacional para el registro de todas sus atenciones m�dicas. </p>
   </div>     
    <div class="texto"><p>El Sistema de Atenci�n de Beneficiarios (SAB), es una plataforma computacional que permite registrar los servicios m�dicos otorgados a derechohabientes y verificar su identidad a trav�s del uso de un lector biom�trico de huellas dactilares.</p>
    </div>
    
   <div class="texto">
   		<p>En este sentido declaro y acepto la responsabilidad y las obligaciones que de esto se derivan relacionadas con la operaci�n directa y la correcta utilizaci�n del Sistema SAB, y que como parte de mis funciones est� la de ingresar a dicho sistema informaci�n personal y sensible del derechohabiente, respecto de la cual asumo la responsabilidad como Encargado, sujet�ndome a lo establecido en el marco de la Ley Federal de Protecci�n de Datos Personales en Posesi�n de los Particulares.
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