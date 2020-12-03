package cn.imooc.demo.springboot.es.entity.es;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "blog", type = "doc",
        useServerConfiguration =  true, createIndex = false)
//useServerConfiguration - 使用线上的index的配置和mapping
//createIndex - 当项目启动，创建index；如果线上已经有blog index，会删除重新创建，所以将其设置成false。
public class EsBlog {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String author;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;
    //将时间格式化，时间无需要analyzer
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyy-MM-dd || epoch_millis")
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyy-MM-dd || epoch_millis")
    private Date updateTime;
}
