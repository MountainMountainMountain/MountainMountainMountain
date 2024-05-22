import { defineStore } from 'pinia';
import axios from 'axios';

// Base64URL 디코딩 함수
function base64URLDecode(str) {
  str = str.replace(/-/g, '+').replace(/_/g, '/');
  while (str.length % 4) {
    str += '=';
  }
  return atob(str);
}

export const useChatStore = defineStore('chat', () => {
  // 상태
  const loginUserName = ref('');
  const loginUserId = ref('');
  const headers = ref({});

  // 사용자 로그인 함수
  const userLogin = (id, password) => {
    return new Promise((resolve, reject) => {
      axios.post(`${REST_USER_API}/user/login`, {
        id,
        password
      })
      .then((res) => {
        sessionStorage.setItem('access-token', res.data["access-token"]);

        const token = res.data['access-token'].split('.');
        headers.value = res.headers;

        try {
          const tokenPayload = JSON.parse(decodeURIComponent(escape(base64URLDecode(token[1]))));
          loginUserId.value = tokenPayload['id'];
          loginUserName.value = tokenPayload['name'];

          console.log(loginUserName.value);
        } catch (e) {
          console.error('Failed to decode token payload:', e);
          reject(e);
          return;
        }

        resolve(); // 성공 시 resolve 호출
      })
      .catch((error) => {
        console.error(error);
        reject(error); // 실패 시 reject 호출
      });
    });
  };

  return {
    loginUserName,
    loginUserId,
    headers,
    userLogin,
  };
});
