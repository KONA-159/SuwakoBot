package org.example.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 个人空间实体
 *
 * @author 13988
 * @date 2023/03/09
 */
@lombok.NoArgsConstructor
@lombok.Data
@Mapper
public class SpaceEntity implements Entity {

    @com.fasterxml.jackson.annotation.JsonProperty("code")
    private Integer code;
    @com.fasterxml.jackson.annotation.JsonProperty("message")
    private String message;
    @com.fasterxml.jackson.annotation.JsonProperty("ttl")
    private Integer ttl;
    @com.fasterxml.jackson.annotation.JsonProperty("data")
    private DataDTO data;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class DataDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("list")
        private DataDTO.ListDTO list;
        @com.fasterxml.jackson.annotation.JsonProperty("page")
        private DataDTO.PageDTO page;
        @com.fasterxml.jackson.annotation.JsonProperty("episodic_button")
        private DataDTO.EpisodicButtonDTO episodicButton;
        @com.fasterxml.jackson.annotation.JsonProperty("is_risk")
        private Boolean isRisk;
        @com.fasterxml.jackson.annotation.JsonProperty("gaia_res_type")
        private Integer gaiaResType;
        @com.fasterxml.jackson.annotation.JsonProperty("gaia_data")
        private Object gaiaData;

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class ListDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("tlist")
            private DataDTO.ListDTO.TlistDTO tlist;
            @com.fasterxml.jackson.annotation.JsonProperty("vlist")
            private List<VlistDTO> vlist;

            @lombok.NoArgsConstructor
            @lombok.Data
            public static class TlistDTO {
                @com.fasterxml.jackson.annotation.JsonProperty("3")
                private DataDTO.ListDTO.TlistDTO._$3DTO $3;
                @com.fasterxml.jackson.annotation.JsonProperty("5")
                private DataDTO.ListDTO.TlistDTO._$5DTO $5;
                @com.fasterxml.jackson.annotation.JsonProperty("36")
                private DataDTO.ListDTO.TlistDTO._$36DTO $36;
                @com.fasterxml.jackson.annotation.JsonProperty("129")
                private DataDTO.ListDTO.TlistDTO._$129DTO $129;
                @com.fasterxml.jackson.annotation.JsonProperty("160")
                private DataDTO.ListDTO.TlistDTO._$160DTO $160;
                @com.fasterxml.jackson.annotation.JsonProperty("177")
                private DataDTO.ListDTO.TlistDTO._$177DTO $177;
                @com.fasterxml.jackson.annotation.JsonProperty("211")
                private DataDTO.ListDTO.TlistDTO._$211DTO $211;
                @com.fasterxml.jackson.annotation.JsonProperty("217")
                private DataDTO.ListDTO.TlistDTO._$217DTO $217;
                @com.fasterxml.jackson.annotation.JsonProperty("234")
                private DataDTO.ListDTO.TlistDTO._$234DTO $234;

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$3DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$5DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$36DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$129DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$160DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$177DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$211DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$217DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class _$234DTO {
                    @com.fasterxml.jackson.annotation.JsonProperty("tid")
                    private Integer tid;
                    @com.fasterxml.jackson.annotation.JsonProperty("count")
                    private Integer count;
                    @com.fasterxml.jackson.annotation.JsonProperty("name")
                    private String name;
                }
            }

            @lombok.NoArgsConstructor
            @lombok.Data
            public static class VlistDTO {
                @com.fasterxml.jackson.annotation.JsonProperty("comment")
                private Integer comment;
                @com.fasterxml.jackson.annotation.JsonProperty("typeid")
                private Integer typeid;
                @com.fasterxml.jackson.annotation.JsonProperty("play")
                private Integer play;
                @com.fasterxml.jackson.annotation.JsonProperty("pic")
                private String pic;
                @com.fasterxml.jackson.annotation.JsonProperty("subtitle")
                private String subtitle;
                @com.fasterxml.jackson.annotation.JsonProperty("description")
                private String description;
                @com.fasterxml.jackson.annotation.JsonProperty("copyright")
                private String copyright;
                @com.fasterxml.jackson.annotation.JsonProperty("title")
                private String title;
                @com.fasterxml.jackson.annotation.JsonProperty("review")
                private Integer review;
                @com.fasterxml.jackson.annotation.JsonProperty("author")
                private String author;
                @com.fasterxml.jackson.annotation.JsonProperty("mid")
                private Integer mid;
                @com.fasterxml.jackson.annotation.JsonProperty("created")
                private Integer created;
                @com.fasterxml.jackson.annotation.JsonProperty("length")
                private String length;
                @com.fasterxml.jackson.annotation.JsonProperty("video_review")
                private Integer videoReview;
                @com.fasterxml.jackson.annotation.JsonProperty("aid")
                private Integer aid;
                @com.fasterxml.jackson.annotation.JsonProperty("bvid")
                private String bvid;
                @com.fasterxml.jackson.annotation.JsonProperty("hide_click")
                private Boolean hideClick;
                @com.fasterxml.jackson.annotation.JsonProperty("is_pay")
                private Integer isPay;
                @com.fasterxml.jackson.annotation.JsonProperty("is_union_video")
                private Integer isUnionVideo;
                @com.fasterxml.jackson.annotation.JsonProperty("is_steins_gate")
                private Integer isSteinsGate;
                @com.fasterxml.jackson.annotation.JsonProperty("is_live_playback")
                private Integer isLivePlayback;
                @com.fasterxml.jackson.annotation.JsonProperty("meta")
                private Object meta;
                @com.fasterxml.jackson.annotation.JsonProperty("is_avoided")
                private Integer isAvoided;
                @com.fasterxml.jackson.annotation.JsonProperty("attribute")
                private Integer attribute;
            }
        }

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class PageDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("pn")
            private Integer pn;
            @com.fasterxml.jackson.annotation.JsonProperty("ps")
            private Integer ps;
            @com.fasterxml.jackson.annotation.JsonProperty("count")
            private Integer count;
        }

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class EpisodicButtonDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("text")
            private String text;
            @com.fasterxml.jackson.annotation.JsonProperty("uri")
            private String uri;
        }
    }
}
