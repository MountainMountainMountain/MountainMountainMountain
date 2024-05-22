<template>
    <div id="signup-container">
        <form id="signup">
            <fieldset id="signupbox">
                <div class="jointext">
                    <h4 class="jointitle"><span>회원가입</span></h4>
                </div>
                <hr>
                <table>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userId">아이디</label></td>
                        <td class="input-cell">
                            <input v-model="user.id" @change="idCheck" id="userId" type="text" class="mb-4 form-control form-control-lg" />
                            <div v-if="!userIdChk" class="error-message">* 중복된 아이디가 존재합니다</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userPassword">비밀번호</label></td>
                        <td class="input-cell">
                            <input v-model="user.password" id="userPassword" type="password" class="mb-4 form-control form-control-lg" />
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인</label></td>
                        <td class="input-cell">
                            <input v-model="user.repassword" @change="passwordCheck" id="userRePassword" type="password"
                                   :class="['mb-4', 'form-control', 'form-control-lg', { 'is-invalid': !userPwdChk }]" />
                            <div v-if="!userPwdChk" class="error-message">* 비밀번호가 일치하지 않습니다.</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userEmail">이메일</label></td>
                        <td class="input-cell">
                            <input v-model="user.email" @change="emailCheck" id="userEmail" type="email" class="mb-4 form-control form-control-lg" placeholder="you@example.com" />
                            <div v-if="!userEmailChk" class="error-message">* 유효한 이메일 주소를 입력하세요</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userName">이름</label></td>
                        <td class="input-cell">
                            <input v-model="user.name" id="userName" type="text" class="mb-4 form-control form-control-lg" />
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userBirthDate">생일</label></td>
                        <td class="input-cell">
                            <input v-model="user.birthDate" id="userBirthDate" type="date" class="mb-4 form-control form-control-lg" />
                        </td>
                    </tr>
                    <tr>
                        <td class="label-cell"><label class="mb-4 form-label" for="userGender">성별</label></td>
                        <td class="input-cell">
                            <select v-model="user.gender" id="userGender" class="form-control mb-4">
                                <option value="">선택하세요</option>
                                <option value="M">남자</option>
                                <option value="F">여자</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <hr>
                <div class="underbar">
                    <router-link :to="{ name: 'Login' }">
                        <button @click.prevent="createUser" type="button" class="btn btn-primary">회원가입</button>
                    </router-link>
                </div>
            </fieldset>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/userstore';

const user = ref({
    id: '',
    password: '',
    repassword: '',
    name: '',
    email: '',
    birthDate: '',
    gender: ''
});

const userIdChk = ref(true);
const userPwdChk = ref(true);
const userEmailChk = ref(true);

const userStore = useUserStore();
const router = useRouter();

const idCheck = () => {
    userStore.checkId(user.value.id).then(response => {
        userIdChk.value = response.isAvailable;
    }).catch(error => {
        console.error('아이디 체크 중 오류 발생:', error);
        userIdChk.value = false;
    });
};

const passwordCheck = () => {
    userPwdChk.value = user.value.password === user.value.repassword;
};

const emailCheck = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    userEmailChk.value = emailPattern.test(user.value.email);
};

const createUser = () => {
    if (userIdChk.value && userPwdChk.value && userEmailChk.value) {
        userStore.createUser(user.value)
            .then(() => {
                router.push({ name: 'Login' });
            })
            .catch(() => {
                alert('회원가입 중 오류가 발생했습니다.');
            });
    } else {
        alert('모든 필드를 올바르게 입력하세요.');
    }
};
</script>

<style scoped>
#signup-container {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f8f9fa;
}

#signup {
    width: 1000px;
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

#signupbox {
    border: none;
}

table {
    width: 100%;
}

.label-cell {
    width: 25%;
    padding: 8px;
}

.input-cell {
    width: 65%;
    padding: 8px;
}

input,
select {
    width: 100%;
    padding: 8px;
    border-radius: 5px;
    border: 1px solid #ced4da;
}

.error-message {
    color: red;
    font-size: 0.875em;
    margin-top: 5px;
}

.is-invalid {
    border-color: red;
}

button {
    background-color: #44af56;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 1em;
}

button:hover {
    background-color: #1e8b32;
}

.jointext {
    height: 100px;
    margin-top: 30px;
}

span {
    box-shadow: inset 0 -13px 0 #bfffa1;
}

.underbar {
    text-align: center;
}
</style>
