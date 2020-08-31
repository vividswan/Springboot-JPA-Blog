let index = {
  init: function () {
    $("#btn-save").on("click", () => {
      this.save();
    });
  },
  save: function () {
    let data = {
      title: $("#title").val(),
      content: $("#content").val(),
    };

    $.ajax({
      type: "POST",
      url: "/api/saveProc",
      data: JSON.stringify(data), 
      contentType: "application/json; charset=utf-8",
      //dataType: "json"
    }).done(function (response) {
      alert('글 작성이 완료되었습니다.');
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
};

index.init();
