<template>
    <div>
        <MainLoginNavBar />
        <h1>list</h1>
        <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink>

        <ul>
            <li v-for="ms in mountainStore.mountainList" :key="ms.serial">
            <span> name: {{ ms.name }}</span>
            <span> , altitude: {{ ms.altitude }}</span>
            <span> , course: {{ ms.course }} </span>
            <span> , point: {{ ms.point }}</span>
            <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
            </li>
        </ul>
    </div>
</template>

<script setup>
import MainLoginNavBar from '@/components/common/MainLoginNavBar.vue';
import { onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';

const mountainStore = useMountainStore()
onMounted(() => {
    mountainStore.getMountainList();
})
</script>

<style scoped></style>