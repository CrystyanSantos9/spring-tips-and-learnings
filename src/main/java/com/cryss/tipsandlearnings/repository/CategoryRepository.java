package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.recursiveentity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = {"children"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
            select c from Category c
            """)
    List<Category> findAllCategory(PageRequest pageRequest);

//    @EntityGraph(attributePaths = {"children"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("""
//            select c from Category c
//            """)
//    List<Category> findAllCategoryPaginated(PageRequest pageRequest);

    @EntityGraph(attributePaths = {"children"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
            select c from Category c
            """)
    Page<Category> findAllCategoryPaginated(Pageable pageable);

}

