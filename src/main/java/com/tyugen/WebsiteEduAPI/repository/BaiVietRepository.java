package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {

    /**
     * Finds a BaiViet object by its tenBaiViet.
     *
     * @param tenBaiViet the tenBaiViet of the BaiViet to be found
     * @return a BaiViet object
     */
    BaiViet findByTenBaiViet(String tenBaiViet);

    /**
     * Finds BaiViet objects by their keyword of tenBaiViet.
     *
     * @param pageable   the Pageable object
     * @param keyword the tenBaiViet of the BaiViet objects to be found
     * @return a Page object containing BaiViet objects
     */
    Page<BaiViet> findByTenBaiVietContaining(Pageable pageable, String keyword);
}
