<!-- 메인화면 외 NAVBAR -->
<template>
    <div>
        <div class="container">
            <div class="userBar">
                <div class="brand">
                    <router-link class="navbar-brand" to="/">
                        <img style="width: 64px;" src="" alt="이미지">
                    </router-link>
                </div>
                <router-link class="nav-link" :to="{ name: 'Login' }">로그인</router-link>
                &nbsp;<router-link class="nav-link" :to="{ name: 'SignUp' }"> <i class=" fas
                    fa-user-plus"></i></router-link>

            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.container {
    background-color: rgba(212, 223, 196, 0.33);
    display: flex;
    align-items: center;
    height: 50px;
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

a {
    text-decoration: none;
    color: black;
}

a:hover {
    color: blue;
}
</style>