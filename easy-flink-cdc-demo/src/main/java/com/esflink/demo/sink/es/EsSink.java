package com.esflink.demo.sink.es;

import com.alibaba.fastjson.JSON;
import com.esflink.demo.document.CompanyDocument;
import com.esflink.demo.mapper.es.CompanyDocumentMapper;
import com.esflink.starter.annotation.FlinkSink;
import com.esflink.starter.common.data.DataChangeInfo;
import com.esflink.starter.common.data.FlinkDataChangeSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * <p> 同步信息到 es </p>
 *
 * @author zhouhongyin
 * @since 2023/6/9 17:11
 */
@FlinkSink("ourea")
public class EsSink implements FlinkDataChangeSink {

    @Autowired
    private CompanyDocumentMapper companyDocumentMapper;

    @Override
    public void invoke(DataChangeInfo value, Context context) throws Exception {
        String afterData = value.getAfterData();
        CompanyDocument companyDocument = JSON.parseObject(afterData, CompanyDocument.class);
        companyDocument.setKeyword(companyDocument.getName());
        companyDocumentMapper.insert(companyDocument);
    }
}