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
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taiKhoanID;

    @Column
    @Size(max = 50, message = "Tên người dùng không được vượt quá 50 ký tự")
    private String tenNguoiDung;

    @Column
    @Size(max = 50, message = "Tài khoản không được vượt quá 50 ký tự")
    private String taiKhoan;

    @Column
    @Size(max = 50, message = "Mật khẩu không được vượt quá 50 ký tự")
    private String matKhau;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quyenHanID", foreignKey = @ForeignKey(name = "fk_taikhoan_quyenhan"))
    private QuyenHan quyenHan;

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("taiKhoan")
    private Set<BaiViet> baiViets;

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("taiKhoan")
    private Set<DangKyHoc> dangKyHocs;

}
