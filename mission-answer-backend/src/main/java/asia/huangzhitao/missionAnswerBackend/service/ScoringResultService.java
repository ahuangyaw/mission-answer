package asia.huangzhitao.missionAnswerBackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.missionAnswerBackend.model.dto.scoringresult.ScoringResultQueryRequest;
import asia.huangzhitao.missionAnswerBackend.model.entity.ScoringResult;
import asia.huangzhitao.missionAnswerBackend.model.vo.ScoringResultVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 评分结果表服务
 *
  */
public interface ScoringResultService extends IService<ScoringResult> {

    /**
     * 校验数据
     *
     * @param scoringResult
     * @param add 对创建的数据进行校验
     */
    void validScoringResult(ScoringResult scoringResult, boolean add);

    /**
     * 获取查询条件
     *
     * @param scoringResultQueryRequest
     * @return
     */
    QueryWrapper<ScoringResult> getQueryWrapper(ScoringResultQueryRequest scoringResultQueryRequest);
    
    /**
     * 获取评分结果表封装
     *
     * @param scoringResult
     * @param request
     * @return
     */
    ScoringResultVO getScoringResultVO(ScoringResult scoringResult, HttpServletRequest request);

    /**
     * 分页获取评分结果表封装
     *
     * @param scoringResultPage
     * @param request
     * @return
     */
    Page<ScoringResultVO> getScoringResultVOPage(Page<ScoringResult> scoringResultPage, HttpServletRequest request);
}
