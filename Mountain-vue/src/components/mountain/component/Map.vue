<template>
    <div class="image-container">
        <img src="@/assets/images/mountain2.jpg" alt="Background Image" class="background-image">
        <RouterLink :to="{ name: 'MountainListPage', params: { state: 'california' } }">
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
    display: inline-block;
}

.background-image {
    width: 100%;
    height: auto;
}

.overlay-image {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 430px;
    /* 원하는 크기로 설정 */
    height: 790px;
    /* 원하는 크기로 설정 */
    text-align: center;
    pointer-events: all;
    /* 이미지가 클릭 가능하도록 설정 */
    cursor: pointer;
}
</style>