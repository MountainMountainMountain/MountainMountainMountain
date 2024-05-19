<template>
    <div id="login-container">
        <form id="login">
            <fieldset id="loginbox">

                <ul>
                    <router-link to="/">
                        <h2>산산산</h2>
                    </router-link>
                    <li><label for="user-id" style="margin-right: 38%;">아이디</label></li>
                    <li><input id="user-id" type="text" placeholder="아이디를 입력하세요" autofocus required></li>
                    <li><label for="user-password" style="margin-right: 38%;">비밀번호</label></li>
                    <li><input id="user-password" type="text" placeholder="비밀번호를 입력하세요"></li>
                    <button type="button" class="btn btn-secondary"
                        style="background-color: green; color: aliceblue;  border-radius: 10%;">로그인<router-link
                            to="/"></router-link></button>
                    <div>
                        <router-link :to="{ name: 'SignUp' }" class="nav-link">회원가입</router-link>
                    </div>
                </ul>
            </fieldset>
        </form>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const goHome = () => {
    router.push('/');
};
</script>

<style scoped>
#login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 100px);
    /* 100px for footer height */
}

#login {
    width: 100%;
    max-width: 400px;
}

#loginbox {
    border: none;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

ul {
    list-style-type: none;
    padding: 0;
    text-align: center;
}

a {
    color: #000;
    text-decoration: none;
}

a:hover {
    color: blue;
}

.signup-link {
    font-size: 0.8em;
    /* Smaller font size for signup link */
}

button {
    background-color: greenyellow;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 1em;
}

button:hover {
    background-color: limegreen;
}
</style>