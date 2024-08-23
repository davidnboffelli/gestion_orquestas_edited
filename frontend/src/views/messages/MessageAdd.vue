<template>
  <div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-secondary float-rigth" v-on:click="goBack">Volver</button>
    </div>
    <message-form :mode="mode" :edit-message="messages" v-on:submit="handleSubmission($event)"></message-form>
  </div>
</template>
<script>
import MessageForm from "@/components/Forms/MessageForm.vue";
import axios from "@/helpers/axiosInterceptor";
export default {
  data() {
    return {
      messages: {
        checked: false,
        date: null,
        email: "",
        id:null,
        message: "",
        motive: "",
        name: "",
        phone: "",
        zipCode: ""
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
        console.log(resp);
        this.messages = resp.data;
        this.mode = "edit";
        console.log(this.messages); // Verifica los valores
      })
      .catch(error => {
        console.error("Error al cargar el mensaje:", error);
      });
    }
  },
  methods: {
    async handleSubmission(messages) {
      await axios.post("/api/messages", messages);
      this.$router.replace({ name: "MessageList" });
    },
    goBack() {
      this.$router.replace({ name: 'MessageList' });
    },
  }
};
</script>
