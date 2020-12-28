package com.hs.yourfit.domain.dress.entity;

import com.hs.yourfit.common.model.BaseEntity;
import com.hs.yourfit.domain.dress.entity.enums.LengthCategory;
import com.hs.yourfit.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_pants")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "pants_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
@Builder
@Setter
public class Pants extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "length_category",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LengthCategory lengthCategory;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "waist", nullable = false)
    private int waist;

    @Column(name = "thigh", nullable = false)
    private int thigh;

    @Column(name = "crotch", nullable = false)
    private int crotch;

    @Column(name = "tail_edge", nullable = false)
    private int tailEdge;

    public void updatePants(LengthCategory lengthCategory,
                            int length,
                            int waist,
                            int thigh,
                            int crotch,
                            int tailEdge){
        this.lengthCategory = lengthCategory;
        this.length = length;
        this.waist = waist;
        this.thigh = thigh;
        this.crotch = crotch;
        this.tailEdge = tailEdge;
    }
}
