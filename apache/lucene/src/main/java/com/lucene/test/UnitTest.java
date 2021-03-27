package com.lucene.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/25 13:54
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class UnitTest {

    private String dataDir = "D:/test";
    private String indexDir = "D:/test/index";

    /**
     * 1.lucene: 存储数据到索引
     */
    @Test
    public void createIndex() {
        try {
            Directory dir = FSDirectory.open(new File(indexDir));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
           /*
           OpenMode.CREATE     -       总是会创建新索引，会覆盖已经存在的索引，会将索引文件夹中的文件删除
           CREATE_OR_APPEND    -       如果索引不存在就创建，如果索引存在就追加内容
           */

            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter writer = new IndexWriter(dir, config);
            File file = new File(dataDir);
            File[] files = file.listFiles();
            for (File f : files) {
                Document doc = new Document();
                doc.add(new StringField("filename", f.getName(), Field.Store.YES));
                doc.add(new TextField("content", "i love java", Field.Store.YES));
                writer.addDocument(doc);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.查询
     * 【描述】
     *
     *
     * 【返回值】
     *
     *
     * 【注意事项】
     *
     *
     */
    @Test
    public void search() {
        try {
            Directory dir = FSDirectory.open(new File(indexDir));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);
            StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            QueryParser qp = new QueryParser(Version.LUCENE_4_9, "content", standardAnalyzer);
            Query query = qp.parse("java");
            TopDocs search = searcher.search(query, 10);
            ScoreDoc[] scoreDocs = search.scoreDocs;
            for (ScoreDoc sc : scoreDocs) {
                int docId = sc.doc;
                Document document = reader.document(docId);
                System.out.println(document.get("filename"));
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
