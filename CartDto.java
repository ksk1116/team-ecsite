package jp.co.internous.wings.model.domain.dto;

import java.sql.Timestamp;

import jp.co.internous.wings.model.domain.MstProduct;
import jp.co.internous.wings.model.domain.TblCart;

public class CartDto {

	private int id;

	private int userId;

	private String imageFullPath;

	private String productName;

	private int price;

	private int productCount;

	private Timestamp createdAt;

	private Timestamp updatedAt;

	public CartDto() {}

	public CartDto(MstProduct mstProduct, TblCart tblCart) {
		this.setId(tblCart.getId());
		this.setUserId(tblCart.getId());
		this.setImageFullPath(mstProduct.getImageFullPath());
		this.setProductName(mstProduct.getProductName());
		this.setPrice(mstProduct.getPrice());
		this.setProductCount(tblCart.getProductCount());
		this.setCreatedAt(tblCart.getCreatedAt());
		this.setUpdatedAt(tblCart.getUpdatedAt());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImageFullPath() {
		return imageFullPath;
	}

	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
