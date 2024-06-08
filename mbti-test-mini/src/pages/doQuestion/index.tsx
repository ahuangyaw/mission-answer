import { View } from "@tarojs/components";
import { useEffect, useState } from "react";
import { AtButton, AtRadio } from "taro-ui";
import Taro from "@tarojs/taro";
import questions from "../../data/question.json";
import "./index.scss";

/**
 * 做题页面
 */
export default () => {
  //当前题目序号,从1开始
  const [current, setCurrent] = useState<number>(1);
  //当前题目
  const [currentQuestion, setCurrentQuestion] = useState(questions[0]);
  // 当前答案
  const [currentAnswer, setCurrentAnswer] = useState<string>();
  // 回答列表
  const [answerList] = useState<string[]>([]);

  //序号变化时,题目和答案跟随变化
  useEffect(() => {
    setCurrentQuestion(questions[current - 1]);
    setCurrentAnswer(answerList[current - 1]);
  }, [answerList, current]);

  const questionOptions = currentQuestion.options.map((option) => {
    return {
      label: `${option.key}.${option.value}`,
      value: option.key,
    };
  });

  return (
    <View className="do-question-page">
      <View className="at-article__h2 title">
        {current}.{currentQuestion.title}
      </View>
      <AtRadio
        options={questionOptions}
        value={currentAnswer}
        onClick={(value) => {
          setCurrentAnswer(value);
          // 更新答案列表
          answerList[current - 1] = value;
        }}
      />
      {current < questions.length && (
        <AtButton
          className="next-btn"
          circle
          type="primary"
          size="normal"
          disabled={!currentAnswer}
          onClick={() => {
            setCurrent(current + 1);
          }}
        >
          下一题
        </AtButton>
      )}
      {current == questions.length && (
        <AtButton
          className="private-btn"
          circle
          type="primary"
          size="normal"
          onClick={() => {
            // 传递答案
            Taro.setStorageSync("answerList", answerList);
            // 跳转到目的页面，在当前页面打开
            Taro.reLaunch({
              url: "/pages/result/index",
            });
          }}
        >
          查看结果
        </AtButton>
      )}
      {current > 1 && (
        <AtButton
          className="private-btn"
          circle
          size="normal"
          onClick={() => {
            setCurrent(current - 1);
          }}
        >
          上一题
        </AtButton>
      )}
      {JSON.stringify(answerList)}
    </View>
  );
};
