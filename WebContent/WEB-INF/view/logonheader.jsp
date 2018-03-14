<html>
<head>
<title>SPRING - by git</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Spring</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">My Page</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Friends <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/friend?one=${logon }">lists</a></li>
						<li><a href="/friend/follow">follow</a></li>
						<li><a href="/friend/request">requests</a></li>
					</ul></li>
				<li><a href="/login/out">logout</a></li>
			</ul>
		</div>
	</nav>
