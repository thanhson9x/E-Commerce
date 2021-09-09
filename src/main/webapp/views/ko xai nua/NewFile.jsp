	
	@GetMapping("/category")
	public String showCategoryForm(Model model, HttpSession session) {

		if (session.getAttribute("checkCategory") != null && session.getAttribute("checkCategory").equals("!")) {

			CategoryEntity category = (CategoryEntity) session.getAttribute("tempCategory");
			model.addAttribute("category", category);
			model.addAttribute("message", session.getAttribute("message"));
			model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
			session.removeAttribute("checkCategory");
			session.removeAttribute("message");
			session.removeAttribute("errorMessage");
			return "category";

		} else {

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
	public String submitCategory(@ModelAttribute(name = "category") @Valid CategoryEntity category,
			BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
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

		List<ProductEntity> listProduct = productService.getList();
		if (listProduct != null && !listProduct.isEmpty()) {
			for (ProductEntity productEntity : listProduct) {
				if (productEntity.getCategory().getId() == id) {
					productService.delete(productEntity);
				}
			}
		}
		categoryService.deleteById(id);
		return "redirect:/admin/allCategories";

	}