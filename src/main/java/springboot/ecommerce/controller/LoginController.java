package springboot.ecommerce.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import springboot.ecommerce.entity.CategoryEntity;
import springboot.ecommerce.entity.GenderEntity;
import springboot.ecommerce.entity.ProductEntity;
import springboot.ecommerce.entity.RoleEntity;
import springboot.ecommerce.entity.UserEntity;
import springboot.ecommerce.service.CategoryService;
import springboot.ecommerce.service.GenderService;
import springboot.ecommerce.service.ProductService;
import springboot.ecommerce.service.RoleService;
import springboot.ecommerce.service.SendMailService;
import springboot.ecommerce.service.UserService;

@Controller
public class LoginController {

	private static int FIRST_PAGE = 1;

	@Autowired
	public GenderService genderService;

	@Autowired
	public RoleService roleService;

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public ProductService productService;

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

	@GetMapping("/forward")
	public String forward(Model model, HttpSession session, Authentication authentication) {

		if (authentication != null) {
			UserEntity mainUser = userService.findByEmail(authentication.getName());
			if (mainUser != null) {
				session.setAttribute("user", mainUser);
				for (RoleEntity roleEntity : mainUser.getRoles()) {
					if (roleEntity.getName().equals("ROLE_ADMIN")) {
						return "redirect:/admin/info";
					}
				}
				return "redirect:/customer/index";
			}
		}
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "index";

	}

	@GetMapping({ "/", "/index" })
	public String showHomepage(Model model, HttpSession session, Authentication authentication) {

		if (authentication != null) {
			UserEntity mainUser = userService.findByEmail(authentication.getName());
			if (mainUser != null) {
				session.setAttribute("user", mainUser);
				for (RoleEntity roleEntity : mainUser.getRoles()) {
					if (roleEntity.getName().equals("ROLE_ADMIN")) {
						return "redirect:/admin/index";
					}
				}
				return "redirect:/customer/index";
			}
		}

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
		model.addAttribute("path", "index");
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
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
		return "redirect:/index";

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
		return "redirect:/index";

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
		return "redirect:/index";

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
		model.addAttribute("path", "index");
		List<CategoryEntity> list = categoryService.getList();
		model.addAttribute("listCategory", list);
		return "index";

	}

	@GetMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable(name = "id") int id, HttpSession session) {

		ProductEntity product = productService.findById(id);
		session.setAttribute("product", product);
		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "productDetails";

	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {

		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "login";

	}

	@GetMapping("/accessDenied")
	public String accessDenied(Model model) {

		List<CategoryEntity> listCategory = categoryService.getList();
		model.addAttribute("listCategory", listCategory);
		return "accessDenied";

	}

	@GetMapping("/register")
	public String showRegisterForm(Model model, HttpSession session) {

		if (session.getAttribute("checkUser") != null && session.getAttribute("checkUser").equals("error")) {

			UserEntity user = (UserEntity) session.getAttribute("errorUser");
			model.addAttribute("user", user);
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("errorUser");
			session.removeAttribute("checkUser");
			session.removeAttribute("errorMessage");
			return "customer";

		} else {

			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("user", new UserEntity());
			return "customer";

		}

	}

	@PostMapping("/submitUser")
	public String submitUser(Model model, @ModelAttribute(name = "user") @Valid UserEntity user, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			List<GenderEntity> listGender = genderService.getList();
			model.addAttribute("listGender", listGender);
			List<CategoryEntity> listCategory = categoryService.getList();
			model.addAttribute("listCategory", listCategory);
			return "customer";
		} else {

			// check mail User
			List<UserEntity> listUser = userService.getList();
			if (listUser != null && listUser.size() > 0) {
				for (UserEntity userEntity : listUser) {
					if (userEntity.getEmail().equalsIgnoreCase(user.getEmail())) {
						session.setAttribute("errorUser", user);
						session.setAttribute("checkUser", "error");
						session.setAttribute("errorMessage", "This email address is NOT available!!!");
						return "redirect:/register";
					}
				}
			}

			// Set role
			List<RoleEntity> listRole = roleService.getList();
			listRole.removeIf(n -> !(n.getName().equalsIgnoreCase("ROLE_CUSTOMER")));
			user.setRoles(listRole);

			// encode password
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// save user
			userService.save(user);

			// send mail
			sendMailService.sendSimpleMessage(user.getEmail(), "Congratulations Email",
					"Congratulations! You have successfully registered");
			// them tai khoan va mat khau de nguoi dung dang nhap
			return "redirect:/login";

		}

	}

}
