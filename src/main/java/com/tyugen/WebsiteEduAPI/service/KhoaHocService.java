package com.tyugen.WebsiteEduAPI.service;

import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.KhoaHocRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KhoaHocService {
    private final KhoaHocRepository khoaHocRepository;

    /**
     * Constructs a new KhoaHocService object with the specified KhoaHocRepository.
     *
     * @param khoaHocRepository the khoaHocRepository to be used by this service
     */
    public KhoaHocService(KhoaHocRepository khoaHocRepository) {
        this.khoaHocRepository = khoaHocRepository;
    }

    /**
     * Gets all KhoaHoc objects from the database displaying the specified number of objects per page.
     *
     * @param page the page number
     * @param size the number of objects per page
     * @return a Page containing the KhoaHoc objects
     */
    public Page<KhoaHoc> getKhoaHocByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return khoaHocRepository.findAll(pageable);
    }



}
