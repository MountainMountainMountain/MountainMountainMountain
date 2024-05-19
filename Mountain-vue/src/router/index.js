import { createRouter, createWebHistory } from "vue-router";

import MountainView from "@/views/MountainView.vue";
import MountainMainPage from "@/components/mountain/page/MountainMainPage.vue";
import MountainListPage from "@/components/mountain/page/MountainListPage.vue";
import MountainDetailPage from "@/components/mountain/page/MountainDetailPage.vue";

import MypageView from "@/views/MypageView.vue";
<<<<<<< HEAD
import AccountView from "@/views/AccountView.vue";
import Login from "@/components/Account/Login.vue";
import SignUp from "@/components/Account/SignUp.vue";
=======
import MyPage from "@/components/mypage/page/MyPage.vue";
import MyInfo from "@/components/mypage/component/MyInfo.vue";
import MyComplete from "@/components/mypage/component/MyComplete.vue";
import MyFriends from "@/components/mypage/component/MyFriends.vue";

import CommentView from "@/views/CommentView.vue";
import CommentCreatePage from "@/components/comment/page/CommentCreatePage.vue";
import CommentDetailPage from "@/components/comment/page/CommentDetailPage.vue";
import CommentModifyPage from "@/components/comment/page/CommentModifyPage.vue";

import ChatView from "@/views/ChatView.vue";
import DetailChat from "@/components/chat/page/DetailChat.vue";
import JoinChat from "@/components/chat/page/JoinChat.vue";

import AccountView from "@/views/AccountView.vue";
import LoginPage from "@/components/account/page/LoginPage.vue";
import SignUpPage from "@/components/account/page/SignUpPage.vue";
>>>>>>> cbead122813f3b8057178ac6a114aaecfcb91f89

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
<<<<<<< HEAD
      path: "/",
      name: "home",
      component: HomeView,
      // children:[
      //   {
      //     path:
      //   },
      // ]
    },
    {
      path: "/mountain",
      name: "mountain",
=======
      path: '/',
      name: 'Home',
>>>>>>> cbead122813f3b8057178ac6a114aaecfcb91f89
      component: MountainView,
      children: [
        {
          path: '',
          name: 'MountainMainView',
          component: MountainMainPage,
        },
        {
          path: ':state',
          name: 'MountainListPage',
          component: MountainListPage,
        },
        {
          path: 'mountain/:mountainSerial',
          name: 'MountainDetailPge',
          component: MountainDetailPage,
        },
      ],
    },
    {
<<<<<<< HEAD
      path: "/mypage",
      name: "MyPage",
      component: MypageView,
      redirect: "/myPage/A/MyInfo",
      // children: [
      //   {
      //     path: '',
      //     name: 'myInfo',
      //     component: MyInfo,
      //   },
      //   {
      //     path: 'myFriends',
      //     name: 'myFriends',
      //     component: MyFriends,
      //   },
      //   {
      //     path: 'myComplete',
      //     name: 'myComplete',
      //     component: MyComplete,
      //   },
      // { 이거는 모달이나 그 자체에서 바꿀까?
      //   path: 'modifyInfo',
      //   name: 'modifyInfo',
      //   component: ModifyInfo,
      // },
      // ],
    },
    //   {
    //     path: '/comment',
    //     name: 'Comment',
    //     component: CommentView,
    //     children: [
    //       {
    //         path: 'create',
    //         name: 'CommentCreate',
    //         component: CommentCreate,
    //       },
    //       {
    //         path: 'detail',
    //         name: "CommentDetail",
    //         component: CommentDetail,
    //       },
    //       {
    //         path: 'list',
    //         name: 'CommentList',
    //         component: CommentDetail,
    //       },
    //       {
    //         path: 'modify',
    //         name: 'CommentModify',
    //         component: CommentModify,
    //       },

    //     ],
    //   },
    //   {
    //     path: '/chat',
    //     name: 'Chat',
    //     component: ChatView,
    //     children: [
    //       {
    //         path: 'detailChat',
    //         name: 'DetailChat',
    //         component: DetailChat,
    //       },
    //       {
    //         path: 'joinChat',
    //         name: 'JoinChat',
    //         component: JoinChat,
    //       },
    //     ],
    //   },
    // {
    //   path: '/account',
    //   name: 'account',
    //   component: AccountView,
    //   // redirect: '/account/login',
    //   children: [
    //     {
    //       path: 'login',
    //       name: 'Login',
    //       component: Login,
    //     },
    //     {
    //       path: 'signUp',
    //       name: 'SignUp',
    //       component: SignUp,
    //     },
    //   ],
    // },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUp
    },
      // {
      //   path: '/',
      // },
=======
      path: '/mypage',
      name: 'MyPage',
      component: MyPage,
      children: [
        {
          path: '/',
          name: 'myInfo',
          component: MyInfo,
        },
        {
          path: 'myFriends',
          name: 'myFriends',
          component: MyFriends,
        },
        {
          path: 'myComplete',
          name: 'myComplete',
          component: MyComplete,
        },
        // { 이거는 모달이나 그 자체에서 바꿀까?
        //   path: 'modifyInfo',
        //   name: 'modifyInfo',
        //   component: ModifyInfo,
        // },
      ],
    },
    {
      path: '/comment',
      name: 'Comment',
      component: CommentView,
      children: [
        {
          path: 'commentCreate',
          name: 'CommentCreatePage',
          component: CommentCreatePage,
        },
        {
          path: 'commentDetail',
          name: "CommentDetailPage",
          component: CommentDetailPage,
        },
        {
          path: 'commentModify',
          name: 'CommentModifyPage',
          component: CommentModifyPage,
        },
      ],
    },
    {
      path: '/chat',
      name: 'Chat',
      component: ChatView,
      children: [
        {
          path: 'detailChat',
          name: 'DetailChat',
          component: DetailChat,
        },
        {
          path: 'joinChat',
          name: 'JoinChat',
          component: JoinChat,
        },
      ],
    },
    {
      path: '/account',
      name: 'account',
      component: AccountView,
      children: [
        {
          path: 'login',
          name: 'Login',
          component: LoginPage,
        },
        {
          path: 'signUp',
          name: 'SignUp',
          component: SignUpPage,
        },
      ],
    }
>>>>>>> cbead122813f3b8057178ac6a114aaecfcb91f89
  ],
});

export default router;
