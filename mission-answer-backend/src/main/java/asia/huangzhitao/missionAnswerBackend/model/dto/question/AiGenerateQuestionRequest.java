package asia.huangzhitao.missionAnswerBackend.model.dto.question;

import lombok.Data;

/**
 * @author tao
 * @description AigenerateQuestionRequest
 */
@Data
public class AiGenerateQuestionRequest {

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 题目数
     */
    int questionNumber = 10;

    /**
     * 选项数
     */
    int optionNumber = 2;



    private static final long serialVersionUID = 1L;
}
