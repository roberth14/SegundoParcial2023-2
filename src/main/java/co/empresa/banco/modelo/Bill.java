package co.empresa.banco.modelo;

import java.io.Serializable;

public class Bill implements Serializable {
	
	private Integer serial;
	private String date_bill;
	private Integer id;
	private Integer value;
	private Integer type;
	private String observation;
	public Integer getSerial() {
		return serial;
	}
	
	
	
	
	public Bill(String date_bill, Integer id, Integer value, Integer type, String observation) {
		super();
		this.date_bill = date_bill;
		this.id = id;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}




	public Bill(Integer serial, String date_bill, Integer id, Integer value, Integer type, String observation) {
		super();
		this.serial = serial;
		this.date_bill = date_bill;
		this.id = id;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}




	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	public String getDate_bill() {
		return date_bill;
	}
	public void setDate_bill(String date_bill) {
		this.date_bill = date_bill;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
	

}
