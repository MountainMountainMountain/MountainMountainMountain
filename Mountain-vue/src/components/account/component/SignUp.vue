<template>

    <div id="signup-container">
        <form id="signup">
            <fieldset id="signupbox">

                <h2 style="text-align: center; color: green; font-size: xx-large">산산산 가입을 환영합니다 !</h2>
                <hr>
                <!-- <ul>
                    <li><label for="user-id" style="margin-right: 38%;">아이디</label>
                    <input id="user-id" type="text" placeholder="아이디를 입력하세요" autofocus required></li>
                    <li><label for="user-password" style="margin-right: 38%;">비밀번호</label>
                    <input id="user-password" type="text" placeholder="비밀번호를 입력하세요"></li>
                    <button type="button" class="btn btn-secondary"
                        style="background-color: goldenrod">남</button>
                    <button type="button" class="btn btn-secondary"
                        style="background-color: greenyellow">여</button>
                    
                </ul> -->
                <table>

                    <tr>
                        <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">아이디</label>
                        </td>
                        <td v-if="userIdChk" class="px-4">
                            <input v-model="userId" @change="idcheck" id="userId" type="text"
                                class="mb-4 form-control form-control-lg">
                        </td>
                        <td v-else class="px-4 form-floating">
                            <label for="userId" class="ps-5 text-danger" style="color: red;">* 중복된 아이디가 존재합니다</label>
                            <br>
                            <input v-model="userId" @change="idcheck" id="userId" type="text"
                                class="mb-4 form-control is-invalid form-control-lg">
                            <button>확인</button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userName">비밀번호 </label></td>
                        <td>
                            <span class="border border-primary"><input type="text" name="userPassword" id="userPassword"></span>
                            
                        </td>
                    </tr>
                    <!-- <tr>
                        <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userPassword">비밀번호
                                <span class="text-danger">*</span></label></td>
                        <td v-if="userPwdChk" class="px-4">
                            <input v-model="userPassword" id="userPassword" type="password"
                                class="mb-4 form-control form-control-lg">
                        </td>
                        <td v-else class="px-4 form-floating">
                            <input v-model="userPassword" id="userPassword" type="password"
                                class="mb-4 form-control is-invalid form-control-lg">
                            <label for="userPassword" class="ps-5 text-danger">비밀번호가 일치하지 않습니다. </label>
                        </td>
                    </tr> -->
                    <tr>
                        <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호
                                확인 <span class="text-danger"></span></label>
                        </td>
                        <td v-if="userPwdChk" class="px-4">
                            <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password"
                                class="mb-4 form-control form-control-lg">
                        </td>
                        <td v-else class="px-4 form-floating">
                            <label for="userRePassword" class="ps-5 text-danger" style="color: red">* 비밀번호가 일치하지 않습니다. </label>
                            <br>
                            <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password"
                                class="mb-4 form-control is-invalid form-control-lg">
                        </td>
                    </tr>
                    <!-- <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userName">비밀번호 확인 </label></td>
                        <td>
                            <input type="text" name="userPassword" id="userPassword" placeholder="비밀번호 재입력">
                        </td>
                    </tr> -->
                    <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userEmail">이메일 </label></td>
                        <td>
                            <input type="text" name="userEmail" id="userEmail" placeholder="you@example.com">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userName">이름 </label></td>
                        <td class="px-4"><input v-model="userName" id="userName" type="text"
                                class="mb-4 form-control form-control-lg"></td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userEmail">생일</label></td>
                        <td>
                            <li>
                                <select name="" id="" class="form-control">
                                    <option value="">년도</option>
                                    <option value="">1994</option>
                                    <option value="">1995</option>
                                    <option value="">1996</option>
                                    <option value="">1997</option>
                                    <option value="">1998</option>
                                    <option value="">1999</option>
                                    <option value="">2000</option>
                                    <option value="">2001</option>
                                </select>
                                -
                                <select name="" id="" class="form-control">
                                    <option value="">월</option>
                                    <option value="">1</option>
                                    <option value="">2</option>
                                    <option value="">3</option>
                                    <option value="">4</option>
                                    <option value="">5</option>
                                    <option value="">6</option>
                                    <option value="">7</option>
                                    <option value="">8</option>
                                    <option value="">9</option>
                                    <option value="">10</option>
                                    <option value="">11</option>
                                    <option value="">12</option>
                                </select>

                                <!-- <select name="" id="" class="form-control">
                                    <option value="">일</option>
                                    <option value="">1</option>
                                    <option value="">2</option>
                                    <option value="">3</option>
                                    <option value="">4</option>
                                    <option value="">5</option>
                                    <option value="">6</option>
                                    <option value="">7</option>
                                    <option value="">8</option>
                                    <option value="">9</option>
                                    <option value="">10</option>
                                    <option value="">11</option>
                                    <option value="">12</option>
                                    <option value="">13</option>
                                    <option value="">14</option>
                                    <option value="">15</option>
                                    <option value="">16</option>
                                    <option value="">17</option>
                                    <option value="">18</option>
                                    <option value="">19</option>
                                    <option value="">20</option>
                                    <option value="">21</option>
                                    <option value="">22</option>
                                    <option value="">23</option>
                                    <option value="">24</option>
                                </select> -->
                            </li>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label"
                                for="userGender">성별</label></td>
                        <td>
                            <button type="button" class="btn btn-primary" style="width: 30%;">남자</button>
                            &nbsp;<button type="button" class="btn btn-primary" style="width: 30%;">여자</button>
                        </td>
                    </tr>
                </table>
                <hr>
                <div class="underbar">
                    <router-link :to="{name:'Login'}">
                        <button type="button" class="btn btn-primary"
                            style="width: 30%; height: 30px; background-color: greenyellow; border: none; ">회원가입</button>
                    </router-link>
                    <!-- <router-link to="/login">
                        <button type="button" class="btn btn-primary">취소</button>
                    </router-link> -->
                </div>
            </fieldset>
        </form>
    </div>
</template>

<script setup>

import MainNavBar from '@/components/common/MainNavBar.vue';
</script>

<style scoped>
#signup-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 10px);
    /* 100px for footer height */
}

#signup {
    width: 100%;
    max-width: 800px;
}

#signupbox {
    border: none;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

li {
    list-style-type: none;
    padding: 0;
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

/* button {
    background-color: greenyellow;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 1em;
} */

button:hover {
    background-color: rgb(151, 181, 248);
}

.underbar {
    text-align: center;
}

table {
    text-decoration: center;
}
</style>