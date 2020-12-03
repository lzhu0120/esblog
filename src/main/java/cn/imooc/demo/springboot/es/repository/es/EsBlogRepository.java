package cn.imooc.demo.springboot.es.repository.es;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//ElasticsearchRepository<EsBlog, Integer> 中第一个表示需要操作的entity以及entity id 的类型
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Integer> {

}
