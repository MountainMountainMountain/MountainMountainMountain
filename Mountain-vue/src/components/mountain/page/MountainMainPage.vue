<template>
    <div>
        <Map />
    </div>
</template>

<script setup>
import Map from "../component/Map.vue"
import { useUserStore } from '@/stores/userstore';

const userStore = useUserStore();

</script>

<style scoped></style>