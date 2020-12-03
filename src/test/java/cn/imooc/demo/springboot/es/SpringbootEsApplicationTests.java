package cn.imooc.demo.springboot.es;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Iterator;

@SpringBootTest
class SpringbootEsApplicationTests {

    @Autowired
    EsBlogRepository esBlogRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testEs() {
        esBlogRepository.findAll().iterator()
                .forEachRemaining(elasticArticle -> System.out.println(elasticArticle.getTitle()));
/*
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false)
                .build();

        try{
            TransportClient transportClient = new PreBuiltTransportClient(settings);
            Client client = transportClient
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (Exception e){
            e.printStackTrace();
        }

        Iterable<EsBlog> all = esBlogRepository.findAll();
        Iterator<EsBlog> iterator = all.iterator();
        if (iterator.hasNext()) {
            EsBlog next = iterator.next();
            System.out.println("------" + next.getTitle());
        }*/
        /*Iterable<EsBlog> all = esBlogRepository.findAll();
        Iterator<EsBlog> esBlogIterator = all.iterator();
        EsBlog next = esBlogIterator.next();
        System.out.println("-------" + next.getTitle());*/
    }
    

}
