<template>
    <div>
        <BasicHeader name="로그인" />
        <div class="container my-5">
            <div class="d-flex flex-column align-items-center">
                <form class="row g-1 p-0 p-md-4" style="max-width: 32rem;">
                    <h2 style="color: green; text-align: center" >산산산</h2>
                    <div class="col-12">
                        <div class="mb-2">
                            <label class="form-label" for="userId">아이디</label>
                            <input v-model="userId" id="userId" type="text" class="form-control form-control-lg"
                                placeholder="아이디를 입력하세요">
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="mb-2">
                            <label class="form-label" for="userPassword">비밀번호</label>
                            <input @keyup.enter="validateForm" v-model="userPassword" id="userPassword" type="password"
                                class="form-control form-control-lg" placeholder="비밀번호를 입력하세요">
                        </div>
                    </div>
                    <div class="col-12 text-center mt-4">
                        <button @click="validateForm"
                            class="w-100 btn btn-lg btn-primary lift text-uppercase">로그인</button>
                    </div>
                        <div>
                            <router-link :to="{ name: 'SignUp' }" class="nav-link">회원가입</router-link>
                    </div>
                </form>
            </div>
        </div>
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
a {
    color: blue;
    text-decoration: underline;
    text-align: center;
    font-size: 13px;
}


.signup-link {
    font-size: 0.8em;
    /* Smaller font size for signup link */
}

button {
    background-color: limegreen;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 1em;
}

button:hover {
    background-color: rgb(4, 156, 57);
}
</style>