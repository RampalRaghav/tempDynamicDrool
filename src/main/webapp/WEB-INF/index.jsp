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
					<li class="active"><a href="<c:out value="/"/>">Home</a></li>

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

		<c:choose>
			<c:when test="${!qualifiers.equals(' ')}">
				<div class="alert alert-success" role="alert">
					Rules qualified: <strong><c:out value="${qualifiers}" /></strong>
				</div>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${!netOutput.equals(' ')}">
				<div class="alert alert-success" role="alert">
					Output: <strong><c:out value="${netOutput}" /></strong>
				</div>
			</c:when>
		</c:choose>

		<a href="<c:out value="/home"/>">Rule Engine Configuration</a>

	</div>

	<!-- Added Rules are displayed below -->

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#room" role="tab" data-toggle="tab">Rule
				Set</a></li>
		<li><a href="#orderLine" role="tab" data-toggle="tab">Order
				Lines</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane active" id="room">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Rule Number</th>
								<th>Column Name</th>
								<th>Column Value</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ruleSetup" varStatus="item"
								items="${ruleSetUpList}">
								<tr>
									<td>${ruleSetup.ruleNumber}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="tab-pane" id="orderLine">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Order Line#</th>
								<th>Column Name</th>
								<th>Column Value</th>
								<th><form:form method="POST" action="generateOffer"
										commandName="demoForm">
										<div class="input-group">
											<span class="input-group-btn"> <input
												class="btn btn-success" type="submit" value="Generate Offer" />
											</span>
										</div>
									</form:form></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="orderLine" varStatus="item"
								items="${orderLineList}">
								<tr>
									<form:form method="POST" action="deleteOrder"
										commandName="demoForm">
										<td>${orderLine.orderLineNumber}</td>
										<td><span class="input-group-btn"> <input
												class="btn btn-success" type="submit" value="Delete Order" />
										</span></td>
									</form:form>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- /container -->

	<!-- Latest compiled and minified Jquery and Bootstrap JavaScript -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>