package springboot.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orderDetails")
	private int id;

	@NotNull(message = "Quantity is required")
	private Integer quantity;

	@NotNull(message = "Unit price is required")
	@Column(name = "unit_price")
	private Double unitPrice;

	@Column(name = "total_price")
	private Double totalPrice = 0.0;

	@Column(name = "total_discount_price")
	private Double totalDiscountPrice = 0.0;

	@ManyToOne
	@JoinColumn(name = "id_product")
	private ProductEntity product;

	@ManyToOne
	@JoinColumn(name = "id_order")
	private OrderEntity order;

	public OrderDetailsEntity() {
		super();
	}

	public OrderDetailsEntity(@NotNull(message = "Quantity is required") Integer quantity,
			@NotNull(message = "Unit price is required") Double unitPrice, Double totalPrice, Double totalDiscountPrice,
			ProductEntity product) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(Double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

}
