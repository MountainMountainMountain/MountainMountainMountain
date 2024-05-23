<template>
    <div class="image-container">
        <img src="@/assets/images/mountain2.jpg" alt="Background Image" class="background-image">
        <RouterLink :to="{ name: 'MountainStatePage', params: { state: '전체' } }">
            <img src="@/assets/map/KoreaMap.png" alt="Overlay Image" class="overlay-image">
        </RouterLink>
    </div>
</template>

<script>
export default {
    name: 'OverlayImage',
};
</script>

<style scoped>
.image-container {
    position: relative;
    width: 100vw;
    height: 160vh;
    overflow: hidden;
}

.background-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    opacity: 0.88;
}

.overlay-image {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-width: 90%;
    max-height: 90%;
    pointer-events: all; 
    cursor: pointer;
}
</style>
