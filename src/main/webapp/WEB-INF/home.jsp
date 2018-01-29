<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>HBG Discounting</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
</head>

<body role="document">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>

				</ul>
			</div>
		</div>
	</div>
	<div class="container theme-showcase" role="main">
		<div class="page-header">
			<h1>
				<!-- Intentionally left blank to give some leading space -->
			</h1>
		</div>
		<div class="page-header">
			<h2>HBG Discounting Application</h2>
		</div>

		<th><form:form method="POST" action="deleteRuleSet"
				commandName="demoForm">
				<div class="input-group">
					<span class="input-group-btn"> <input
						class="btn btn-success" type="submit" value="Reset All" />
					</span>
				</div>
			</form:form></th>

		<!-- RULE SETUP SECTION-->
		<div class="page-header">
			<h1>Add Rules</h1>
		</div>
		<div class="row">
			<div class="col-md-6">
				<form:form method="POST" action="addRule" commandName="demoForm">
					<div class="input-group">
						<form:input path="ruleNumber" id="ruleNumber-input" type="text"
							placeholder="Type rule number" class="form-control" />
						<form:input path="columnName" id="columnName-input"  type="text"
							placeholder="Type Column Name" class="form-control" />
						<form:input path="columnValue" id="columnValue-input" type="text"
							placeholder="Type Column value" class="form-control" />
						<span class="input-group-btn"> <input
							class="btn btn-success" type="submit" value="Add Rule" />
						</span>
					</div>
				</form:form>
			</div>
		</div>

		<!-- ORDER SETUP SECTION-->
		<div class="page-header">
			<h1>Add Order Lines</h1>
		</div>
		<div class="row">
			<div class="col-md-6">
				<form:form method="POST" action="addOrder" commandName="demoForm">
					<div class="input-group">
						<form:input path="orderLineNumber" id="orderLineNumber-input" type="text"
							placeholder="Type order line number" class="form-control" />
						<form:input path="columnName" id="columnNam1e-input" type="text"
							placeholder="Type Column Name" class="form-control" />
						<form:input path="columnValue" id="columnValue1-input" type="text"
							placeholder="Type Column Value" class="form-control" />

						<span class="input-group-btn"> <input
							class="btn btn-success" type="submit" value="Add Order" />
						</span>
					</div>
				</form:form>
			</div>
		</div>

	<!-- Latest compiled and minified Jquery and Bootstrap JavaScript -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>