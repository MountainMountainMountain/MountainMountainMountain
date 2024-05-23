<template>
    <div class="photoContainer">
    <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="@/assets/images/mountain10.png" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="@/assets/images/with.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="@/assets/images/mountain5.jpg" class="d-block w-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.photoContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(228vh / 4); /* 뷰포트 높이의 1/3 */
  padding: 0 300px; /* 양 옆에 20px 여백 */
}

.carousel {
  width: 100%;
  height: 100%;
}

.carousel-inner {
  height: 100%;
}

.carousel-item img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지를 비율에 맞게 잘라서 보여줍니다 */
}
</style>