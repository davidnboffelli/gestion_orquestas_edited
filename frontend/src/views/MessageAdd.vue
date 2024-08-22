<template>
  <div id="message-container" class="card">
    <h1>Contacto</h1>
    <div class="col-md-12">
      <div class="row">
        <div class="mb-3 col-md-12 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Motivo</label>
          </div>
          <text-input
            v-model.trim="motive"
            :class="{ 'is-invalid': $v.motive.$error }"
            type="text"
          />
        </div>
        <div class="mb-3 col-md-12 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Mensaje</label>
            <small class="is-invalid" v-if="$v.message.$invalid">Mínimo 20 caracteres</small>
          </div>
          <text-input v-model.trim="message" :class="{ 'is-invalid': $v.message.$error }" />
        </div>
        <div class="mb-3 col-md-6 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Nombre</label>
            <small class="is-invalid" v-if="$v.name.$invalid">Debe ingresar su nombre</small>
          </div>
          <text-input v-model.trim="name" :class="{ 'is-invalid': $v.name.$error }" type="text" />
        </div>
        <div class="mb-3 col-md-12 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Email</label>
            <small class="is-invalid" v-if="$v.email.$invalid">Ingrese e-mail válido</small>
          </div>
          <text-input v-model.trim="email" :class="{ 'is-invalid': $v.email.$error }" />
        </div>
        <div class="mb-3 col-md-12 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Teléfono</label>
            <small class="is-invalid" v-if="$v.phone.$invalid">Debe ingresar su teléfono</small>
          </div>
          <text-input
            v-model.trim="phone"
            :class="{ 'is-invalid': $v.phone.$error }"
            type="text"
          />
        </div>
        <div class="mb-3 col-md-12 col-xs-12">
          <div class="d-flex justify-content-between">
            <label>Código Postal</label>
            <small class="is-invalid" v-if="$v.zipcode.$invalid">Debe ingresar el código postal</small>
          </div>
          <text-input
            v-model.trim="zipcode"
            :class="{ 'is-invalid': $v.zipcode.$error }"
            type="text"
          />
        </div>
        <div class="mb-3 col-md-6 col-xs-12">
          <button v-on:click="register" type="button" class="btn btn-primary mb-3">
            Enviar Mensaje
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "@/helpers/axiosInterceptor";
import { validationMixin } from "vuelidate";
import { required, minLength, email, sameAs } from "vuelidate/lib/validators";
import TextInput from "../components/UI/TextInput.vue";
export default {
  components: { TextInput },
  mixins: [validationMixin],
  data() {
    return {
      motive: "",
      message: "",
      name: "",
      email: "",
      phone: "",
      zipcode: ""
    };
  },
  validations: {
    motive: {
      required
    },
    message: {
      required,
      minLength: minLength(20)
    },
    name: {
      required,
    },
    email: {
      required,
      email
    },
    phone: {
      required,
    },
    zipcode: {
      required
    }
  },
  computed: {
    contacto() {
      return {
        motive: this.motive,
        message: this.message,
        name: this.name,
        email: this.email,
        phone: this.phone,
        zipcode: this.zipcode
      };
    }
  },
  methods: {
    async register() {
      this.$v.$touch();
      if (!this.$v.$invalid) {
        axios
          .post("/api/messages", this.contacto)
          .then(async resp => {
            this.$router.replace({ name: "Dashboard" });
          })
          .catch(error => {
            this.$toastr.e(error.response.data.message);
          });
      }
    }
  }
};
</script>
<style lang="scss" scoped>
#message-container {
  padding: 50px;
}

@media (min-width: 1200px) {
  #message-container {
    width: 700px;
  }
  small.is-invalid {
    color: #dc3545;
  }
}
</style>
