package org.example.mapper;

import org.example.entity.UpLatestVideoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13988
* @description 针对表【up_latest_video_entity】的数据库操作Mapper
* @createDate 2023-03-07 21:42:54
* @Entity entity.UpLatestVideoEntity
*/
public interface UpLatestVideoEntityMapper extends BaseMapper<UpLatestVideoEntity> {
    /**
     * 查询所有up主的最新视频
     *
     * @return {@link List}<{@link UpLatestVideoEntity}>
     */
    public List<UpLatestVideoEntity> findAll();

    /**
     * 插入up主的最新视频
     *
     * @param video 视频
     * @return int
     */
    public int insertVideo(@Param("video") UpLatestVideoEntity video);

    /**
     * 删除up主的最新视频
     *
     * @param uid uid
     * @return int
     */
    public int deleteByUid(@Param("uid")String uid);

    /**
     * 更新最新视频
     *
     * @param newOne 新一个
     * @return int
     */
    public int updateLatestVideo(@Param("newOne") UpLatestVideoEntity newOne);

    /**
     * 根据uid查询up主的最新视频
     *
     * @param uid uid
     * @return {@link UpLatestVideoEntity}
     */
    public UpLatestVideoEntity selectByUid(@Param("uid") String uid);
}




