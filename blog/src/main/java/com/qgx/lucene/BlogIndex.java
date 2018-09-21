package com.qgx.lucene;

import com.qgx.entity.Blog;
import com.qgx.util.DateUtil;
import com.qgx.util.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: goxcheer
 * @Date:21:55 2018/9/14
 * @email:604721660@qq.com
 * @decription: 博客索引
 */
public class BlogIndex {

    private Directory dir;

    /**
     * 获取IndexWriter实例
     *
     * @return
     * @throws Exception
     */
    private IndexWriter getIndexWriter() throws Exception {
        dir = FSDirectory.open(Paths.get("/usr/lucene"));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 添加博客索引
     *
     * @param blog
     * @throws Exception
     */
    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = this.getIndexWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 删除指定博客的索引
     *
     * @param blogId
     * @throws Exception
     */
    public void deleteIndex(String blogId) throws Exception {
        IndexWriter writer = this.getIndexWriter();
        writer.deleteDocuments(new Term("id", blogId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
        writer.close();
    }

    /**
     * 更新博客索引
     *
     * @param blog
     * @throws Exception
     */
    public void updateIndex(Blog blog) throws Exception {
        IndexWriter writer = this.getIndexWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        writer.close();
    }

    /**
     * 查询博客集合
     *
     * @param keyboard
     * @return
     */
    public List<Blog> searchBlog(String keyboard)  {
        List<Blog> blogList = null;
        try {
            dir = FSDirectory.open(Paths.get("/usr/lucene"));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(reader);
            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            QueryParser parser = new QueryParser("title", analyzer);
            Query query = parser.parse(keyboard);

            QueryParser parser2 = new QueryParser("content", analyzer);
            Query query2 = parser2.parse(keyboard);

            booleanQuery.add(query, BooleanClause.Occur.SHOULD);
            booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

            TopDocs hits = is.search(booleanQuery.build(), 100);
            QueryScorer scorer = new QueryScorer(query);
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
            highlighter.setTextFragmenter(fragmenter);

            blogList = new LinkedList<Blog>();
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = is.doc(scoreDoc.doc);
                Blog blog = new Blog();
                blog.setId(Integer.parseInt(doc.get("id")));
                blog.setReleaseDateStr(doc.get("releaseDate"));
                String title = doc.get("title");
                String content = StringEscapeUtils.escapeHtml(doc.get("content"));
                if (title != null) {
                    TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                    String hTitle = highlighter.getBestFragment(tokenStream, title);
                    if (StringUtil.isEmpty(hTitle)) {
                        blog.setTitle(title);
                    } else {
                        blog.setTitle(hTitle);
                    }
                }

                if (content != null) {
                    TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                    String hContent = highlighter.getBestFragment(tokenStream, content);
                    if (StringUtil.isEmpty(hContent)) {
                        if (content.length() <= 200) {
                            blog.setContent(content);
                        } else {
                            blog.setContent(content.substring(0, 200));
                        }
                    } else {
                        blog.setContent(hContent);
                    }
                }
                blogList.add(blog);
            }
        }catch (Exception e) {
            e.printStackTrace();
            }
        return blogList;

    }
}
