package com.tyugen.WebsiteEduAPI.service;

import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.DangKyHocRepository;
import com.tyugen.WebsiteEduAPI.repository.HocVienRepository;
import com.tyugen.WebsiteEduAPI.repository.KhoaHocRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KhoaHocService {
    private final KhoaHocRepository khoaHocRepository;
    private final HocVienRepository hocVienRepository;
    private final DangKyHocRepository dangKyHocRepository;

    /**
     * Constructs a new KhoaHocService object with the specified KhoaHocRepository.
     *
     * @param khoaHocRepository   the khoaHocRepository to be used by this service
     * @param hocVienRepository   the hocVienRepository to be used by this service
     * @param dangKyHocRepository the dangKyHocRepository to be used by this service
     */
    public KhoaHocService(KhoaHocRepository khoaHocRepository, HocVienRepository hocVienRepository, DangKyHocRepository dangKyHocRepository) {
        this.khoaHocRepository = khoaHocRepository;
        this.hocVienRepository = hocVienRepository;
        this.dangKyHocRepository = dangKyHocRepository;
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

    //update soHocVien in KhoaHoc after a new HocVien is added in dangKyHoc
    public void updateSoHocVien(Integer khoaHocID) {
        Optional<KhoaHoc> khoaHocOptional = khoaHocRepository.findById(khoaHocID);
        if (khoaHocOptional.isPresent()) {
            KhoaHoc khoaHoc = khoaHocOptional.get();
            int soHocVien = dangKyHocRepository.countByKhoaHoc(khoaHoc);
            khoaHoc.setSoHocVien(soHocVien);
            khoaHocRepository.save(khoaHoc);
        }
    }


}
