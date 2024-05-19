<template>
    <div>
        <div class="container">
            <div class="userBar">
                <div class="brand">
                    <router-link class="navbar-brand" to="/">
                        <img style="width: 64px;" src="" alt="이미지">
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.container {
    background-color: rgba(195, 232, 184, 0.33);
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
.brand{
    display: flex;
    align-items: center;
}

/* i {
    font-size: large;
    margin-bottom: 20px;
} */
</style>