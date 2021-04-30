package com.szpt.cn.blog.dao;

import com.szpt.cn.blog.po.Tag;
import com.szpt.cn.blog.po.Type;
import com.szpt.cn.blog.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long>, JpaSpecificationExecutor<Type> {
    Type findByName(String name);

    @Query(value = "select distinct t from Type t left join Blog b on t.id = b.type.id where b.published = true")
    List<Type> findTop( Pageable pageable);
}
