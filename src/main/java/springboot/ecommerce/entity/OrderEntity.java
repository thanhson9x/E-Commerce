package springboot.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "orderz") // ko dc dat. ten^ bang~ la` order vi` trung` keyword trong mysql
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private int id;

	@NotBlank(message = "Status is required")
	private String status;

	@Column(name = "order_price")
	private Double orderPrice;

	@CreationTimestamp
	@Column(name = "date_created")
	private LocalDateTime dateCreated;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "order")
	private List<OrderDetailsEntity> orderDetails;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserEntity user;

//	@ManyToMany
//	@JoinTable(name = "orderdetails", joinColumns = @JoinColumn(name = "id_order"), inverseJoinColumns = @JoinColumn(name = "id_product"))
//	List<ProductEntity> products;

	public OrderEntity() {
		super();
	}

	public OrderEntity(@NotBlank(message = "Status is required") String status, Double orderPrice, UserEntity user,
			List<OrderDetailsEntity> orderDetails) {
		super();
		this.status = status;
		this.orderPrice = orderPrice;
		this.user = user;
		this.orderDetails = orderDetails;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<OrderDetailsEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
