<!-- 홈페이지 로그인 한 navbar -->

<template>
    <div>
        <div class="container">
            <div class="userBar">
                <div class="brand">
                    <router-link class="navbar-brand" to="/">
                        <img style="width: 64px;" src="" alt="이미지">
                    </router-link>
                </div>
                <router-link class="navbar-brand" :to="{ name: 'MyInfo' }">
                    <img style="width: 64px;" src="" alt="프로필이미지">
                </router-link>
                <!-- &nbsp;{{ userName }}님 환영합니다. -->
                &nbsp;<router-link class="nav-link" :to="{name: 'ChatList'}"><i class="fas fa-comments"></i></router-link>
                <!-- &nbsp;<a class="" @click="onClickLogout">로그아웃</a> -->
                &nbsp;<router-link class="nav-link" to="/">로그아웃</router-link>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.container {
    background-color: rgba(47, 48, 47, 0.2);
    display: flex;
    align-items: center;
    height: 60px;
}

.userBar {
    text-align: right;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    padding: 0 4%;

}

.brand {
    display: flex;
    align-items: center;
}

/* i {
    font-size: large;
    margin-bottom: 20px;
} */
</style>