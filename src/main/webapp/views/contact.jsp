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
									<li><i class="ti-location-pin"></i> Store location</li>
									<li><i class="ti-alarm-clock"></i> <a
										href="${pageContext.request.contextPath}/index/discount">Daily
											deal</a></li>
									<li><i class="ti-user"></i> <a
										href="${pageContext.request.contextPath}/register">Register</a></li>
									<li><i class="ti-power-off"></i><a
										href="${pageContext.request.contextPath}/login">Login</a></li>
								</sec:authorize>

								<sec:authorize access="hasRole('ADMIN')">
									<li><i class="ti-location-pin"></i> Store location</li>
									<li><i class="ti-alarm-clock"></i> <a
										href="${pageContext.request.contextPath}/admin/index/discount">Daily
											deal</a></li>
									<li><i class="ti-user"></i> <a
										href="${pageContext.request.contextPath}/admin/info">ADMIN</a></li>
									<li><form:form
											action="${pageContext.request.contextPath}/logout"
											method="post">
											<input class="btn animate" type="submit" value="Logout" />
										</form:form></li>
								</sec:authorize>

								<sec:authorize access="hasRole('CUSTOMER')">
									<li><i class="ti-location-pin"></i> Store location</li>
									<li><i class="ti-alarm-clock"></i> <a
										href="${pageContext.request.contextPath}/customer/index/discount">Daily
											deal</a></li>
									<li><i class="ti-user"></i> <a
										href="${pageContext.request.contextPath}/customer/info">${sessionScope.user.firstName}</a></li>
									<li><form:form
											action="${pageContext.request.contextPath}/logout"
											method="post">
											<input class="btn animate" type="submit" value="Logout" />
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
						<div class="logo">

							<sec:authorize access="!isAuthenticated()">
								<a href="${pageContext.request.contextPath}/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</sec:authorize>

							<sec:authorize access="hasRole('ADMIN')">
								<a href="${pageContext.request.contextPath}/admin/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<a href="${pageContext.request.contextPath}/customer/index"><img
									src="${pageContext.request.contextPath}/images/logo.png"
									alt="logo"></a>
							</sec:authorize>

						</div>
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
										class="total-count"> <c:choose>
												<c:when test="${sessionScope.listOrderDetails.size()>0}">${sessionScope.listOrderDetails.size()}</c:when>
												<c:otherwise>0</c:otherwise>
											</c:choose>
									</span></a>
									<!-- Shopping Item -->
									<div class="shopping-item">
										<div class="dropdown-cart-header">
											<span><c:choose>
													<c:when test="${sessionScope.listOrderDetails.size()>0}">${sessionScope.listOrderDetails.size()}</c:when>
													<c:otherwise>0</c:otherwise>
												</c:choose> Items</span> <a
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
															href="${pageContext.request.contextPath}/admin/cart/deleteOrderDetails?id=${item.product.id }"
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
												class="btn">Checkout</a>
										</div>
									</div>
									<!--/ End Shopping Item -->
								</div>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<div class="sinlge-bar shopping">
									<a href="${pageContext.request.contextPath}/customer/cart"
										class="single-icon"><i class="ti-bag"></i> <span
										class="total-count"><c:choose>
												<c:when test="${sessionScope.listOrderDetails.size()>0}">${sessionScope.listOrderDetails.size()}</c:when>
												<c:otherwise>0</c:otherwise>
											</c:choose></span></a>
									<!-- Shopping Item -->
									<div class="shopping-item">
										<div class="dropdown-cart-header">
											<span><c:choose>
													<c:when test="${sessionScope.listOrderDetails.size()>0}">${sessionScope.listOrderDetails.size()}</c:when>
													<c:otherwise>0</c:otherwise>
												</c:choose> Items</span> <a
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
															href="${pageContext.request.contextPath}/customer/cart/deleteOrderDetails?id=${item.product.id }"
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
												class="btn">Checkout</a>
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
						<div class="col-12">
							<div class="menu-area">
								<!-- Main Menu -->
								<nav class="navbar navbar-expand-lg">
									<div class="navbar-collapse">
										<div class="nav-inner">
											<ul class="nav main-menu menu navbar-nav">

												<sec:authorize access="!isAuthenticated()">
													<li class="active"><a
														href="${pageContext.request.contextPath}/index">Home</a></li>
													<li><a
														href="${pageContext.request.contextPath}/index/discount">SALE
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
													<li><a
														href="${pageContext.request.contextPath}/admin/index/discount">SALE
															OFF</a></li>
													<li><a href="#">Blog<i class="ti-angle-down"></i></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/admin/blog">Blog
																	Single Sidebar</a></li>
														</ul></li>
													<li><a
														href="${pageContext.request.contextPath}/admin/contact">Contact
															Us</a></li>
												</sec:authorize>

												<sec:authorize access="hasRole('CUSTOMER')">
													<li class="active"><a
														href="${pageContext.request.contextPath}/customer/index">Home</a></li>
													<li><a
														href="${pageContext.request.contextPath}/customer/index/discount">SALE
															OFF</a></li>
													<li><a href="#">Blog<i class="ti-angle-down"></i></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/customer/blog">Blog
																	Single Sidebar</a></li>
														</ul></li>
													<li><a
														href="${pageContext.request.contextPath}/customer/contact">Contact
															Us</a></li>
												</sec:authorize>

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
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">

							<sec:authorize access="!isAuthenticated()">
								<li><a href="${pageContext.request.contextPath}/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/contact">Contact</a></li>
							</sec:authorize>

							<sec:authorize access="hasRole('ADMIN')">
								<li><a
									href="${pageContext.request.contextPath}/admin/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/admin/info">Admin<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/contact">Contact</a></li>
							</sec:authorize>

							<sec:authorize access="hasRole('CUSTOMER')">
								<li><a
									href="${pageContext.request.contextPath}/customer/index">Home<i
										class="ti-arrow-right"></i></a></li>
								<li><a
									href="${pageContext.request.contextPath}/customer/info">${sessionScope.user.firstName}<i
										class="ti-arrow-right"></i></a></li>
								<li class="active"><a
									href="${pageContext.request.contextPath}/customer/contact">Contact</a></li>
							</sec:authorize>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<!-- Start Contact -->
	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-8 col-12">
						<div class="form-main">
							<div class="title">
								<h4>Get in touch</h4>
								<h3>Write us a message</h3>
							</div>
							<form class="form" method="post" action="mail/mail.php">
								<div class="row">
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Your Name<span>*</span></label> <input name="name"
												type="text" placeholder="">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Your Subjects<span>*</span></label> <input
												name="subject" type="text" placeholder="">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Your Email<span>*</span></label> <input name="email"
												type="email" placeholder="">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Your Phone<span>*</span></label> <input
												name="company_name" type="text" placeholder="">
										</div>
									</div>
									<div class="col-12">
										<div class="form-group message">
											<label>your message<span>*</span></label>
											<textarea name="message" placeholder=""></textarea>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button">
											<button type="submit" class="btn ">Send Message</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-4 col-12">
						<div class="single-head">
							<div class="single-info">
								<i class="fa fa-phone"></i>
								<h4 class="title">Call us Now:</h4>
								<ul>
									<li>+123 456-789-1120</li>
									<li>+522 672-452-1120</li>
								</ul>
							</div>
							<div class="single-info">
								<i class="fa fa-envelope-open"></i>
								<h4 class="title">Email:</h4>
								<ul>
									<li><a href="mailto:info@yourwebsite.com">info@yourwebsite.com</a></li>
									<li><a href="mailto:info@yourwebsite.com">support@yourwebsite.com</a></li>
								</ul>
							</div>
							<div class="single-info">
								<i class="fa fa-location-arrow"></i>
								<h4 class="title">Our Address:</h4>
								<ul>
									<li>KA-62/1, Travel Agency, 45 Grand Central Terminal, New
										York.</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/ End Contact -->

	<!-- Map Section -->
	<div class="map-section">
		<div id="myMap"></div>
	</div>
	<!--/ End Map Section -->

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

								<sec:authorize access="!isAuthenticated()">
									<li><a href="${pageContext.request.contextPath}/contact">Contact
											Us</a></li>
								</sec:authorize>

								<sec:authorize access="hasRole('ADMIN')">
									<li><a
										href="${pageContext.request.contextPath}/admin/contact">Contact
											Us</a></li>
								</sec:authorize>

								<sec:authorize access="hasRole('CUSTOMER')">
									<li><a
										href="${pageContext.request.contextPath}/customer/contact">Contact
											Us</a></li>
								</sec:authorize>

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
	<!-- Jquery Counterup JS -->
	<script
		src="${pageContext.request.contextPath}/js/jquery-counterup.min.js"></script>
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
	<!-- Google Map JS -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnhgNBg6jrSuqhTeKKEFDWI0_5fZLx0vM"></script>
	<script src="${pageContext.request.contextPath}/js/gmap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/map-script.js"></script>
	<!-- Active JS -->
	<script src="${pageContext.request.contextPath}/js/active.js"></script>
</body>
</html>