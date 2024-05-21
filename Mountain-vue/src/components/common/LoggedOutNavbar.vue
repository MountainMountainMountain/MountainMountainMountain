<template>
    <div class="container">
        <div class="userBar">
            <div class="brand">
                <router-link class="navbar-brand" to="/">
                    <img style="width: 64px;" src="" alt="이미지">
                </router-link>
            </div>
            <div class="links">
                <router-link class="nav-link" :to="{ name: 'Login' }">로그인</router-link>
                <router-link class="nav-link" :to="{ name: 'SignUp' }"><i class="fas fa-user-plus"></i></router-link>
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.container {
    background-color: rgba(74, 75, 73, 0.33);
    display: flex;
    align-items: center;
    height: 60px;
}
.userBar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    padding: 0 4%;
}
.brand {
    display: flex;
    align-items: center;
}
.links {
    display: flex; /* 로그인과 회원가입 링크를 가로로 나열 */
}
.links a {
    text-decoration: none;
    color: black;
    margin-left: 10px; /* 링크 간격을 조정할 수 있습니다 */
}
.links a:hover {
    color: blue;
}
</style>
