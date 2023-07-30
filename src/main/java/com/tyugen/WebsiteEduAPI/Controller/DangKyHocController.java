package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.service.DangKyHocService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dangkyhoc")
public class DangKyHocController {

    private final DangKyHocService dangKyHocService;

    public DangKyHocController(DangKyHocService dangKyHocService) {
        this.dangKyHocService = dangKyHocService;
    }

    /**
     * This method is used to add a new DangKyHoc object to the database.
     *
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addDangKyHoc(@RequestBody String dangKyHoc) {
        return dangKyHocService.addDangKyHoc(dangKyHoc);
    }

    /**
     * This method is used to update an existing DangKyHoc object in the database.
     *
     * @param id        is the ID of the DangKyHoc object that needs to be updated.
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDangKyHoc(@PathVariable("id") int id, @RequestBody String dangKyHoc) {
        return dangKyHocService.updateDangKyHoc(id, dangKyHoc);
    }

    /**
     * This method is used to delete an existing DangKyHoc object in the database.
     *
     * @param id is the ID of the DangKyHoc object that needs to be deleted.
     * @return a ResponseEntity object that contains the status of the request.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDangKyHoc(@PathVariable("id") int id) {
        return dangKyHocService.deleteDangKyHoc(id);
    }

    /**
     * This method is used to get all DangKyHoc objects in the database.
     *
     * @return a ResponseEntity object that contains the status of the request.
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllDangKyHoc() {
        return dangKyHocService.getAllDangKyHoc();
    }

    /**
     * Retrieve a page of dang ky hoc objects
     *
     * @param page the page number
     * @param size the number of objects per page
     * @return a Page object that contains the DangKyHoc objects.
     */
    @GetMapping("/page")
    public Page<DangKyHoc> getDangKyHocHocByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return dangKyHocService.getDangKyHocByPage(page, size);
    }
}
