package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chuDeID;

    @Column
    @Size(max = 50, message = "Tên chủ đề không được vượt quá 50 ký tự")
    private String tenChuDe;

    @Column
    private String noiDung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaiBaiVietID", foreignKey = @ForeignKey(name = "fk_chude_loaibaiviet"))
    private LoaiBaiViet loaiBaiViet;

    @OneToMany(mappedBy = "chuDes", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("chuDes")
    private Set<BaiViet> baiViets;

    public Integer getChuDeID() {
        return chuDeID;
    }

    public void setChuDeID(Integer chuDeID) {
        this.chuDeID = chuDeID;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LoaiBaiViet getLoaiBaiViet() {
        return loaiBaiViet;
    }

    public void setLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        this.loaiBaiViet = loaiBaiViet;
    }

    public Set<BaiViet> getBaiViets() {
        return baiViets;
    }

    public void setBaiViets(Set<BaiViet> baiViets) {
        this.baiViets = baiViets;
    }
}
