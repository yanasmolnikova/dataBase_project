package com.example.demo;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarRepository {

    @Select("SELECT * FROM test ORDER BY id")
    List<Car> findAll();

    @Update("UPDATE test SET num=#{num}, color=#{color}, mark=#{mark} WHERE id=#{id}")
    void update(Car car);

    @Insert("INSERT INTO test(num, color, mark) VALUES(#{num}, #{color}, #{mark})")
    void create(Car car);

    @Delete("DELETE FROM test WHERE id=#{id}")
    void delete(Car car);

}
