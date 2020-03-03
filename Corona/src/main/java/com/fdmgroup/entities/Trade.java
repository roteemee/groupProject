package com.fdmgroup.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "trades")
public class Trade {
@Id
@Column(name =  "trade_id")
private int tradeId;
@OneToMany
@Column(name =  "share_id")
private int shareId;
@OneToMany
@Column(name =  "broker_id")
private int brokerId;
@OneToMany
@Column(name =  "stock_ex_id")
private int stockExchangeId;
@Temporal(TemporalType.TIMESTAMP)
@Column(name =  "transaction_time")
private Date orderDate;	
@Column(name =  "share_ammount")
private int shareAmmount;
@Column(name =  "price_total")
private int priceTotal;
public int getTradeId() {
	return tradeId;
}
public void setTradeId(int tradeId) {
	this.tradeId = tradeId;
}
public int getShareId() {
	return shareId;
}
public void setShareId(int shareId) {
	this.shareId = shareId;
}
public int getBrokerId() {
	return brokerId;
}
public void setBrokerId(int brokerId) {
	this.brokerId = brokerId;
}
public int getStockExchangeId() {
	return stockExchangeId;
}
public void setStockExchangeId(int stockExchangeId) {
	this.stockExchangeId = stockExchangeId;
}
public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
public int getShareAmmount() {
	return shareAmmount;
}
public void setShareAmmount(int shareAmmount) {
	this.shareAmmount = shareAmmount;
}
public int getPriceTotal() {
	return priceTotal;
}
public void setPriceTotal(int priceTotal) {
	this.priceTotal = priceTotal;
} 




}
