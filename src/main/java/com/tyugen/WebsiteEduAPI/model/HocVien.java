package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer hocVienID;

    @Column
    private String hinhAnh;
    @Column
    private String hoTen;
    @Column
    private Date ngaySinh;
    @Column
    private String soDienThoai;
    @Column
    private String email;
    @Column
    private String tinhThanh;
    @Column
    private String quanHuyen;
    @Column
    private String phuongXa;
    @Column
    private String soNha;

    @OneToMany(mappedBy = "hocVien", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("hocVien")
    private Set<DangKyHoc> dangKyHocs;
}
