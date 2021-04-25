package com.szpt.cn.blog.dao;
import com.szpt.cn.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long>, JpaSpecificationExecutor<Tag> {
    Tag findByName(String name);

    @Query("select distinct t from Tag t ,BlogTags bt,Blog b where t.id = bt.tags_id and b.id = bt.blogs_id and b.published = true ")
    List<Tag> findTop(Pageable pageable);




}
