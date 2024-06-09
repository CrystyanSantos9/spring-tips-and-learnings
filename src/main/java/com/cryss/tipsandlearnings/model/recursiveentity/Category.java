package com.cryss.tipsandlearnings.model.recursiveentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "TB_CATEGORIA_CATALOG", schema = "CART")
@Entity
public class Category implements Serializable {

    private static final Long serialVersionUID = 1L;


    @Id
    private long id;

    @NotNull
    private String name;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_category", referencedColumnName = "id")
    @JsonIgnore
    @JsonBackReference
    private Category parentCategory;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentCategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Category> children;

    @JsonManagedReference
    @ManyToMany(mappedBy = "categories", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    Set<Product> products = new HashSet<> ();



}
