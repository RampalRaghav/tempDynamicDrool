package org.arpit.java2blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;

@Entity
@Proxy(lazy=false)
@Table(name="Discount")
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;  
    
    @Column(name="percentage")
    private Double percentage;
    
    @Column(name="additionalPercentage")
    private Double additionalPercentage;
    
    @Column(name="netPrice")
    private Double netPrice;

    public Double getAdditionalPercentage() {
		return additionalPercentage;
	}

	public void setAdditionalPercentage(Double additionalPercentage) {
		this.additionalPercentage = additionalPercentage;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

    public Discount(Double percentage) {
        this.percentage = percentage;
    }
        

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

    
    
}
