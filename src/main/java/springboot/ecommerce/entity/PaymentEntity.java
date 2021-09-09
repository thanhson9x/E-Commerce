//package springboot.ecommerce.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "payment")
//public class PaymentEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "idPayment")
//	private int id;
//
//	private LocalDateTime dateCreated;
//
//	@OneToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "idOrder")
//	private OrderEntity order;
//
//	@ManyToOne
//	@JoinColumn(name = "idMethod")
//	private MethodEntity method;
//
//	public PaymentEntity() {
//		super();
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public LocalDateTime getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(LocalDateTime dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//
//	public OrderEntity getOrder() {
//		return order;
//	}
//
//	public void setOrder(OrderEntity order) {
//		this.order = order;
//	}
//
//	public MethodEntity getMethod() {
//		return method;
//	}
//
//	public void setMethod(MethodEntity method) {
//		this.method = method;
//	}
//
//}
