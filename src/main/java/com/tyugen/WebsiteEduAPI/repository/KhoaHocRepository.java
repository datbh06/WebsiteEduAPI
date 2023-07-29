package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The KhoaHocRepository interface is used to define custom database methods for the KhoaHoc class.
 */
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer>, PagingAndSortingRepository<KhoaHoc, Integer> {
    /**
     * Finds a KhoaHoc object from the database by its tenKhoaHoc.
     *
     * @param tenKhoaHoc the tenKhoaHoc of the KhoaHoc object to be found
     * @return an Optional containing the KhoaHoc object with the specified tenKhoaHoc if it exists
     */
    KhoaHoc findByTenKhoaHoc(String tenKhoaHoc);
}
