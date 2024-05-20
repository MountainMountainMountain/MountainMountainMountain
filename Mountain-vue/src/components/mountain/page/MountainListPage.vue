<template>
    <div>
        <MainLoginNavBar />
        <h1>list</h1>
        <RouterLink :to="{ name: 'MountainDetailPge', params: { mountainSerial: '1' } }">1</RouterLink>

    </div>
</template>

<script setup>
import MainLoginNavBar from '@/components/common/MainLoginNavBar.vue';
</script>

<style scoped></style>