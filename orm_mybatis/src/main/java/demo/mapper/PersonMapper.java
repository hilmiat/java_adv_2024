package demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import demo.entity.Person;

public interface PersonMapper {
    String sql = "select * from person";
    @Select(sql)
    @Results(
        value={
          @Result(property = "id",column = "id"),
          @Result(property = "firstname",column = "firstname"),
          @Result(property = "lastname",column = "lastname")       
        }
    )
    List<Person> getAll();

    @Select("select * from person where id=#{pk}")
    @Results(
        value = {
            @Result(property = "id",column = "id"),
            @Result(property = "firstname",column = "firstname"),
            @Result(property = "lastname",column = "lastname")
        }
    )
    Person get(Long pk);

    @Insert("insert into person(id,firstname,lastname) values(#{id},#{firstname},#{lastname})")
    Integer addData(Person newData);

    @Update("update person set firstname=#{firstname}, lastname=#{lastname} where id=#{id}")
    void update(Person dataUpdate);

    @Delete("delete from person where id=#{pk}")
    void delete(Long pk);
}