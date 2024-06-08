import { View } from "@tarojs/components";
import { AtButton } from "taro-ui";
import Taro from "@tarojs/taro";
import { getBestQuestionResult } from "../../utils/bizUtils";
import questions from "../../data/question.json";
import questionsResults from "../../data/question_results.json";
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";

/**
 * 结果页面
 */
export default () => {
  // 获取答案
  const answerList = Taro.getStorageSync("answerList");
  if (!answerList || answerList.length < 1) {
    Taro.showToast({
      title: "答案为空",
      icon: "error",
      duration: 3000,
    });
  }
  const result = getBestQuestionResult(answerList, questions, questionsResults);
  // 获取测试结果
  return (
    <View className="result-page">
      <View className="at-article__h1 title">{result.resultName}</View>
      <View className="at-article__p submit-title">{result.resultDesc}</View>
      <AtButton
        className="enter-btn"
        circle
        type="primary"
        size="normal"
        onClick={() => {
          // 跳转到目的页面，在当前页面打开
          Taro.reLaunch({
            url: "/pages/index/index",
          });
        }}
      >
        返回主页
      </AtButton>
      <GlobalFooter></GlobalFooter>
    </View>
  );
};
