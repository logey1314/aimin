package com.logey.aimin.ai.service.impl;



import com.logey.aimin.ai.service.FileUploadService;
import com.logey.aimin.base.response.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService{

    private final VectorStore vectorStore;

    @Override
    public void upload(MultipartFile file) {
        if(file.isEmpty()){
            throw new BusinessException("不能上传空文件");
        }
        String fileExtension = getFileExtension(file.getOriginalFilename());
        if(!isValidFileType(fileExtension)){
            throw new BusinessException("仅支持PDF/WORD/TXT格式");
        }

        try (InputStream docInputStream = file.getInputStream()) {
            // 2. 读取文件内容并保存到向量存储
            List<Document> splitDocuments = readAndSplitDocument(docInputStream, fileExtension);

            if (splitDocuments.isEmpty()) {
                throw new BusinessException("文件内容解析失败");
            }

//            // 生成关键字元数据
//            KeywordMetadataEnricher enricher = new KeywordMetadataEnricher(chatModel, 2);
//            List<Document> apply = enricher.apply(splitDocuments);

            // 3. 保存到向量存储
            vectorStore.write(splitDocuments);

            log.info("文件已上传并保存到向量存储中，文件名：{}，切分文档数：{}",
                    file.getOriginalFilename(), splitDocuments.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Document> readAndSplitDocument(InputStream inputStream, String fileType) throws IOException {
        // 将InputStream转换为可重复读取的字节数组
        byte[] fileBytes = inputStream.readAllBytes();
        List<Document> docs = getDocuments(fileType, fileBytes);

        if (docs.stream().allMatch(doc -> doc.getText().isBlank())) {
            throw new BusinessException("文档内容解析失败或为空");
        }

        // 3. 优化文本分割参数（增加元数据）
        List<Document> list = new TokenTextSplitter(
                1024,    // 块大小：适合大多数embedding模型
                200,     // 重叠长度：保持上下文连贯性
                10,      // 最小块数：保证基本分割
                2000,    // 最大块数：防止内存溢出
                true     // 启用递归分割
        ).split(docs).stream()
                .peek(doc -> doc.getMetadata().put("file_type", fileType))
                .toList();

        return list;
    }

    public List<Document> getDocuments(String fileType, byte[] fileBytes) {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(fileBytes));

        // 1. 修正文档读取器初始化逻辑
        DocumentReader reader = switch (fileType.toLowerCase()) {
            case "pdf" -> new PagePdfDocumentReader(resource);
            case "doc", "docx" -> new TikaDocumentReader(resource);
            case "txt" -> new TextReader(resource);
            default -> throw new BusinessException("不支持的文档类型: " + fileType);
        };

        return reader.read();
    }

    /**
     * 文件类型获取 简单的实现
     * @param filename
     * @return
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private boolean isValidFileType(String fileType) {
        if (fileType == null || fileType.isEmpty()) {
            return false;
        }

        String type = fileType.toLowerCase();
        return type.equals("pdf") || type.equals("doc") || type.equals("docx") || type.equals("txt");
    }


}



