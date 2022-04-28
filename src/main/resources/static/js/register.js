$("button").click(function(){
    $.post("/user",
    {
      userId: 0,
      username: ("#rer_username").val(),
      password: ("#reg_password").val(),
      email: ("#reg_email").val(),
      userRole: 1,
      thumbnailURL: "",
      isEnabled: true
    },
    function(data, status){
      alert("Data: " + data + "\nStatus: " + status);
    });
  });