package com.tyugen.WebsiteEduAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiBaiViet {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loaiBaiVietID;

    @Column
    private String tenLoai;

    @OneToMany(mappedBy = "loaiBaiViet", cascade = CascadeType.REMOVE)
    private Set<ChuDe> chuDes;

}
