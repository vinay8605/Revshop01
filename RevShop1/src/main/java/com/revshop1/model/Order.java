package com.revshop1.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    private int buyerId;

    @Column(nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private BigDecimal deliveryFee;

    @Column(nullable = false)
    private BigDecimal tax;

    @Column(nullable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    




	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}



	public String getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getPincode() {
		return pincode;
	}



	public void setPincode(String pincode) {
		this.pincode = pincode;
	}



	public PaymentType getPaymentType() {
		return paymentType;
	}



	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}



	public BigDecimal getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}



	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}



	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}



	public BigDecimal getTax() {
		return tax;
	}



	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}



	public List<OrderItem> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	



	public Order(int orderId, int buyerId, String paymentId, String firstName, String lastName, String email,
			String phone, String address, String city, String state, String pincode, PaymentType paymentType,
			BigDecimal totalPrice, BigDecimal deliveryFee, BigDecimal tax, Timestamp createdAt,
			List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.buyerId = buyerId;
		this.paymentId = paymentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		this.deliveryFee = deliveryFee;
		this.tax = tax;
		this.createdAt = createdAt;
		this.orderItems = orderItems;
	}


	



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", buyerId=" + buyerId + ", paymentId=" + paymentId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", paymentType="
				+ paymentType + ", totalPrice=" + totalPrice + ", deliveryFee=" + deliveryFee + ", tax=" + tax
				+ ", createdAt=" + createdAt + "]";
	}



	



	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}







	public enum PaymentType {
        COD, ONLINE
    }
}
