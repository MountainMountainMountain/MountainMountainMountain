<template>
    <div id="signup-container">
      <form id="signup">
        <fieldset id="signupbox">
          <h2 class="title">산산산 가입을 환영합니다 !</h2>
          <hr>
          <form class="row g-1 p-0 p-md-4 w-100">
            <table class="table table-borderless">
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">아이디</label></td>
                <td v-if="userIdChk" class="px-4">
                  <input v-model="userId" @change="idcheck" id="userId" type="text" class="mb-4 form-control form-control-lg">
                </td>
                <td v-else class="px-4">
                  <input v-model="userId" @change="idcheck" id="userId" type="text" class="mb-4 form-control is-invalid form-control-lg">
                  <label for="userId" class="text-danger" style="color: red;">* 중복된 아이디가 존재합니다</label>
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userPassword">비밀번호</label></td>
                <td class="px-4">
                  <input v-model="userPassword" id="userPassword" type="password" class="mb-4 form-control form-control-lg">
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인</label></td>
                <td v-if="userPwdChk" class="px-4">
                  <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password" class="mb-4 form-control form-control-lg">
                </td>
                <td v-else class="px-4">
                  <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password" class="mb-4 form-control is-invalid form-control-lg">
                  <label for="userRePassword" class="text-danger">* 비밀번호가 일치하지 않습니다.</label>
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userEmail">이메일</label></td>
                <td class="px-4">
                  <input v-model="userEmail" id="userEmail" type="email" class="mb-4 form-control form-control-lg" placeholder="you@example.com">
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userName">이름</label></td>
                <td class="px-4">
                  <input v-model="userName" id="userName" type="text" class="mb-4 form-control form-control-lg">
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userBirthday">생일</label></td>
                <td class="px-4">
                  <div class="d-flex">
                    <select class="form-control me-2">
                      <option value="">년도</option>
                      <option value="1994">1994</option>
                      <option value="1995">1995</option>
                      <option value="1996">1996</option>
                      <option value="1997">1997</option>
                      <option value="1998">1998</option>
                      <option value="1999">1999</option>
                      <option value="2000">2000</option>
                      <option value="2001">2001</option>
                    </select>
                    <select class="form-control">
                      <option value="">월</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>
                    </select>
                  </div>
                </td>
              </tr>
              <tr>
                <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userGender">성별</label></td>
                <td class="px-4">
                  <div class="btn-group" role="group" aria-label="Gender">
                    <input type="radio" class="btn-check" name="gender" id="male" autocomplete="off" checked>
                    <label class="btn btn-outline-primary" for="male">남자</label>
  
                    <input type="radio" class="btn-check" name="gender" id="female" autocomplete="off">
                    <label class="btn btn-outline-primary" for="female">여자</label>
                  </div>
                </td>
              </tr>
            </table>
          </form>
          <hr>
          <div class="join">
            <router-link :to="{ name: 'Login' }">
              <button type="button" class="btn btn-success w-50" style="">회원가입</button>
            </router-link>
          </div>
        </fieldset>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import MainNavBar from '@/components/common/MainNavBar.vue';
  
  const userId = ref('');
  const userPassword = ref('');
  const userRePassword = ref('');
  const userEmail = ref('');
  const userName = ref('');
  const userIdChk = ref(true);
  const userPwdChk = ref(true);
  
  const idcheck = () => {
    // 아이디 중복 확인 로직
  };
  
  const passwordcheck = () => {
    userPwdChk.value = userPassword.value === userRePassword.value;
  };
  </script>
  
  <style scoped>
  #signup-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 10px);
  }
  
  #signup {
    width: 100%;
    max-width: 800px;
    transform: scale(0.7); /* 크기를 80%로 줄이기 */
  }
  
  #signupbox {
    border: none;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  }
  
  .title {
    text-align: center;
    color: green;
    font-size: xx-large;
  }
  
  .table td {
    vertical-align: middle;
  }
  
  .btn-group .btn {
    width: 100px;
  }
  </style>
  