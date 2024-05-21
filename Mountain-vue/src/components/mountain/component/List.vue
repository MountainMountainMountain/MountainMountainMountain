<template>
    <div>
        <h1>list</h1>
        <div v-for="ms in mountainStore.mountainList" :key="ms.serial">
            <router-link :to="`/mountain/${ms.serial}/MountainInfo`">
                <span> name: {{ ms.name }}</span>
                <span> , altitude: {{ ms.altitude }}</span>
                <span> , course: {{ ms.course }} </span>
                <span> , point: {{ ms.point }}</span>
            </router-link>
        </div>
    </div>
</template>

<script setup>
import { watch, onMounted } from 'vue';
import { useRoute } from "vue-router";
import { useMountainStore } from '@/stores/mountainstore';

const route = useRoute();
const mountainStore = useMountainStore();

const fetchMountains = () => {
    console.log(route.params.state);
    mountainStore.getMountainState(route.params.state);
};

onMounted(() => {
    fetchMountains();
});

watch(() => route.params.state, (newState) => {
    fetchMountains();
});
</script>

<style scoped>
</style>
