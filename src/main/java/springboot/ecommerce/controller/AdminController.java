package springboot.ecommerce.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import springboot.ecommerce.entity.CategoryEntity;
import springboot.ecommerce.entity.CreditCardEntity;
import springboot.ecommerce.entity.DiscountEntity;
import springboot.ecommerce.entity.GenderEntity;
import springboot.ecommerce.entity.OrderDetailsEntity;
import springboot.ecommerce.entity.OrderEntity;
import springboot.ecommerce.entity.ProductEntity;
import springboot.ecommerce.entity.RoleEntity;
import springboot.ecommerce.entity.UserEntity;
import springboot.ecommerce.service.CategoryService;
import springboot.ecommerce.service.CreditCardService;
import springboot.ecommerce.service.DiscountService;
import springboot.ecommerce.service.GenderService;
import springboot.ecommerce.service.OrderDetailsService;
import springboot.ecommerce.service.OrderService;
import springboot.ecommerce.service.ProductService;
import springboot.ecommerce.service.RoleService;
import springboot.ecommerce.service.SendMailService;
import springboot.ecommerce.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	public static final String saveDir = "resources\\images";

	private static int FIRST_PAGE = 1;

	@Autowired
	public ProductService productService;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public DiscountService discountService;

	@Autowired
	public RoleService roleService;

	@Autowired
	public GenderService genderService;

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

