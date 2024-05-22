<template>
    <div>
        <h1>commentstar</h1>

        {{ commentStore.MountainStarList }}

        <div v-for="(count, star) in commentStore.MountainStarList" :key="star">
            {{ star }} star: {{ count }}
        </div>

    </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/commentstore';
import { onMounted } from 'vue';
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const commentStore = useCommentStore();

onMounted(() => {
    commentStore.getCommentStar(route.params.mountainSerial);
})

</script>

<style scoped></style>