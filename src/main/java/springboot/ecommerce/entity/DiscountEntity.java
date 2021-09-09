package springboot.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "discount")
public class DiscountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_discount")
	private int id;

	@NotBlank(message = "Discount name is required")
	private String name;

//	@NotNull(message = "Date start is required")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@FutureOrPresent
//	private LocalDate dateStart;
//
//	@NotNull(message = "Date end is required")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@FutureOrPresent
//	private LocalDate dateEnd;

	// Integer, Double moi' dung` @NotNull
	@NotNull(message = "Discount percentage is required")
	@Column(name = "discount_percentage")
	private Integer discountPercentage;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "discount")
	private List<ProductEntity> products;

	public DiscountEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

}
