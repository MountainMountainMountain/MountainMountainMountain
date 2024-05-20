<template>
    <div id="app">
        <div id="background">
            <div id="jido"></div>
        </div>
    </div>
</template>


<script setup>
</script>

<style scoped>
#app {
    display: flex;
    flex-direction: column;
    height: 100vh;
    /* Viewport height를 100%로 설정 */
}

#background {
    flex: 1;
    /* 남은 공간을 꽉 채우도록 설정 */
    background-image: url('@/assets/images/mountain1.jpg');
    background-size: cover;
    /* 이미지를 요소에 맞게 조정 */
    background-position: center;
    /* 이미지를 가운데에 배치 */
    opacity: 0.4;
    /* 투명도 설정 */
    position: relative;
    z-index: -1;
    /* 내용보다 뒤에 위치하도록 설정 */
}

#jido {
    image-rendering: url('@/assets/map/KoreaMap.png');
    background-size: contain;
    /* 이미지를 요소 내에 맞게 조정 */
    /* background-position: center; */
    /* 이미지를 가운데에 배치 */
    /* background-repeat: no-repeat; */
    /* 이미지 반복을 방지 */
    /* background-color: white; */
    /* 배경색을 흰색으로 설정 */
    /* width: 100%; */
    /* 요소의 너비를 100%로 설정 */
    /* height: 100%; */
    /* 요소의 높이를 100%로 설정 */
    /* position: absolute; */
    /* 부모 요소에 상대적인 절대 위치 */
    /* top: 0; */
    /* 부모 요소의 상단에 위치 */
    /* left: 0; */
    /* 부모 요소의 좌측에 위치 */
    /* display: flex; */
    /* justify-content: center;
    align-items: center; */
 }

router-view {
    position: relative;
    z-index: 1;
    /* 내용이 배경보다 앞에 위치하도록 설정 */
}

/* 예시로 navbar 스타일도 추가 */
#app>MainNavBar {
    position: relative;
    z-index: 2;
    /* navbar가 항상 맨 앞에 위치하도록 설정 */
}
</style>
