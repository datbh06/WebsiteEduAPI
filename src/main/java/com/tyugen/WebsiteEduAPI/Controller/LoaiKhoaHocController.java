package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.LoaiKhoaHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loaikhoahoc")
public class LoaiKhoaHocController {

    private final LoaiKhoaHocService loaiKhoaHocService;

    /**
     * Constructs a new LoaiKhoaHocController object with the specified LoaiKhoaHocRepository.
     *
     * @param loaiKhoaHocService the LoaiKhoaHocService to be used by this controller
     */
    public LoaiKhoaHocController(LoaiKhoaHocService loaiKhoaHocService) {
        this.loaiKhoaHocService = loaiKhoaHocService;
    }

    /**
     * Adds a new LoaiKhoaHoc object to the database.
     *
     * @param loaiKhoaHoc a JSON representation of the new LoaiKhoaHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addLoaiKhoaHoc(@RequestBody String loaiKhoaHoc) {
        return loaiKhoaHocService.addLoaiKhoaHoc(loaiKhoaHoc);
    }

    /**
     * Updates a LoaiKhoaHoc object with the specified ID.
     *
     * @param id          the ID of the LoaiKhoaHoc object to be updated
     * @param loaiKhoaHoc a JSON representation of the updated LoaiKhoaHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLoaiKhoaHoc(@PathVariable("id") Integer id, @RequestBody String loaiKhoaHoc) {
        return loaiKhoaHocService.updateLoaiKhoaHoc(id, loaiKhoaHoc);
    }

    /**
     * Deletes a LoaiKhoaHoc object with the specified ID.
     *
     * @param id the ID of the LoaiKhoaHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoaiKhoaHoc(@PathVariable("id") Integer id) {
        return loaiKhoaHocService.deleteLoaiKhoaHoc(id);
    }

}
