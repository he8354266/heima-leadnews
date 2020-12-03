package com.heima.article.service.impl;

import com.heima.article.service.ApArticleSearchService;
import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.article.pojos.ApAssociateWords;
import com.heima.model.article.pojos.ApHotWords;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApAssociateWordsMapper;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApHotWordsMapper;
import com.heima.model.mappers.app.ApUserSearchMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserSearch;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/29:11
 */
@Service
@SuppressWarnings("all")
public class ApArticleSearchServiceImpl implements ApArticleSearchService {
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private ApUserSearchMapper apUserSearchMapper;
    @Autowired
    private ApAssociateWordsMapper apAssociateWordsMapper;
    @Autowired
    private ApHotWordsMapper apHotWordsMapper;

    public ResponseResult getEntryId(UserSearchDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        // 用户和设备不能同时为空
        if (user == null && dto.getEquipmentId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }

        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        System.out.println(apBehaviorEntry);
        // 行为实体找以及注册了，逻辑上这里是必定有值得，除非参数错误
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(apBehaviorEntry.getId());
    }


    @Override
    public ResponseResult findUserSearch(UserSearchDto dto) {
        if (dto.getPageSize() > 50 || dto.getPageSize() < 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //先获取行为实体id
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        //查询搜索记录
        List<ApUserSearch> list = apUserSearchMapper.selectByEntryId((Integer) result.getData(), dto.getPageSize());
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult delUserSearch(UserSearchDto dto) {
        if (dto.getHisList() == null || dto.getHisList().size() <= 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //获取行为实体id
        ResponseResult result = this.getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        List<Integer> ids = dto.getHisList().stream().map(ApUserSearch::getId).collect(Collectors.toList());
        //通过id修改状态
        int count = apUserSearchMapper.delUserSearch((Integer) result.getData(), ids);
        return ResponseResult.okResult(count);
    }

    @Override
    public ResponseResult clearUserSearch(UserSearchDto dto) {
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        int count = apUserSearchMapper.clearUserSearch((Integer) result.getData());
        return ResponseResult.okResult(count);
    }

    @Override
    public ResponseResult hotkeywords(String date) {
        if (StringUtils.isEmpty(date)) {
            date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        }
        List<ApHotWords> apHotWordsList = apHotWordsMapper.queryByHotDate(date);
        return ResponseResult.okResult(apHotWordsList);
    }

    @Override
    public ResponseResult searchAssociate(UserSearchDto dto) {
        if (dto.getPageSize() > 50 || dto.getPageSize() < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        List<ApAssociateWords> apHotWordsList = apAssociateWordsMapper.selectByAssociateWords("%" + dto.getSearchWords() + "%", dto.getPageSize());
        return ResponseResult.okResult(apHotWordsList);
    }

    @Override
    public ResponseResult saveUserSearch(Integer entryId, String searchWords) {
        //去检查当前数据是否存在
        int count = apUserSearchMapper.checkExist(entryId, searchWords);
        if (count > 0) {
            return ResponseResult.okResult(count);
        }
        //不存在，保存
        ApUserSearch apUserSearch = new ApUserSearch();
        apUserSearch.setEntryId(entryId);
        apUserSearch.setKeyword(searchWords);
        apUserSearch.setStatus(1);
        apUserSearch.setCreatedTime(new Date());
        int insert = apUserSearchMapper.insert(apUserSearch);
        return ResponseResult.okResult(insert);
    }

    @Override
    public ResponseResult esArticleSearch(UserSearchDto dto) {
        return null;
    }


}
