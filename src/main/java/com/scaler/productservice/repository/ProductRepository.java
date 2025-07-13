package com.scaler.productservice.repository;

import com.scaler.productservice.Projections.ProductWithIdAndPriceProjection;
import com.scaler.productservice.models.Product;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);

    @Override
    List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable); //pageNo, pageSize - data will be given from the fronted
    //from which limit and which offset you need to fetch?
    //pageNo = 20, pageSize = 25 (25 results on one page)
    //pageNo starts from 0 , 20*25 = 500 retrieved earlier
    //20th page will show 501th to 525th products

    @Override
    Optional<Product> findById(Long aLong); // Declared querry
    //Product findById(long id);

    List<Product> findByCategory(Category category);
    List<Product> findAllByCategory_Title(String category);
    List<Product> findAllByCategory_id(Long id);

    /*
Providing the query to JPA can be done :- (Both are custome querries)
1. HQL
2. Native SQL   // (we need to use DB language specific queries)
*/
    @Query("select p.id, p.price from Product p where p.category.title = :categoryName")
    List<ProductWithIdAndPriceProjection> getProductIdAndPriceByGivenCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select * from products p where p.title = :title", nativeQuery = true)
    List<ProductWithIdAndPriceProjection> getIdAndPricesOfAllProductsWithGivenTitle(@Param("title") String title);
}