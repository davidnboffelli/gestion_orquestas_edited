<template>
    <form>
      <div class="mb-3 col-md-12 col-xs-12">
        <div class="d-flex justify-content-between">
          <label>Motivo</label>
        </div>
        <text-input
          v-model.trim="message.motive"
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
          <small class="is-invalid" v-if="$v.message.name.$invalid">Debe ingresar su nombre</small>
        </div>
        <text-input v-model.trim="message.name" :class="{ 'is-invalid': $v.message.name.$error }" type="text" />
      </div>
      <div class="mb-3 col-md-12 col-xs-12">
        <div class="d-flex justify-content-between">
          <label>Email</label>
          <small class="is-invalid" v-if="$v.message.email.$invalid">Ingrese e-mail válido</small>
        </div>
        <text-input v-model.trim="message.email" :class="{ 'is-invalid': $v.message.email.$error }" />
      </div>
      <div class="mb-3 col-md-12 col-xs-12">
        <div class="d-flex justify-content-between">
          <label>Teléfono</label>
          <small class="is-invalid" v-if="$v.message.phone.$invalid">Debe ingresar su teléfono</small>
        </div>
        <text-input
          v-model.trim="message.phone"
          :class="{ 'is-invalid': $v.message.phone.$error }"
          type="text"
        />
      </div>
      <div class="mb-3 col-md-12 col-xs-12">
        <div class="d-flex justify-content-between">
          <label>Código Postal</label>
          <small class="is-invalid" v-if="$v.message.zipcode.$invalid">Debe ingresar el código postal</small>
        </div>
        <text-input v-model.trim="message.zipcode" :class="{ 'is-invalid': $v.message.zipcode.$error }" />
        <div class="d-flex justify-content-between">
          <label>Visto</label>
          <small class="is-invalid" v-if="$v.message.checked.$invalid">Debe ingresar el código postal</small>
        </div>
        <text-input
          v-model.trim="message.checked"
          :class="{ 'is-invalid': $v.message.checked.$error }"
          type="text"
        />
      </div>
      <div class="mb-3 col-md-6 col-xs-12">
        <button v-on:click="register" type="button" class="btn btn-primary mb-3">
          Guardar
        </button>
      </div>
    </form>
</template>
<script>

import { validationMixin } from 'vuelidate';
import { required, integer, minValue } from 'vuelidate/lib/validators';
import afterCurrentDateValidator from '@/helpers/vuelidate/afterCurrentDateValidator.js';

import TextInput from '../UI/TextInput.vue';
import TextAreaInput from '../UI/TextAreaInput.vue';
import DateInput from '../UI/DateInput.vue';
import CheckInput from '../UI/CheckInput.vue';
import SelectInput from '../UI/SelectInput.vue';

export default {
  props: ['editMessage', 'mode'],
  components: {
    TextInput,
    TextAreaInput,
    DateInput,
    CheckInput,
    SelectInput,
  },
  mixins: [validationMixin],
  watch: {
    editMessage(newVal) {
      this.message = {
        ...newVal
      };
    }
  },
  data() {
    return {
      message: {
        motive: "",
        message: "",
        name: "",
        date: null,
        email: "",
        phone: "",
        zipcode: "",
        checked: false
      }
    };
  },
  validations: {
    message: {
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
    }
  },
  methods: {
    register() {
      console.log(this.message);
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.$emit('register', this.message);
      }
    },
  },
};
</script>
