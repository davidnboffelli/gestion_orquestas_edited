<template>
    <div data-app v-if="messages" class="col-md-12">
        <div class="d-flex flex-row-reverse">
            <!-- <router-link :to="{ name: 'CrearNoticia' }" class="btn btn-primary"
                ><Plus />Agregar</router-link
            > -->
        </div>
        <v-data-table :headers="tableHeaders" :items="messages">
            <template v-slot:item.motive="{ item }">
                {{ item.motive }}
            </template>
            <template v-slot:item.message="{ item }">
                {{ item.message | preview }}
            </template>
            <template v-slot:item.name="{ item }">
                {{ item.name }}
            </template>
            <template v-slot:item.date="{ item }">
                {{ item.date | formatDate }}
            </template>
            <template v-slot:item.checked="{ item }">
                {{ item.checked ? 'Sí': 'No' }}
            </template>
            <template v-slot:item.actions="{ item }">
                <router-link
                    class="btn btn-primary btn-sm"
                    :to="{ name: 'EditarMessage', params: { id: item.id } }"
                    ><CommentEdit
                /></router-link>
            </template>
        </v-data-table>
    </div>
</template>
<script>
import Plus from "vue-material-design-icons/Plus.vue";
import CommentEdit from "vue-material-design-icons/CommentEdit.vue";
import Delete from "vue-material-design-icons/Delete.vue";

import moment from "moment";
import axios from "@/helpers/axiosInterceptor";
export default {
    components: {
        Plus,
        Delete,
        CommentEdit,
    },
    data() {
        return {
            tableHeaders: [
                {
                    text: "Motivo",
                    align: "start",
                    sortable: true,
                    value: "motive",
                },
                {
                    text: "Mensaje",
                    align: "start",
                    sortable: false,
                    value: "message",
                },
                {
                    text: "Nombre",
                    align: "start",
                    sortable: false,
                    value: "name",
                },
                {
                    text: "Fecha",
                    align: "start",
                    sortable: false,
                    value: "date",
                },
                {
                    text: "Visto",
                    align: "start",
                    sortable: false,
                    value: "checked",
                },
                {
                    text: "Acciones",
                    align: "start",
                    sortable: false,
                    value: "actions",
                },
            ],
            messages: [],
            newDeleting: null,
            deleteDialog: false,
        };
    },
    mounted() {
        axios
            .get("/api/messages")
            .then((resp) => {
                this.messages = resp.data;
            })
            .catch((e) => {});
    },
    filters: {
        preview: function (text) {
            return text.substring(0, 15) + "...";
        },
        formatDate(stringDate, format = "DD/MM/YYYY") {
            return moment(stringDate).format(format);
        },
    },
    methods: {

    },
};
</script>