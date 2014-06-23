///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package www.wcy.wat.gorky.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import www.wcy.wat.gorky.dto.ExampleDTO;
//
///**
// * 
// * @author Krzysztof Jedynak
// */
//@Entity
//@Table(name = "example")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Example.findAll", query = "SELECT e FROM Example e"),
//		@NamedQuery(name = "Example.findById", query = "SELECT e FROM Example e WHERE e.id = :id"),
//		@NamedQuery(name = "Example.findByText", query = "SELECT e FROM Example e WHERE e.text = :text"),
//		@NamedQuery(name = "Example.findByAge", query = "SELECT e FROM Example e WHERE e.age = :age") })
//public class Example implements Serializable {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@Basic(optional = false)
//	@NotNull
//	@Column(name = "ID")
//	private Integer id;
//	@Size(max = 32)
//	@Column(name = "TEXT")
//	private String text;
//	@Column(name = "AGE")
//	private Integer age;
//
//	public Example() {
//	}
//	
//	public Example(ExampleDTO exampleDTO)
//	{
//		this.text = exampleDTO.getText();
//		this.age = 12;
//	}
//
//	public Example(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getText() {
//		return text;
//	}
//
//	public void setText(String text) {
//		this.text = text;
//	}
//
//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//
//	@Override
//	public int hashCode() {
//		int hash = 0;
//		hash += (id != null ? id.hashCode() : 0);
//		return hash;
//	}
//
//	@Override
//	public boolean equals(Object object) {
//		// TODO: Warning - this method won't work in the case the id fields are
//		// not set
//		if (!(object instanceof Example)) {
//			return false;
//		}
//		Example other = (Example) object;
//		if ((this.id == null && other.id != null)
//				|| (this.id != null && !this.id.equals(other.id))) {
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "www.wcy.wat.gorky.model.Example[ id=" + id + " ]";
//	}
//
//}
