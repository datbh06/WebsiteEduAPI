package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khoaHocID;

    @Column
    @Size(max = 50, message = "Tên khóa học không được quá 50 ký tự")
    private String tenKhoaHoc;
    @Column
    private int thoiGianHoc;
    @Column
    private String gioiThieu;
    @Column
    private String noiDung;
    @Column
    private Double hocPhi;
    @Column
    private Integer soHocVien;
    @Column
    private Integer soLuongMon;
    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("khoaHocs")
    @JoinColumn(name = "loaiKhoaHocID", foreignKey = @ForeignKey(name = "fk_khoahoc_loaikhoahoc"))
    private LoaiKhoaHoc loaiKhoaHoc;

    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("khoaHoc")
    private Set<DangKyHoc> dangKyHocs;
}
