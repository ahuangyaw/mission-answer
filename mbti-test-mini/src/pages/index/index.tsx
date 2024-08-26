import { View } from "@tarojs/components";
import { AtButton } from "taro-ui";
import Taro from "@tarojs/taro";
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";

/**
 * 首页
 */
export default () => {
  return (
    <View className="index-page">
      <View className="at-article__h1 title">MBTI性格测试</View>
      <View className="at-article__p submit-title">
        只需2分钟就能测出你的性格特点啊啊啊
      </View>
      <AtButton
        className="enter-btn"
        circle
        type="primary"
        size="normal"
        onClick={() => {
          // 跳转到目的页面，在当前页面打开
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        开始测试
      </AtButton>
      <GlobalFooter></GlobalFooter>
    </View>
  );
};
