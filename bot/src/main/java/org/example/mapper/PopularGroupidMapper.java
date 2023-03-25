package org.example.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Shimarin
* @description 针对表【popular_groupid】的数据库操作Mapper
* @createDate 2023-03-11 14:27:29
* @Entity generator.domain.PopularGroupid
*/
public interface PopularGroupidMapper {
    List<String> getGroupIds();

    void insertGroupId(@Param("groupId") String groupId);

    void deleteGroupId(@Param("groupId")String groupId);
}




