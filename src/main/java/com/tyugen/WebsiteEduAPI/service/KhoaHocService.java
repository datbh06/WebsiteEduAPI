package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.exceptions.ResourceNotFoundException;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.KhoaHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Adds a new KhoaHoc object to the database.
     *
     * @param khoaHoc a JSON representation of the new KhoaHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addKhoaHoc(@RequestBody String khoaHoc) {
        Gson gson = new Gson();
        KhoaHoc khoaHocNew = gson.fromJson(khoaHoc, KhoaHoc.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<KhoaHoc>> violations = validator.validate(khoaHocNew);

        if (violations.isEmpty()) {
            khoaHocRepository.save(khoaHocNew);
            return ResponseEntity.ok().build();
        } else {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
    }

    /**
     * Updates an existing KhoaHoc object in the database.
     *
     * @param id      the ID of the KhoaHoc object to be updated
     * @param khoaHoc a JSON representation of the updated KhoaHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */

    public ResponseEntity<?> updateKhoaHoc(@PathVariable("id") int id, @RequestBody String khoaHoc) {
        Gson gson = new Gson();
        KhoaHoc khoaHocNew = gson.fromJson(khoaHoc, KhoaHoc.class);
        Optional<KhoaHoc> khoaHocOld = khoaHocRepository.findById(id);
        if (khoaHocOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<KhoaHoc>> violations = validator.validate(khoaHocNew);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                khoaHocNew.setKhoaHocID(id);
                khoaHocRepository.save(khoaHocNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing KhoaHoc object from the database.
     *
     * @param id the ID of the KhoaHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteKhoaHoc(@PathVariable("id") int id) {
        Optional<KhoaHoc> khoaHoc = khoaHocRepository.findById(id);
        if (khoaHoc.isPresent()) {
            khoaHocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets all KhoaHoc objects from the database.
     *
     * @return a ResponseEntity containing the retrieved KhoaHoc objects
     */
    public ResponseEntity<?> getAllKhoaHoc() {
        Optional<List<KhoaHoc>> optionalKhoaHocList = Optional.of(khoaHocRepository.findAll());
        return optionalKhoaHocList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets a KhoaHoc object from the database by its name
     *
     * @param tenKhoaHoc the name of the KhoaHoc object to be retrieved
     * @return a ResponseEntity containing the retrieved KhoaHoc object
     */
    public ResponseEntity<?> findKhoaHocByName(@PathVariable("tenKhoaHoc") String tenKhoaHoc) {
        Optional<KhoaHoc> khoaHoc = Optional.ofNullable(khoaHocRepository.findByTenKhoaHoc(tenKhoaHoc));
        if (khoaHoc.isPresent()) {
            return ResponseEntity.ok(khoaHoc.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

    /**
     * Increments the number of students (soHocVien) of a KhoaHoc object by 1.
     *
     * @param khoaHocID the ID of the KhoaHoc object to update
     * @throws ResourceNotFoundException if the KhoaHoc object with the given ID is not found
     */
    public void updateSoHocVien(Integer khoaHocID) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(khoaHocID).orElseThrow(()
                -> new ResourceNotFoundException("Khóa học không tồn tại"));
        Integer soHocVien = khoaHoc.getSoHocVien();
        soHocVien++;
        khoaHoc.setSoHocVien(soHocVien);
        khoaHocRepository.save(khoaHoc);
    }

}
