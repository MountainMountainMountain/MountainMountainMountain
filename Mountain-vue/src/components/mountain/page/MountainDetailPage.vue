<template>
    <div>
        <h1>detail</h1>
        <Photo />
        <RouterLink :to="{ name: 'MountainInfo' }">정보</RouterLink> |
        <RouterLink :to="{ name: 'MountainLocation' }">위치</RouterLink> |
        이건 동적으로 바뀔 예정
        <RouterLink :to="{ name: 'Book' }">예약</RouterLink>
        <RouterView />

    </div>
</template>

<script setup>
import Photo from '@/components/mountain/component/Photo.vue';

const token = sessionStorage.getItem('access-token');
</script>

<style scoped></style>