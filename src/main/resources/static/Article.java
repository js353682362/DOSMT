package templates;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @（#）:Article.java
 * @description:
 * @author: jiaosen 2018/5/8
 * @version: Version 1.0
 */
public class Article {

    @Field
    private String id;

    private String categoryId;

    private String authorId;

    @Field
    private String name;

    @Field
    private String content;

    @Field
    private Date createDate;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

//    public static void main(String[] args) {
//        Article article = new Article();
//        article.setId("adfasdfasdfasdfas" + Math.random());
//        article.setName("solr测试1");
//        article.setContent("吉林市长春药店");
//        article.setCreateDate(Calendar.getInstance().getTime());
//        System.out.println(SolrUtil.saveSolrResource(article));
//
//        try {
//            QueryResponse response = SolrUtil.query("药店");
//            List<Article> articleList = response.getBeans(Article.class);
//
//            System.out.println(articleList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        }
//    }
}
