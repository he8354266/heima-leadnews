package com.itheima.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.heima.common.aliyun.AliyunImageScanRequest;
import com.heima.common.aliyun.AliyunTextScanRequest;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleConfig;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.mappers.admin.AdChannelMapper;
import com.heima.model.mappers.app.*;
import com.heima.model.mappers.wemedia.WmNewsMapper;
import com.heima.model.mappers.wemedia.WmUserMapper;
import com.heima.model.media.pojos.WmNews;
import com.heima.model.media.pojos.WmUser;
import com.heima.model.user.pojos.ApUserMessage;
import com.heima.utils.common.Compute;
import com.heima.utils.common.ZipUtils;
import com.itheima.admin.service.ReviewMediaArticleService;
import io.searchbox.client.JestClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/910:13
 */
@Service
@Log4j2
@SuppressWarnings("all")
public class ReviewMediaArticleServiceImpl implements ReviewMediaArticleService {
    @Autowired
    private WmNewsMapper wmNewsMapper = null;
    @Autowired
    private AliyunTextScanRequest aliyunTextScanRequest = null;
    @Autowired
    private AliyunImageScanRequest aliyunImageScanRequest = null;
    @Autowired
    private ApAuthorMapper apAuthorMapper = null;
    @Autowired
    private WmUserMapper wmUserMapper;
    @Autowired
    private AdChannelMapper adChannelMapper;
    @Value("${FILE_SERVER_URL}")
    private String fileServerUrl;
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;
    //    @Autowired
//    private JestClient jestClient;
    @Autowired
    private ApUserMessageMapper apUserMessageMapper;

