package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.service.KhoaHocService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * KhoaHocController is a RestController that handles HTTP requests for the KhoaHoc API.
 */
@RestController
@RequestMapping("api/v1/khoahoc")
public class KhoaHocController {

    private final KhoaHocService khoaHocService;

    /**
     * Constructs a new KhoaHocController object with the specified KhoaHocRepository.
     *
     * @param khoaHocService the KhoaHocService to be used by this controller
     */
    public KhoaHocController(KhoaHocService khoaHocService) {
        this.khoaHocService = khoaHocService;
    }

    /**
     * Adds a new KhoaHoc object to the database.
     *
     * @param khoaHoc a JSON representation of the new KhoaHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addKhoaHoc(@RequestBody String khoaHoc) {
        return khoaHocService.addKhoaHoc(khoaHoc);
    }

    /**
     * Updates an existing KhoaHoc object in the database.
     *
     * @param id      the ID of the KhoaHoc object to be updated
     * @param khoaHoc a JSON representation of the updated KhoaHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateKhoaHoc(@PathVariable("id") int id, @RequestBody String khoaHoc) {
        return khoaHocService.updateKhoaHoc(id, khoaHoc);
    }

    /**
     * Deletes an existing KhoaHoc object from the database.
     *
     * @param id the ID of the KhoaHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteKhoaHoc(@PathVariable("id") int id) {
        return khoaHocService.deleteKhoaHoc(id);
    }

    /**
     * Gets all KhoaHoc objects from the database.
     *
     * @return a ResponseEntity containing the retrieved KhoaHoc object
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllKhoaHoc() {
        return khoaHocService.getAllKhoaHoc();
    }

    /**
     * Gets a KhoaHoc object from the database by its name
     *
     * @param tenKhoaHoc the name of the KhoaHoc object to be retrieved
     * @return a ResponseEntity containing the retrieved KhoaHoc object
     */
    @GetMapping("/find/{tenKhoaHoc}")
    public ResponseEntity<?> findKhoaHocByName(@PathVariable("tenKhoaHoc") String tenKhoaHoc) {
        return khoaHocService.findKhoaHocByName(tenKhoaHoc);
    }

    /**
     * Gets all KhoaHoc objects from the database displaying the specified number of objects per page.
     *
     * @param page the page number
     * @param size the number of objects per page
     * @return a Page containing the KhoaHoc objects
     */
    @GetMapping("/page")
    public Page<KhoaHoc> getKhoaHocByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return khoaHocService.getKhoaHocByPage(page, size);
    }

    /**
     * Updates the number of students (soHocVien) of a KhoaHoc object
     * by calling the updateSoHocVien method of the KhoaHocService class.
     *
     * @param id the ID of the KhoaHoc object to update
     * @return an HTTP 200 OK response
     */
    @PutMapping("/{id}/sohocvien")
    public ResponseEntity<KhoaHoc> updateSoHocVien(@PathVariable int id) {
        khoaHocService.updateSoHocVien(id);
        return ResponseEntity.ok().build();
    }

}
