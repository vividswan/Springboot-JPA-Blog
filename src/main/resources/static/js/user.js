let index = {
  init: function () {
    $("#btn-save").on("click", () => { // function(){}을 ()=>{} this를 바인딩하기 위해서!! function()에서 this -> window의 this가 된다, arrow function에서의 this -> index!
      this.save();
    });
    $("#btn-login").on("click", () => { // function(){}을 ()=>{} this를 바인딩하기 위해서!! function()에서 this -> window의 this가 된다, arrow function에서의 this -> index!
      this.login();
    });
  },
  save: function () {
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val(),
    };

    // ajax 호출 시 비동기 호출이 default
    // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    $.ajax({
      type: "POST",
      url: "/api/user",
      data: JSON.stringify(data), // http body 데이터
      contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
      //dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript object로 변경
    }).done(function (response) {
      alert('회원가입이 완료되었습니다.');
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  login: function () {
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
    };

    $.ajax({
      type: "POST",
      url: "/api/login",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
    }).done(function (response) {
      alert('로그인이 완료되었습니다.');
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
};

index.init();
