package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.HocVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HocVienController is a RestController that handles HTTP requests for the HocVien API.
 */
@RestController
@RequestMapping("/api/v1/hocvien")
public class HocVienController {
    private final HocVienService hocVienService;

    /**
     * Constructs a new HocVienController object with the specified HocVienService.
     *
     * @param hocVienService the HocVienService to be used by this controller
     */
    public HocVienController(HocVienService hocVienService) {
        this.hocVienService = hocVienService;
    }

    /**
     * Adds a new HocVien object to the database.
     *
     * @param hocVien a JSON representation of the new HocVien object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addHocVien(@RequestBody String hocVien) {
        return hocVienService.addHocVien(hocVien);
    }

    /**
     * Updates an existing HocVien object in the database.
     *
     * @param id      the ID of the HocVien object to be updated
     * @param hocVien a JSON representation of the updated HocVien object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHocVien(@PathVariable("id") int id, @RequestBody String hocVien) {
        return hocVienService.updateHocVien(id, hocVien);
    }

    /**
     * Deletes an existing HocVien object from the database.
     *
     * @param id the ID of the HocVien object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHocVien(@PathVariable("id") int id) {
        return hocVienService.deleteHocVien(id);
    }

    /**
     * Retrieves a list of HocVien objects from the database.
     *
     * @return a ResponseEntity containing a list of HocVien objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getHocViens() {
        return hocVienService.getAllHocVien();
    }
}
