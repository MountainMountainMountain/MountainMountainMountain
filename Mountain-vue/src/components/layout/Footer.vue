<template>
    <div class="block-header py-3 border-top">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div id='wrap'>
                        <footer>
                                <h2>site name</h2>
                                <nav>
                                    <a>이용약관</a> |
                                    <a>개인정보처리방침</a> |
                                    <a>사이트맵</a>
                                </nav>
                                <p>
                                    <span>고객센터 041-123-1234 (평일 상담시간 09:00 ~ 17:00)</span><br />
                                    <span>본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.</span><br />
                                    <span>Copyright &copy; SY All Rights Reserved.</span>
                                </p>
                        </footer>
                    </div>
                </div>
                <div class="col text-end">
                    <i class="fa-brands fa-facebook"></i>
                    
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.bi {
    font-size: 1.7rem;
}

.bi:hover {
    color: dodgerblue;
}

.container {
    font-size: 0.9rem;
    color: #808080;
}

footer {
    width: 100%;
    height: 90px;
}

footer a {
    display: inline-block;
    margin: 0 20px 10px 20px;
    color: #808080;
}

footer a:visited {
    color: #808080;
}

footer p {
    margin-top: 0;
    margin-bottom: 0;
}

footer p span {
    display: inline-block;
    margin-left: 20px;
}

#wrap{
    border-top: 1px solid #b4b4b4;
}
</style>