    @Override
    @Transactional
    public void autoReviewArticleByMedia(int newsId) {
        //根据文章id查询文章信息
        WmNews wmNews = wmNewsMapper.selectNewsDetailByPrimaryKey(newsId);
        if (wmNews != null) {
            //状态为4的时候，直接保存数据
            if (wmNews.getStatus() == 4) {
                this.reviewSuccessSaveAll(wmNews);
                return;
            }
            //审核通过后待发布文章，判断发布时间
            if (wmNews.getStatus() == 8 && wmNews.getPublishTime() != null && wmNews.getPublishTime().getTime() < new Date().getTime()) {
                reviewSuccessSaveAll(wmNews);
                return;
            }
            //审核文章
            if (wmNews.getStatus() == 1) {
                //审核文章的标题与内容的匹配度
                String content = wmNews.getContent();
                String title = wmNews.getTitle();
                double degree = Compute.SimilarDegree(content, title);
                if (degree <= 0) {
                    //文章标题与内容匹配
                    updateWmNews(wmNews, (short) 2, "文章标题与内容不匹配");
                    return;
                }
                //审核文本内容 阿里接口
                List<String> images = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                JSONArray jsonArray = JSON.parseArray(content);
                handlerTextAndImages(images, sb, jsonArray);

                try {
                    String response = aliyunTextScanRequest.textScanRequest(sb.toString());
                    if ("review".equals(response)) { //人工审核
                        updateWmNews(wmNews, (short) 3, "需要人工审核");
                        return;
                    }
                    if ("block".equals(response)) { //审核失败
                        updateWmNews(wmNews, (short) 2, "文本内容审核失败");
                        return;
                    }
                    //审核文章中的图片信息
                    String imagesResponse = aliyunImageScanRequest.imageScanRequest(images);
                    if(imagesResponse!=null){
                        if("review".equals(imagesResponse)){
                            updateWmNews(wmNews, (short) 3,"需要人工审核");
                        }
                        if("block".equals(imagesResponse)){ //审核失败
                            updateWmNews(wmNews, (short) 2,"图片审核失败");
                            return;
                        }
                    }else{
                        updateWmNews(wmNews, (short) 2,"图片审核出现问题");
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (wmNews.getPublishTime() != null) {
                    if (wmNews.getPublishTime().getTime() > new Date().getTime()) {
                        updateWmNews(wmNews, (short) 8, "待发布");
                    } else {
                        reviewSuccessSaveAll(wmNews);
                    }
                } else {
                }
                reviewSuccessSaveAll(wmNews);
            }
        }
    }

    private void handlerTextAndImages(List<String> images, StringBuilder sb, JSONArray jsonArray) {
        for (Object obj : jsonArray) {
            JsonObject jsonObj = (JsonObject) obj;
            String type = String.valueOf(jsonObj.get("type"));
            if ("image".equals(type)) {
                if ("image".equals(type)) {
                    String value = String.valueOf(jsonObj.get("value"));
                    images.add(value);
                }
                if ("text".equals(type)) {
                    sb.append(jsonObj.get("value"));
                }
            }
        }
    }

    /**
     * 保存数据
     * ap_article_config
     * ap_article
     * ap_article_content
     * ap_author
     *
     * @param wmNews
     */
    private void reviewSuccessSaveAll(WmNews wmNews) {
        ApAuthor apAuthor = null;
        if (wmNews.getUserId() != null) {
            WmUser wmUser = wmUserMapper.selectById(wmNews.getUserId());
            if (wmUser != null && wmUser.getName() != null) {
                apAuthor = apAuthorMapper.selectByAuthorName(wmUser.getName());
                if (apAuthor == null || apAuthor.getId() == null) {
                    apAuthor = new ApAuthor();
                    apAuthor.setUserId(wmNews.getUserId());
                    apAuthor.setCreatedTime(new Date());
                    apAuthor.setType(2);
                    apAuthor.setName(wmUser.getName());
                    apAuthor.setWmUserId(wmUser.getId());
                    apAuthorMapper.insert(apAuthor);
                }
            }
        }
        //ap_article
        ApArticle apArticle = new ApArticle();
        if (apAuthor != null) {
            apArticle.setAuthorId(apAuthor.getId().longValue());
            apArticle.setAuthorName(apAuthor.getName());
        }
        apArticle.setCreatedTime(new Date());
        Integer channelId = wmNews.getChannelId();
        if (channelId != null) {
            AdChannel adChannel = adChannelMapper.selectByPrimaryKey(channelId);
            apArticle.setChannelId(channelId);
            apArticle.setChannelName(adChannel.getName());
        }
        apArticle.setLayout(wmNews.getType());
        apArticle.setTitle(wmNews.getTitle());
        String images = wmNews.getImages();
        if (images != null) {
            String[] split = images.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(fileServerUrl);
                sb.append(split[i]);
            }
            apArticle.setImages(sb.toString());
        }
        apArticleMapper.insert(apArticle);
        //ap_article_config,ap_article_content
        ApArticleContent apArticleContent = new ApArticleContent();
        apArticleContent.setArticleId(apArticle.getId());
        apArticleContent.setContent(ZipUtils.gzip(wmNews.getContent()));
        apArticleContentMapper.insert(apArticleContent);

        ApArticleConfig apArticleConfig = new ApArticleConfig();
        apArticleConfig.setArticleId(apArticle.getId());

        apArticleConfig.setIsComment(true);
        apArticleConfig.setIsDelete(false);
        apArticleConfig.setIsDown(false);
        apArticleConfig.setIsForward(true);
        apArticleConfigMapper.insert(apArticleConfig);

        //修改wmNews的状态 为  9
        wmNews.setArticleId(apArticle.getId());
        updateWmNews(wmNews, (short) 9, "审核成功");
        //通知用户审核成功
        ApUserMessage apUserMessage = new ApUserMessage();
        apUserMessage.setUserId(wmNews.getUserId());
        apUserMessage.setCreatedTime(new Date());
        apUserMessage.setIsRead(false);
        apUserMessage.setContent("文章审核成功");
        apUserMessage.setType(108);//文章审核通过
        apUserMessageMapper.insertSelective(apUserMessage);
    }

    /**
     * 修改wmnews
     *
     * @param wmNews
     * @param i
     * @param 待发布
     */
    private void updateWmNews(WmNews wmNews, short status, String message) {
        wmNews.setStatus(status);
        wmNews.setReason(message);
        wmNewsMapper.updateByPrimaryKeySelective(wmNews);
    }
}
