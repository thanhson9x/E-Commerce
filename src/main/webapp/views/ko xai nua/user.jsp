<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Meta Tag -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name='copyright' content=''>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title Tag  -->
<title>Son Store</title>
<!-- Favicon -->
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/images/favicon.png">
<!-- Web Font -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
	rel="stylesheet">

<!-- StyleSheet -->

<!-- Bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<!-- Magnific Popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/magnific-popup.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.css">
<!-- Fancybox -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.fancybox.min.css">
<!-- Themify Icons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/themify-icons.css">
<!-- Nice Select CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/niceselect.css">
<!-- Animate CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/animate.css">
<!-- Flex Slider CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/flex-slider.min.css">
<!-- Owl Carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl-carousel.css">
<!-- Slicknav -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slicknav.min.css">

<!-- Eshop StyleSheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/responsive.css">

</head>

<body class="js">
	<!-- Preloader -->
	<div class="preloader">
		<div class="preloader-inner">
			<div class="preloader-icon">
				<span></span> <span></span>
			</div>
		</div>
	</div>
	<!-- End Preloader -->

	<!-- Header -->
	<header class="header shop">
		<!-- Topbar -->
		<div class="topbar">
			<div class="container">
				<div class="row">
					<div class="col-lg-5 col-md-12 col-12">
						<!-- Top Left -->
						<div class="top-left">
							<ul class="list-main">
								<li><i class="ti-headphone-alt"></i> +84 123 456 789</li>
								<li><i class="ti-email"></i> support@sonstore.com</li>
							</ul>
						</div>
						<!--/ End Top Left -->
					</div>
					<div class="col-lg-7 col-md-12 col-12">
						<!-- Top Right -->
						<div class="right-content">
							<ul class="list-main">

								<sec:authorize access="!isAuthenticated()">
									<li><i class="ti-power-off"></i><a
										href="${pageContext.request.contextPath}/login">Login</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ADMIN')">
									<li><i class="ti-user"></i> <a
										href="${pageContext.request.contextPath}/admin/info">ADMIN</a></li>
									<li><form:form action="logout" method="post">
											<input class="btn" type="submit" value="Logout" />
										</form:form></li>
								</sec:authorize>
								<sec:authorize access="hasRole('CUSTOMER')">
									<li><i class="ti-user"></i> <a
										href="${pageContext.request.contextPath}/customer/info">${sessionScope.user.firstName}</a></li>
									<li><form:form action="logout" method="post">
											<input class="btn" type="submit" value="Logout" />
										</form:form></li>
								</sec:authorize>

							</ul>
						</div>
						<!-- End Top Right -->
					</div>
				</div>
			</div>
		</div>
		<!-- End Topbar -->
		<div class="middle-inner">
			<div class="container">
				<div class="row">
					<div class="col-lg-2 col-md-2 col-12">
						<!-- Logo -->

						<sec:authorize access="!isAuthenticated()">
							<div class="logo">
								<a href="${pageContext.request.contextPath}/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</div>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<div class="logo">
								<a href="${pageContext.request.contextPath}/admin/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</div>
						</sec:authorize>
						<sec:authorize access="hasRole('CUSTOMER')">
							<div class="logo">
								<a href="${pageContext.request.contextPath}/customer/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</div>
						</sec:authorize>

						<!--/ End Logo -->
						<!-- Search Form -->
						<div class="search-top">
							<div class="top-search">
								<a href="#0"><i class="ti-search"></i></a>
							</div>
							<!-- Search Form -->
							<div class="search-top">
								<form class="search-form">
									<input type="text" placeholder="Search here..." name="search">
									<button value="search" type="submit">
										<i class="ti-search"></i>
									</button>
								</form>
							</div>
							<!--/ End Search Form -->
						</div>
						<!--/ End Search Form -->
						<div class="mobile-nav"></div>
					</div>

					<!-- SEARCH HERE -->

					<div class="col-lg-8 col-md-7 col-12">
						<div class="search-bar-top">

							<sec:authorize access="!isAuthenticated()">
								<form action="${pageContext.request.contextPath}/index/search"
									method="get">
									<div class="search-bar">
										<select name="category">
											<option value="0">All Category</option>
											<c:if
												test="${listCategory ne null && listCategory.size() gt 0}">
												<c:forEach items="${listCategory }" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</c:if>
										</select> <input name="name" value=""
											placeholder="Search by product name..." type="search">
										<button class="btnn" value="search" type="submit">
											<i class="ti-search"></i>
										</button>
									</div>
								</form>
							</sec:authorize>

							<sec:authorize access="hasRole('ADMIN')">
								<form
									action="${pageContext.request.contextPath}/admin/index/search"
									method="get">
									<div class="search-bar">
										<select name="category">
											<option value="0">All Category</option>
											<c:if
												test="${listCategory ne null && listCategory.size() gt 0}">
												<c:forEach items="${listCategory }" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</c:if>
										</select> <input name="name" value=""
											placeholder="Search by product name..." type="search">
										<button class="btnn" value="search" type="submit">
											<i class="ti-search"></i>
										</button>
									</div>
								</form>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<form
									action="${pageContext.request.contextPath}/customer/index/search"
									method="get">
									<div class="search-bar">
										<select name="category">
											<option value="0">All Category</option>
											<c:if
												test="${listCategory ne null && listCategory.size() gt 0}">
												<c:forEach items="${listCategory }" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</c:if>
										</select> <input name="name" value=""
											placeholder="Search by product name..." type="search">
										<button class="btnn" value="search" type="submit">
											<i class="ti-search"></i>
										</button>
									</div>
								</form>
							</sec:authorize>

						</div>
					</div>

					<!-- SEARCH HERE -->

					<div class="col-lg-2 col-md-3 col-12">
						<div class="right-bar">
							<!-- Search Form -->

							<sec:authorize access="!isAuthenticated()">
								<div class="sinlge-bar shopping">
									<a href="${pageContext.request.contextPath}/register"
										class="single-icon"><i class="ti-bag"></i> <span
										class="total-count">2</span></a>
									<!-- Shopping Item -->
									<div class="shopping-item">
										<div class="dropdown-cart-header">
											<span>2 Items</span> <a
												href="${pageContext.request.contextPath}/register">View
												Cart</a>
										</div>
										<ul class="shopping-list">
											<li><a href="#" class="remove" title="Remove this item"><i
													class="fa fa-remove"></i></a> <a class="cart-img" href="#"><img
													src="https://via.placeholder.com/70x70" alt="#"></a>
												<h4>
													<a href="#">Woman Ring</a>
												</h4>
												<p class="quantity">
													1x - <span class="amount">$99.00</span>
												</p></li>
											<li><a href="#" class="remove" title="Remove this item"><i
													class="fa fa-remove"></i></a> <a class="cart-img" href="#"><img
													src="https://via.placeholder.com/70x70" alt="#"></a>
												<h4>
													<a href="#">Woman Necklace</a>
												</h4>
												<p class="quantity">
													1x - <span class="amount">$35.00</span>
												</p></li>
										</ul>
										<div class="bottom">
											<div class="total">
												<span>Total</span> <span class="total-amount">$134.00</span>
											</div>
											<a href="${pageContext.request.contextPath}/register"
												class="btn animate">Checkout</a>
										</div>
									</div>
									<!--/ End Shopping Item -->
								</div>
							</sec:authorize>

							<sec:authorize access="hasRole('ADMIN')">
								<div class="sinlge-bar shopping">
									<a href="${pageContext.request.contextPath}/admin/cart"
										class="single-icon"><i class="ti-bag"></i> <span
										class="total-count">${sessionScope.listOrderDetails.size()}</span></a>
									<!-- Shopping Item -->
									<div class="shopping-item">
										<div class="dropdown-cart-header">
											<span>${sessionScope.listOrderDetails.size()} Items</span> <a
												href="${pageContext.request.contextPath}/admin/cart">View
												Cart</a>
										</div>
										<ul class="shopping-list">
											<c:choose>
												<c:when
													test="${sessionScope.listOrderDetails ne null && sessionScope.listOrderDetails.size() gt 0}">
													<c:forEach items="${sessionScope.listOrderDetails }"
														var="item">
														<li><a
															href="${pageContext.request.contextPath}/admin/cart/deleteOrderDetails?id=${item.id }"
															class="remove" title="Remove this item"><i
																class="fa fa-remove"></i></a> <a class="cart-img"
															href="${pageContext.request.contextPath}/admin/product/${item.product.id}"><img
																src="${pageContext.request.contextPath}/${item.product.path }"
																alt="image"></a>
															<h4>
																<a
																	href="${pageContext.request.contextPath}/admin/product/${item.product.id}">${item.product.name }</a>
															</h4>
															<p class="quantity">
																${item.quantity }x - <span class="amount">$
																	${item.unitPrice }</span>
															</p></li>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<td class="text-center">Data not found!</td>
												</c:otherwise>
											</c:choose>
										</ul>
										<div class="bottom">
											<div class="total">
												<span>Total</span> <span class="total-amount">$
													${sessionScope.orderDiscountPrice }</span>
											</div>
											<a href="${pageContext.request.contextPath}/admin/checkout"
												class="btn animate">Checkout</a>
										</div>
									</div>
									<!--/ End Shopping Item -->
								</div>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<div class="sinlge-bar shopping">
									<a href="${pageContext.request.contextPath}/customer/cart"
										class="single-icon"><i class="ti-bag"></i> <span
										class="total-count">${sessionScope.listOrderDetails.size()}</span></a>
									<!-- Shopping Item -->
									<div class="shopping-item">
										<div class="dropdown-cart-header">
											<span>${sessionScope.listOrderDetails.size()} Items</span> <a
												href="${pageContext.request.contextPath}/customer/cart">View
												Cart</a>
										</div>
										<ul class="shopping-list">
											<c:choose>
												<c:when
													test="${sessionScope.listOrderDetails ne null && sessionScope.listOrderDetails.size() gt 0}">
													<c:forEach items="${sessionScope.listOrderDetails }"
														var="item">
														<li><a
															href="${pageContext.request.contextPath}/customer/cart/deleteOrderDetails?id=${item.id }"
															class="remove" title="Remove this item"><i
																class="fa fa-remove"></i></a> <a class="cart-img"
															href="${pageContext.request.contextPath}/customer/product/${item.product.id}"><img
																src="${pageContext.request.contextPath}/${item.product.path }"
																alt="image"></a>
															<h4>
																<a
																	href="${pageContext.request.contextPath}/customer/product/${item.product.id}">${item.product.name }</a>
															</h4>
															<p class="quantity">
																${item.quantity }x - <span class="amount">$
																	${item.unitPrice }</span>
															</p></li>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<td class="text-center">Data not found!</td>
												</c:otherwise>
											</c:choose>
										</ul>
										<div class="bottom">
											<div class="total">
												<span>Total</span> <span class="total-amount">$
													${sessionScope.orderDiscountPrice }</span>
											</div>
											<a
												href="${pageContext.request.contextPath}/customer/checkout"
												class="btn animate">Checkout</a>
										</div>
									</div>
									<!--/ End Shopping Item -->
								</div>
							</sec:authorize>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header Inner -->
		<div class="header-inner">
			<div class="container">
				<div class="cat-nav-head">
					<div class="row">

						<div class="col-lg-3">
							<div class="all-category">

								<sec:authorize access="!isAuthenticated()">
									<h3 class="cat-heading">
										<a href="${pageContext.request.contextPath}/index"></a> <i
											class="fa fa-bars" aria-hidden="true"></i>HOME
									</h3>
								</sec:authorize>

								<sec:authorize access="hasRole('ADMIN')">
									<h3 class="cat-heading">
										<i class="fa fa-bars" aria-hidden="true"></i>MANAGEMENT
									</h3>
									<ul class="main-category">
										<li><a href="#">Order <i class="fa fa-angle-right"
												aria-hidden="true"></i></a>
											<ul class="sub-category">
												<li><a href="#">accessories</a></li>
												<li><a href="#">best selling</a></li>
												<li><a href="#">top 100 offer</a></li>
												<li><a href="#">sunglass</a></li>
												<li><a href="#">watch</a></li>
												<li><a href="#">man’s product</a></li>
												<li><a href="#">ladies</a></li>
												<li><a href="#">westrn dress</a></li>
												<li><a href="#">denim </a></li>
											</ul></li>
										<li class="main-mega"><a href="#">Shipping <i
												class="fa fa-angle-right" aria-hidden="true"></i></a>
											<ul class="mega-menu">
												<li class="single-menu"><a href="#" class="title-link">Shop
														Kid's</a>
													<div class="image">
														<img src="https://via.placeholder.com/225x155" alt="#">
													</div>
													<div class="inner-link">
														<a href="#">Kids Toys</a> <a href="#">Kids Travel Car</a>
														<a href="#">Kids Color Shape</a> <a href="#">Kids Tent</a>
													</div></li>
												<li class="single-menu"><a href="#" class="title-link">Shop
														Men's</a>
													<div class="image">
														<img src="https://via.placeholder.com/225x155" alt="#">
													</div>
													<div class="inner-link">
														<a href="#">Watch</a> <a href="#">T-shirt</a> <a href="#">Hoodies</a>
														<a href="#">Formal Pant</a>
													</div></li>
												<li class="single-menu"><a href="#" class="title-link">Shop
														Women's</a>
													<div class="image">
														<img src="https://via.placeholder.com/225x155" alt="#">
													</div>
													<div class="inner-link">
														<a href="#">Ladies Shirt</a> <a href="#">Ladies Frog</a> <a
															href="#">Ladies Sun Glass</a> <a href="#">Ladies
															Watch</a>
													</div></li>
											</ul></li>
										<li><a href="#">Product <i class="fa fa-angle-right"
												aria-hidden="true"></i>
										</a>
											<ul class="sub-category">
												<li><a
													href="${pageContext.request.contextPath}/admin/product">Submit
														Product</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/allProducts">View
														All Products</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/discount">Submit
														Discount</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/allDiscounts">View
														All Discounts</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/category">Submit
														Category</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/allCategories">View
														All Categories</a></li>
											</ul></li>
										<li><a href="#">Account <i class="fa fa-angle-right"
												aria-hidden="true"></i>
										</a>
											<ul class="sub-category">
												<li><a
													href="${pageContext.request.contextPath}/admin/customer">Submit
														Customer</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/allCustomers">View
														All Customers</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/form">Submit
														Admin</a></li>
												<li><a
													href="${pageContext.request.contextPath}/admin/allAdmins">View
														All Admins</a></li>
											</ul></li>
										<li><a href="#">Payment</a></li>

									</ul>
								</sec:authorize>

								<sec:authorize access="hasRole('CUSTOMER')">
									<h3 class="cat-heading">
										<i class="fa fa-bars" aria-hidden="true"></i>MANAGEMENT
									</h3>
									<ul class="main-category">
										<li><a
											href="${pageContext.request.contextPath}/customer/allOrders">View
												All Orders <i class="fa fa-angle-right" aria-hidden="true"></i>
										</a> <!-- <ul class="sub-category">
											<li><a href="#">accessories</a></li>											
										</ul> --></li>
										<li><a href="#">Payment</a></li>
									</ul>
								</sec:authorize>

							</div>
						</div>

						<div class="col-lg-9 col-12">
							<div class="menu-area">
								<!-- Main Menu -->
								<nav class="navbar navbar-expand-lg">
									<div class="navbar-collapse">
										<div class="nav-inner">
											<ul class="nav main-menu menu navbar-nav">

												<sec:authorize access="!isAuthenticated()">
													<li><a
														href="${pageContext.request.contextPath}/allDiscounts">SALE
															OFF</a></li>
													<li><a href="#">Blog<i class="ti-angle-down"></i></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/blog">Blog
																	Single Sidebar</a></li>
														</ul></li>
													<li><a
														href="${pageContext.request.contextPath}/contact">Contact
															Us</a></li>
												</sec:authorize>

												<sec:authorize access="hasRole('ADMIN')">
													<li class="active"><a
														href="${pageContext.request.contextPath}/admin/index">Home</a></li>
													<li><a href="#">SALE OFF</a></li>
													<li><a href="#">Blog<i class="ti-angle-down"></i></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/admin/blog">Blog
																	Single Sidebar</a></li>
														</ul></li>
													<li><a
														href="${pageContext.request.contextPath}/admin/contact">Contact
															Us</a></li>
													<li><a href="#">Shop<i class="ti-angle-down"></i><span
															class="new">New</span></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/admin/cart">Cart</a></li>
															<li><a
																href="${pageContext.request.contextPath}/admin/checkout">Checkout</a></li>
														</ul></li>
												</sec:authorize>

												<sec:authorize access="hasRole('CUSTOMER')">
													<li class="active"><a
														href="${pageContext.request.contextPath}/customer/index">Home</a></li>
													<li><a href="#">SALE OFF</a></li>
													<li><a href="#">Blog<i class="ti-angle-down"></i></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/customer/blog">Blog
																	Single Sidebar</a></li>
														</ul></li>
													<li><a
														href="${pageContext.request.contextPath}/customer/contact">Contact
															Us</a></li>
													<li><a href="#">Shop<i class="ti-angle-down"></i><span
															class="new">New</span></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/customer/cart">Cart</a></li>
															<li><a
																href="${pageContext.request.contextPath}/customer/checkout">Checkout</a></li>
														</ul></li>
												</sec:authorize>

												<!-- <li><a href="#">Product</a></li>
												<li><a href="#">Service</a></li>
												<li><a href="#">Shop<i class="ti-angle-down"></i><span
														class="new">New</span></a>
													<ul class="dropdown">
														<li><a href="cart">Cart</a></li>
														<li><a href="checkout">Checkout</a></li>
													</ul></li> -->

											</ul>
										</div>
									</div>
								</nav>
								<!--/ End Main Menu -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/ End Header Inner -->
	</header>
	<!--/ End Header -->

	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-12"></div>
				<div class="col-lg-9 col-12">
					<!-- <div class="col-12"> -->
					<div class="bread-inner">
						<ul class="bread-list">

							<sec:authorize access="!isAuthenticated()">
								<li><a href="${pageContext.request.contextPath}/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/login">Login</a></li>
							</sec:authorize>

							<sec:authorize access="hasRole('ADMIN')">
								<li><a
									href="${pageContext.request.contextPath}/admin/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/admin/info">Admin<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/customer">Customer<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/allCustomers">All
										Customers</a></li>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<li><a
									href="${pageContext.request.contextPath}/customer/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/customer/info">${sessionScope.user.firstName}</a></li>
							</sec:authorize>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<!-- Start Checkout -->
	<section class="shop checkout section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-12"></div>
				<div class="col-lg-9 col-12">
					<!-- <div class="col-lg-8 col-12"> -->
					<div class="checkout-form">

						<sec:authorize access="!isAuthenticated()">
							<h2>Fill Your Info Here</h2>
							<p>Please register in order to shopping more conveniently</p>
						</sec:authorize>

						<sec:authorize access="hasRole('ADMIN')">
							<h2>This Is Customer Page</h2>
							<p>You can submit customer here!</p>
						</sec:authorize>

						<sec:authorize access="hasRole('CUSTOMER')">
							<h2>This Is Your Page</h2>
							<p>You can change your info here!</p>
						</sec:authorize>

						<c:choose>
							<c:when test="${message ne null && message.length() > 0}">
								<span style="color: blue;"><c:out value="${message }"></c:out></span>
							</c:when>
							<c:when
								test="${errorMessage ne null && errorMessage.length() > 0}">
								<span style="color: red;"><c:out value="${errorMessage }"></c:out></span>
							</c:when>
						</c:choose>

						<!-- Form -->

						<sec:authorize access="!isAuthenticated()">
							<form:form class="form"
								action="${pageContext.request.contextPath}/submitUser"
								method="post" modelAttribute="user">
								<div class="row">

									<form:hidden path="id" readonly="true" />
									<form:errors path="id" cssClass="error" />

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>First Name<span>*</span></label>
											<form:input path="firstName" required="required" />
											<form:errors path="firstName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Last Name<span>*</span></label>
											<form:input path="lastName" required="required" />
											<form:errors path="lastName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Day of birth<span>*</span></label>
											<form:input type="date" path="dob" required="required" />
											<form:errors path="dob" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Gender<span>*</span></label>
											<form:select path="gender.id">
												<c:forEach items="${listGender }" var="item">
													<form:option value="${item.id }">${item.name }</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Address<span>*</span></label>
											<form:input path="address" required="required" />
											<form:errors path="address" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Phone Number<span>*</span></label>
											<form:input type="number" path="phone" required="required" />
											<form:errors path="phone" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Email<span>*</span></label>
											<form:input type="email" path="email" required="required" />
											<form:errors path="email" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Password<span>*</span></label>
											<form:input type="password" path="password"
												required="required" />
											<form:errors path="password" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-3 col-md-3 col-12"></div>
									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<div class="single-widget get-button">
												<div class="content">
													<div class="button">
														<input class="btn" type="submit" value="Submit" />
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</form:form>
						</sec:authorize>

						<sec:authorize access="hasRole('ADMIN')">
							<form:form class="form"
								action="${pageContext.request.contextPath}/admin/submitUser"
								method="post" modelAttribute="user">
								<div class="row">

									<form:hidden path="id" readonly="true" />
									<form:errors path="id" cssClass="error" />

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>First Name<span>*</span></label>
											<form:input path="firstName" required="required" />
											<form:errors path="firstName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Last Name<span>*</span></label>
											<form:input path="lastName" required="required" />
											<form:errors path="lastName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Day of birth<span>*</span></label>
											<form:input type="date" path="dob" required="required" />
											<form:errors path="dob" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Gender<span>*</span></label>
											<form:select path="gender.id">
												<c:forEach items="${listGender }" var="item">
													<form:option value="${item.id }">${item.name }</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Address<span>*</span></label>
											<form:input path="address" required="required" />
											<form:errors path="address" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Phone Number<span>*</span></label>
											<form:input type="number" path="phone" required="required" />
											<form:errors path="phone" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Email<span>*</span></label>
											<form:input type="email" path="email" required="required" />
											<form:errors path="email" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Password<span>*</span></label>
											<form:input type="password" path="password"
												required="required" />
											<form:errors path="password" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-3 col-md-3 col-12"></div>
									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<div class="single-widget get-button">
												<div class="content">
													<div class="button">
														<input class="btn" type="submit" value="Submit" />
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</form:form>
						</sec:authorize>

						<sec:authorize access="hasRole('CUSTOMER')">
							<form:form class="form"
								action="${pageContext.request.contextPath}/customer/submit"
								method="post" modelAttribute="user">
								<div class="row">

									<form:hidden path="id" readonly="true" />
									<form:errors path="id" cssClass="error" />

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>First Name<span>*</span></label>
											<form:input path="firstName" required="required" />
											<form:errors path="firstName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Last Name<span>*</span></label>
											<form:input path="lastName" required="required" />
											<form:errors path="lastName" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Day of birth<span>*</span></label>
											<form:input type="date" path="dob" required="required" />
											<form:errors path="dob" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Gender<span>*</span></label>
											<form:select path="gender.id">
												<c:forEach items="${listGender }" var="item">
													<form:option value="${item.id }">${item.name }</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Address<span>*</span></label>
											<form:input path="address" required="required" />
											<form:errors path="address" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Phone Number<span>*</span></label>
											<form:input type="number" path="phone" required="required" />
											<form:errors path="phone" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Email<span>*</span></label>
											<form:input type="email" path="email" required="required" />
											<form:errors path="email" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<label>Password<span>*</span></label>
											<form:input type="password" path="password"
												required="required" />
											<form:errors path="password" cssClass="error" />
										</div>
									</div>

									<div class="col-lg-3 col-md-3 col-12"></div>
									<div class="col-lg-6 col-md-6 col-12">
										<div class="form-group">
											<div class="single-widget get-button">
												<div class="content">
													<div class="button">
														<input class="btn" type="submit" value="Submit" />
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</form:form>
						</sec:authorize>

						<!--/ End Form -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/ End Checkout -->

	<!-- Start Shop Services Area  -->
	<section class="shop-services section home">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-rocket"></i>
						<h4>Free shiping</h4>
						<p>Orders over $100</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-reload"></i>
						<h4>Free Return</h4>
						<p>Within 30 days returns</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-lock"></i>
						<h4>Sucure Payment</h4>
						<p>100% secure payment</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-tag"></i>
						<h4>Best Peice</h4>
						<p>Guaranteed price</p>
					</div>
					<!-- End Single Service -->
				</div>
			</div>
		</div>
	</section>
	<!-- End Shop Services -->

	<!-- Start Shop Newsletter  -->
	<section class="shop-newsletter section">
		<div class="container">
			<div class="inner-top">
				<div class="row">
					<div class="col-lg-8 offset-lg-2 col-12">
						<!-- Start Newsletter Inner -->
						<div class="inner">
							<h4>Newsletter</h4>
							<p>
								Subscribe to our newsletter and get <span>10%</span> off your
								first purchase
							</p>
							<form action="mail/mail.php" method="get" target="_blank"
								class="newsletter-inner">
								<input name="EMAIL" placeholder="Your email address" required
									type="email">
								<button class="btn">Subscribe</button>
							</form>
						</div>
						<!-- End Newsletter Inner -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Shop Newsletter -->

	<!-- Start Footer Area -->
	<footer class="footer">
		<!-- Footer Top -->
		<div class="footer-top section">
			<div class="container">
				<div class="row">
					<div class="col-lg-5 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer about">
							<div class="logo">

								<sec:authorize access="!isAuthenticated()">
									<a href="${pageContext.request.contextPath}/index"><img
										src="${pageContext.request.contextPath}/images/logo2.png"
										alt="logo2"></a>
								</sec:authorize>

								<sec:authorize access="hasRole('ADMIN')">
									<a href="${pageContext.request.contextPath}/admin/index"><img
										src="${pageContext.request.contextPath}/images/logo2.png"
										alt="logo2"></a>
								</sec:authorize>

								<sec:authorize access="hasRole('CUSTOMER')">
									<a href="${pageContext.request.contextPath}/customer/index"><img
										src="${pageContext.request.contextPath}/images/logo2.png"
										alt="logo2"></a>
								</sec:authorize>

							</div>
							<p class="text">Praesent dapibus, neque id cursus ucibus,
								tortor neque egestas augue, magna eros eu erat. Aliquam erat
								volutpat. Nam dui mi, tincidunt quis, accumsan porttitor,
								facilisis luctus, metus.</p>
							<p class="call">
								Got Question? Call us 24/7<span><a href="tel:123456789">+0123
										456 789</a></span>
							</p>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-2 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer links">
							<h4>Information</h4>
							<ul>
								<li><a href="#">About Us</a></li>
								<li><a href="#">Faq</a></li>
								<li><a href="#">Terms - Conditions</a></li>
								<li><a href="#">Contact Us</a></li>
								<li><a href="#">Help</a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-2 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer links">
							<h4>Customer Service</h4>
							<ul>
								<li><a href="#">Payment Methods</a></li>
								<li><a href="#">Money-back</a></li>
								<li><a href="#">Returns</a></li>
								<li><a href="#">Shipping</a></li>
								<li><a href="#">Privacy Policy</a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-3 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer social">
							<h4>Get In Tuch</h4>
							<!-- Single Widget -->
							<div class="contact">
								<ul>
									<li>115 Nguyen Van Linh, Nam Duong, Hai Chau</li>
									<li>Da Nang</li>
									<li>info@sonstore.com</li>
									<li>+84 123 456 789</li>
								</ul>
							</div>
							<!-- End Single Widget -->
							<ul>
								<li><a href="#"><i class="ti-facebook"></i></a></li>
								<li><a href="#"><i class="ti-twitter"></i></a></li>
								<li><a href="#"><i class="ti-flickr"></i></a></li>
								<li><a href="#"><i class="ti-instagram"></i></a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
				</div>
			</div>
		</div>
		<!-- End Footer Top -->
		<div class="copyright">
			<div class="container">
				<div class="inner">
					<div class="row">
						<div class="col-lg-6 col-12">
							<div class="left">
								<p>
									Copyright © 2021 <a href="http://www.soncorporation.com"
										target="_blank">Son Corporation</a> - All Rights Reserved.
								</p>
							</div>
						</div>
						<div class="col-lg-6 col-12">
							<div class="right">
								<img
									src="${pageContext.request.contextPath}/images/payments.png"
									alt="#">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- /End Footer Area -->

	<!-- Jquery -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery-migrate-3.0.0.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<!-- Popper JS -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Color JS -->
	<script src="${pageContext.request.contextPath}/js/colors.js"></script>
	<!-- Slicknav JS -->
	<script src="${pageContext.request.contextPath}/js/slicknav.min.js"></script>
	<!-- Owl Carousel JS -->
	<script src="${pageContext.request.contextPath}/js/owl-carousel.js"></script>
	<!-- Magnific Popup JS -->
	<script src="${pageContext.request.contextPath}/js/magnific-popup.js"></script>
	<!-- Fancybox JS -->
	<script src="${pageContext.request.contextPath}/js/facnybox.min.js"></script>
	<!-- Waypoints JS -->
	<script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script
		src="${pageContext.request.contextPath}/js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script src="${pageContext.request.contextPath}/js/nicesellect.js"></script>
	<!-- Ytplayer JS -->
	<script src="${pageContext.request.contextPath}/js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="${pageContext.request.contextPath}/js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="${pageContext.request.contextPath}/js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="${pageContext.request.contextPath}/js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="${pageContext.request.contextPath}/js/easing.js"></script>
	<!-- Active JS -->
	<script src="${pageContext.request.contextPath}/js/active.js"></script>
</body>
</html>