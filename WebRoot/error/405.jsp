<!doctype html>
<html>
<head>

<style>
body {
	background-image:url(${pageContext.request.contextPath}/resources/img/bg_error.jpg);
	background-repeat:repeat-x repeat-y;
}

@font-face {
    font-family: "quicksand-bold";
    src:url(${pageContext.request.contextPath}/resources/font/Quicksand-Bold.otf) format("truetype");
}

.contenido_error {
	margin:0 auto;
	width:696px;
	height:343px;
	background-image:url(${pageContext.request.contextPath}/resources/img/error_pantalla.png);
}

.texto_error {
	width:550px;
	margin-left:70px;
	margin-top:180px;
	font-size:17px;
	color:#FFFFFF;
	display:inline-block;
}

.texto_error_volver {
	width:70px;
	margin-left:70px;
	font-size:17px;
	color:#FFFFFF;
	display:inline-block;
	vertical-align:middle;
	font-family:quicksand-bold;
	font-size:15px;
}

.btn_volver {
	display:inline-block;
	vertical-align:middle;
}

p {
	font-family:quicksand-bold;
}

.btn_inicio {
	width:100px;
	height:35px;
	display:inline-block;
}

</style>
<meta charset="utf-8">
<title>Untitled Document</title>
</head>

<body>

<div class="contenido_error">
	<div class="texto_error">
    	<p>Lo sentimos, por el momento la información que solicita
no está disponible, inténtelo más tarde.</p>
    </div>
    <div class="texto_error_volver">
		<p>Volver al</p>
    </div>
    <div class="btn_volver">
    	<a class="btn_inicio" href="/sab/login?logout"><img src="${pageContext.request.contextPath}/resources/img/btn_inicio.png" alt="Inicio" /></a>
    </div>
</div>
</body>
</html>
