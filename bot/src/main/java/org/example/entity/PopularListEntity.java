package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
@NoArgsConstructor
@Data
public class PopularListEntity implements Entity{
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("list")
        private List<ListDTO> list;
        @JsonProperty("no_more")
        private Boolean noMore;

        @NoArgsConstructor
        @Data
        public static class ListDTO {
            @JsonProperty("aid")
            private Integer aid;
            @JsonProperty("videos")
            private Integer videos;
            @JsonProperty("tid")
            private Integer tid;
            @JsonProperty("tname")
            private String tname;
            @JsonProperty("copyright")
            private Integer copyright;
            @JsonProperty("pic")
            private String pic;
            @JsonProperty("title")
            private String title;
            @JsonProperty("pubdate")
            private Integer pubdate;
            @JsonProperty("ctime")
            private Integer ctime;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("state")
            private Integer state;
            @JsonProperty("duration")
            private Integer duration;
            @JsonProperty("rights")
            private RightsDTO rights;
            @JsonProperty("owner")
            private OwnerDTO owner;
            @JsonProperty("stat")
            private StatDTO stat;
            @JsonProperty("dynamic")
            private String dynamic;
            @JsonProperty("cid")
            private Integer cid;
            @JsonProperty("dimension")
            private DimensionDTO dimension;
            @JsonProperty("short_link")
            private String shortLink;
            @JsonProperty("short_link_v2")
            private String shortLinkV2;
            @JsonProperty("first_frame")
            private String firstFrame;
            @JsonProperty("pub_location")
            private String pubLocation;
            @JsonProperty("bvid")
            private String bvid;
            @JsonProperty("season_type")
            private Integer seasonType;
            @JsonProperty("is_ogv")
            private Boolean isOgv;
            @JsonProperty("ogv_info")
            private Object ogvInfo;
            @JsonProperty("rcmd_reason")
            private RcmdReasonDTO rcmdReason;
            @JsonProperty("mission_id")
            private Integer missionId;
            @JsonProperty("season_id")
            private Integer seasonId;
            @JsonProperty("up_from_v2")
            private Integer upFromV2;

            @NoArgsConstructor
            @Data
            public static class RightsDTO {
                @JsonProperty("bp")
                private Integer bp;
                @JsonProperty("elec")
                private Integer elec;
                @JsonProperty("download")
                private Integer download;
                @JsonProperty("movie")
                private Integer movie;
                @JsonProperty("pay")
                private Integer pay;
                @JsonProperty("hd5")
                private Integer hd5;
                @JsonProperty("no_reprint")
                private Integer noReprint;
                @JsonProperty("autoplay")
                private Integer autoplay;
                @JsonProperty("ugc_pay")
                private Integer ugcPay;
                @JsonProperty("is_cooperation")
                private Integer isCooperation;
                @JsonProperty("ugc_pay_preview")
                private Integer ugcPayPreview;
                @JsonProperty("no_background")
                private Integer noBackground;
                @JsonProperty("arc_pay")
                private Integer arcPay;
                @JsonProperty("pay_free_watch")
                private Integer payFreeWatch;
            }

            @NoArgsConstructor
            @Data
            public static class OwnerDTO {
                @JsonProperty("mid")
                private Integer mid;
                @JsonProperty("name")
                private String name;
                @JsonProperty("face")
                private String face;
            }

            @NoArgsConstructor
            @Data
            public static class StatDTO {
                @JsonProperty("aid")
                private Integer aid;
                @JsonProperty("view")
                private Integer view;
                @JsonProperty("danmaku")
                private Integer danmaku;
                @JsonProperty("reply")
                private Integer reply;
                @JsonProperty("favorite")
                private Integer favorite;
                @JsonProperty("coin")
                private Integer coin;
                @JsonProperty("share")
                private Integer share;
                @JsonProperty("now_rank")
                private Integer nowRank;
                @JsonProperty("his_rank")
                private Integer hisRank;
                @JsonProperty("like")
                private Integer like;
                @JsonProperty("dislike")
                private Integer dislike;
                @JsonProperty("vt")
                private Integer vt;
                @JsonProperty("vv")
                private Integer vv;
            }

            @NoArgsConstructor
            @Data
            public static class DimensionDTO {
                @JsonProperty("width")
                private Integer width;
                @JsonProperty("height")
                private Integer height;
                @JsonProperty("rotate")
                private Integer rotate;
            }

            @NoArgsConstructor
            @Data
            public static class RcmdReasonDTO {
                @JsonProperty("content")
                private String content;
                @JsonProperty("corner_mark")
                private Integer cornerMark;
            }
        }
    }
}
