<template>
  <div class="chat-container">
    <!-- 聊天消息区域 -->
    <div class="chat-history">
      <div class="message user-message">Can you tell me how to calculate the perimeter of a rectangle?</div>
      <div class="message bot-message">Okay, I'm searching for the answer for you...</div>
    </div>

    <!-- 输入区域 -->
    <div class="chat-input">
      <input type="text" placeholder="Type a message..." v-model="message" @keyup.enter="sendMessage" />
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: "",
    };
  },
  methods: {
    sendMessage() {
      if (this.message.trim()) {
        // 这里可以实现将消息发送到后台
        this.message = "";
      }
    }
  }
};
</script>

<style scoped>
/* 整个容器样式 */
.chat-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100vh;
  background-color: #f0f4f8;
  padding: 20px;
}

/* 聊天历史记录样式 */
.chat-history {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 聊天消息样式 */
.message {
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 10px;
  max-width: 60%;
}

.user-message {
  background-color: #007bff;
  color: white;
  align-self: flex-end;
}

.bot-message {
  background-color: #e5e5ea;
  color: black;
  align-self: flex-start;
}

/* 输入区域样式 */
.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #ffffff;
  border-radius: 30px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin-top: 10px;
}

.chat-input input {
  flex: 1;
  padding: 10px;
  border: none;
  border-radius: 20px;
  margin-right: 10px;
  background-color: #f1f3f4;
}

.chat-input button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
}

.chat-input button:hover {
  background-color: #0056b3;
}
</style>
