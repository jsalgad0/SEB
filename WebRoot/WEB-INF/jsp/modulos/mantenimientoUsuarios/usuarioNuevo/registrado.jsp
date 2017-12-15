<div id="contenedor">
	<div id="subcontenedor">
	
   		<div class="texto2"><p>El Usuario con RFC</p>
   		</div>     
   		<div class="campo_rchico">${mantenimientoUsuarioNuevoFormRegistrado.rfcRegistrado}</div>
   		<div class="texto2"><p>Con el Nombre</p>
   		</div>
   		<div class="campo_rchico">${mantenimientoUsuarioNuevoFormRegistrado.nombreRegistrado}</div>
   		<div class="texto"><p>Ya está registrado en el lugar de Atención</p>
   		</div>
   		<div class="campo_r">${mantenimientoUsuarioNuevoFormRegistrado.lugaresRegistrado}</div>
    	<div class="texto"><p>Desea registrarlo además en el lugar de atención?</p>
    	</div>
   		<div class="campo_r">${mantenimientoUsuarioNuevoFormRegistrado.lugarRegistrado}</div>
   
   		<div class="btn_half">
        	<div class="btn_guardar margen" onclick="aceptar();">
            </div>
       </div>
       
       <div class="btn_half">
        	<div class="btn_cerrar" onclick="noAceptar();">
            </div>
       </div>
    </div>
</div>

<script type="text/javascript">
	function aceptar(){
		parent.$.fancybox.close();
		parent.document.getElementById("respuestaRegistrado").value = "true";
	}
	
	function noAceptar(){
		parent.$.fancybox.close();
		parent.document.getElementById("respuestaRegistrado").value = "false";
	}
</script>