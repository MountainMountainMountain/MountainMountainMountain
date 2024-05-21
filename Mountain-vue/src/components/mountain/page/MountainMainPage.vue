<template>
    <div>
        <h1>main</h1>
        <Map />
    </div>
</template>

<script setup>
import Map from "../component/Map.vue"
import { useUserStore } from '@/stores/userstore';

const userStore = useUserStore();

</script>

<style scoped></style>