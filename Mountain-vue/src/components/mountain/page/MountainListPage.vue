<template>
    <div>
        <h1>list</h1>
        <Search />

        <ul>
            <li v-for="ms in mountainStore.mountainList" :key="ms.serial">
                <router-link :to="`/mountain/${ms.serial}/MountainInfo`">
                    <span> name: {{ ms.name }}</span>
                    <span> , altitude: {{ ms.altitude }}</span>
                    <span> , course: {{ ms.course }} </span>
                    <span> , point: {{ ms.point }}</span>
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script setup>
import Search from "../component/Search.vue"
import { onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';

const mountainStore = useMountainStore()
onMounted(() => {
    mountainStore.getMountainList();
})
</script>

<style scoped></style>