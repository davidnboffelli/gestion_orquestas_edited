<template>
  <div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-secondary float-rigth" v-on:click="goBack">Volver</button>
    </div>
    <message-form :mode="mode" :edit-message="aMessage" v-on:submit="handleSubmission($event)"></message-form>
  </div>
</template>
<script>
import MessageForm from "@/components/Forms/MessageForm.vue";
import axios from "@/helpers/axiosInterceptor";
export default {
  data() {
    return {
      aMessage: {
        motive: "",
        message: "",
        name: "",
        email: "",
        phone: "",
        zipcode: ""
      },
      mode: "add"
    };
  },
  components: {
    MessageForm
  },
  mounted() {
    if (this.$route.params.id) {
      const request = axios.get("/api/messages", {
        params: { id: this.$route.params.id }
      });
      request.then(resp => {
        this.aMessage = resp.data;
        this.mode = "edit";
      })
      .catch(error => {
        console.error("Error al cargar el mensaje:", error);
      });
    }
  },
  methods: {
    async handleSubmission(aMessage) {
      await axios.post("/api/messages", aMessage);
      this.$router.replace({ name: "MessageList" });
    },
    goBack() {
      this.$router.replace({ name: 'MessageList' });
    },
  }
};
</script>
