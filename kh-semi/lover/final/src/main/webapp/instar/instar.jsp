<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Instagram</title>
    <link rel="stylesheet"  href="../resources/css/bootstrap.css">
    <link href="../resources/css/instar.css" rel="stylesheet" type="text/css" />
    <script src="../resources/js/header_bar.js" defer></script>
    <!-- favicon -->
    
   
    <script src="https://kit.fontawesome.com/7c6041109a.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic+Coding:wght@700&family=Nanum+Pen+Script&family=Noto+Sans+KR&display=swap" rel="stylesheet">
  </head>
  <body>
  <jsp:include page="../header.jsp" />
    <!-- navigation -->
    
    <!-- main -->
    <main>
      <div class="feeds">
        <!-- article -->
        <article>
          <header>
            <div class="profile-of-article">
              <img class="img-profile pic" src="../resources/images/고양이.jpg" alt="dlwlrma님의 프로필 사진">
              <span class="userID main-id point-span">이지은</span>
            </div>
            <img class="icon-react icon-more" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png" alt="more">
          </header>
          <div class="main-image">
            <img src="../resources/images/홍대3.png" alt="dlwlrma님의 피드 사진" class="mainPic">
            

            
          </div>
          <div class="icons-react">
            <div class="icons-left">
              <img id="heartIcon1" img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart1()">
              <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/comment.png" alt="말풍선">
              <img class="icon-react" src="../resources/images/instalogo.png" alt="DM">  
            </div>
            <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/bookmark.png" alt="북마크">
          </div>
          <!-- article text data -->
          <div class="reaction">
            <div class="liked-people">
              <img class="pic" src="../resources/images/dlwhdtjr.jpeg" alt="johnnyjsuh님의 프로필 사진">
              <p><span class="point-span">johnnyjsuh</span>님 <span class="point-span">외 2,412,751명</span>이 좋아합니다</p>
            </div>
            <div class="description">
              <p><span class="point-span userID">kongnamu</span><span class="at-tag">#연남동맛집 #데이트코스 #연남취향</span> 🌱</p>
            </div>
            <div class="comment-section">
              <ul class="comments">
                <li>
                  <span><span class="point-span userID">xeesoxee</span>은호랑 같이 가고싶어요!</span>
                  <img id="heartIcon2" img class="comment-heart" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart2()">
                </li>
                <!-- input 값 여기에 추가 -->
              </ul>
              <div class="time-log">
                <span>32분 전</span>
              </div>
            </div>
          </div>
          <div class="hl"></div>
          <div class="comment">
            <input id="input-comment" class="input-comment" type="text" placeholder="댓글 달기..." >
            <button type="submit" class="submit-comment" disabled>게시</button>
          </div>
        </article> 
        
        
         <article>
          <header>
            <div class="profile-of-article">
              <img class="img-profile pic" src="../resources/images/고양이.jpg" alt="dlwlrma님의 프로필 사진">
              <span class="userID main-id point-span">고병진</span>
            </div>
            <img class="icon-react icon-more" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png" alt="more">
          </header>
          <div class="main-image">
            <img src="../resources/images/홍대1.jpg" alt="고병진님의 피드 사진" class="mainPic">
            

            
          </div>
          <div class="icons-react">
            <div class="icons-left">
              <img id="heartIcon1" img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart1()">
              <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/comment.png" alt="말풍선">
              <img class="icon-react" src="../resources/images/instalogo.png" alt="DM">  
            </div>
            <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/bookmark.png" alt="북마크">
          </div>
          <!-- article text data -->
          <div class="reaction">
            <div class="liked-people">
              <img class="pic" src="../resources/images/dlwhdtjr.jpeg" alt="johnnyjsuh님의 프로필 사진">
              <p><span class="point-span">johnnyjsuh</span>님 <span class="point-span">외 3,000,000명</span>이 좋아합니다</p>
            </div>
            <div class="description">
              <p><span class="point-span userID">고병진</span><span class="at-tag">#홍대대형카페 #카페공명 #데이트코스</span> 🌱</p>
            </div>
            <div class="comment-section">
              <ul class="comments">
                <li>
                  <span><span class="point-span userID">sungjae</span>요기 커피 맛집임</span>
                  <img id="heartIcon2" img class="comment-heart" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart2()">
                </li>
                <!-- input 값 여기에 추가 -->
              </ul>
              <div class="time-log">
                <span>32분 전</span>
              </div>
            </div>
          </div>
          <div class="hl"></div>
          <div class="comment">
            <input id="input-comment" class="input-comment" type="text" placeholder="댓글 달기..." >
            <button type="submit" class="submit-comment" disabled>게시</button>
          </div>
        </article>
        
         <article>
          <header>
            <div class="profile-of-article">
              <img class="img-profile pic" src="../resources/images/고양이.jpg" alt="dlwlrma님의 프로필 사진">
              <span class="userID main-id point-span">최은호</span>
            </div>
            <img class="icon-react icon-more" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/more.png" alt="more">
          </header>
          <div class="main-image">
            <img src="../resources/images/홍대5.jpg" alt="최은호님의 피드 사진" class="mainPic">
            

            
          </div>
          <div class="icons-react">
            <div class="icons-left">
              <img id="heartIcon1" img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart1()">
              <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/comment.png" alt="말풍선">
              <img class="icon-react" src="../resources/images/instalogo.png" alt="DM">  
            </div>
            <img class="icon-react" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/bookmark.png" alt="북마크">
          </div>
          <!-- article text data -->
          <div class="reaction">
            <div class="liked-people">
              <img class="pic" src="../resources/images/dlwhdtjr.jpeg" alt="johnnyjsuh님의 프로필 사진">
              <p><span class="point-span">johnnyjsuh</span>님 <span class="point-span">외 2,412,751명</span>이 좋아합니다</p>
            </div>
            <div class="description">
              <p><span class="point-span userID">최은호</span><span class="at-tag">#4233마음전시회 #데이트코스 #홍대전시회</span> 🌱</p>
            </div>
            <div class="comment-section">
              <ul class="comments">
                <li>
                  <span><span class="point-span userID">postmalone</span>내가 입으면 더 잘어울릴 것 같아</span>
                  <img id="heartIcon2" img class="comment-heart" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/bearu/heart.png" alt="하트" onclick="toggleHeart2()">
                </li>
                <!-- input 값 여기에 추가 -->
              </ul>
              <div class="time-log">
                <span>32분 전</span>
              </div>
            </div>
          </div>
          <div class="hl"></div>
          <div class="comment">
            <input id="input-comment" class="input-comment" type="text" placeholder="댓글 달기..." >
            <button type="submit" class="submit-comment" disabled>게시</button>
          </div>
        </article>
        

      </div>
     
    </main>
    <script src="../resources/js/instar.js"></script>
    <jsp:include page="../footer.jsp" />
  </body>

  </body>
</html>