<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>내정보</title>
  <link rel="stylesheet" type="text/css" href="resources/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<script>
var lat;// 위도
var lon;// 경도

if (navigator.geolocation) {
    //위치 정보를 얻기
    navigator.geolocation.getCurrentPosition (function(pos) {
    	lat = pos.coords.latitude;     // 위도
    	lon = pos.coords.longitude; // 경도
    	map = document.getElementById("map");
    	map.href = map.href+"?lat="+lat+"&lon="+lon;
    });
} else {
    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
}
</script>
</head>

<body class="page02">
        <div class="b01 pd16">
            <div class="oH w100">
                <div class="name fL">
                    <b><%//=vo.getUname() %></b>님 안녕하세요!
                </div>
                <div class="fR">
                    <button onclick="location.href='MyModi'">내정보수정</button>
                    <a href="logout.jsp" class="blue">로그아웃</a>
                    <!--평소에는 내정보수정 버튼만 보이고, 수정 중일때만 완료버튼 표시-->
                </div>
            </div>  

            <table>
                <tr>
                    <td>이름</td>
                    <td><%//=vo.getUname() %></td>
                </tr>
                <tr>
                    <td>휴대폰 번호</td>
                    <td><%//=vo.getUid() %></td>
                </tr>
                <tr>
                    <td>학교</td>
                    <td><%//=vo.getSchoolname() %></td>
                </tr>
                <tr>
                    <td>학년반</td>
                    <td><%//=vo.getGradeclass() %></td>
                </tr>
                <tr>
                    <td>노선</td>
                    <td><%//=vo.getRoute() %></td>
                </tr>
                <tr>
                    <td>탑승장소</td>
                    <td><%//=vo.getBoardingplace() %></td>
                </tr>
            </table>
        </div>

        <div class="b02 pd16">
            <a href="bus.jsp" class="mb16 qrbtn">
                <img src="resources/img/img01.png">QR 승하차 체크하기<img src="resources/img/ico_arrow.png" class="fR">
            </a>
            <div class="mb16">
                <img src="resources/img/img02.png">잔여 이용 횟수
                <span class="fR"><b>4</b>회</span>
            </div>
            <a href="map.jsp" class="mb16" id="map">
                <img src="resources/img/img03.png">실시간 버스 위치<img src="resources/img/ico_arrow2.png" class="fR">
            </a>
            <a href="line.jsp">
                <img src="resources/img/img05.png">버스 노선 안내<img src="resources/img/ico_arrow2.png" class="fR">
            </a>
        </div>
        

        <div class="cscenter">
            가람광광여행사 고객센터 <span>054-251-3800</span><br><a href="privacy.jsp">[개인정보처리방침]</a><br>
        </div>

    <div class="FAB">
        <ul class="ulFl">
            <li class="on"><a href="mypage.jsp"><img src="resources/img/ico01_on.png"><p>내정보</p></a></li>
            <li><a href="bus.jsp"><img src="resources/img/ico02.png"><p>버스탑승</p></a></li>
            <li><a href="List"><img src="resources/img/ico03.png"><p>이용내역</p></a></li>
        </ul>
    </div>
</body>
</html>
