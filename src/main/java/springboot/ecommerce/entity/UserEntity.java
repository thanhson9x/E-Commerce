package springboot.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int id;

	@NotBlank(message = "First name is required")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Date of birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate dob;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_gender")
	private GenderEntity gender;

	@NotBlank(message = "Address is required")
	private String address;

	@NotNull(message = "Phone number is required")
	private Integer phone;

	@NotBlank(message = "Email is required")
	@Email
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<OrderEntity> orders;
	// @LazyCollection(LazyCollectionOption.FALSE) tuong tu. nhu FetchType EAGER
	// FetchType EAGER de~ lay' list order cua~ user
	// CascadeType ALL de~ khi update user se~ update luon list order

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany()
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<RoleEntity> roles;

	public UserEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public GenderEntity getGender() {
		return gender;
	}

	public void setGender(GenderEntity gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}
