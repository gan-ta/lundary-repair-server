package com.hs.yourfit.domain.dress.repository.Impl;

import com.hs.yourfit.domain.dress.entity.QTop;
import com.hs.yourfit.domain.dress.entity.Top;
import com.hs.yourfit.domain.dress.repository.TopRepositoryCustom;
import com.hs.yourfit.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class TopRepositoryCustomImpl extends QuerydslRepositorySupport implements TopRepositoryCustom {

    private final JPAQueryFactory jpaQuery;
    private final QUser user = QUser.user;
    private final QTop top = QTop.top;

    public TopRepositoryCustomImpl(JPAQueryFactory jpaQuery){
        super(Top.class);
        this.jpaQuery = jpaQuery;
    }

    private JPAQuery<Top> setFetchJoinQuery(){
        return jpaQuery.select(this.top)
                .from(this.top)
                .innerJoin(this.top.user, this.user)
                .fetchJoin();
    }

    @Override
    public Optional<Top> fetchById(Long topId) {
        return Optional.of(setFetchJoinQuery().where(this.top.id.eq(topId)).fetchOne());
    }
}
