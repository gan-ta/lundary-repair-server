package com.hs.yourfit.domain.dress.entity;

import com.hs.yourfit.common.model.BaseEntity;
import com.hs.yourfit.domain.dress.entity.enums.LengthCategory;
import com.hs.yourfit.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_top")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "top_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
@Builder
@Setter
public class Top extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "length_category",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LengthCategory lengthCategory;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "shoulder", nullable = false)
    private int shoulder;

    @Column(name = "chest", nullable = false)
    private int chest;

    @Column(name = "retail", nullable = false)
    private int retail;

    public void updateTop(LengthCategory lengthCategory,
                          int length,
                          int shoulder,
                          int chest,
                          int retail){
        this.lengthCategory = lengthCategory;
        this.length = length;
        this.shoulder = shoulder;
        this.chest = chest;
        this.retail = retail;
    }
}
