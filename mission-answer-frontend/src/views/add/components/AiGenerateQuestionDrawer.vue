<template>
  <a-button type="outline" @click="handleClick">AI 生成题目</a-button>
  <a-drawer
    :width="340"
    :visible="visible"
    @ok="handleOk"
    @cancel="handleCancel"
    unmountOnClose
  >
    <template #title>AI 生成题目</template>
    <div>
      <a-form
        :model="form"
        label-align="left"
        auto-label-width
        @submit="handleSubmit"
      >
        <a-form-item label="应用 id">
          {{ appId }}
        </a-form-item>
        <a-form-item field="questionNumber" label="题目数量">
          <a-input-number
            Min="0"
            Max="20"
            v-model="form.questionNumber"
            placeholder="请输入题目数量"
          />
        </a-form-item>
        <a-form-item field="optionNumber" label="选项数量">
          <a-input-number
            Min="0"
            Max="6"
            v-model="form.optionNumber"
            placeholder="请输入选项数量"
          />
        </a-form-item>
        <a-form-item>
          <a-space>
            <a-button
              :loading="submitting"
              type="primary"
              html-type="submit"
              style="width: 120px"
            >
              {{ submitting ? "生成中" : "一键生成" }}
            </a-button>
            <!--            <a-button-->
            <!--              :loading="sseSubmitting"-->
            <!--              type="primary"-->
            <!--              style="width: 120px"-->
            <!--              html-type="submit"-->
            <!--              @click="handleSSESubmit"-->
            <!--            >-->
            <!--              {{ submitting ? "生成中" : "实时生成" }}-->
            <!--            </a-button>-->
          </a-space>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>
</template>

<script setup lang="ts">
import { defineProps, reactive, ref, withDefaults } from "vue";
import API from "@/api";
import { aiGenerateQuestionUsingPost } from "@/api/questionController";
import message from "@arco-design/web-vue/es/message";

interface Props {
  appId: string;
  onSuccess?: (result: API.QuestionContentDTO[]) => void;
  onSSESuccess: (result: API.QuestionContentDTO) => void;
  onSSEStart: (event: any) => void;
  onSSEClose: (event: any) => void;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return "";
  },
});

const form = reactive({
  optionNumber: 2,
  questionNumber: 10,
} as API.AiGenerateQuestionRequest);

const visible = ref(false);
const submitting = ref(false);
const sseSubmitting = ref(false);

const handleClick = () => {
  visible.value = true;
};
const handleOk = () => {
  visible.value = false;
};
const handleCancel = () => {
  visible.value = false;
};

/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId) {
    return;
  }
  submitting.value = true;
  const res = await aiGenerateQuestionUsingPost({
    appId: props.appId as any,
    ...form,
  });
  console.log(res);
  if (res.data.code === 0 && res.data.data.length > 0) {
    if (props.onSuccess) {
      props.onSuccess(res.data.data);
    } else {
      message.success("生成题目成功");
    }
    // 关闭抽屉
    handleCancel();
  } else {
    message.error("操作失败，" + res.data.message);
  }
  submitting.value = false;
};
/**
 * 提交(实时生成)
 */
const handleSSESubmit = async () => {
  if (!props.appId) {
    return;
  }
  sseSubmitting.value = true;
  // 创建 SSE 请求
  const eventSource = new EventSource(
    // todo 手动填写完整的后端地址
    `http://localhost:8101/api/question/ai_generate/sse` +
      `?appId=${props.appId}&optionNumber=${form.optionNumber}&questionNumber=${form.questionNumber}`
  );
  let first = true;
  // 接收消息
  eventSource.onmessage = function (event) {
    if (first) {
      props.onSSESuccess?.(event);
      handleCancel();
      first = !first;
    }
    props.onSSESuccess?.(JSON.parse(event.data));
  };
  // 报错或正常关闭
  eventSource.onerror = function (event) {
    if (event.eventPhase === EventSource.CLOSED) {
      console.log("连接关闭");
      eventSource.close();
      props.onSSEClose?.(event);
    }
    eventSource.close();
  };
  // 建立连接
  // eventSource.onopen = function (event) {
  //   console.log("建立连接");
  //   props.onSSEStart?.(event);
  //   handleCancel();
  // };
  sseSubmitting.value = false;
};
</script>
