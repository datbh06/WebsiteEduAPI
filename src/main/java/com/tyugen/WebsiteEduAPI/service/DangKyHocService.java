package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tyugen.WebsiteEduAPI.Adapter.DateTypeAdapter;
import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.DangKyHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DangKyHocService is a service class that handles the business logic of the DangKyHocController class.
 */
@Service
public class DangKyHocService {
    private final DangKyHocRepository dangKyHocRepository;

    /**
     * Constructor for DangKyHocService class.
     *
     * @param dangKyHocRepository is a repository class that handles the database operations of the DangKyHocController class.
     */
    public DangKyHocService(DangKyHocRepository dangKyHocRepository) {
        this.dangKyHocRepository = dangKyHocRepository;
    }

    /**
     * This method is used to add a new DangKyHoc object to the database.
     *
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    public ResponseEntity<?> addDangKyHoc(String dangKyHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        DangKyHoc dangKyHocNew = gson.fromJson(dangKyHoc, DangKyHoc.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<DangKyHoc>> violations = validator.validate(dangKyHocNew);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            dangKyHocRepository.save(dangKyHocNew);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * This method is used to update an existing DangKyHoc object in the database.
     *
     * @param id        is the ID of the DangKyHoc object that needs to be updated.
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    public ResponseEntity<?> updateDangKyHoc(int id, String dangKyHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        DangKyHoc dangkyNew = gson.fromJson(dangKyHoc, DangKyHoc.class);
        Optional<DangKyHoc> dangKyOld = dangKyHocRepository.findById(id);
        if (dangKyOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<DangKyHoc>> violations = validator.validate(dangkyNew);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                dangkyNew.setDangKyHocID(id);
                dangKyHocRepository.save(dangkyNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is used to delete an existing DangKyHoc object in the database.
     *
     * @param id is the ID of the DangKyHoc object that needs to be deleted.
     * @return a ResponseEntity object that contains the status of the request.
     */
    public ResponseEntity<?> deleteDangKyHoc(int id) {
        Optional<DangKyHoc> dangKyHoc = dangKyHocRepository.findById(id);
        if (dangKyHoc.isPresent()) {
            dangKyHocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is used to get all DangKyHoc objects from the database.
     *
     * @return a ResponseEntity object that contains the status of the request.
     */
    public ResponseEntity<?> getAllDangKyHoc() {
        Optional<List<DangKyHoc>> optionalDangKyHocList = Optional.of(dangKyHocRepository.findAll());
        return optionalDangKyHocList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieve a page of dang ky hoc objects
     *
     * @param page the page number
     * @param size the number of objects per page
     * @return a Page object that contains the DangKyHoc objects.
     */
    public Page<DangKyHoc> getDangKyHocByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dangKyHocRepository.findAll(pageable);
    }
}
