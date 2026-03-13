package com.logey.aimin.ai.Controller;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.DropCollectionParam;
import io.milvus.param.collection.HasCollectionParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final VectorStore vectorStore;
    private final MilvusServiceClient milvusClient;

    @GetMapping("/test1")
    public String test1() {
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));

        // Add the documents to Milvus Vector Store
        vectorStore.add(documents);

        // Retrieve documents similar to a query
        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query("Spring").topK(5).build());
        return results.toString();
    }

    /**
     * 删除 Milvus 集合
     * 用于清理旧的集合结构,以便重新创建
     */
    @GetMapping("/drop-collection")
    public String dropCollection() {
        String collectionName = "vector_store";
        
        try {
            // 检查集合是否存在
            R<Boolean> hasCollectionResp = milvusClient.hasCollection(
                    HasCollectionParam.newBuilder()
                            .withCollectionName(collectionName)
                            .build()
            );

            if (hasCollectionResp.getStatus() != 0) {
                log.error("检查集合失败: {}", hasCollectionResp.getMessage());
                return "检查集合失败: " + hasCollectionResp.getMessage();
            }

            if (!hasCollectionResp.getData()) {
                log.info("集合 {} 不存在", collectionName);
                return "集合 " + collectionName + " 不存在";
            }

            // 删除集合
            R<RpcStatus> dropResp = milvusClient.dropCollection(
                    DropCollectionParam.newBuilder()
                            .withCollectionName(collectionName)
                            .build()
            );

            if (dropResp.getStatus() != 0) {
                log.error("删除集合失败: {}", dropResp.getMessage());
                return "删除集合失败: " + dropResp.getMessage();
            }

            log.info("集合 {} 已成功删除", collectionName);
            return "✓ 集合 " + collectionName + " 已成功删除,请重启应用以重新创建";

        } catch (Exception e) {
            log.error("删除集合异常", e);
            return "删除集合异常: " + e.getMessage();
        }
    }

}
