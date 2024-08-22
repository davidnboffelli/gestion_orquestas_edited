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
          <div class="d-flex justify-content-between">
            <label>Visto</label>
            <small class="is-invalid" v-if="$v.checked.$invalid">Debe ingresar el código postal</small>
          </div>
          <text-input
            v-model.trim="checked"
            :class="{ 'is-invalid': $v.checked.$error }"
            type="text"
          />
        </div>
        <div class="mb-3 col-md-6 col-xs-12">
          <button v-on:click="register" type="button" class="btn btn-primary mb-3">
            Guardar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import moment from 'moment';

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
  methods: {
    register() {
      console.log(this.motive);
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.$emit('register', this.motive);
      }
    },
  },
};
</script>
