package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.common.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

//사업자 정보
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_business_info")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "business_info_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
@Builder
public class BusinessInfo extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "corporationName")
    private String corporationName;

    @Column(name = "representationName")
    private String representationName;

    @Column(name = "openDate")
    private LocalDate openDate;

    @Column(name = "registrationNumber")
    private String registrationNumber;//법인 등록번호


}