//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//
//		session.removeAttribute("primaryAdmin");
//		session.removeAttribute("tempAdmin");
//		session.removeAttribute("errorAdmin");
//		session.removeAttribute("checkAdmin");
//		session.removeAttribute("checkBox");
//		session.removeAttribute("tempCategory");
//		session.removeAttribute("checkCategory");
//		session.removeAttribute("tempDiscount");
//		session.removeAttribute("checkDiscount");
//		session.removeAttribute("tempProduct");
//		session.removeAttribute("checkProduct");
//		session.removeAttribute("tempCustomer");
//		session.removeAttribute("checkCustomer");
//		session.removeAttribute("name");
//		session.removeAttribute("category");
//		return "redirect:/index";
//
//	}

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
	public String viewHomepage(Model model, HttpSession session, Authentication authentication) {

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
		model.addAttribute("path", "admin/index");
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		if (authentication != null) {
			UserEntity mainUser = userService.findByEmail(authentication.getName());
			if (mainUser != null) {
				session.setAttribute("user", mainUser);
			}
		}
		return "index";
		// return ve` trang index.jsp chu' ko phai~ duong` dan~ /index
		// muon' ve` duong` dan~ /index thi` return "redirect:/index"

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
		return "redirect:/admin/index";

	}

	@GetMapping("/index/category/{id}")
	public String showProductByCategory(@PathVariable(name = "id") int category, HttpSession session) {

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
		return "redirect:/admin/index";

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
		return "redirect:/admin/index";

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
		model.addAttribute("path", "admin/index");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "index";
	}

	// --------------- ADMIN ---------------

	@GetMapping("/info")
	public String showAdminInfo(Model model, @ModelAttribute(name = "user") @Valid UserEntity user,
			BindingResult result, HttpSession session, Authentication authentication) {

		if (session.getAttribute("checkAdmin") != null && session.getAttribute("checkAdmin").equals("ok")) {

			UserEntity tempAdmin = (UserEntity) session.getAttribute("tempAdmin");
			model.addAttribute("user", tempAdmin);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("checkAdmin");
			session.removeAttribute("message");
			return "admin";

		} else if (session.getAttribute("checkAdmin") != null && session.getAttribute("checkAdmin").equals("error")) {

			UserEntity errorAdmin = (UserEntity) session.getAttribute("errorAdmin");
			model.addAttribute("user", errorAdmin);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("errorAdmin");
			session.removeAttribute("checkAdmin");
			session.removeAttribute("errorMessage");
			return "admin";

		} else {

			if (authentication != null) {
				UserEntity mainAdmin = userService.findByEmail(authentication.getName());
				if (mainAdmin != null) {
					session.setAttribute("user", mainAdmin);
				}
			}
			UserEntity currentUser = (UserEntity) session.getAttribute("user");
			UserEntity mainAdmin = userService.findById(currentUser.getId());
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("user", mainAdmin);
			return "admin";

		}

	}

	@GetMapping("/form")
	public String showAdminForm(Model model, HttpSession session) {

		model.addAttribute("user", new UserEntity());
		List<GenderEntity> listGender = genderService.getList();
		model.addAttribute("listGender", listGender);
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("message", session.getAttribute("message"));
		session.removeAttribute("message");
		return "admin";

	}

	@PostMapping("/submit")
	public String submitAdmin(Model model, @ModelAttribute(name = "user") @Valid UserEntity user, BindingResult result,
			HttpSession session, Authentication authentication) {

		if (result.hasErrors()) {
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "admin";
		} else {

			// check mail User
			List<UserEntity> listUser = userService.getList();
			if (listUser != null && !listUser.isEmpty()) {
				for (UserEntity userEntity : listUser) {
					if (userEntity.getEmail().equalsIgnoreCase(user.getEmail()) && userEntity.getId() != user.getId()) {
						session.setAttribute("errorAdmin", user);
						session.setAttribute("checkAdmin", "error");
						session.setAttribute("errorMessage", "This email address is NOT available!!!");
						return "redirect:/admin/info";
					}
				}
			}

			// Set role
			List<RoleEntity> listRole = roleService.getList();
			listRole.removeIf(n -> !(n.getName().equalsIgnoreCase("ROLE_ADMIN")));
			user.setRoles(listRole);

			// admin chua save se~ co' id = 0
			if (user.getId() == 0) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userService.save(user);
				session.setAttribute("message", "Your admin has been created!");
				return "redirect:/admin/form";
			}

			// encode password
			UserEntity oldUser = userService.findById(user.getId());
			if (!user.getPassword().equals(oldUser.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			// set lai gia tri cho session user chinh' la` mainAdmin
			if (authentication != null) {
				UserEntity mainAdmin = userService.findByEmail(authentication.getName());
				if (mainAdmin != null) {
					session.setAttribute("user", mainAdmin);
				}
			}
			UserEntity mainAdmin = (UserEntity) session.getAttribute("user");
			if (mainAdmin.getId() == user.getId()) {
				session.setAttribute("user", user);
			}

			// save admin
			userService.save(user);
			session.setAttribute("tempAdmin", user);
			session.setAttribute("checkAdmin", "ok");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/admin/info";

		}

	}

//	@GetMapping("/updateAdminForm")
//	public String updateAdminForm(@RequestParam(name = "id") int id, HttpSession session) {
//
//		UserEntity user = userService.findById(id);
//		session.setAttribute("tempAdmin", user);
//		session.setAttribute("checkAdmin", "ok");
//		return "redirect:/admin/info";
//
//	}

	@GetMapping(value = "/deleteUser")
	public String deleteUser(Model model, @RequestParam(name = "id") int id, HttpServletRequest request) {

		if (id == 1) {
			return "redirect:/admin/allUsers";
		}
		UserEntity userEntity = userService.findById(id);
		List<OrderEntity> listOrder = userEntity.getOrders();
		if (listOrder != null && listOrder.size() > 0) {
			for (OrderEntity orderEntity : listOrder) {
				List<OrderDetailsEntity> listOrderDetails = orderEntity.getOrderDetails();
				for (OrderDetailsEntity orderDetailsEntity : listOrderDetails) {
					orderDetailsService.delete(orderDetailsEntity);
				}
				orderService.delete(orderEntity);
			}
		}
		userService.delete(userEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	// --------------- CATEGORY ---------------

	@GetMapping("/category")
	public String showCategoryForm(Model model, HttpSession session) {

		if (session.getAttribute("checkCategory") != null && session.getAttribute("checkCategory").equals("!")) {

			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			CategoryEntity category = (CategoryEntity) session.getAttribute("tempCategory");
			model.addAttribute("category", category);
			model.addAttribute("message", session.getAttribute("message"));
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkCategory");
			session.removeAttribute("message");
			session.removeAttribute("errorMessage");
			return "category";

		} else {

			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("category", new CategoryEntity());
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("message");
			return "category";

		}

	}

	@GetMapping("/allCategories")
	public String viewAllCategories(Model model) {

		List<CategoryEntity> list = categoryService.getList();
		if (list == null || list.isEmpty()) {
			return "redirect:/admin/category";
		}
		model.addAttribute("listCategory", list);
		return "listCategory";

	}

	@GetMapping("/updateCategory")
	public String updateCategory(@RequestParam(name = "id") int id, HttpSession session) {

		CategoryEntity category = categoryService.findById(id);
		session.setAttribute("tempCategory", category);
		session.setAttribute("checkCategory", "!");
		return "redirect:/admin/category";

	}

	@PostMapping("/submitCategory")
	public String submitCategory(Model model, @ModelAttribute(name = "category") @Valid CategoryEntity category,
			BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "category";
		} else {

			List<CategoryEntity> listCategory = categoryService.getList();
			if (listCategory != null && !listCategory.isEmpty()) {
				for (CategoryEntity categoryEntity : listCategory) {
					if ((categoryEntity.getName().equalsIgnoreCase(category.getName()))
							&& (categoryEntity.getId() != category.getId())) {
						session.setAttribute("tempCategory", category);
						session.setAttribute("checkCategory", "!");
						session.setAttribute("errorMessage", "This category name is NOT available!!!");
						return "redirect:/admin/category";
					}
				}
			}

			// category chua save se~ co' id = 0
			if (category.getId() == 0) {
				categoryService.save(category);
				session.setAttribute("message", "Your category has been created!");
				return "redirect:/admin/category";
			}

			categoryService.save(category);
			session.setAttribute("tempCategory", category);
			session.setAttribute("checkCategory", "!");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/admin/category";

		}

	}

	@GetMapping(value = "/deleteCategory")
	public String deleteCategory(Model model, @RequestParam(name = "id") int id) {

		CategoryEntity categoryEntity = categoryService.findById(id);
		List<ProductEntity> listProduct = categoryEntity.getProducts();
		if (listProduct != null && listProduct.size() > 0) {
			for (ProductEntity productEntity : listProduct) {
				List<OrderDetailsEntity> listOrderDetails = productEntity.getOrderDetails();
				if (listOrderDetails != null && listOrderDetails.size() > 0) {
					for (OrderDetailsEntity orderDetailsEntity : listOrderDetails) {
						OrderEntity tempOrder = orderDetailsEntity.getOrder();
						for (OrderDetailsEntity orderDetails : tempOrder.getOrderDetails()) {
							orderDetailsService.delete(orderDetails);
						}
						orderService.delete(tempOrder);
					}
				}
				productService.delete(productEntity);
			}
		}
		categoryService.delete(categoryEntity);
		return "redirect:/admin/allCategories";

	}

	// --------------- DISCOUNT ---------------

	@GetMapping("/discount")
	public String showDiscountForm(Model model, HttpSession session) {

		if (session.getAttribute("checkDiscount") != null && session.getAttribute("checkDiscount").equals("!")) {

			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			DiscountEntity discount = (DiscountEntity) session.getAttribute("tempDiscount");
			model.addAttribute("discount", discount);
			model.addAttribute("message", session.getAttribute("message"));
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkDiscount");
			session.removeAttribute("message");
			session.removeAttribute("errorMessage");
			return "discount";

		} else {

			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("discount", new DiscountEntity());
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("message");
			return "discount";

		}

	}

	@GetMapping("/allDiscounts")
	public String viewAllDiscounts(Model model) {

		List<DiscountEntity> list = discountService.getList();
		if (list != null && list.isEmpty()) {
			return "redirect:/admin/discount";
		}
		model.addAttribute("listDiscount", list);
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "listDiscount";

	}

	@GetMapping("/updateDiscount")
	public String updateDiscount(@RequestParam(name = "id") int id, HttpSession session) {

		DiscountEntity discount = discountService.findById(id);
		session.setAttribute("tempDiscount", discount);
		session.setAttribute("checkDiscount", "!");
		return "redirect:/admin/discount";

	}

	@PostMapping("/submitDiscount")
	public String submitDiscount(Model model, @ModelAttribute(name = "discount") @Valid DiscountEntity discount,
			BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "discount";
		} else {

			List<DiscountEntity> listDiscount = discountService.getList();
			if (listDiscount != null && !listDiscount.isEmpty()) {
				for (DiscountEntity discountEntity : listDiscount) {
					if ((discountEntity.getName().equalsIgnoreCase(discount.getName()))
							&& (discountEntity.getId() != discount.getId())) {
						session.setAttribute("tempDiscount", discount);
						session.setAttribute("checkDiscount", "!");
						session.setAttribute("errorMessage", "This discount name is NOT available!!!");
						return "redirect:/admin/discount";
					}
				}
			}

			// discount chua save se~ co' id = 0
			if (discount.getId() == 0) {
				discountService.save(discount);
				session.setAttribute("message", "Your discount has been created!");
				return "redirect:/admin/discount";
			}

			discountService.save(discount);
			session.setAttribute("tempDiscount", discount);
			session.setAttribute("checkDiscount", "!");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/admin/discount";

		}

	}

	@GetMapping(value = "/deleteDiscount")
	public String deleteDiscount(Model model, @RequestParam(name = "id") int id) {

		DiscountEntity discountEntity = discountService.findById(id);
		List<ProductEntity> listProduct = discountEntity.getProducts();
		if (listProduct != null && listProduct.size() > 0) {
			for (ProductEntity productEntity : listProduct) {
				List<OrderDetailsEntity> listOrderDetails = productEntity.getOrderDetails();
				if (listOrderDetails != null && listOrderDetails.size() > 0) {
					for (OrderDetailsEntity orderDetailsEntity : listOrderDetails) {
						OrderEntity tempOrder = orderDetailsEntity.getOrder();
						for (OrderDetailsEntity orderDetails : tempOrder.getOrderDetails()) {
							orderDetailsService.delete(orderDetails);
						}
						orderService.delete(tempOrder);
					}
				}
				productService.delete(productEntity);
			}
		}
		discountService.delete(discountEntity);
		return "redirect:/admin/allDiscounts";

	}

	// --------------- PRODUCT ---------------

	@GetMapping("/product")
	public String showProductForm(Model model, HttpSession session) {

		if (session.getAttribute("checkProduct") != null && session.getAttribute("checkProduct").equals("!")) {

			ProductEntity product = (ProductEntity) session.getAttribute("tempProduct");
			List<CategoryEntity> listCategory = categoryService.getList();
			List<DiscountEntity> listDiscount = discountService.getList();
			model.addAttribute("product", product);
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("listDiscount", listDiscount);
			model.addAttribute("message", session.getAttribute("message"));
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkProduct");
			session.removeAttribute("message");
			session.removeAttribute("errorMessage");
			return "product";

		} else {

			List<CategoryEntity> listCategory = categoryService.getList();
			List<DiscountEntity> listDiscount = discountService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("listDiscount", listDiscount);
			model.addAttribute("product", new ProductEntity());
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("message");
			return "product";

		}

	}

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
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("orderDetails", new OrderDetailsEntity());
			return "productDetails";

		}

	}

	@GetMapping("/allProducts/search")
	public String searchProduct(Model model, @RequestParam(name = "name") String name,
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
		return "redirect:/admin/allProducts";

	}

	@GetMapping("/allProducts")
	public String viewAllProducts(Model model, HttpSession session) {

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
		}

		// Cai nay la setting path cua cai @GetMapping("/allProducts")
		model.addAttribute("path", "admin/allProducts");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listProduct";

	}

	@GetMapping("/allProducts/{page}")
	public String getProductsPaging(Model model, @PathVariable(name = "page") int pageNum, HttpSession session) {

		model.addAttribute("currentPage", pageNum);
		String name = (String) session.getAttribute("name");
		Integer category = (Integer) session.getAttribute("category");

		if (name != null && category != null) {
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

		// Cai nay la setting path cua cai @GetMapping("/allProducts")
		model.addAttribute("path", "admin/allProducts");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listProduct";

	}

	@GetMapping("/allProducts/updateProduct")
	public String updateProduct(@RequestParam(name = "id") int id, HttpSession session) {

		ProductEntity product = productService.findById(id);
		session.setAttribute("tempProduct", product);
		session.setAttribute("checkProduct", "!");
		return "redirect:/admin/product";

	}

	@GetMapping("/allProducts/deleteProduct")
	public String deleteProduct(Model model, @RequestParam(name = "id") int id, HttpServletRequest request) {

		ProductEntity productEntity = productService.findById(id);
		List<OrderDetailsEntity> listOrderDetails = productEntity.getOrderDetails();
		if (listOrderDetails != null && listOrderDetails.size() > 0) {
			for (OrderDetailsEntity orderDetailsEntity : listOrderDetails) {
				OrderEntity tempOrder = orderDetailsEntity.getOrder();
				for (OrderDetailsEntity orderDetails : tempOrder.getOrderDetails()) {
					orderDetailsService.delete(orderDetails);
				}
				orderService.delete(tempOrder);
			}
		}
		productService.delete(productEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@PostMapping("/allProducts/submitInventory")
	public String submitInventory(Model model, @RequestParam(name = "id") int id,
			@RequestParam(name = "inventory") int inventory, HttpSession session, HttpServletRequest request) {

		ProductEntity productEntity = productService.findById(id);
		productEntity.setInventory(inventory);
		productService.save(productEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@PostMapping("/submitProduct")
	public String submitProduct(Model model, @ModelAttribute(name = "product") @Valid ProductEntity product,
			BindingResult result, HttpSession session, HttpServletRequest request) {

		if (result.hasErrors()) {
			System.out.println(result);
			List<CategoryEntity> listCategory = categoryService.getList();
			List<DiscountEntity> listDiscount = discountService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("listDiscount", listDiscount);
			return "product";
		} else {

			List<ProductEntity> listProduct = productService.getList();
			if (listProduct != null && !listProduct.isEmpty()) {
				for (ProductEntity productEntity : listProduct) {
					if ((productEntity.getName().equalsIgnoreCase(product.getName()))
							&& (productEntity.getId() != product.getId())) {
						session.setAttribute("tempProduct", product);
						session.setAttribute("checkProduct", "!");
						session.setAttribute("errorMessage", "This product name is NOT available!!!");
						return "redirect:/admin/product";
					}
				}
			}

			// Upload file
			String path = request.getServletContext().getRealPath("");
			System.out.println(path);
			// D:\eclipse-workspace\springboot_ecommerce\src\main\webapp\
			
			String savePath = path + saveDir;
			try {

				MultipartFile multipartFile = product.getMultipartFile();
				String fileName = multipartFile.getOriginalFilename();
				if (!fileName.isEmpty()) {
					
					// tao. object File de~ chua' duong` dan~ + file name vua` upload
					File file = new File(savePath, fileName);
					
					// luu file vao` duong` dan~
					multipartFile.transferTo(file);
					
					// set path image cho product
					product.setPath("resources/images/" + fileName);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// product chua save se~ co' id = 0
			if (product.getId() == 0) {
				productService.save(product);
				session.setAttribute("message", "Your product has been created!");
				return "redirect:/admin/product";
			}

			// set lai. path cu~
			if (product.getPath() == null) {
				ProductEntity pathProduct = productService.findById(product.getId());
				product.setPath(pathProduct.getPath());
			}
			productService.save(product);
			session.setAttribute("tempProduct", product);
			session.setAttribute("checkProduct", "!");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/admin/product";

		}

	}

	// --------------- CART ---------------

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
			return "redirect:/admin/product/" + id;

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
						return "redirect:/admin/product/" + id;
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
			return "redirect:/admin/product/" + id;

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
			return "redirect:/admin/index";
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
		return "redirect:/admin/cart";

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
				UserEntity mainAdmin = userService.findByEmail(authentication.getName());
				if (mainAdmin != null) {
					session.setAttribute("user", mainAdmin);
				}
			}
			UserEntity currentUser = (UserEntity) session.getAttribute("user");
			UserEntity mainAdmin = userService.findById(currentUser.getId());

			// set lai. list order cu~
			List<OrderEntity> listOld = new ArrayList<OrderEntity>();
			List<OrderEntity> listDB = orderService.getList();
			for (OrderEntity orderEntity : listDB) {
				if (orderEntity.getUser().getId() == mainAdmin.getId()) {
					listOld.add(orderEntity);
				}
			}
			mainAdmin.setOrders(listOld);

			List<OrderDetailsEntity> list = (List<OrderDetailsEntity>) session.getAttribute("listOrderDetails");
			session.removeAttribute("listOrderDetails");

			// vi` OrderDetails id chua chinh' xac' nen^ phai~ save de~ set id
			for (OrderDetailsEntity orderDetailsEntity : list) {
				orderDetailsService.save(orderDetailsEntity);
			}

			// get list OrderDetails chua co' id order
			List<OrderDetailsEntity> listNullOrder = orderDetailsService.getListByNullOrder();
			OrderEntity order = new OrderEntity("unpaid", orderDiscountPrice, mainAdmin, listNullOrder);

			// set list order cho user
			List<OrderEntity> listOrder = (List<OrderEntity>) mainAdmin.getOrders();
			listOrder.add(order);
			mainAdmin.setOrders(listOrder);
			userService.save(currentUser);
			orderService.save(order);

			// set Order cho OrderDetails
			for (OrderDetailsEntity orderDetailsEntity : listNullOrder) {
				orderDetailsEntity.setOrder(order);
				orderDetailsService.save(orderDetailsEntity);
			}

			session.setAttribute("order", order);
			model.addAttribute("orderDiscountPrice", orderDiscountPrice);
			model.addAttribute("user", mainAdmin);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "checkout";
		}
		return "redirect:/admin/index";

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

	// --------------- ORDER ---------------

	@GetMapping("/allOrders")
	public String viewAllOrders(Model model, HttpSession session) {

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
			model.addAttribute("listOrder", orderService.getOrdersPaging(FIRST_PAGE).getContent());
			model.addAttribute("totalPages", orderService.getOrdersPaging(FIRST_PAGE).getTotalPages());
			model.addAttribute("totalItems", orderService.getOrdersPaging(FIRST_PAGE).getTotalElements());
			session.removeAttribute("keyword");
		}

		// Cai nay la setting path cua cai @GetMapping("/allOrders")
		model.addAttribute("path", "admin/allOrders");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listOrder";

	}

	@GetMapping("/allOrders/{page}")
	public String getOrdersPaging(Model model, @PathVariable(name = "page") int pageNum, HttpSession session) {

		model.addAttribute("currentPage", pageNum);
		String keyword = (String) session.getAttribute("keyword");

		if (keyword != null) {
			model.addAttribute("listOrder", orderService.getOrderPageBySearch(keyword, pageNum).getContent());
			model.addAttribute("totalPages", orderService.getOrderPageBySearch(keyword, pageNum).getTotalPages());
			model.addAttribute("totalItems", orderService.getOrderPageBySearch(keyword, pageNum).getTotalElements());
		} else {
			model.addAttribute("listOrder", orderService.getOrdersPaging(pageNum).getContent());
			model.addAttribute("totalPages", orderService.getOrdersPaging(pageNum).getTotalPages());
			model.addAttribute("totalItems", orderService.getOrdersPaging(pageNum).getTotalElements());
		}

		// Cai nay la setting path cua cai @GetMapping("/allOrders")
		model.addAttribute("path", "admin/allOrders");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "listOrder";

	}

	@PostMapping("/allOrders/submitStatus")
	public String submitStatu(@RequestParam(name = "id") int id, @RequestParam(name = "status") String status,
			HttpServletRequest request) {

		OrderEntity orderEntity = orderService.findById(id);
		orderEntity.setStatus(status);
		orderService.save(orderEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@GetMapping("/allOrders/search")
	public String searchOrder(Model model, @RequestParam(name = "keyword") String keyword, HttpSession session) {

		List<OrderEntity> list = orderService.getOrderPageBySearch(keyword, FIRST_PAGE).getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = orderService.getOrderPageBySearch(keyword, FIRST_PAGE).getTotalPages();
		long totalItems = orderService.getOrderPageBySearch(keyword, FIRST_PAGE).getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("keyword", keyword);
		return "redirect:/admin/allOrders";

	}

	@GetMapping(value = "/deleteOrder")
	public String deleteOrder(Model model, @RequestParam(name = "id") int id, HttpServletRequest request) {

		OrderEntity orderEntity = orderService.findById(id);
		List<OrderDetailsEntity> list = orderEntity.getOrderDetails();
		for (OrderDetailsEntity orderDetailsEntity : list) {
			orderDetailsService.delete(orderDetailsEntity);
		}
		orderService.delete(orderEntity);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	// --------------- CUSTOMER ---------------

	@GetMapping("/customer")
	public String showCustomerForm(Model model, HttpSession session) {

		if (session.getAttribute("checkCustomer") != null && session.getAttribute("checkCustomer").equals("!")) {

			UserEntity customer = (UserEntity) session.getAttribute("tempCustomer");
			List<GenderEntity> listGender = genderService.getList();
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("user", customer);
			model.addAttribute("listGender", listGender);
			model.addAttribute("message", session.getAttribute("message"));
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkCustomer");
			session.removeAttribute("message");
			session.removeAttribute("errorMessage");
			return "customer";

		} else {
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("user", new UserEntity());
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("message");
			return "customer";
		}

	}

	@GetMapping("/allUsers")
	public String viewAllUsers(Model model, HttpSession session) {

		model.addAttribute("currentPage", FIRST_PAGE);

		if (session.getAttribute("search") != null && session.getAttribute("search").equals("on")) {
			model.addAttribute("listUser", session.getAttribute("listSearch"));
			model.addAttribute("totalPages", session.getAttribute("totalPages"));
			model.addAttribute("totalItems", session.getAttribute("totalItems"));
			session.removeAttribute("listSearch");
			session.removeAttribute("totalPages");
			session.removeAttribute("totalItems");
			session.removeAttribute("search");
		} else {
			model.addAttribute("listUser", userService.getUsersPaging(FIRST_PAGE).getContent());
			model.addAttribute("totalPages", userService.getUsersPaging(FIRST_PAGE).getTotalPages());
			model.addAttribute("totalItems", userService.getUsersPaging(FIRST_PAGE).getTotalElements());
			session.removeAttribute("keyword");
			session.removeAttribute("role");
		}

		// Cai nay la setting path cua cai @GetMapping("/allProducts")
		model.addAttribute("path", "admin/allUsers");
		List<RoleEntity> listRole = roleService.getList();
		model.addAttribute("listRole", listRole);
		return "listUser";

	}

	@GetMapping("/allUsers/search")
	public String searchUser(Model model, @RequestParam(name = "keyword") String keyword,
			@RequestParam(name = "role") int role, HttpSession session) {

		List<UserEntity> list = userService.getUserPageBySearch(keyword, role, FIRST_PAGE).getContent();
		// neu' kiem' ko ra thi` page tra~ ve` ko phan` tu~ (not null & size = 0)

		int totalPages = userService.getUserPageBySearch(keyword, role, FIRST_PAGE).getTotalPages();
		long totalItems = userService.getUserPageBySearch(keyword, role, FIRST_PAGE).getTotalElements();
		session.setAttribute("listSearch", list);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("totalItems", totalItems);
		session.setAttribute("search", "on");
		session.setAttribute("keyword", keyword);
		session.setAttribute("role", role);
		return "redirect:/admin/allUsers";

	}

	@GetMapping("/allUsers/{page}")
	public String getUsersPaging(Model model, @PathVariable(name = "page") int pageNum, HttpSession session) {

		model.addAttribute("currentPage", pageNum);
		String keyword = (String) session.getAttribute("keyword");
		Integer role = (Integer) session.getAttribute("role");

		if (keyword != null && role != null) {
			model.addAttribute("listUser", userService.getUserPageBySearch(keyword, role, pageNum).getContent());
			model.addAttribute("totalPages", userService.getUserPageBySearch(keyword, role, pageNum).getTotalPages());
			model.addAttribute("totalItems",
					userService.getUserPageBySearch(keyword, role, pageNum).getTotalElements());
		} else {
			model.addAttribute("listUser", userService.getUsersPaging(pageNum).getContent());
			model.addAttribute("totalPages", userService.getUsersPaging(pageNum).getTotalPages());
			model.addAttribute("totalItems", userService.getUsersPaging(pageNum).getTotalElements());
		}

		// Cai nay la setting path cua cai @GetMapping("/allProducts")
		model.addAttribute("path", "admin/allUsers");
		List<RoleEntity> listRole = roleService.getList();
		model.addAttribute("listRole", listRole);
		return "listUser";

	}

	@PostMapping("/allUsers/submitRole")
	public String submitRole(Model model, @RequestParam(name = "id") int id, @RequestParam(name = "role") int role,
			HttpSession session, HttpServletRequest request) {

		List<RoleEntity> listRole = roleService.getList();
		listRole.removeIf(n -> !(n.getId() == role));
		UserEntity user = userService.findById(id);
		List<RoleEntity> listRoleUser = user.getRoles();
		listRoleUser.clear();
		listRoleUser.addAll(listRole);
		userService.save(user);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;

	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam(name = "id") int id, HttpSession session) {

		UserEntity user = userService.findById(id);
		for (RoleEntity roleEntity : user.getRoles()) {
			if (roleEntity.getName().equals("ROLE_ADMIN")) {
				session.setAttribute("tempAdmin", user);
				session.setAttribute("checkAdmin", "ok");
				return "redirect:/admin/info";
			}
		}
		session.setAttribute("tempCustomer", user);
		session.setAttribute("checkCustomer", "!");
		return "redirect:/admin/customer";

	}

	@PostMapping("/submitCustomer")
	public String submitCustomer(Model model, @ModelAttribute(name = "user") @Valid UserEntity user,
			BindingResult result, HttpSession session) {

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
						session.setAttribute("tempCustomer", user);
						session.setAttribute("checkCustomer", "!");
						session.setAttribute("errorMessage", "This email address is NOT available!!!");
						return "redirect:/admin/customer";
					}
				}
			}

			// Set role
			List<RoleEntity> listRole = roleService.getList();
			listRole.removeIf(n -> !(n.getName().equalsIgnoreCase("ROLE_CUSTOMER")));
			user.setRoles(listRole);

			// encode password
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// user chua save se~ co' id = 0
			if (user.getId() == 0) {
				userService.save(user);
				session.setAttribute("message", "Your customer has been created!");
				return "redirect:/admin/customer";
			}

			// save customer
			userService.save(user);
			session.setAttribute("tempCustomer", user);
			session.setAttribute("checkCustomer", "!");
			session.setAttribute("message", "Your information has been submitted!");
			return "redirect:/admin/customer";

		}

	}

}
