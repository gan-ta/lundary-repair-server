package com.hs.yourfit.domain.dress.repository.Impl;

import com.hs.yourfit.domain.dress.entity.Pants;
import com.hs.yourfit.domain.dress.entity.QPants;
import com.hs.yourfit.domain.dress.repository.PantsRepositoryCustom;
import com.hs.yourfit.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class PantsRepositoryCustomImpl extends QuerydslRepositorySupport implements PantsRepositoryCustom {

    private final JPAQueryFactory jpaQuery;
    private final QUser user = QUser.user;
    private final QPants pants = QPants.pants;

    public PantsRepositoryCustomImpl(JPAQueryFactory jpaQuery){
        super(Pants.class);
        this.jpaQuery = jpaQuery;
    }

    private JPAQuery<Pants> setFetchJoinQuery(){
        return jpaQuery.select(this.pants)
                .from(this.pants)
                .innerJoin(this.pants.user, this.user)
                .fetchJoin();
    }

    @Override
    public Optional<Pants> fetchById(Long pantsId) {
        return Optional.of(setFetchJoinQuery().where(this.pants.id.eq(pantsId)).fetchOne());
    }
}
