let index = {
  init: function () {
    $("#btn-save").on("click", () => {
      this.save();
    });
    $("#btn-delete").on("click", () => {
      this.deleteById();
    });
    $("#btn-update").on("click", () => {
      this.update();
    });
    $("#btn-reply-save").on("click", () => {
      this.replySave();
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
  deleteById: function () {
    let id = $("#id").text();
    $.ajax({
      type: "DELETE",
      url: "/api/delete/" + id,
    }).done(function (response) {
      alert('글 삭제가 완료되었습니다.');
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  update: function () {
    let id = $("#id").val();
    console.log(id);
    let data = {
      title: $("#title").val(),
      content: $("#content").val(),
    };

    $.ajax({
      type: "PUT",
      url: "/api/putProc/" + id,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
    }).done(function (response) {
      alert('글 수정이 완료되었습니다.');
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  replySave: function () {
    let data = {
      content: $("#reply-content").val(),
    };
    let boardId = $("#boardId").val();

    $.ajax({
      type: "POST",
      url: `/api/board/${boardId}/reply`,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8"
    }).done(function (response) {
      alert('댓글 작성이 완료되었습니다.');
      location.href = `/board/${boardId}`;
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
};

index.init();
