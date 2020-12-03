package com.heima.login.service.impl;

import com.heima.login.config.IdWorker;
import com.heima.login.service.AppFollowBehaviorService;
import com.heima.login.service.AppUserRelationService;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.behavior.dtos.FollowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApAuthorMapper;
import com.heima.model.mappers.app.ApUserFanMapper;
import com.heima.model.mappers.app.ApUserFollowMapper;
import com.heima.model.mappers.app.ApUserMapper;
import com.heima.model.user.dtos.UserRelationDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserFan;
import com.heima.model.user.pojos.ApUserFollow;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/116:18
 */
@Service
@SuppressWarnings("all")
public class AppUserRelationServiceImpl implements AppUserRelationService {
    @Autowired
    private ApAuthorMapper apAuthorMapper;
    @Autowired
    private ApUserMapper apUserMapper;

    @Autowired
    private ApUserFollowMapper apUserFollowMapper;

    @Autowired
    private ApUserFanMapper apUserFanMapper;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private AppFollowBehaviorService appFollowBehaviorService;

    @Override
    public ResponseResult follow(UserRelationDto dto) {
        if (dto.getOperation() == null || dto.getOperation() < 0 || dto.getOperation() > 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "无效的operation参数");
        }
        //获取到followId
        Integer followId = dto.getUserid();
        if (followId == null && dto.getAuthorid() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "followId或authorId不能同时为空");
        } else if (followId == null) {
            ApAuthor apAuthor = apAuthorMapper.selectById(dto.getAuthorid());
            if (apAuthor != null) {
                followId = apAuthor.getUserId().intValue();
            }
        }
        if (followId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "关注人不存在");
        } else {
            //判断当前用户是否已经关注
            ApUser user = AppThreadLocalUtils.getUser();
            if (user != null) {
                if (dto.getOperation() == 0) {
                    //关注操作
                    //保存ap_user_follow  ap_user_fan  保存关注的行为
                    return followByUserId(user, followId, dto.getArticleid());
                } else {
                    //取消关注
                    //删除ap_user_follow  ap_user_fan
                    return followCancelByUserId(user, followId);
                }

            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            }
        }

    }

    private ResponseResult followCancelByUserId(ApUser user, Integer followId) {
        ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(user.getId(), followId);
        if (apUserFollow == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else {
            ApUserFan apUserFan = apUserFanMapper.selectByFansId(followId, user.getId());
            if (apUserFan != null) {
                apUserFanMapper.deleteByFansId(followId, user.getId());
            }
            int count = apUserFollowMapper.deleteByFollowId(user.getId(), followId);
            return ResponseResult.okResult(count);
        }
    }

    private ResponseResult followByUserId(ApUser user, Integer followId, Integer articleId) {
        //判断用户是否存在
        ApUser apUser = apUserMapper.selectById(followId);
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "关注用户不存在");
        }
        ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(user.getId(), followId);
        if (apUserFollow == null) {
            ApUserFan apUserFan = new ApUserFan();
            if (apUserFan == null) {
                apUserFan.setId(idWorker.nextId());
                apUserFan.setUserId(followId);
                apUserFan.setFansId(user.getId());
                apUserFan.setFansName(user.getName());
                apUserFan.setLevel((short) 0);
                apUserFan.setIsDisplay(true);
                apUserFan.setIsShieldComment(false);
                apUserFan.setIsShieldLetter(false);
                apUserFanMapper.insert(apUserFan);
            }
            apUserFollow = new ApUserFollow();
            apUserFollow.setId(idWorker.nextId());
            apUserFollow.setUserId(user.getId());
            apUserFollow.setFollowId(followId);
            apUserFollow.setFollowName(apUser.getName());
            apUserFollow.setCreatedTime(new Date());
            apUserFollow.setLevel((short) 0);
            apUserFollow.setIsNotice(true);
            //记录用户关注的行为
            FollowBehaviorDto dto = new FollowBehaviorDto();
            dto.setFollowId(followId);
            dto.setArticleId(articleId);

            appFollowBehaviorService.saveFollowBehavior(dto);
            int insert = apUserFollowMapper.insert(apUserFollow);
            return ResponseResult.okResult(insert);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST, "已关注");
        }

    }
}
