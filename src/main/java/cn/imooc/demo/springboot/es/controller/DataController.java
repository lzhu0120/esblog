package cn.imooc.demo.springboot.es.controller;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import cn.imooc.demo.springboot.es.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//restcontroller 会将返回值变成Json的字符段返回给前端

@RestController
public class DataController {

    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    EsBlogRepository esBlogRepository;

    @GetMapping("/blogs")
    public Object blog(){
      List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryAll();
      return mysqlBlogs;
    }


    @PostMapping("/search")
    public Object search(@RequestBody Param param){
        HashMap<String, Object> map = new HashMap<>();
        //计算耗时
        StopWatch watch = new StopWatch();
        watch.start();
         String type = param.getType();
         if(type.equalsIgnoreCase("mysql")){
             //mysql search
             List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlogs(param.getKeyword());
             map.put("list", mysqlBlogs);
         } else if(type.equalsIgnoreCase("es")) {
             //es search
             /*POST /blog/_search
             {
                 "query": {
                 "bool": {
                     "should": {
                         "match_phrase": {
                             "title": "springboot"
                         },
                         "match_phrase": {
                             "content": "springboot"
                         }
                     }
                 }
             }
             }*/
             BoolQueryBuilder builder = QueryBuilders.boolQuery();
             builder.should(QueryBuilders.matchPhraseQuery("title", param.getKeyword()));
             builder.should(QueryBuilders.matchPhraseQuery("content", param.getKeyword()));
             String s = builder.toString();
             System.out.println(s);
             Page<EsBlog> search = (Page<EsBlog>) esBlogRepository.search(builder);
             List<EsBlog> content = search.getContent();
             map.put("list", content);

        } else{
             return "please specify a search type, either be 'mysql' or 'es' ";
         }
         watch.stop();
         Long totalTimeMilis = watch.getTotalTimeMillis();
         map.put("duration", totalTimeMilis);
         return map;
    }

    @GetMapping("/blog/{id}")
    public Object blog(@PathVariable Integer id) {
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        return byId.get();

    }

    @Data
    public static class Param {
        //需要知道用户前台传过来的是mysql/es查询方式
        private String type;
        //用户传过来的keyword
        private String keyword;
    }

}
