package org.example.mapper;

import org.example.entity.GroupRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 13988
 * @description 针对表【group】的数据库操作Mapper
 * @createDate 2023-03-07 22:46:51
 * @Entity generator.domain.Group
 */
public interface GroupMapper {

    /**
     * 插入订阅记录
     *
     * @param record 记录
     * @return int
     */
    int insert(GroupRecord record);

    /**
     * 删除一个群的所有订阅记录
     *
     * @param groupId 组id
     * @return int
     */
    int deleteByGroup(@Param("groupId") String groupId);

    /**
     * 删除订阅记录
     *
     * @param record 记录
     * @return int
     */
    int deleteRecord(GroupRecord record);

    /**
     * 查询群组订阅的所有uid
     *
     * @param group 集团
     * @return {@link List}<{@link String}>
     */
    List<String> selectByGroupId(@Param("groupId") String group);

    /**
     * 查询所有订阅某uid的群组
     *
     * @param uid uid
     * @return {@link List}<{@link String}>
     */
    List<String> selectByUid(@Param("uid") String uid);

    /**
     * 查询一个群组订阅的所有up主的名字
     *
     * @param GroupId 组id
     * @return {@link List}<{@link String}>
     */
    List<String> selectAuthorByGroup(@Param("groupId") String GroupId);
}




