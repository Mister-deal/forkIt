package com.example.backend_forkit.repository;

import com.example.backend_forkit.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByCuisineType(String cuisineType);

    List<Restaurant> findByAverageRatingGreaterThanEqual(Double rating);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(concat('%', :name, '%' ))")
    List<Restaurant> findByNameContainingIgnoreCase(@Param("name") String name);

}
