package com.katashiyo515.api.infrastructure.mapper

import com.katashiyo515.api.domain.entity.Konamon
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface KonamonDbMapper {
    @Insert("INSERT INTO konamon (name, description, updated_at, created_at) VALUES(#{name}, #{description}, sysdate, sysdate)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(konamon: Konamon)

    @Select("SELECT id, name, description FROM konamon WHERE id = #{id}")
    fun findById(id: Int): Konamon?

    @Select("SELECT id, name, description FROM konamon")
    fun findAll(): List<Konamon>

    @Update("UPDATE konamon SET name = #{name}, description = #{description}, updated_at = sysdate WHERE id = #{id}")
    fun update(konamon: Konamon): Int

    @Select("SELECT id, name, description FROM konamon WHERE id = #{id} FOR UPDATE")
    fun lock(id: Int): Konamon?

    @Delete("DELETE FROM konamon WHERE id = #{id}")
    fun delete(id: Int): Int
}
