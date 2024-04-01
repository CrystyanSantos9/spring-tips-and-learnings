package com.cryss.tipsandlearnings.model.recursiveentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TB_CATEGORIA_CATALOG", schema = "CART")
public class Category implements Serializable {

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

}
