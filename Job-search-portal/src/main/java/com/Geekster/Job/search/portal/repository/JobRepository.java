package com.Geekster.Job.search.portal.repository;

import com.Geekster.Job.search.portal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContaining(String keyword);

    List<Job> findByDescriptionContaining(String keyword);

    List<Job> findByLocation(String location);

    List<Job> findByCompanyName(String companyName);

    @Query("UPDATE Job SET title = :title WHERE id = :id")
    @Modifying
    void updateJobTitle(@Param("id") Long id, @Param("title") String title);

    @Query("DELETE FROM Job WHERE employerName = :employerName")
    @Modifying
    void deleteByEmployerName(@Param("employerName") String employerName);

    @Query("SELECT j FROM Job j WHERE LOWER(j.companyEmail) LIKE CONCAT('%', LOWER(:domain), '%')")
    List<Job> findByCompanyEmailDomain(@Param("domain") String domain);

    @Query("SELECT j FROM Job j WHERE LOWER(j.description) LIKE CONCAT('%', LOWER(:keyword1), '%') AND LOWER(j.description) LIKE CONCAT('%', LOWER(:keyword2), '%')")
    List<Job> findByDescriptionContainingKeywords(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2);

    List<Job> findByTitleContainingAndDescriptionContaining(String title, String description);
}
