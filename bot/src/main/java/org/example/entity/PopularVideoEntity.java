package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
@AllArgsConstructor
@Data
public class PopularVideoEntity implements Entity{
    private String author;
    private String pic;
    private String title;
    private String link;
}
