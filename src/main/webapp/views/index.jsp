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
						<div class="col-lg-3">
							<div class="all-category">
								<h3 class="cat-heading">
									<i class="fa fa-bars" aria-hidden="true"></i>CATEGORIES
								</h3>
								<ul class="main-category">

									<c:if
										test="${listCategory ne null && listCategory.size() gt 0}">
										<c:forEach items="${listCategory }" var="item">
											<li><sec:authorize access="!isAuthenticated()">
													<a
														href="${pageContext.request.contextPath}/index/category/${item.id }">${item.name }
													</a>
												</sec:authorize> <sec:authorize access="hasRole('ADMIN')">
													<a
														href="${pageContext.request.contextPath}/admin/index/category/${item.id }">${item.name }
													</a>
												</sec:authorize> <sec:authorize access="hasRole('CUSTOMER')">
													<a
														href="${pageContext.request.contextPath}/customer/index/category/${item.id }">${item.name }
													</a>
												</sec:authorize></li>
										</c:forEach>
									</c:if>

									<!-- <li><a href="#">New Arrivals <i
											class="fa fa-angle-right" aria-hidden="true"></i></a>
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
										</ul></li> -->
									<li class="main-mega"><a href="#">Best Selling <i
											class="fa fa-angle-right" aria-hidden="true"></i></a>
										<ul class="mega-menu">
											<li class="single-menu"><a href="#" class="title-link">Shop
													Kid's</a>
												<div class="image">
													<img src="https://via.placeholder.com/225x155" alt="#">
												</div>
												<div class="inner-link">
													<a href="#">Kids Toys</a> <a href="#">Kids Travel Car</a> <a
														href="#">Kids Color Shape</a> <a href="#">Kids Tent</a>
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
														href="#">Ladies Sun Glass</a> <a href="#">Ladies Watch</a>
												</div></li>
										</ul></li>
								</ul>
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
													<li><a href="#">Shop<i class="ti-angle-down"></i><span
															class="new">New</span></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/register">Cart</a></li>
														</ul></li>
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
													<li><a href="#">Shop<i class="ti-angle-down"></i><span
															class="new">New</span></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/admin/cart">Cart</a></li>
														</ul></li>
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
													<li><a href="#">Shop<i class="ti-angle-down"></i><span
															class="new">New</span></a>
														<ul class="dropdown">
															<li><a
																href="${pageContext.request.contextPath}/customer/cart">Cart</a></li>
														</ul></li>
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

	<!-- Slider Area -->
	<section class="hero-slider">
		<!-- Single Slider -->
		<div class="single-slider">
			<div class="container">
				<div class="row no-gutters">
					<div class="col-lg-9 offset-lg-3 col-12">
						<div class="text-inner">
							<div class="row">
								<div class="col-lg-7 col-12">
									<div class="hero-text">

										<!-- <h1>
											<span>UP TO 50% OFF </span>Shirt For Man
										</h1>
										<p>
											Maboriosam in a nesciung eget magnae <br> dapibus
											disting tloctio in the find it pereri <br> odiy
											maboriosm.
										</p> 
										<div class="button">
											<a href="#" class="btn">Shop Now!</a>
										</div> -->

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/ End Single Slider -->
	</section>
	<!--/ End Slider Area -->

	<!-- Start Small Banner  -->
	<!-- <section class="small-banner section">
		<div class="container-fluid">
			<div class="row">
				Single Banner 
				<div class="col-lg-4 col-md-6 col-12">
					<div class="single-banner">
						<img src="https://via.placeholder.com/600x370" alt="#">
						<div class="content">
							<p>Man's Collectons</p>
							<h3>
								Summer travel <br> collection
							</h3>
							<a href="#">Discover Now</a>
						</div>
					</div>
				</div>
				/End Single Banner 
				Single Banner 
				<div class="col-lg-4 col-md-6 col-12">
					<div class="single-banner">
						<img src="https://via.placeholder.com/600x370" alt="#">
						<div class="content">
							<p>Bag Collectons</p>
							<h3>
								Awesome Bag <br> 2020
							</h3>
							<a href="#">Shop Now</a>
						</div>
					</div>
				</div>
				/End Single Banner 
				Single Banner 
				<div class="col-lg-4 col-12">
					<div class="single-banner tab-height">
						<img src="https://via.placeholder.com/600x370" alt="#">
						<div class="content">
							<p>Flash Sale</p>
							<h3>
								Mid Season <br> Up to <span>40%</span> Off
							</h3>
							<a href="#">Discover Now</a>
						</div>
					</div>
				</div>
				/End Single Banner 
			</div>
		</div>
	</section> -->
	<!-- End Small Banner -->

	<!-- Start Product Area -->
	<div class="product-area section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>All Items</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="product-info">
						<div class="nav-main">
							<!-- Tab Nav -->

							<ul class="nav nav-tabs" id="myTab">
								<c:if test="${listCategory ne null && listCategory.size() gt 0}">
									<c:forEach items="${listCategory }" var="item">
										<li class="nav-item"><a class="nav-link "
											href="${pageContext.request.contextPath}/index/category/${item.id }">${item.name }</a></li>
									</c:forEach>
								</c:if>

							</ul>

							<!-- End Tab Nav -->
						</div>

						<div class="tab-content" id="myTabContent">
							<!-- Start Single Tab -->
							<div class="tab-pane fade show active" id="man">
								<div class="tab-single">
									<div class="row">

										<c:choose>
											<c:when
												test="${listProduct ne null && listProduct.size() gt 0}">
												<c:forEach items="${listProduct }" var="item">
													<div class="col-xl-3 col-lg-4 col-md-4 col-12">
														<div class="single-product">

															<sec:authorize access="!isAuthenticated()">
																<div class="product-img">
																	<a
																		href="${pageContext.request.contextPath}/product/${item.id}">
																		<img class="default-img"
																		src="${pageContext.request.contextPath}/${item.path }"
																		alt="default"> <img class="hover-img"
																		src="${pageContext.request.contextPath}/${item.path }">
																	</a>
																	<div class="button-head">
																		<!-- <div class="product-action">
																			<a data-toggle="modal" data-target="#exampleModal"
																				title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
																					Shop</span></a> <a title="Wishlist" href="#"><i
																				class=" ti-heart "></i><span>Add to Wishlist</span></a>
																			<a title="Compare" href="#"><i
																				class="ti-bar-chart-alt"></i><span>Add to
																					Compare</span></a>
																		</div> -->
																		
																		<c:choose>

																			<c:when test="${item.inventory gt 0 }">
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/register">Add
																						to cart</a>
																				</div>
																			</c:when>

																			<c:otherwise>
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/customer/product/${item.id}">Add
																						to cart</a>
																				</div>
																			</c:otherwise>

																		</c:choose>
																		
																	</div>
																</div>
																<div class="product-content">
																	<h3>
																		<a
																			href="${pageContext.request.contextPath}/product/${item.id}">${item.name }</a>
																	</h3>
																	<div class="product-price">
																		<c:choose>
																			<c:when test="${item.discount.id ne 1}">
																				<span style="color: red;">$
																					${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}
																				</span>
																				<del>$ ${item.unitPrice}</del>
																			</c:when>
																			<c:otherwise>
																				<span>$ ${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}</span>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
															</sec:authorize>

															<sec:authorize access="hasRole('ADMIN')">
																<div class="product-img">
																	<a
																		href="${pageContext.request.contextPath}/admin/product/${item.id}">
																		<img class="default-img"
																		src="${pageContext.request.contextPath}/${item.path }"
																		alt="default"> <img class="hover-img"
																		src="${pageContext.request.contextPath}/${item.path }">
																	</a>
																	<div class="button-head">
																		<!-- <div class="product-action">
																			<a data-toggle="modal" data-target="#exampleModal"
																				title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
																					Shop</span></a> <a title="Wishlist" href="#"><i
																				class=" ti-heart "></i><span>Add to Wishlist</span></a>
																			<a title="Compare" href="#"><i
																				class="ti-bar-chart-alt"></i><span>Add to
																					Compare</span></a>
																		</div> -->

																		<c:choose>

																			<c:when test="${item.inventory gt 0 }">
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/customer/product/${item.id}/toCart?quantity=1">Add
																						to cart</a>
																				</div>
																			</c:when>

																			<c:otherwise>
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/customer/product/${item.id}">Add
																						to cart</a>
																				</div>
																			</c:otherwise>

																		</c:choose>

																	</div>
																</div>
																<div class="product-content">
																	<h3>
																		<a
																			href="${pageContext.request.contextPath}/admin/product/${item.id}">${item.name }</a>
																	</h3>
																	<div class="product-price">
																		<c:choose>
																			<c:when test="${item.discount.id ne 1}">
																				<span style="color: red;">$
																					${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}
																				</span>
																				<del>$ ${item.unitPrice}</del>
																			</c:when>
																			<c:otherwise>
																				<span>$ ${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}</span>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
															</sec:authorize>

															<sec:authorize access="hasRole('CUSTOMER')">
																<div class="product-img">
																	<a
																		href="${pageContext.request.contextPath}/customer/product/${item.id}">
																		<img class="default-img"
																		src="${pageContext.request.contextPath}/${item.path }"
																		alt="default"> <img class="hover-img"
																		src="${pageContext.request.contextPath}/${item.path }">
																	</a>
																	<div class="button-head">
																		<!-- <div class="product-action">
																			<a data-toggle="modal" data-target="#exampleModal"
																				title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
																					Shop</span></a> <a title="Wishlist" href="#"><i
																				class=" ti-heart "></i><span>Add to Wishlist</span></a>
																			<a title="Compare" href="#"><i
																				class="ti-bar-chart-alt"></i><span>Add to
																					Compare</span></a>
																		</div> -->

																		<c:choose>

																			<c:when test="${item.inventory gt 0 }">
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/customer/product/${item.id}/toCart?quantity=1">Add
																						to cart</a>
																				</div>
																			</c:when>

																			<c:otherwise>
																				<div class="product-action-2">
																					<a title="Add to cart"
																						href="${pageContext.request.contextPath}/customer/product/${item.id}">Add
																						to cart</a>
																				</div>
																			</c:otherwise>

																		</c:choose>

																	</div>
																</div>
																<div class="product-content">
																	<h3>
																		<a
																			href="${pageContext.request.contextPath}/customer/product/${item.id}">${item.name }</a>
																	</h3>
																	<div class="product-price">
																		<c:choose>
																			<c:when test="${item.discount.id ne 1}">
																				<span style="color: red;">$
																					${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}
																				</span>
																				<del>$ ${item.unitPrice}</del>
																			</c:when>
																			<c:otherwise>
																				<span>$ ${(item.unitPrice) * ((100-item.discount.discountPercentage)/100)}</span>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
															</sec:authorize>

														</div>
													</div>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<div class="col-12 text-center">
													<h3 style="color: red;">NOT FOUND!!!</h3>
												</div>
											</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>
							<!--/ End Single Tab -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Product Area -->

	<div class="col-12 text-center">
		<c:import url="common/homepagePaging.jsp" />
	</div>

	<div class="container">
		<div class="row">
			<br>
		</div>
	</div>

	<!-- Start Midium Banner  -->
	<!-- <section class="midium-banner">
		<div class="container">
			<div class="row">
				Single Banner 
				<div class="col-lg-6 col-md-6 col-12">
					<div class="single-banner">
						<img src="https://via.placeholder.com/600x370" alt="#">
						<div class="content">
							<p>Man's Collectons</p>
							<h3>
								Man's items <br>Up to<span> 50%</span>
							</h3>
							<a href="#">Shop Now</a>
						</div>
					</div>
				</div>
				/End Single Banner 
				Single Banner 
				<div class="col-lg-6 col-md-6 col-12">
					<div class="single-banner">
						<img src="https://via.placeholder.com/600x370" alt="#">
						<div class="content">
							<p>shoes women</p>
							<h3>
								mid season <br> up to <span>70%</span>
							</h3>
							<a href="#" class="btn">Shop Now</a>
						</div>
					</div>
				</div>
				/End Single Banner 
			</div>
		</div>
	</section> -->
	<!-- End Midium Banner -->

	<!-- Start Most Popular -->
	<%-- <div class="product-area most-popular section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>Hot Item</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="owl-carousel popular-slider">
						<!-- Start Single Product -->
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.html"> <img class="default-img"
									src="https://via.placeholder.com/550x750" alt="#"> <img
									class="hover-img" src="https://via.placeholder.com/550x750"
									alt="#"> <span class="out-of-stock">Hot</span>
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal"
											title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
												Shop</span></a> <a title="Wishlist" href="#"><i class=" ti-heart "></i><span>Add
												to Wishlist</span></a> <a title="Compare" href="#"><i
											class="ti-bar-chart-alt"></i><span>Add to Compare</span></a>
									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="#">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3>
									<a href="product-details.html">Black Sunglass For Women</a>
								</h3>
								<div class="product-price">
									<span class="old">$60.00</span> <span>$50.00</span>
								</div>
							</div>
						</div>
						<!-- End Single Product -->
						<!-- Start Single Product -->
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.html"> <img class="default-img"
									src="https://via.placeholder.com/550x750" alt="#"> <img
									class="hover-img" src="https://via.placeholder.com/550x750"
									alt="#">
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal"
											title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
												Shop</span></a> <a title="Wishlist" href="#"><i class=" ti-heart "></i><span>Add
												to Wishlist</span></a> <a title="Compare" href="#"><i
											class="ti-bar-chart-alt"></i><span>Add to Compare</span></a>
									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="#">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3>
									<a href="product-details.html">Women Hot Collection</a>
								</h3>
								<div class="product-price">
									<span>$50.00</span>
								</div>
							</div>
						</div>
						<!-- End Single Product -->
						<!-- Start Single Product -->
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.html"> <img class="default-img"
									src="https://via.placeholder.com/550x750" alt="#"> <img
									class="hover-img" src="https://via.placeholder.com/550x750"
									alt="#"> <span class="new">New</span>
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal"
											title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
												Shop</span></a> <a title="Wishlist" href="#"><i class=" ti-heart "></i><span>Add
												to Wishlist</span></a> <a title="Compare" href="#"><i
											class="ti-bar-chart-alt"></i><span>Add to Compare</span></a>
									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="#">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3>
									<a href="product-details.html">Awesome Pink Show</a>
								</h3>
								<div class="product-price">
									<span>$50.00</span>
								</div>
							</div>
						</div>
						<!-- End Single Product -->
						<!-- Start Single Product -->
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.html"> <img class="default-img"
									src="https://via.placeholder.com/550x750" alt="#"> <img
									class="hover-img" src="https://via.placeholder.com/550x750"
									alt="#">
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal"
											title="Quick View" href="#"><i class=" ti-eye"></i><span>Quick
												Shop</span></a> <a title="Wishlist" href="#"><i class=" ti-heart "></i><span>Add
												to Wishlist</span></a> <a title="Compare" href="#"><i
											class="ti-bar-chart-alt"></i><span>Add to Compare</span></a>
									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="#">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3>
									<a href="product-details.html">Awesome Bags Collection</a>
								</h3>
								<div class="product-price">
									<span>$50.00</span>
								</div>
							</div>
						</div>
						<!-- End Single Product -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Most Popular Area -->

	<!-- <section class="section free-version-banner">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-8 offset-md-2 col-xs-12">
					<div class="section-title mb-60">
						<span class="text-white wow fadeInDown" data-wow-delay=".2s"
							style="visibility: visible; animation-delay: 0.2s; animation-name: fadeInDown;">Eshop
							Free Lite version</span>
						<h2 class="text-white wow fadeInUp" data-wow-delay=".4s"
							style="visibility: visible; animation-delay: 0.4s; animation-name: fadeInUp;">
							Currently You are using free<br> lite Version of Eshop.
						</h2>
						<p class="text-white wow fadeInUp" data-wow-delay=".6s"
							style="visibility: visible; animation-delay: 0.6s; animation-name: fadeInUp;">
							Please, purchase full version of the template to get all pages,<br>
							features and commercial license.
						</p>

						<div class="button">
							<a
								href="https://wpthemesgrid.com/downloads/eshop-ecommerce-html5-template/"
								target="_blank" rel="nofollow" class="btn wow fadeInUp"
								data-wow-delay=".8s">Purchase Now</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section> -->

	<!-- Start Shop Home List  -->
	<section class="shop-home-list section">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 col-12">
					<div class="row">
						<div class="col-12">
							<div class="shop-section-title">
								<h1>On sale</h1>
							</div>
						</div>
					</div>
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h4 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h4>
									<p class="price with-discount">$59</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$44</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$89</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
				</div>
				<div class="col-lg-4 col-md-6 col-12">
					<div class="row">
						<div class="col-12">
							<div class="shop-section-title">
								<h1>Best Seller</h1>
							</div>
						</div>
					</div>
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$65</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$33</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$77</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
				</div>
				<div class="col-lg-4 col-md-6 col-12">
					<div class="row">
						<div class="col-12">
							<div class="shop-section-title">
								<h1>Top viewed</h1>
							</div>
						</div>
					</div>
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$22</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$35</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
					<!-- Start Single List  -->
					<div class="single-list">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<div class="list-image overlay">
									<img src="https://via.placeholder.com/115x140" alt="#"> <a
										href="#" class="buy"><i class="fa fa-shopping-bag"></i></a>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-12 no-padding">
								<div class="content">
									<h5 class="title">
										<a href="#">Licity jelly leg flat Sandals</a>
									</h5>
									<p class="price with-discount">$99</p>
								</div>
							</div>
						</div>
					</div>
					<!-- End Single List  -->
				</div>
			</div>
		</div>
	</section> --%>
	<!-- End Shop Home List  -->

	<!-- Start Shop Blog  -->
	<!-- <section class="shop-blog section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>From Our Blog</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-6 col-12">
					Start Single Blog 
					<div class="shop-single-blog">
						<img src="https://via.placeholder.com/370x300" alt="#">
						<div class="content">
							<p class="date">22 July , 2020. Monday</p>
							<a href="#" class="title">Sed adipiscing ornare.</a> <a href="#"
								class="more-btn">Continue Reading</a>
						</div>
					</div>
					End Single Blog 
				</div>
				<div class="col-lg-4 col-md-6 col-12">
					Start Single Blog 
					<div class="shop-single-blog">
						<img src="https://via.placeholder.com/370x300" alt="#">
						<div class="content">
							<p class="date">22 July, 2020. Monday</p>
							<a href="#" class="title">Man’s Fashion Winter Sale</a> <a
								href="#" class="more-btn">Continue Reading</a>
						</div>
					</div>
					End Single Blog 
				</div>
				<div class="col-lg-4 col-md-6 col-12">
					Start Single Blog 
					<div class="shop-single-blog">
						<img src="https://via.placeholder.com/370x300" alt="#">
						<div class="content">
							<p class="date">22 July, 2020. Monday</p>
							<a href="#" class="title">Women Fashion Festive</a> <a href="#"
								class="more-btn">Continue Reading</a>
						</div>
					</div>
					End Single Blog 
				</div>
			</div>
		</div>
	</section> -->
	<!-- End Shop Blog  -->

	<!-- Start Shop Services Area -->
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
	<!-- End Shop Services Area -->

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

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="ti-close" aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row no-gutters">
						<div class="col-lg-6 offset-lg-3 col-12">
							<h4
								style="margin-top: 100px; font-size: 14px; font-weight: 500; color: #F7941D; display: block; margin-bottom: 5px;">Eshop
								Free Lite</h4>
							<h3 style="font-size: 30px; color: #333;">Currently You are
								using free lite Version of Eshop.</h3>
							<p
								style="display: block; margin-top: 20px; color: #888; font-size: 14px; font-weight: 400;">Please,
								purchase full version of the template to get all pages, features
								and commercial license</p>
							<div class="button" style="margin-top: 30px;">
								<a
									href="https://wpthemesgrid.com/downloads/eshop-ecommerce-html5-template/"
									target="_blank" class="btn" style="color: #fff;">Buy Now!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal end -->

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
	<!-- Waypoints JS -->
	<script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script
		src="${pageContext.request.contextPath}/js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script src="${pageContext.request.contextPath}/js/nicesellect.js"></script>
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