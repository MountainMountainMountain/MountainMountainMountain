<template>
    <div class="image-container">
        <img src="@/assets/images/mountain2.jpg" alt="Background Image" class="background-image">
        <RouterLink :to="{ name: 'MountainStatePage', params: { state: '수도권' } }">
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
    width: 100vw; /* Viewport width */
    height: 100vh; /* Viewport height */
    overflow: hidden; /* Hide overflow to prevent scrolling */
}

.background-image {
    width: 100%;
    height: 100%;
    object-fit: cover; /* Cover the entire container without stretching */
    opacity: 0.88;
}

.overlay-image {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-width: 90%; /* Max width to ensure it doesn't overflow */
    max-height: 90%; /* Max height to ensure it doesn't overflow */
    pointer-events: all; /* Make the image clickable */
    cursor: pointer;
}
</style>
