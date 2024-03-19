//package com.cryss.tipsandlearnings.model.recursiveentity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OrderBy;
//
//import java.io.Serializable;
//import java.util.Collection;
//
//@Entity
//public class ParentCategory implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Other entity attributes
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formVersion", fetch = FetchType.EAGER)
//    @OrderBy("order_position ASC")
//    @JsonManagedReference
//    private Collection<Category> categories;
//}
