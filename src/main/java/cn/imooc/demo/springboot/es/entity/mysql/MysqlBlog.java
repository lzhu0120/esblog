package cn.imooc.demo.springboot.es.entity.mysql;
import java.util.Date;
import lombok.Data;

import javax.persistence.*;


//maven中的lombok插件 针对实体经常需要大量的setter、getter、 hashCode、 equals、 toString方法，通过此工具可以免去相关处理

/**
 * CREATE TABLE 't_blog' (
 * 'id' int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
 * 'title' varchar(60) DEFAULT NULL COMMENT '博客标题',
 * 'author' varchar(60) DEFAULT NULL COMMENT '博客作者',
 * 'content' mediumtext COMMENT '博客内容',
 * 'create_time' datetime DEFAULT NULL COMMENT '创建时间',
 * 'update_time' datetime DEFAULT NULL COMMENT '更新时间',
 * PRIMARY KEY ('id')
 * ) ENGINE = InnoDB AUTO_INCREMENT=12, DEFAULT CHARSET=utf8mb4;
 *
 * **/

/**
 * @author lz
 * @date 2020/11/12
 */


@Data
@Table (name = "t_blog")
@Entity
public class MysqlBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用数据表定义的增长策略（自增）
    private Integer id;
    private String title;
    private String author;
    // String类型默认映射的是varchar类型，content字段使用到了mediumtext，需要显式指定一下
    @Column(columnDefinition = "mediumtext")
    private String content;
    private Date createTime;
    private Date updateTime;
}
