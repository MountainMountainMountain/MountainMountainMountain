<template>
    <div id="particles-js"></div>
    <div class="animated bounceInDown">
        <div class="container">
            <span class="error animated tada" id="msg"></span>
            <form id="login" @submit.prevent="login" name="form1" class="box">
                <router-link to="/">
                    <h4><span><img style="width: 200px;" src="@/assets/images/back1.png" alt="이미지"></span></h4>
                </router-link>
                <br>
                <h5>계정에 로그인하세요.</h5>
                <input type="text" name="email" placeholder="아이디를 입력하세요" autocomplete="off" v-model.trim="id">
                <i class="typcn typcn-eye" id="eye"></i>
                <input type="password" name="password" placeholder="비밀번호를 입력하세요" id="pwd" autocomplete="off" v-model.trim="pw">
                <a @click.prevent="showForgotPasswordModal" class="forgetpass" style="font-size: small;">비밀번호를 잊으셨나요?</a>
                <input type="submit" value="로그인" class="btn1">
            </form>
            <router-link :to="{ name: 'SignUp' }" class="dnthave">계정이 없으신가요? 회원가입</router-link>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '@/stores/userstore'
import Swal from 'sweetalert2'
import axios from 'axios'

const id = ref("")
const pw = ref("")

const store = useUserStore();
const router = useRouter();
const route = useRoute();

const login = function () {
    store.userLogin(id.value, pw.value)
        .then(() => {
            location.replace('/');
        })
        .catch((error) => {
            console.error(error);
            alert('로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.');
        });
}

const showForgotPasswordModal = () => {
    Swal.fire({
        title: '비밀번호 재설정',
        text: "이메일을 입력하시면 비밀번호 재설정 링크를 보내드립니다.",
        input: 'email',
        inputPlaceholder: '이메일을 입력하세요',
        showCancelButton: true,
        confirmButtonText: '보내기',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            // 이메일 전송 로직 구현 못 함
            sendResetPasswordEmail(result.value);
        }
    });
}

const sendResetPasswordEmail = (email) => {
    axios.post('https://your-backend-api.com/reset-password', { email })
        .then(response => {
            Swal.fire('전송 완료!', '비밀번호 재설정 링크가 이메일로 전송되었습니다.', 'success');
        })
        .catch(error => {
            console.error(error);
            Swal.fire('전송 실패', '이메일 전송 중 오류가 발생했습니다. 다시 시도해주세요.', 'error');
        });
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400');

body, html {
    font-family: 'Source Sans Pro', sans-serif;
    background-color: #1d243d;
    padding: 0;
    margin: 0;
}

#particles-js {
    position: absolute;
    width: 100%;
    height: 100%;
}

.container {
    margin: 0;
    top: 300px;
    left: 50%;
    position: absolute;
    text-align: center;
    transform: translateX(-50%);
    background-color: white;
    border-radius: 5px;
    width: 500px;
    height: 600px;
}

.box h4 { 
    margin-top: 74px;
}

.box h5 {
    font-family: 'Source Sans Pro', sans-serif;
    font-size: 13px;
    color: #26997c;
    letter-spacing: 1.5px;
    margin-top: -15px;
    margin-bottom: 70px;
}

.box input[type="text"], .box input[type="password"] {
    display: block;
    margin: 20px auto;
    background: #f3f3f3;
    border: 0;
    border-radius: 5px;
    padding: 14px 10px;
    width: 320px;
    outline: none;
    color: #2c2c2c;
    transition: all .2s ease-out;
}

.box input[type="text"]:focus, .box input[type="password"]:focus {
    border: 2px solid #79A6FE;
}

a {
    color: #5c7fda;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

label input[type="checkbox"] {
    display: none;
}

label span {
    height: 13px;
    width: 13px;
    border: 1px solid #464d64;
    border-radius: 2px;
    display: inline-block;
    position: relative;
    cursor: pointer;
    left: 7.5%;
}

.btn1 {
    border: 0;
    background: #7f5feb;
    color: #dfdeee;
    border-radius: 100px;
    width: 340px;
    height: 49px;
    font-size: 16px;
    position: absolute;
    top: 73%;
    left: 16%;
    transition: 0.3s;
    cursor: pointer;
}

.btn1:hover {
    background: #5d33e6;
}

.rmb {
    position: absolute;
    margin-left: -22%;
    margin-top: 0px;
    color: #232324;
    font-size: 13px;
}

.forgetpass {
    position: relative;
    float: right;
    right: 78px;
}

.dnthave {
    position: absolute;
    top: 82%;
    left: 29%;
}

[type=checkbox]:checked + span:before {
    font-family: FontAwesome;
    font-size: 16px;
    content: "\f00c";
    position: absolute;
    top: -4px;
    color: #896cec;
    left: -1px;
    width: 13px;
}

.typcn {
    position: absolute;
    left: 339px;
    top: 282px;
    color: #3b476b;
    font-size: 22px;
    cursor: pointer;
}

.typcn.active {
    color: #7f60eb;
}

.error {
    background: #ff3333;
    text-align: center;
    width: 337px;
    height: 20px;
    padding: 2px;
    border: 0;
    border-radius: 5px;
    margin: 10px auto 10px;
    position: absolute;
    top: 31%;
    left: 7.2%;
    color: white;
    display: none;
}

.footer {
    position: relative;
    left: 0;
    bottom: 0;
    top: 605px;
    width: 100%;
    color: #78797d;
    font-size: 14px;
    text-align: center;
}

.footer .fa {
    color: #7f5feb;
}
</style>
