<template>

    <v-data-table
        :headers="headers"
        :items="viewpage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ViewpageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "name", value: "name" },
                { text: "description", value: "description" },
                { text: "price", value: "price" },
                { text: "reservationStatus", value: "reservationStatus" },
            ],
            viewpage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/viewpages'))

            temp.data._embedded.viewpages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.viewpage = temp.data._embedded.viewpages;
        },
        methods: {
        }
    }
</script>

