package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.DangKyHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
