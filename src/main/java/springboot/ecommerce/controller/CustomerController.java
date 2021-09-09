package springboot.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.ecommerce.entity.CategoryEntity;
import springboot.ecommerce.entity.CreditCardEntity;
import springboot.ecommerce.entity.GenderEntity;
import springboot.ecommerce.entity.OrderDetailsEntity;
import springboot.ecommerce.entity.OrderEntity;
import springboot.ecommerce.entity.ProductEntity;
import springboot.ecommerce.entity.RoleEntity;
import springboot.ecommerce.entity.UserEntity;
import springboot.ecommerce.service.CategoryService;
import springboot.ecommerce.service.CreditCardService;
import springboot.ecommerce.service.GenderService;
import springboot.ecommerce.service.OrderDetailsService;
import springboot.ecommerce.service.OrderService;
import springboot.ecommerce.service.ProductService;
import springboot.ecommerce.service.RoleService;
import springboot.ecommerce.service.SendMailService;
import springboot.ecommerce.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static int FIRST_PAGE = 1;

	@Autowired
	public GenderService genderService;

	@Autowired
	public RoleService roleService;

	@Autowired
	public ProductService productService;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public OrderDetailsService orderDetailsService;

	@Autowired
	public OrderService orderService;

	@Autowired
	public CreditCardService creditCardService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SendMailService sendMailService;

	@GetMapping("/blog")
	public String showBlog(Model model) {

		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "blog";

	}

	@GetMapping("/contact")
	public String showContact(Model model) {

		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "contact";

	}

	@GetMapping("/index")
	public String showHomepage(Model model, HttpSession session, Authentication authentication) {

		model.addAttribute("currentPage", FIRST_PAGE);

		if (session.getAttribute("search") != null && session.getAttribute("search").equals("on")) {
			model.addAttribute("listProduct", session.getAttribute("listSearch"));
			model.addAttribute("totalPages", session.getAttribute("totalPages"));
			model.addAttribute("totalItems", session.getAttribute("totalItems"));
			session.removeAttribute("listSearch");
			session.removeAttribute("totalPages");
			session.removeAttribute("totalItems");
			session.removeAttribute("search");
		} else {
			model.addAttribute("listProduct", productService.getProductsPaging(FIRST_PAGE).getContent());
			model.addAttribute("totalPages", productService.getProductsPaging(FIRST_PAGE).getTotalPages());
			model.addAttribute("totalItems", productService.getProductsPaging(FIRST_PAGE).getTotalElements());
			session.removeAttribute("name");
			session.removeAttribute("category");
			session.removeAttribute("check");
		}

		// Cai nay la setting path cua cai @GetMapping("/index")
		model.addAttribute("path", "customer/index");
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		if (authentication != null) {
			UserEntity mainUser = userService.findByEmail(authentication.getName());
			if (mainUser != null) {
				session.setAttribute("user", mainUser);
			}
		}
		return "index";

	}

	@GetMapping("/index/search")
	public String searchProductHomepage(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "category") int category, HttpSession session) {

		List<ProductEntity> list = productService.getProductPageBySearch(name, category, FIRST_PAGE).getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = productService.getProductPageBySearch(name, category, FIRST_PAGE).getTotalPages();
		long totalItems = productService.getProductPageBySearch(name, category, FIRST_PAGE).getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("name", name);
		session.setAttribute("category", category);
		return "redirect:/customer/index";

	}

	@GetMapping("/index/category/{id}")
	public String showProductByCategory(Model model, @PathVariable(name = "id") int category, HttpSession session) {

		List<ProductEntity> list = productService.getProductPageBySearch(null, category, FIRST_PAGE).getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = productService.getProductPageBySearch(null, category, FIRST_PAGE).getTotalPages();
		long totalItems = productService.getProductPageBySearch(null, category, FIRST_PAGE).getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("name", null);
		session.setAttribute("category", category);
		return "redirect:/customer/index";

	}

	@GetMapping("/index/discount")
	public String showDiscountProducts(HttpSession session) {

		List<ProductEntity> list = productService.getProductPageByDiscount(1, FIRST_PAGE).getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = productService.getProductPageByDiscount(1, FIRST_PAGE).getTotalPages();
		long totalItems = productService.getProductPageByDiscount(1, FIRST_PAGE).getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("check", "discount");
		return "redirect:/customer/index";

	}

	@GetMapping("/index/{page}")
	public String getHomepagePaging(Model model, @PathVariable(name = "page") int pageNum, HttpSession session) {

		model.addAttribute("currentPage", pageNum);
		String name = (String) session.getAttribute("name");
		Integer category = (Integer) session.getAttribute("category");
		String check = (String) session.getAttribute("check");
		if (check != null && check.equals("discount")) {
			model.addAttribute("listProduct", productService.getProductPageByDiscount(1, pageNum).getContent());
			model.addAttribute("totalPages", productService.getProductPageByDiscount(1, pageNum).getTotalPages());
			model.addAttribute("totalItems", productService.getProductPageByDiscount(1, pageNum).getTotalElements());
		} else if (name != null && category != null) {
			model.addAttribute("listProduct",
					productService.getProductPageBySearch(name, category, pageNum).getContent());
			model.addAttribute("totalPages",
					productService.getProductPageBySearch(name, category, pageNum).getTotalPages());
			model.addAttribute("totalItems",
					productService.getProductPageBySearch(name, category, pageNum).getTotalElements());
		} else {
			model.addAttribute("listProduct", productService.getProductsPaging(pageNum).getContent());
			model.addAttribute("totalPages", productService.getProductsPaging(pageNum).getTotalPages());
			model.addAttribute("totalItems", productService.getProductsPaging(pageNum).getTotalElements());
		}

		// Cai nay la setting path cua cai @GetMapping("/index")
		model.addAttribute("path", "customer/index");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "index";

	}

	// --------------- CUSTOMER ---------------

	@GetMapping("/info")
	public String showLoginForm(Model model, HttpSession session, Authentication authentication) {

		if (session.getAttribute("checkUser") != null && session.getAttribute("checkUser").equals("ok")) {

			UserEntity user = (UserEntity) session.getAttribute("user");
			model.addAttribute("user", user);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("checkUser");
			session.removeAttribute("message");
			return "customer";

		} else if (session.getAttribute("checkUser") != null && session.getAttribute("checkUser").equals("error")) {

			UserEntity errorUser = (UserEntity) session.getAttribute("errorUser");
			model.addAttribute("user", errorUser);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkUser");
			session.removeAttribute("errorUser");
			session.removeAttribute("errorMessage");
			return "customer";

		} else {

			if (authentication != null) {
				UserEntity mainCustomer = userService.findByEmail(authentication.getName());
				if (mainCustomer != null) {
					session.setAttribute("user", mainCustomer);
				}
			}
			UserEntity currentUser = (UserEntity) session.getAttribute("user");
			UserEntity mainCustomer = userService.findById(currentUser.getId());
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("user", mainCustomer);
			return "customer";

		}
	}

	@PostMapping("/submit")
	public String submit(Model model, @ModelAttribute(name = "user") @Valid UserEntity user, BindingResult result,
			HttpSession session, Authentication authentication) {

		if (result.hasErrors()) {
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "customer";
		} else {

			// check mail User
			List<UserEntity> listUser = userService.getList();
			if (listUser != null && !listUser.isEmpty()) {
				for (UserEntity userEntity : listUser) {
					if (userEntity.getEmail().equalsIgnoreCase(user.getEmail()) && userEntity.getId() != user.getId()) {
						session.setAttribute("errorUser", user);
						session.setAttribute("checkUser", "error");
						session.setAttribute("errorMessage", "This email address is NOT available!!!");
						return "redirect:/customer/info";
					}
				}
			}

			// Set role
			List<RoleEntity> listRole = roleService.getList();
			listRole.removeIf(n -> !(n.getName().equalsIgnoreCase("ROLE_CUSTOMER")));
			user.setRoles(listRole);

//			if (session.getAttribute("checkBox") != null && session.getAttribute("checkBox").equals("on")) {
//				session.setAttribute("email", user.getEmail());
//				session.setAttribute("password", user.getPassword());
//			}

			// encode password
			UserEntity oldUser = userService.findById(user.getId());
			if (!user.getPassword().equals(oldUser.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			// save customer
			userService.save(user);
			session.setAttribute("user", user);
			session.setAttribute("checkUser", "ok");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/customer/info";
		}

	}

	// --------------- PRODUCT ---------------

	@GetMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable(name = "id") int id, HttpSession session) {

		if (session.getAttribute("checkOrderDetails") != null
				&& session.getAttribute("checkOrderDetails").equals("error")) {

			ProductEntity product = productService.findById(id);
			session.setAttribute("product", product);
			OrderDetailsEntity errorOrderDetails = (OrderDetailsEntity) session.getAttribute("errorOrderDetails");
			model.addAttribute("orderDetails", errorOrderDetails);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			session.removeAttribute("errorOrderDetails");
			session.removeAttribute("checkOrderDetails");
			return "productDetails";

		} else {

			ProductEntity product = productService.findById(id);
			session.setAttribute("product", product);
			model.addAttribute("orderDetails", new OrderDetailsEntity());
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "productDetails";

		}

	}

	@GetMapping("/product/{id}/toCart")
	public String addToCart(Model model, @PathVariable(name = "id") int id,
			@RequestParam(name = "quantity") int quantity, HttpSession session, HttpServletRequest request) {

		// create new OrderDetails
		ProductEntity productEntity = productService.findById(id);
		double unitPrice = (productEntity.getUnitPrice() * (100 - productEntity.getDiscount().getDiscountPercentage()))
				/ 100;
		double totalPrice = productEntity.getUnitPrice() * quantity;
		double totalDiscountPrice = unitPrice * quantity;
		OrderDetailsEntity orderDetails = new OrderDetailsEntity(quantity, (double) Math.ceil(unitPrice * 100) / 100,
				(double) Math.ceil(totalPrice * 100) / 100, (double) Math.ceil(totalDiscountPrice * 100) / 100,
				productEntity);

		List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
		if (list == null) {
			list = new ArrayList<OrderDetailsEntity>();
		} else if (list != null && list.size() > 0) {

			// check OrderDetails duplicate
			for (OrderDetailsEntity orderDetailsEntity : list) {
				int id1 = orderDetailsEntity.getProduct().getId();
				int id2 = orderDetails.getProduct().getId();
				double price1 = orderDetailsEntity.getUnitPrice();
				double price2 = orderDetails.getUnitPrice();
				if ((id1 == id2) && (price1 == price2)) {

					orderDetailsEntity.setQuantity(orderDetailsEntity.getQuantity() + orderDetails.getQuantity());

					totalPrice = orderDetailsEntity.getTotalPrice()
							+ orderDetailsEntity.getProduct().getUnitPrice() * orderDetails.getQuantity();
					orderDetailsEntity.setTotalPrice((double) Math.ceil(totalPrice * 100) / 100);

					totalDiscountPrice = orderDetailsEntity.getTotalDiscountPrice()
							+ orderDetailsEntity.getUnitPrice() * orderDetails.getQuantity();
					orderDetailsEntity.setTotalDiscountPrice((double) Math.ceil(totalDiscountPrice * 100) / 100);

					Double orderPrice = (Double) session.getAttribute("orderDiscountPrice");
					if (orderPrice == null) {
						orderPrice = 0.0;
					}
					orderPrice = orderPrice + orderDetailsEntity.getUnitPrice() * orderDetails.getQuantity();
					session.setAttribute("orderDiscountPrice", (double) Math.ceil(orderPrice * 100) / 100);

					// back ve` trang vua` roi`
					String referer = request.getHeader("Referer");
					return "redirect:" + referer;

				}
			}
		}

		list.add(orderDetails);
		session.setAttribute("listOrderDetails", list);

		Double orderPrice = (Double) session.getAttribute("orderDiscountPrice");
		if (orderPrice == null) {
			orderPrice = 0.0;
		}
		orderPrice = orderPrice + orderDetails.getTotalDiscountPrice();
		session.setAttribute("orderDiscountPrice", (double) Math.ceil(orderPrice * 100) / 100);

		// back ve` trang vua` roi`
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@PostMapping("/product/{id}/toCart")
	public String addToCart(@ModelAttribute(name = "orderDetails") @Valid OrderDetailsEntity orderDetails,
			BindingResult result, @PathVariable(name = "id") int id, HttpSession session) {

		if (result.hasErrors()) {

			System.out.println(result); // xem loi~ gi`
			session.setAttribute("errorOrderDetails", orderDetails);
			session.setAttribute("checkOrderDetails", "error");
			return "redirect:/customer/product/" + id;

		} else {

			List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");

			// set Product for OrderDetails by id
			orderDetails.setProduct(productService.findById(orderDetails.getProduct().getId()));

			if (list == null) {
				list = new ArrayList<OrderDetailsEntity>();
			} else if (list != null && list.size() > 0) {

				// check OrderDetails duplicate
				for (OrderDetailsEntity orderDetailsEntity : list) {
					int id1 = orderDetailsEntity.getProduct().getId();
					int id2 = orderDetails.getProduct().getId();
					double price1 = orderDetailsEntity.getUnitPrice();
					double price2 = orderDetails.getUnitPrice();
					if ((id1 == id2) && (price1 == price2)) {

						orderDetailsEntity.setQuantity(orderDetailsEntity.getQuantity() + orderDetails.getQuantity());

						double totalPrice = orderDetailsEntity.getTotalPrice()
								+ orderDetailsEntity.getProduct().getUnitPrice() * orderDetails.getQuantity();
						orderDetailsEntity.setTotalPrice((double) Math.ceil(totalPrice * 100) / 100);

						double totalDiscountPrice = orderDetailsEntity.getTotalDiscountPrice()
								+ orderDetailsEntity.getUnitPrice() * orderDetails.getQuantity();
						orderDetailsEntity.setTotalDiscountPrice((double) Math.ceil(totalDiscountPrice * 100) / 100);

						Double orderPrice = (Double) session.getAttribute("orderDiscountPrice");
						if (orderPrice == null) {
							orderPrice = 0.0;
						}
						orderPrice = orderPrice + orderDetailsEntity.getUnitPrice() * orderDetails.getQuantity();
						session.setAttribute("orderDiscountPrice", (double) Math.ceil(orderPrice * 100) / 100);
						return "redirect:/customer/product/" + id;
					}
				}

			}

			double totalPrice = orderDetails.getProduct().getUnitPrice() * orderDetails.getQuantity();
			orderDetails.setTotalPrice((double) Math.ceil(totalPrice * 100) / 100);

			double totalDiscountPrice = orderDetails.getUnitPrice() * orderDetails.getQuantity();
			orderDetails.setTotalDiscountPrice((double) Math.ceil(totalDiscountPrice * 100) / 100);

			list.add(orderDetails);
			session.setAttribute("listOrderDetails", list);

			Double orderPrice = (Double) session.getAttribute("orderDiscountPrice");
			if (orderPrice == null) {
				orderPrice = 0.0;
			}
			orderPrice = orderPrice + orderDetails.getTotalDiscountPrice();
			session.setAttribute("orderDiscountPrice", (double) Math.ceil(orderPrice * 100) / 100);
			// pass id to redirect
			return "redirect:/customer/product/" + id;

		}

	}

	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {

		List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
		if (list != null && list.size() > 0) {
			double orderPrice = 0.0;
			double orderDiscountPrice = 0.0;
			for (OrderDetailsEntity orderDetailsEntity : list) {
				orderPrice = orderPrice + orderDetailsEntity.getTotalPrice();
				orderDiscountPrice = orderDiscountPrice + orderDetailsEntity.getTotalDiscountPrice();
			}
			double savedPrice = orderPrice - orderDiscountPrice;
			session.setAttribute("orderDiscountPrice", (double) Math.ceil(orderDiscountPrice * 100) / 100);
			model.addAttribute("orderPrice", (double) Math.ceil(orderPrice * 100) / 100);
			model.addAttribute("orderDiscountPrice", (double) Math.ceil(orderDiscountPrice * 100) / 100);
			model.addAttribute("savedPrice", (double) Math.ceil(savedPrice * 100) / 100);
			model.addAttribute("listOrderDetails", list);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "cart";

		} else {
			return "redirect:/customer/index";
		}

	}

	@PostMapping("/cart/submitQuantity")
	public String submitQuantity(Model model, @RequestParam(name = "id") int id,
			@RequestParam(name = "quantity") int quantity, HttpSession session) {

		List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
		for (OrderDetailsEntity orderDetailsEntity : list) {
			if (orderDetailsEntity.getProduct().getId() == id) {
				orderDetailsEntity.setQuantity(quantity);

				double totalPrice = orderDetailsEntity.getProduct().getUnitPrice() * quantity;
				orderDetailsEntity.setTotalPrice((double) Math.ceil(totalPrice * 100) / 100);

				double totalDiscountPrice = orderDetailsEntity.getUnitPrice() * quantity;
				orderDetailsEntity.setTotalDiscountPrice((double) Math.ceil(totalDiscountPrice * 100) / 100);
				break;
			}
		}
		return "redirect:/customer/cart";

	}

	@GetMapping("/cart/deleteOrderDetails")
	public String deleteOrderDetails(Model model, @RequestParam(name = "id") int id, HttpSession session,
			HttpServletRequest request) {

		List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
		if (list != null && list.size() > 0) {
			for (OrderDetailsEntity orderDetailsEntity : list) {
				if (orderDetailsEntity.getProduct().getId() == id) {
					double orderPrice = (double) session.getAttribute("orderDiscountPrice");
					orderPrice = orderPrice - orderDetailsEntity.getTotalDiscountPrice();
					session.setAttribute("orderDiscountPrice", orderPrice);
					list.remove(orderDetailsEntity);
					break;
				}
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@GetMapping("/checkout")
	public String showCheckoutForm(Model model, HttpSession session, Authentication authentication) {

		Double orderDiscountPrice = (Double) session.getAttribute("orderDiscountPrice");
		if (orderDiscountPrice != null && orderDiscountPrice.doubleValue() > 0) {
			session.removeAttribute("orderDiscountPrice");
//			LocalDateTime dateCreated = LocalDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.of("+07")));

			if (authentication != null) {
				UserEntity mainCustomer = userService.findByEmail(authentication.getName());
				if (mainCustomer != null) {
					session.setAttribute("user", mainCustomer);
				}
			}
			UserEntity currentUser = (UserEntity) session.getAttribute("user");
			UserEntity mainCustomer = userService.findById(currentUser.getId());

			// set lai. list order cu~
			List<OrderEntity> listOld = new ArrayList<OrderEntity>();
			List<OrderEntity> listDB = orderService.getList();
			for (OrderEntity orderEntity : listDB) {
				if (orderEntity.getUser().getId() == mainCustomer.getId()) {
					listOld.add(orderEntity);
				}
			}
			mainCustomer.setOrders(listOld);

			List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
			session.removeAttribute("listOrderDetails");

			// vi` OrderDetails id chua chinh' xac' nen^ phai~ save de~ set id
			for (OrderDetailsEntity orderDetailsEntity : list) {
				orderDetailsService.save(orderDetailsEntity);
			}

			// get list da~ set id tu` db de~ luu vao` Order
			List<OrderDetailsEntity> listNullOrder = orderDetailsService.getListByNullOrder();
			OrderEntity order = new OrderEntity("unpaid", orderDiscountPrice, mainCustomer, listNullOrder);

			// set list order cho user
			List<OrderEntity> listOrder = (List<OrderEntity>) mainCustomer.getOrders();
			listOrder.add(order);
			mainCustomer.setOrders(listOrder);
			userService.save(currentUser);
			orderService.save(order);

			// set Order cho OrderDetails
			for (OrderDetailsEntity orderDetailsEntity : listNullOrder) {
				orderDetailsEntity.setOrder(order);
				orderDetailsService.save(orderDetailsEntity);
			}

			session.setAttribute("order", order);
			model.addAttribute("orderDiscountPrice", orderDiscountPrice);
			model.addAttribute("user", mainCustomer);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "checkout";
		}
		return "redirect:/customer/index";

	}

	@GetMapping("/checkout/{id}")
	public String checkoutOrder(Model model, @PathVariable(name = "id") int id, HttpSession session) {

		OrderEntity order = orderService.findById(id);
		session.setAttribute("order", order);
		model.addAttribute("orderDiscountPrice", order.getOrderPrice());
		model.addAttribute("user", order.getUser());
		List<GenderEntity> listGender = genderService.getList();
		model.addAttribute("listGender", listGender);
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "checkout";

	}

	@PostMapping("/doCheckout")
	public String doCheckout(Model model, @ModelAttribute(name = "user") @Valid UserEntity user, BindingResult result,
			@RequestParam(name = "payment") String payment, HttpSession session, Authentication authentication) {

		if (result.hasErrors()) {
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "checkout";
		} else {
			session.setAttribute("orderUser", user);
			// send info admin cho bo. phan. shipping

			if (payment != null && payment.equals("credit")) {

				OrderEntity order = (OrderEntity) session.getAttribute("order");
				List<CategoryEntity> listCategory = categoryService.getList();
				model.addAttribute("listCategory", listCategory);
				model.addAttribute("orderDiscountPrice", order.getOrderPrice());
				model.addAttribute("creditCard", new CreditCardEntity());
//				sendMailService.sendSimpleMessage(user.getEmail(), "Congratulations Email",
//						"Congratulations! You have successfully registered");
				return "creditCard";

			} else if (payment != null && payment.equals("cod")) {

				if (authentication != null) {
					UserEntity mainAdmin = userService.findByEmail(authentication.getName());
					if (mainAdmin != null) {
						session.setAttribute("user", mainAdmin);
					}
				}
				UserEntity currentUser = (UserEntity) session.getAttribute("user");
				OrderEntity order = (OrderEntity) session.getAttribute("order");
				model.addAttribute("user", currentUser);
				model.addAttribute("order", order);
				sendMailService.sendSimpleMessage(currentUser.getEmail(), "Congratulations Email",
						"Your orders are processed and shipped within 3-5 business days");
				return "congratulation";

			} else {
				return "checkout";
			}
		}

	}

	@PostMapping("/payment")
	public String doPayment(Model model, @ModelAttribute(name = "creditCard") @Valid CreditCardEntity creditCard,
			BindingResult result, HttpSession session, Authentication authentication) {

		if (result.hasErrors()) {
			System.out.println(result);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "creditCard";
		} else {

			OrderEntity order = (OrderEntity) session.getAttribute("order");

			// check credit card
			List<CreditCardEntity> listCreditCard = creditCardService.getList();
			for (CreditCardEntity creditCardEntity : listCreditCard) {
				if (creditCardEntity.getCardNumber().equals(creditCard.getCardNumber())
						&& creditCardEntity.getCvv().equals(creditCard.getCvv())
						&& creditCardEntity.getExpirationDate().equals(creditCard.getExpirationDate())
						&& creditCardEntity.getPin().equals(creditCard.getPin())) {
					if (creditCardEntity.getBalance().doubleValue() >= order.getOrderPrice().doubleValue()) {
						creditCardEntity.setBalance(creditCardEntity.getBalance() - order.getOrderPrice());
						creditCardService.save(creditCardEntity);
						List<OrderDetailsEntity> list = order.getOrderDetails();
						for (OrderDetailsEntity orderDetailsEntity : list) {
							ProductEntity product = orderDetailsEntity.getProduct();
							product.setInventory(product.getInventory() - orderDetailsEntity.getQuantity());
							productService.save(product);
						}
						order.setStatus("paid");
						orderService.save(order);
						session.removeAttribute("order");
					} else {				
						List<CategoryEntity> listCategory = categoryService.getList();
						model.addAttribute("listCategory", listCategory);
						model.addAttribute("errorMessage", "Your balance can't cover the full amount!!!");
						return "creditCard";
					}
				} else {
					List<CategoryEntity> listCategory = categoryService.getList();
					model.addAttribute("listCategory", listCategory);
					model.addAttribute("errorMessage", "Your bank wasn't able to verify your card!!!");
					return "creditCard";
				}
			}

			if (authentication != null) {
				UserEntity mainAdmin = userService.findByEmail(authentication.getName());
				if (mainAdmin != null) {
					session.setAttribute("user", mainAdmin);
				}
			}
			UserEntity currentUser = (UserEntity) session.getAttribute("user");
			model.addAttribute("user", currentUser);
			model.addAttribute("order", order);
			sendMailService.sendSimpleMessage(currentUser.getEmail(), "Congratulations Email",
					"Congratulations! Your order is accepted!");
			return "congratulation";

		}

	}

//	@GetMapping("/allOrders")
//	public String viewAllOrderss(Model model, HttpSession session, Authentication authentication) {
//
//		if (authentication != null) {
//			UserEntity mainCustomer = userService.findByEmail(authentication.getName());
//			if (mainCustomer != null) {
//				session.setAttribute("user", mainCustomer);
//			}
//		}
//		UserEntity currentUser = (UserEntity) session.getAttribute("user");
//		UserEntity mainCustomer = userService.findById(currentUser.getId());
//		List<OrderEntity> list = mainCustomer.getOrders();
//		List<OrderEntity> listOrder = new ArrayList<OrderEntity>();
//		for (OrderEntity orderEntity : list) {
//			if (!orderEntity.getStatus().equals("canceled")) {
//				listOrder.add(orderEntity);
//			}
//		}
//		model.addAttribute("listOrder", listOrder);
//		List<CategoryEntity> listCategory = categoryService.getList();
//		model.addAttribute("listCategory", listCategory);
//		return "listOrder";
//
//	}

//	@GetMapping(value = "/deleteOrder")
//	public String deleteOrder(Model model, @RequestParam(name = "id") int id, HttpSession session,
//			Authentication authentication) {
//
//		if (authentication != null) {
//			UserEntity mainCustomer = userService.findByEmail(authentication.getName());
//			if (mainCustomer != null) {
//				session.setAttribute("user", mainCustomer);
//			}
//		}
//		UserEntity user = (UserEntity) session.getAttribute("user");
//		UserEntity mainCustomer = userService.findById(user.getId());
//		List<OrderEntity> list = mainCustomer.getOrders();
//		for (OrderEntity orderEntity : list) {
//			if (orderEntity.getId() == id && orderEntity.getStatus().equals("unpaid")) {
//				orderEntity.setStatus("canceled");
//				orderService.save(orderEntity);
//				break;
//			}
//		}
//		return "redirect:/customer/allOrders";
//
//	}

	@GetMapping("/allOrders")
	public String viewAllOrders(Model model, HttpSession session, Authentication authentication) {

		if (authentication != null) {
			UserEntity mainCustomer = userService.findByEmail(authentication.getName());
			if (mainCustomer != null) {
				session.setAttribute("user", mainCustomer);
			}
		}
		UserEntity currentUser = (UserEntity) session.getAttribute("user");
		UserEntity mainCustomer = userService.findById(currentUser.getId());

		model.addAttribute("currentPage", FIRST_PAGE);

		if (session.getAttribute("search") != null && session.getAttribute("search").equals("on")) {
			model.addAttribute("listOrder", session.getAttribute("listSearch"));
			model.addAttribute("totalPages", session.getAttribute("totalPages"));
			model.addAttribute("totalItems", session.getAttribute("totalItems"));
			session.removeAttribute("listSearch");
			session.removeAttribute("totalPages");
			session.removeAttribute("totalItems");
			session.removeAttribute("search");
		} else {
			model.addAttribute("listOrder",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, FIRST_PAGE).getContent());
			model.addAttribute("totalPages",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, FIRST_PAGE).getTotalPages());
			model.addAttribute("totalItems",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, FIRST_PAGE).getTotalElements());
			session.removeAttribute("keyword");
		}

		// Cai nay la setting path cua cai @GetMapping("/allOrders")
		model.addAttribute("path", "customer/allOrders");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listOrder";

	}

	@GetMapping("/allOrders/{page}")
	public String getOrdersPaging(Model model, @PathVariable(name = "page") int pageNum, HttpSession session,
			Authentication authentication) {

		if (authentication != null) {
			UserEntity mainCustomer = userService.findByEmail(authentication.getName());
			if (mainCustomer != null) {
				session.setAttribute("user", mainCustomer);
			}
		}
		UserEntity currentUser = (UserEntity) session.getAttribute("user");
		UserEntity mainCustomer = userService.findById(currentUser.getId());

		model.addAttribute("currentPage", pageNum);
		String keyword = (String) session.getAttribute("keyword");

		if (keyword != null && !keyword.equals("canceled")) {
			model.addAttribute("listOrder",
					orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, pageNum).getContent());
			model.addAttribute("totalPages",
					orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, pageNum).getTotalPages());
			model.addAttribute("totalItems",
					orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, pageNum).getTotalElements());
		} else {
			model.addAttribute("listOrder",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, pageNum).getContent());
			model.addAttribute("totalPages",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, pageNum).getTotalPages());
			model.addAttribute("totalItems",
					orderService.getOrderPageByStatus(mainCustomer.getId(), null, pageNum).getTotalElements());
		}

		// Cai nay la setting path cua cai @GetMapping("/allOrders")
		model.addAttribute("path", "customer/allOrders");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listOrder";

	}

	@GetMapping("/allOrders/search")
	public String searchOrder(Model model, @RequestParam(name = "keyword") String keyword, HttpSession session,
			Authentication authentication) {

		if (authentication != null) {
			UserEntity mainCustomer = userService.findByEmail(authentication.getName());
			if (mainCustomer != null) {
				session.setAttribute("user", mainCustomer);
			}
		}
		UserEntity currentUser = (UserEntity) session.getAttribute("user");
		UserEntity mainCustomer = userService.findById(currentUser.getId());

		List<OrderEntity> list = orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, FIRST_PAGE)
				.getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, FIRST_PAGE).getTotalPages();
		long totalItems = orderService.getOrderPageByStatus(mainCustomer.getId(), keyword, FIRST_PAGE)
				.getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("keyword", keyword);
		return "redirect:/customer/allOrders";

	}

	@GetMapping(value = "/deleteOrder")
	public String deleteOrder(Model model, @RequestParam(name = "id") int id, HttpSession session,
			HttpServletRequest request) {

		OrderEntity orderEntity = orderService.findById(id);
		orderEntity.setStatus("canceled");
		orderService.save(orderEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

}
