package com.example.backend.repository;

import com.example.backend.entity.Package;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    @Query("select p from Package p where p.trainer_id.id = :trainerId")
    List<Package> findByTrainerId(@Param("trainerId") Integer trainerId);
}
    

